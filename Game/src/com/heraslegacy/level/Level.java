
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.MenuGUI;
import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.level.tile.Tile;
import static com.heraslegacy.main.Game.screen;
import java.awt.Color;
import java.awt.Font;

public class Level{
    public final levelStrategy levelstrategy;
    private Font fontLevel;
    private MenuGUI menu;
    
    public Level(String path, String pathCollision, levelStrategy levelstrategy){
        this.levelstrategy = levelstrategy;
        levelstrategy.loadLevel(path, pathCollision);
        fontLevel=levelstrategy.getFont();
        menu= new MenuGUI();
    }

    public void update() {
        levelstrategy.update();
    }

    public void render(int xScroll, int yScroll) {
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4);
        int x1 = (xScroll + screen.width+16) >> 4;
        int y0 = (yScroll >> 4);
        int y1 = (yScroll + screen.height+16) >> 4;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y);
            }
        }
        levelstrategy.render();
        
    }
    public void superRender(int xScroll, int yScroll){
        levelstrategy.sobreRender(xScroll,yScroll);    
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
    
    public void configPlayer(){
        levelstrategy.configPlayer(this);
    }
    
    public Player getPlayer(){
        return levelstrategy.getPlayer();
    }
   
    public Texto[] getText(){
        return levelstrategy.getText();
    }
    
    public  void setText(String t){
         levelstrategy.setText(t);
    }
    public Color getColor(){
        return levelstrategy.getColor();
    }
    
    public Font getFont(){
        return levelstrategy.getFont();
    }
    
    public Level levelCambio(){
        return levelstrategy.levelCambio();
    }

    public levelStrategy getLevelstrategy() {
        return levelstrategy;
    }
    
}

