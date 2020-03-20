package com.Domain.prototype.level.tile;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.graphics.Sprite;

class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        //Renderizar cosas aquí
        screen.renderTile(x<<4, y<<4, this);
    }
}
