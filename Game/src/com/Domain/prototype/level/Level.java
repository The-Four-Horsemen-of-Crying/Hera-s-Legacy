/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.level;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.level.tile.Tile;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Level {
    
    public static Level level01 = new Level01("/levels/level1.png");
    //protected Tile[] tiles;
    protected int width;
    protected int height;
    protected int[] tilesR;
    protected int[] tiles; //contendrá todos los pixeles del nivel.
    private Random random = new Random();
    private JFrame tarea = new JFrame();

    
    
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesR = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
        Dimension dimension2 = new Dimension(233, 3434);
        tarea.setSize(dimension2);
    }

    //Entidades: ia, npcs, etc

    public void uptade() {
    }

    //position x and y; Screen para manejar el dibujo (renderizado) en el monitor
    //31
    public void render(int xScroll, int yScroll, Screen screen) {//x0=parteinicial de la ventana&&xf=partefinal de la ventana en x
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4);//same that xScroll/16
        int x1 = (xScroll + screen.width+16) >> 4;//Se adiciona 16 para contar con una tile en el lado derecho (width) de la ventana
        int y0 = (yScroll >> 4);
        int y1 = (yScroll + screen.height+16) >> 4; //Se adiciona 16 para contar con una tile en el lado bajo (height) de la ventana
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                
                getTile(x, y).render(x, y, screen);
                
                
                
//                if(x+y*16<0||x+y*16>=256) {
//                    Tile.pikes.render(x, y, screen);
//                    continue;
//                }
//                tiles[x+y*16].render(x, y, screen);
                
            }
        }
    }
    //35
    public Tile getTile(int x, int y) {
        if(x<0 || y<0||x>=width||y>=height) return Tile.pikes;
        //System.out.println(x+"  ||  "+y+"   ||  "+width+"   ||  "+height);    Imprimase para mayor entendimiento del recorrido del level1.png
        if (tiles[x + y * width] == 0xffff0000)return Tile.floor;
        if (tiles[x + y * width] == 0xffff00ff)return Tile.wall;//ff00ff
        
        if (tiles[x + y * width] == 0xff00ff00  &&    tiles[x+1+y*width] == 0xff0000ff)return Tile.mesa;
        if (tiles[x + y * width] == 0xff0000ff  &&    tiles[x-1+y*width] == 0xff00ff00)return Tile.mesa2;
        if (tiles[x + y * width] == 0xffffff00  &&    tiles[x+1+y*width] == 0xffffffff)return Tile.mesa3;
        if (tiles[x + y * width] == 0xffffffff  &&    tiles[x-1+y*width] == 0xffffff00)return Tile.mesa4;
        
        if (tiles[x + y * width] == 0xff0000ff  &&    tiles[x+1+y*width] == 0xff00ff00)return Tile.silla;
        if (tiles[x + y * width] == 0xff00ff00  &&    tiles[x-1+y*width] == 0xff0000ff)return Tile.silla2;   
        if (tiles[x + y * width] == 0xffffffff  &&    tiles[x+1+y*width] == 0xffffff00)return Tile.silla3;
        if (tiles[x + y * width] == 0xffffff00  &&    tiles[x-1+y*width] == 0xffffffff)return Tile.silla4;
        
        return Tile.pikes;
    }

    protected void loadLevel(String path) {
    }

    protected void generateLevel() {
    }

    private void time() {
    }
    public boolean mecanica(){
        Boolean good=false;
        int dos = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Cuanto es 2 + 2?"));
        if(dos != 22){
            JOptionPane.showMessageDialog(tarea, "JAJAJA, PUTA, LA RESPUESTA ERA 22, PERDISTE, NO IRÁS A LA nANAL");
            return false;
        }else{
        return good;
        }
    }

}
