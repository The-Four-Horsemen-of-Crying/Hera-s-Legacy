
package com.heraslegacy.level.tile;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sprite;
import static com.heraslegacy.main.Game.screen;
import javafx.scene.input.KeyCode;

public class Tile {

    public int x;
    public int y;
    public Sprite sprite;
    public boolean solid;
    public TipoTile tipo;
    
    
    
    
    public static Tile woodFloor = new Tile(Sprite.woodFloor, false);
    public static Tile pikes = new Tile(Sprite.null_floor, true);
    public static Tile woodWall = new Tile(Sprite.woodWall, true);
    
    //Lobby
    public static Tile marmolWall[] = {new Tile(Sprite.marmolWall[0], true),new Tile(Sprite.marmolWall[1], true),new Tile(Sprite.marmolWall[2], true)};
    
    public static Tile marmolFloor[] ={new Tile(Sprite.marmolFloor[0], false),new Tile(Sprite.marmolFloor[1], false),
                                       new Tile(Sprite.marmolFloor[2], false),new Tile(Sprite.marmolFloor[3], false)};
    
    public static Tile specialMarmolFloor = new Tile(Sprite.specialMarmolFloor, false);
    
    public static Tile columnas[] = {new Tile(Sprite.columnas[0], false),new Tile(Sprite.columnas[1], true)};
    
    public static Tile techo = new Tile(Sprite.techo, true);
    
    
    //SpaceShit
    public static Tile []spacePices = {new Tile(Sprite.spaceCheese[0], false),new Tile(Sprite.spaceCheese[1], false),new Tile(Sprite.spaceCheese[2], false),
                                       new Tile(Sprite.spaceCheese[2], false),new Tile(Sprite.spaceCheese[4], false),new Tile(Sprite.spaceCheese[3], true),
                                       new Tile(Sprite.spaceCheese[2], true)};
    
    public static Tile []spaceMeteor = {new Tile(Sprite.meteorito[0], true,TipoTile.GAME_OVER),new Tile(Sprite.meteorito[1], true),new Tile(Sprite.meteorito[2], true,TipoTile.GAME_OVER),
                                        new Tile(Sprite.meteorito[3], true,TipoTile.GAME_OVER),new Tile(Sprite.meteorito[4], true),new Tile(Sprite.meteorito[5], true,TipoTile.GAME_OVER),
                                        new Tile(Sprite.meteorito[6], true,TipoTile.GAME_OVER),new Tile(Sprite.meteorito[7], true),new Tile(Sprite.meteorito[8], true,TipoTile.GAME_OVER),
                                        new Tile(Sprite.meteorito[9], true),new Tile(Sprite.meteorito[10], true,TipoTile.GAME_OVER),new Tile(Sprite.meteorito[11], true,TipoTile.GAME_OVER),
                                        new Tile(Sprite.meteorito[12], true)};
    
    public static Tile []niceStuff = {new Tile(Sprite.niceStuffForANiceGame[0], false),new Tile(Sprite.niceStuffForANiceGame[1], false)};
    
    //MathLevel
    public static Tile []mesa = {new Tile(Sprite.mesa[0], true), new Tile(Sprite.mesa[1], true),
                                 new Tile(Sprite.mesa[2], true), new Tile(Sprite.mesa[3], true)};
    
    public static Tile []silla= {new Tile(Sprite.silla[0], true), new Tile(Sprite.silla[1], true),
                                 new Tile(Sprite.silla[2], true), new Tile(Sprite.silla[3], true)};
    
    //LibraryLevel    
    public static Tile puertaE[] ={new Tile(Sprite.puerta,false),new Tile(Sprite.puerta,false),
                                   new Tile(Sprite.puerta,false),new Tile(Sprite.puerta,false)};
    public static Tile puertaS[] ={new Tile(Sprite.puerta,true),new Tile(Sprite.puerta,true),
                                   new Tile(Sprite.puerta,true),new Tile(Sprite.puerta,true)};
    
    public static Tile paredLibrary = new Tile(Sprite.paredLibrary,true);
    public static Tile sueloLibrary = new Tile(Sprite.sueloLibrary,false);
    public static Tile estanterias[] = {new Tile(Sprite.estantes[0],true),new Tile(Sprite.estantes[1],true),
                                        new Tile(Sprite.estantes[2],true),new Tile(Sprite.estantes[3],true)};
    
    public static Tile guardia[] = {new Tile(Sprite.guardia[0],true),new Tile(Sprite.guardia[1],true),
                                    new Tile(Sprite.guardia[2],true),new Tile(Sprite.guardia[3],true)};
    public static Tile libro = new Tile(Sprite.libro,false);

    public Tile(Sprite sprite,boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }
    
    public Tile(Sprite sprite, boolean solid, TipoTile tipo){
        this.sprite=sprite;
        this.solid=solid;
        this.tipo=tipo;
    }
    public void render(int x, int y){
            screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return solid;
    }
    
    public void setSolid(Boolean solid){
        this.solid=solid;
    }
}
