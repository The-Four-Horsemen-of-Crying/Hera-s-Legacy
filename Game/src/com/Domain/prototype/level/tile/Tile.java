/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.level.tile;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.graphics.Sprite;

/**
 *
 * @author Domain
 */
public class Tile {

    public int x;
    public int y;
    public Sprite sprite;
    public static Tile woodFloor = new FloorTile(Sprite.woodFloor);
    public static Tile pikes = new VoidTile(Sprite.null_floor);
    public static Tile woodWall = new Wall(Sprite.woodWall);
    
    public static Tile []mesa = {new Mesa(Sprite.mesa[0]),new Mesa(Sprite.mesa[1]),
                                 new Mesa(Sprite.mesa[2]),new Mesa(Sprite.mesa[3])};
    
    public static Tile []silla= {new Silla(Sprite.silla[0]),new Silla(Sprite.silla[1]),
                                 new Silla(Sprite.silla[2]),new Silla(Sprite.silla[3])};

    
    
    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
