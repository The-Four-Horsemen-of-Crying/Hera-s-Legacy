
package com.heraslegacy.level;

import com.heraslegacy.level.tile.Tile;

public interface levelStrategy {
    
    public void update();
    //public void render(int xScroll, int yScroll, Screen screen);
    public Tile getTile(int x, int y);
    public boolean getCollision(int x, int y);
    public void loadLevel(String path, String pathCollision);
    public void time();
    public void mecanica();
    public void restar();
    public boolean cambio();
    
}
