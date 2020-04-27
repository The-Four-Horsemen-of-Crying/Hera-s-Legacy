
package com.heraslegacy.level;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.level.tile.Tile;

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

    public void restart() {
        levelstrategy.restar();
    }
    
    public boolean cambio(){
        return levelstrategy.cambio();
    }
}

