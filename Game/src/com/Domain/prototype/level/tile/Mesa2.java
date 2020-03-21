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
public class Mesa2 extends Tile{

    public Mesa2(Sprite sprite) {
        super(sprite);
    }


        public void render(int x, int y, Screen screen) {
        screen.renderTile(x<<4, y<<4, this);
    }  
    
}
