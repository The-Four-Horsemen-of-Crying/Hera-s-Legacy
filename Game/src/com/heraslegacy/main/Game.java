
package com.heraslegacy.main;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.Level;
import com.heraslegacy.level.*;
import com.heraslegacy.level.LibraryLevel;
import com.heraslegacy.level.SpaceLevel;
import com.heraslegacy.level.TileCoordenada;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    
    public Level level;
    private Thread thread;
    private KeyBoard key;
    private Mouse mouse;
    public JFrame frame; 
    private static final long serialVersionUID = 1L;
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    private boolean running = true;
    private final double TIME_BEFORE_UPDATE = 1000000000.0 / 120.0;
    public static boolean activarMecanica = false;    
    private TileCoordenada spawnplayer = new TileCoordenada(width / 2, height / 2);
    private int[] spawnpj = spawnplayer.getXY();   
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    public static Screen screen;
    Sound theme;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        frame = new JFrame();
        screen = new Screen(width, height);
        Sound.init();
        theme=new Sound(Sound.de);
        //theme.loop(); //MUSICA PARA EL JUEGO
        key = new KeyBoard();
        //level = new Level("/levels/lobby/lobby.png","/levels/lobby/lobby.png",new Lobby());
        //level = new Level("/levels/level01/level1.png","/levels/level01/collisionlevel1.png",new MathLevel());
        //level = new Level("/levels/level02/level2.png","/levels/level02/collisionlevel2.png",new SpaceLevel());
        level = new Level("/levels/level03/nivel3.png","/levels/level03/nivel3COLLITION.png",new LibraryLevel());
        spawnpj[0]=25;spawnpj[1]=400;// Necesario para el nivel 3
        level.configPlayer(spawnpj[0],spawnpj[1],key, Sprite.player_up, Sprite.player_down, Sprite.player_rigth, Sprite.player_left,false);
        
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
        //level02.render(xScroll, yScroll, screen);     
        level.getPlayer().render(screen);
        
        //if bool colision = true then renderizar datos en Level01 y pasarlos a screen
        
        if(activarMecanica){
            level.mecanica();
            
        }
        
        if(key.restart){
            level.getPlayer().setX(spawnpj[0]);
            level.getPlayer().setY(spawnpj[1]);
            level.restart();
        }
        
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        if( g != null){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.dispose();
            bs.show();
        }
    }
}
