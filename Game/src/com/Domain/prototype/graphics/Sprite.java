
package com.Domain.prototype.graphics;

import com.Domain.prototype.entity.movil.Player;


public class Sprite {
    public final int SIZE;
    private int x;
    private int y;
    public int [] pixels;
    private SpriteSheet sheet;
    
    
    public static Sprite floor_mat = new Sprite(16,1,0,SpriteSheet.nivel01_mat);
    public static Sprite null_floor = new Sprite(16,3,0,SpriteSheet.spriteSheet);
    
    //Mesa
    public static Sprite mesa = new Sprite(16, 4, 4, SpriteSheet.nivel01_mat);
    public static Sprite mesa2 = new Sprite(16, 5, 4, SpriteSheet.nivel01_mat);
    public static Sprite mesa3 = new Sprite(16, 4, 5, SpriteSheet.nivel01_mat);
    public static Sprite mesa4 = new Sprite(16, 5, 5, SpriteSheet.nivel01_mat);
    public static Sprite wall = new Sprite(16,0,0,SpriteSheet.nivel01_mat);
    //Mesa
    
    //Silla
    public static Sprite silla = new Sprite(16,0,4,SpriteSheet.nivel01_mat);
    public static Sprite silla2 = new Sprite(16,1,4,SpriteSheet.nivel01_mat);
    public static Sprite silla3 = new Sprite(16,0,5,SpriteSheet.nivel01_mat);
    public static Sprite silla4 = new Sprite(16,1,5,SpriteSheet.nivel01_mat);
    
    //Player
    public static Sprite[] player_down  = {new Sprite(32,0,1,SpriteSheet.spriteSheet), new Sprite(32,1,1,SpriteSheet.spriteSheet),
                                           new Sprite(32,2,1,SpriteSheet.spriteSheet), new Sprite(32,3,1,SpriteSheet.spriteSheet)};
        
    public static Sprite[] player_rigth = {new Sprite(32,0,3,SpriteSheet.spriteSheet),new Sprite(32,1,3,SpriteSheet.spriteSheet),
                                           new Sprite(32,2,3,SpriteSheet.spriteSheet),new Sprite(32,3,3,SpriteSheet.spriteSheet)};
    
    public static Sprite[] player_up    = {new Sprite(32,0,4,SpriteSheet.spriteSheet),new Sprite(32,1,4,SpriteSheet.spriteSheet),
                                           new Sprite(32,2,4,SpriteSheet.spriteSheet),new Sprite(32,3,4,SpriteSheet.spriteSheet)};
    
    public static Sprite[] player_left  = {new Sprite(32,0,2,SpriteSheet.spriteSheet),new Sprite(32,1,2,SpriteSheet.spriteSheet),
                                           new Sprite(32,2,2,SpriteSheet.spriteSheet),new Sprite(32,3,2,SpriteSheet.spriteSheet)};
    //Player
    
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.x = x*size; //seleccionando la coordenada x del SpriteSheet
        this.y = y*size; //seleccionando la coordenada y del SpriteSheet
        this.sheet=sheet;
        pixels=new int[SIZE*SIZE];
        load();
    }
    
    private void load(){
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
            }
        }
    }
    
    
}
