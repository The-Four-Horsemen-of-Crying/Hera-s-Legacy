
package com.heraslegacy.main;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Fuente;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.Level;
import com.heraslegacy.level.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    
    public Level level;
    public static boolean switched = false; 
    
    private Thread thread;
    private KeyBoard key;
    private Mouse mouse;
    public JFrame frame; 
    private static final long serialVersionUID = 1L;
    public static final int width = 300;
    public static final int height = width / 16 * 9;
    public static final int scale = 3;
    private boolean running = true;
    private final double TIME_BEFORE_UPDATE = 1000000000.0 / 120.0;
    public static boolean activarMecanica = true;
    public static int gameState = 1; 
    //Para dibujar texto
    private Texto text[];
    private int x=0, y=0;
    private Color c=Color.white;
    private Font f;
    //para dibujar texto
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    public static Screen screen;
    Sound theme;

    public Game(){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        frame = new JFrame();
        screen = new Screen(width, height);
        Sound.init();
        Fuente.init();
        text= new Texto[1000];
        f=Fuente.spaceFont;
        theme=new Sound(Sound.de);
        //theme.loop(); //MUSICA PARA EL JUEGO
        key = new KeyBoard();
        level = new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
        level.configPlayer();
        
        addKeyListener(key);
        mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        thread = new Thread(this, "GameHerasLegacy");
        thread.start();
    }
    
    public synchronized void stop() {
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        
        long timer = System.currentTimeMillis();        
        long startTime = System.nanoTime();
        double elapsedTime = 0;
        int frames = 0;
        int updates = 0;
        
        while(running){
            
            elapsedTime += (System.nanoTime() - startTime) / TIME_BEFORE_UPDATE;            
            startTime = System.nanoTime();
            while(elapsedTime >= 1){
                update();
                updates++;
                elapsedTime--;
            }
            
            render();
            frames++;
            
            //Refresh image once per second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("Hera's Legacy | " + updates + " ups | " + frames + " fps");
                updates = 0;
                frames = 0;
            }		
        }
        stop();
    }
   

    public void update() {
        key.uptade();
        level.getPlayer().update();  
        level.update();
    }

    public void render() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        int xScroll = level.getPlayer().getX() - screen.width/2;
        int yScroll = level.getPlayer().getY() - screen.height/2;

        
        level.render(xScroll, yScroll, screen);    
        level.getPlayer().render(screen);
        
        if(activarMecanica){
            level.mecanica();
            
        }        
        if(KeyBoard.restart){
            level.restart();
        }
        if(level.cambio()){
            level = level.levelCambio();
            level.configPlayer();
        }
       
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        if( g != null){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.setFont(Fuente.spaceFont);
            g.setColor(level.getColor());
            for (int i = 0; i < level.getText().length; i++) {
                if(level.getText()[i].isVisible()) {
                    g.drawString(level.getText()[i].getText(), level.getText()[i].getPosx(), level.getText()[i].getPosy());   
                }
            }
            g.dispose();
            bs.show();
        }
    }
    
    public void setLevel(Level newLevel){
        this.level=newLevel;
        this.level.configPlayer();
    }
}
