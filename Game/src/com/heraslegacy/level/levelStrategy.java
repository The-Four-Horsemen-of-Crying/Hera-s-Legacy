
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.level.tile.Tile;
import java.awt.Color;
import java.awt.Font;

public interface levelStrategy {
    
    public void update();
    public Tile getTile(int x, int y);
    public boolean getCollision(int x, int y);
    public void loadLevel(String path, String pathCollision);
    public void time();
    public void mecanica();
    public void restar();
    public boolean cambio();
    public void configPlayer(Level level);
    public Player getPlayer();
    public Texto[] getText();
    public void setText(String c);
    public Color getColor();
    public Level levelCambio();
    public Font getFont();
    public void render();
    public void backWithoutWin();
    public void sobreRender(int xScroll,int yScroll);
}
