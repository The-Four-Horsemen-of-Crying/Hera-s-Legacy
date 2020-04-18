/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.level;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.level.tile.Tile;

/**
 *
 * @author ejose
 */
public class Level{
    
    private final levelStrategy levelstrategy;
    
    public Level(String path, String pathCollision, levelStrategy levelstrategy){
        this.levelstrategy = levelstrategy;
        levelstrategy.loadLevel(path, pathCollision);
    }

    public void update() {
        levelstrategy.update();
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        levelstrategy.render(xScroll, yScroll, screen);
    }

    public Tile getTile(int x, int y) {
        return levelstrategy.getTile(x, y);
    }

    public boolean getCollision(int x, int y) {
        return levelstrategy.getCollision(x, y);
    }

    public void loadLevel(String path, String pathCollision) {
        levelstrategy.loadLevel(path, pathCollision);
    }

    public void time() {
        levelstrategy.time();
    }

    public void mecanica() {
        levelstrategy.mecanica();
    }

    public void restar() {
        levelstrategy.restar();
    }
    
    public boolean cambio(){
        return levelstrategy.cambio();
    }
}
