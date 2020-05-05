
package com.heraslegacy.graphics;



public class Sprite {
    public final int SIZE;
    private int x;
    private int y;
    private int width;
    private int height;
    public int [] pixels;
    private SpriteSheet sheet;
    
    public static Sprite shit = new Sprite(300,168,0,0,SpriteSheet.shit);
    public static Sprite woodFloor = new Sprite(16,1,0,SpriteSheet.nivel01_mat);
    public static Sprite null_floor = new Sprite(16,3,0,SpriteSheet.spriteSheet);
    public static Sprite woodWall = new Sprite(16,0,0,SpriteSheet.nivel01_mat);
    public static Sprite spaceCheese[] = {new Sprite(16, 0,0,SpriteSheet.nivel02_space),new Sprite(16, 1,0,SpriteSheet.nivel02_space),new Sprite(16, 0,1,SpriteSheet.nivel02_space),
                                          new Sprite(16, 2,0,SpriteSheet.nivel02_space),new Sprite(16, 1,1,SpriteSheet.nivel02_space)};
    
    public static Sprite fonts01[] = {new Sprite(32,32,0,0, SpriteSheet.numFonts),new Sprite(32,32,1,0, SpriteSheet.numFonts),new Sprite(32,32,2,0, SpriteSheet.numFonts),
                                      new Sprite(32,32,3,0, SpriteSheet.numFonts),new Sprite(32,32,0,1, SpriteSheet.numFonts),new Sprite(32,32,1,1, SpriteSheet.numFonts),
                                      new Sprite(32,32,2,1, SpriteSheet.numFonts),new Sprite(32,32,3,1, SpriteSheet.numFonts),new Sprite(32,32,0,2, SpriteSheet.numFonts),
                                      new Sprite(32,32,1,2, SpriteSheet.numFonts)};
    
    public static Sprite frasesLvl01[] = {new Sprite(96,32,0,3, SpriteSheet.numFonts)};
    
    public static Sprite []hoja ={new Sprite(150, 150, 0,0, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,0, SpriteSheet.HojasnivelGame01_mat),
                                  new Sprite(150, 150, 0,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,1, SpriteSheet.HojasnivelGame01_mat)};
    //Mesa
    public static Sprite []mesa = {new Sprite(16, 4, 4, SpriteSheet.nivel01_mat),new Sprite(16, 5, 4, SpriteSheet.nivel01_mat),
                                   new Sprite(16, 4, 5, SpriteSheet.nivel01_mat),new Sprite(16, 5, 5, SpriteSheet.nivel01_mat)};

    
    
    public static Sprite []silla = {new Sprite(16,0,4,SpriteSheet.nivel01_mat),new Sprite(16,1,4,SpriteSheet.nivel01_mat),
                                    new Sprite(16,0,5,SpriteSheet.nivel01_mat),new Sprite(16,1,5,SpriteSheet.nivel01_mat)};   
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
        this.width = SIZE;
        this.height= SIZE;
        pixels=new int[SIZE*SIZE];
        load();
    }
    public Sprite(int width, int height, int colour){
        SIZE=width*height;
        this.height=height;
        this.width=width;
        pixels=new int[width*height];
        setColour(colour); 
    }
    public Sprite(int w, int h, int xC, int yC,SpriteSheet sheet){
        SIZE=h*w;
        this.height=h;
        this.width=w;
        this.x=xC*w;
        this.y=yC*h;
        this.sheet=sheet;
        pixels = new int [w*h];
        load2();
    }
    
    
    private void setColour(int colour){
        for (int i = 0; i < width*height; i++) {
            pixels[i]= colour;
        }
    
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    private void load(){
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
            }
        }
    }
    private void load2(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x <width; x++) {
                pixels[x+y*width]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
            }
        }
    
    }
    
    
}
