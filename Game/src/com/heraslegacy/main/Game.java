
package com.heraslegacy.main;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Screen;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.Level;
import com.heraslegacy.level.Level01;
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

    private Level level;
    private Thread thread;
    private Player player;
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
    

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        frame = new JFrame();
        screen = new Screen(width, height);
        key = new KeyBoard();
        level = new Level("/levels/level01/level1.png", "/levels/level01/collisionlevel1.png", new Level01());
        player = new Player(spawnpj[0],spawnpj[1],key);
        player.init(level);
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
        player.update();
    }

    public void render() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        int xScroll = player.getX() - screen.width/2;
        int yScroll = player.getY() - screen.height/2;

        
        level.render(xScroll, yScroll, screen);        
        player.render(screen);
        
        //if bool colision = true then renderizar datos en Level01 y pasarlos a screen
        
        if(activarMecanica){
            level.mecanica();
        }        
        
        if(key.restart){
            player.setX(spawnpj[0]);
            player.setY(spawnpj[1]);
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
