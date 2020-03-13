/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;



public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID=1L;
    public static int width = 300;
    public static int height = width/16*9;
    public static int scale = 3;
    public static int i=0;
    int x;
    int y;
    
    
    
    private Thread thread;
    private boolean running=true;
    JFrame frame;

    
        public Game(int x, int y){
            Dimension size = new Dimension(width * scale, height*scale);
            setPreferredSize(size);
            frame= new JFrame();
            this.x=x;
            this.y=y;
        }
    
    //FIX IT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! please. :P
        public synchronized void start(){
            thread = new Thread(this, "1");
            thread.start();
        }
        
        public synchronized void stop(){
            try{
                thread.join();
            
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
        public void run(){
            while(running){
              render();
            }       
        }
        
        public void render(){
            Random colornum = new Random();
            BufferStrategy bs = getBufferStrategy();
            if(bs==null){
                createBufferStrategy(3);
                return;
                
            }
             
            Graphics g = bs.getDrawGraphics();
            g.setColor(Color.BLACK);
            
            //Aquí
            g.fillRect(0, 0, getWidth(), getHeight());//ventana de fondo 
          
            
            
            g.setColor(new Color(colornum.nextInt(100)+1,colornum.nextInt(100)+1,colornum.nextInt(100)+1));
            
            g.fillRect(x, y, 200, 200); //Rectangulo de colores
            int xcoord[]= {50,100,180,0+i,50} ;//Vector de puntos en x
            int ycoord[]={50,130,150,0+i,50};// Vector de puntos en y
            g.setColor(Color.WHITE);
              g.fillPolygon(xcoord,ycoord, 5/*Numero de puntos*/);
            i+=3;     
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Aquí
        
            //clear the frame
            g.dispose();
            //display the buffer
            bs.show();
            
        }
}
