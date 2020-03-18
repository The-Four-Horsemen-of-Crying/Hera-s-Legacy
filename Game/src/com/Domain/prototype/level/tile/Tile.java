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
    public static Tile floor = new FloorTile(Sprite.floor_mat);
    public static Tile pikes = new VoidTile(Sprite.null_floor);

    public Tile(Sprite sprite) {
        this.sprite = sprite;

    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
