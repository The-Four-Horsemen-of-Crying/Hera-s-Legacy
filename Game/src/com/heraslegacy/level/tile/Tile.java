
package com.heraslegacy.level.tile;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sprite;

public class Tile {

    public int x;
    public int y;
    public Sprite sprite;
    public final boolean solid;
    public static Tile woodFloor = new Tile(Sprite.woodFloor, false);
    public static Tile pikes = new Tile(Sprite.null_floor, true);
    public static Tile woodWall = new Tile(Sprite.woodWall, true);
    
    //Lobby
    public static Tile marmolWall[] = {new Tile(Sprite.marmolWall[0], true),new Tile(Sprite.marmolWall[1], true),new Tile(Sprite.marmolWall[2], true)};
    
    public static Tile marmolFloor[] ={new Tile(Sprite.marmolFloor[0], false),new Tile(Sprite.marmolFloor[1], false),
                                       new Tile(Sprite.marmolFloor[2], false),new Tile(Sprite.marmolFloor[3], false)};
    
    public static Tile specialMarmolFloor = new Tile(Sprite.specialMarmolFloor, false);
    public static Tile columnas[] = {new Tile(Sprite.columnas[0], true),new Tile(Sprite.columnas[1], true)};
    public static Tile techo = new Tile(Sprite.techo, true);
    
    
    //SpaceShit
    public static Tile []spacePices = {new Tile(Sprite.spaceCheese[0], false),new Tile(Sprite.spaceCheese[1], false),new Tile(Sprite.spaceCheese[2], false),
                                       new Tile(Sprite.spaceCheese[2], false),new Tile(Sprite.spaceCheese[4], false),new Tile(Sprite.spaceCheese[3], true)};
    
    public static Tile []spaceMeteor = {new Tile(Sprite.meteorito[0], true),new Tile(Sprite.meteorito[1], true),new Tile(Sprite.meteorito[2], true),
                                        new Tile(Sprite.meteorito[3], true),new Tile(Sprite.meteorito[4], true),new Tile(Sprite.meteorito[5], true),
                                        new Tile(Sprite.meteorito[6], true),new Tile(Sprite.meteorito[7], true),new Tile(Sprite.meteorito[8], true),
                                        new Tile(Sprite.meteorito[9], true),new Tile(Sprite.meteorito[10], true),new Tile(Sprite.meteorito[11], true),
                                        new Tile(Sprite.meteorito[12], true)};
    
    //MathLevel
    public static Tile []mesa = {new Tile(Sprite.mesa[0], true), new Tile(Sprite.mesa[1], true),
                                 new Tile(Sprite.mesa[2], true), new Tile(Sprite.mesa[3], true)};
    
    public static Tile []silla= {new Tile(Sprite.silla[0], true), new Tile(Sprite.silla[1], true),
                                 new Tile(Sprite.silla[2], true), new Tile(Sprite.silla[3], true)};

    public Tile(Sprite sprite,boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void render(int x, int y, Screen screen){
            screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return solid;
    }
}
