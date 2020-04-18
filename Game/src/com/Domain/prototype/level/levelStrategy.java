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
public interface levelStrategy {
    
    public void update();
    public void render(int xScroll, int yScroll, Screen screen);
    public Tile getTile(int x, int y);
    public boolean getCollision(int x, int y);
    public void loadLevel(String path, String pathCollision);
    public void time();
    public void mecanica();
    public void restar();
    //para cambiar de nivel:)
    public boolean cambio();
    
}
