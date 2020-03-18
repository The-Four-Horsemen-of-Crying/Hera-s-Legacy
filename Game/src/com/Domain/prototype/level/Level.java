/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.level;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.level.tile.Tile;

public class Level {

    protected int width;
    protected int height;
    protected int tiles[];

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    //Entidades: ia, npcs, etc

    public void uptade() {
    }

    //position x and y; Screen para manejar el dibujo en el monitor

    public void render(int xScroll, int yScroll, Screen screen) {//x0=parteinicial de la ventana&&xf=partefinal de la ventana en x
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;//same that xScroll/16
        int x1 = (xScroll + screen.width) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height) >> 4; //31
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }
    //35
    public Tile getTile(int x, int y) {
        
        if (tiles[x + y * width] == 0)return Tile.floor;
        return Tile.pikes;
    }

    private void loadLevel(String path) {
    }

    protected void generateLevel() {
    }

    private void time() {
    }

}
