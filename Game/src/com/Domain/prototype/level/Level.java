/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.level;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.level.tile.Tile;
import java.util.Random;
import javax.swing.JFrame;

public class Level {
    
    public static Level level01 = new Level01("/levels/level01/level1.png","/levels/level01/collisionlevel1.png");
    //protected Tile[] tiles;
    protected int width;
    protected int height;
    protected int[] tiles; //contendrá todos los pixeles del nivel.
    protected int[] tilesCollision;
    private Random random = new Random();

    

    public Level(String path,String pathCollision) {
        loadLevel(path, pathCollision);
        generateLevel();
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
            }
        }
    }
    //35
    
    public Tile getTile(int x, int y) {
        return Tile.floor;
    }
    public boolean getCollision(int x, int y){
       return false;
     }

    protected void loadLevel(String path, String pathCollision) {
    }

    protected void generateLevel() {
    }

    private void time() {
    }
    public void mecanica(int x, int y){
    }

}
