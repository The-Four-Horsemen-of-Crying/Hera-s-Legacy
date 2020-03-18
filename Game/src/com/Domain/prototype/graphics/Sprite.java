
package com.Domain.prototype.graphics;


public class Sprite {
    public final int SIZE;
    private int x;
    private int y;
    public int [] pixels;
    private SpriteSheet sheet;
    
    public static Sprite floor_mat = new Sprite(16,1,0,SpriteSheet.floor);
    public static Sprite null_floor = new Sprite(16,0,0,SpriteSheet.floor);
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
