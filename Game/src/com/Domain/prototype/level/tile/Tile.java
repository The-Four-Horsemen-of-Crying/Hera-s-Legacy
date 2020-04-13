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
    public final boolean solid;
    public static Tile woodFloor = new Tile(Sprite.woodFloor,false);
    public static Tile pikes = new Tile(Sprite.null_floor,true);
    public static Tile woodWall = new Tile(Sprite.woodWall,true);
    
    public static Tile []mesa = {new Tile(Sprite.mesa[0],true),new Tile(Sprite.mesa[1],true),
                                 new Tile(Sprite.mesa[2],true),new Tile(Sprite.mesa[3],true)};
    
    public static Tile []silla= {new Tile(Sprite.silla[0],true),new Tile(Sprite.silla[1],true),
                                 new Tile(Sprite.silla[2],true),new Tile(Sprite.silla[3],true)};

    
    
    public Tile(Sprite sprite,boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void render(int x, int y, Screen screen){
            screen.renderTile(x<<4, y<<4, this);
    }

    public boolean solid() {
        return solid;
    }
}
