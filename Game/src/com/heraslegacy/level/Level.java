
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.manager.KeyBoard;

public class Level{
    public Sound b;
    private final levelStrategy levelstrategy;
    
    
    public Level(String path, String pathCollision, levelStrategy levelstrategy){
        this.levelstrategy = levelstrategy;
        b=new Sound(Sound.de);
        levelstrategy.loadLevel(path, pathCollision);
    }

    public void update() {
        levelstrategy.update();
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4);
        int x1 = (xScroll + screen.width+16) >> 4;
        int y0 = (yScroll >> 4);
        int y1 = (yScroll + screen.height+16) >> 4;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
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
    
    public void configPlayer(int x, int y, KeyBoard input, Sprite[] up, Sprite[] down, Sprite[] rigth, Sprite[] left, boolean tipo){
        levelstrategy.configPlayer(x, y, input, up, down, rigth, left, tipo,this);
    }
    
    public Player getPlayer(){
        return levelstrategy.getPlayer();
    }
   
}

