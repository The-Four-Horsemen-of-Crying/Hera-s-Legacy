
package com.Domain.prototype.level.tile;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.graphics.Sprite;

public class FloorTile extends Tile {

    public FloorTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        //Renderizar cosas aquí
        screen.renderTile(x<<4, y<<4, this);
    }

}
