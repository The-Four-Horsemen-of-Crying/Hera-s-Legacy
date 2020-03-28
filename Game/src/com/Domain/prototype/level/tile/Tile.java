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
    public static Tile mesa = new Mesa(Sprite.mesa);
    public static Tile mesa2= new Mesa(Sprite.mesa2);
    public static Tile mesa3= new Mesa(Sprite.mesa3);
    public static Tile mesa4=new Mesa(Sprite.mesa4);
    public static Tile silla=new Silla(Sprite.silla);
    public static Tile silla2=new Silla(Sprite.silla2);
    public static Tile silla3=new Silla(Sprite.silla3);
    public static Tile silla4=new Silla(Sprite.silla4);
    public static Tile woodWall = new Wall(Sprite.woodWall);
    
    
    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
