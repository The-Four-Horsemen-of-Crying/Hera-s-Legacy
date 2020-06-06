
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
    public final levelStrategy LEVELSTRATEGY;
    private Font fontLevel;
    
    
    public Level(String path, String pathCollision, levelStrategy levelstrategy){
        this.LEVELSTRATEGY = levelstrategy;
        levelstrategy.loadLevel(path, pathCollision);
        fontLevel=levelstrategy.getFont();

    }

    public void update() {
        LEVELSTRATEGY.update();
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
        LEVELSTRATEGY.render();
        
    }
    public void superRender(int xScroll, int yScroll){
        LEVELSTRATEGY.sobreRender(xScroll,yScroll);    
    }

    public Tile getTile(int x, int y) {
        return LEVELSTRATEGY.getTile(x, y);
    }

    public boolean getCollision(int x, int y) {
        return LEVELSTRATEGY.getCollision(x, y);
    }

    public void loadLevel(String path, String pathCollision) {
        LEVELSTRATEGY.loadLevel(path, pathCollision);
    }

    public void time() {
        LEVELSTRATEGY.time();
    }

    public void mecanica() {
        LEVELSTRATEGY.mecanica();
    }

    public void restart() {
        LEVELSTRATEGY.restar();
    }
    
    public boolean cambio(){
        return LEVELSTRATEGY.cambio();
    }
    
    public void configPlayer(){
        LEVELSTRATEGY.configPlayer(this);
    }
    
    public Player getPlayer(){
        return LEVELSTRATEGY.getPlayer();
    }
   
    public Texto[] getText(){
        return LEVELSTRATEGY.getText();
    }
    
    public  void setText(String t){
         LEVELSTRATEGY.setText(t);
    }
    public Color getColor(){
        return LEVELSTRATEGY.getColor();
    }
    
    public Font getFont(){
        return LEVELSTRATEGY.getFont();
    }
    
    public Level levelCambio(){
        return LEVELSTRATEGY.levelCambio();
    }

    public levelStrategy getLevelstrategy() {
        return LEVELSTRATEGY;
    }
    public void backLobby(){
        LEVELSTRATEGY.backWithoutWin();
    }

    public void uptadeTexto() {
        LEVELSTRATEGY.uptadeTexto();
    }
    
}

