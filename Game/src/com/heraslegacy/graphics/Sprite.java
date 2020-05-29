
package com.heraslegacy.graphics;



public class Sprite {
    public final int SIZE;
    private int x;
    private int y;
    private int width;
    private int height;
    public int [] pixels;
    private SpriteSheet sheet;
    
    
    public static Sprite woodFloor = new Sprite(16,1,0,SpriteSheet.nivel01_mat);
    public static Sprite null_floor = new Sprite(16,3,0,SpriteSheet.elizabeth);
    public static Sprite woodWall = new Sprite(16,0,0,SpriteSheet.nivel01_mat);
    
    //Lobby
    public static Sprite marmolWall[] = {new Sprite(16,0,1,SpriteSheet.nivel00_lobby), new Sprite(16, 1, 1, SpriteSheet.nivel00_lobby),
                                         new Sprite(16, 2, 1, SpriteSheet.nivel00_lobby)};//NORMAL, UP
    
    public static Sprite marmolFloor[] = {new Sprite(16, 0, 0, SpriteSheet.nivel00_lobby),new Sprite(16, 2, 0, SpriteSheet.nivel00_lobby),//NORMAL,LEFT,DOWN,RIGTH
                                          new Sprite(16, 3, 0, SpriteSheet.nivel00_lobby),new Sprite(16, 4, 0, SpriteSheet.nivel00_lobby)};
    
    public static Sprite specialMarmolFloor = new Sprite(16, 1, 0, SpriteSheet.nivel00_lobby);
    
    public static Sprite columnas[] = {new Sprite(16, 0, 2, SpriteSheet.nivel00_lobby),new Sprite(16, 1, 2, SpriteSheet.nivel00_lobby)};
    
    public static Sprite techo = new Sprite(16, 2, 2, SpriteSheet.nivel00_lobby);
    
    public static Sprite portales [][] = {{new Sprite(32, 48, 0, 0, SpriteSheet.LobbyDoors),new Sprite(32, 48, 1, 0, SpriteSheet.LobbyDoors),new Sprite(32, 48, 2, 0, SpriteSheet.LobbyDoors)},
                                          {new Sprite(32, 48, 0, 1, SpriteSheet.LobbyDoors),new Sprite(32, 48, 1, 1, SpriteSheet.LobbyDoors),new Sprite(32, 48, 2, 1, SpriteSheet.LobbyDoors)},
                                          {new Sprite(32, 48, 0, 2, SpriteSheet.LobbyDoors),new Sprite(32, 48, 1, 2, SpriteSheet.LobbyDoors),new Sprite(32, 48, 2, 2, SpriteSheet.LobbyDoors)}};
    //SpaceShit
    public static Sprite moon = new Sprite(96,0,0,SpriteSheet.singleDrawMoon);
    
    public static Sprite meteorito[] = {new Sprite(16,0,2,SpriteSheet.nivel02_space),new Sprite(16,2,2,SpriteSheet.nivel02_space),new Sprite(16,3,2,SpriteSheet.nivel02_space),
                                        new Sprite(16,4,2,SpriteSheet.nivel02_space),new Sprite(16,5,2,SpriteSheet.nivel02_space),new Sprite(16,2,3,SpriteSheet.nivel02_space),
                                        new Sprite(16,5,3,SpriteSheet.nivel02_space),new Sprite(16,2,5,SpriteSheet.nivel02_space),new Sprite(16,3,5,SpriteSheet.nivel02_space),
                                        new Sprite(16,2,4,SpriteSheet.nivel02_space),new Sprite(16,5,4,SpriteSheet.nivel02_space),new Sprite(16,4,5,SpriteSheet.nivel02_space),
                                        new Sprite(16,5,5,SpriteSheet.nivel02_space)};
    
    public static Sprite spaceCheese[] = {new Sprite(16, 0,0,SpriteSheet.nivel02_space),new Sprite(16, 1,0,SpriteSheet.nivel02_space),new Sprite(16, 3,0,SpriteSheet.nivel02_space),
                                          new Sprite(16, 2,0,SpriteSheet.nivel02_space),new Sprite(16, 3,1,SpriteSheet.nivel02_space)};
    
    public static Sprite niceStuffForANiceGame [] = {new Sprite(16, 3, 3, SpriteSheet.nivel02_space),new Sprite(16, 4, 3, SpriteSheet.nivel02_space)};
    
    
    //ZeroZone
    public static Sprite fonts01[] = {new Sprite(32,32,0,0, SpriteSheet.numFonts),new Sprite(32,32,1,0, SpriteSheet.numFonts),new Sprite(32,32,2,0, SpriteSheet.numFonts),
                                      new Sprite(32,32,3,0, SpriteSheet.numFonts),new Sprite(32,32,0,1, SpriteSheet.numFonts),new Sprite(32,32,1,1, SpriteSheet.numFonts),
                                      new Sprite(32,32,2,1, SpriteSheet.numFonts),new Sprite(32,32,3,1, SpriteSheet.numFonts),new Sprite(32,32,0,2, SpriteSheet.numFonts),
                                      new Sprite(32,32,1,2, SpriteSheet.numFonts)};
    
    public static Sprite frasesLvl01[] = {new Sprite(96,32,0,3, SpriteSheet.numFonts)};
    
    //MathLevel
    public static Sprite []hoja ={null,new Sprite(150, 150, 0,0, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,0, SpriteSheet.HojasnivelGame01_mat),
                                  new Sprite(150, 150, 0,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 0,0, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,0, SpriteSheet.HojasnivelGame01_mat),
                                  new Sprite(150, 150, 0,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 0,0, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,0, SpriteSheet.HojasnivelGame01_mat),
                                  new Sprite(150, 150, 0,1, SpriteSheet.HojasnivelGame01_mat),new Sprite(150, 150, 1,1, SpriteSheet.HojasnivelGame01_mat)};
    
    public static Sprite []mesa = {new Sprite(16, 4, 4, SpriteSheet.nivel01_mat),new Sprite(16, 5, 4, SpriteSheet.nivel01_mat),
                                   new Sprite(16, 4, 5, SpriteSheet.nivel01_mat),new Sprite(16, 5, 5, SpriteSheet.nivel01_mat)};

    
    public static Sprite []silla = {new Sprite(16,0,4,SpriteSheet.nivel01_mat),new Sprite(16,1,4,SpriteSheet.nivel01_mat),
                                    new Sprite(16,0,5,SpriteSheet.nivel01_mat),new Sprite(16,1,5,SpriteSheet.nivel01_mat)};
    
    //Players
        //SpaceShit
    public static Sprite[] apolo_up     = {new Sprite(16, 48, 0, 2, SpriteSheet.apolo11),new Sprite(16, 48, 1, 2, SpriteSheet.apolo11),
                                           new Sprite(16, 48, 2, 2, SpriteSheet.apolo11),new Sprite(16, 48, 3, 2, SpriteSheet.apolo11)};
    
    public static Sprite[] apolo_down   = {new Sprite(16, 48, 4, 2, SpriteSheet.apolo11),new Sprite(16, 48, 5, 2, SpriteSheet.apolo11),
                                           new Sprite(16, 48, 6, 2, SpriteSheet.apolo11),new Sprite(16, 48, 7, 2, SpriteSheet.apolo11)};
    
    
    public static Sprite[] apolo_rigth  = {new Sprite(48, 16, 0, 0, SpriteSheet.apolo11),new Sprite(48, 16, 0, 1, SpriteSheet.apolo11),
                                           new Sprite(48, 16, 0, 2, SpriteSheet.apolo11),new Sprite(48, 16, 0, 3, SpriteSheet.apolo11)};
    
    public static Sprite[] apolo_left   = {new Sprite(64, 16, 1, 0, SpriteSheet.apolo11),new Sprite(64, 16, 1, 1, SpriteSheet.apolo11),
                                           new Sprite(64, 16, 1, 2, SpriteSheet.apolo11),new Sprite(64, 16, 1, 3, SpriteSheet.apolo11)};
    
        //TestPJ
    public static Sprite[] Elizabeth_down  = {new Sprite(32,0,1,SpriteSheet.elizabeth), new Sprite(32,1,1,SpriteSheet.elizabeth),
                                              new Sprite(32,2,1,SpriteSheet.elizabeth), new Sprite(32,3,1,SpriteSheet.elizabeth)};
        
    public static Sprite[] Elizabeth_rigth = {new Sprite(32,0,3,SpriteSheet.elizabeth),new Sprite(32,1,3,SpriteSheet.elizabeth),
                                              new Sprite(32,2,3,SpriteSheet.elizabeth),new Sprite(32,3,3,SpriteSheet.elizabeth)};
    
    public static Sprite[] Elizabeth_up    = {new Sprite(32,0,4,SpriteSheet.elizabeth),new Sprite(32,1,4,SpriteSheet.elizabeth),
                                              new Sprite(32,2,4,SpriteSheet.elizabeth),new Sprite(32,3,4,SpriteSheet.elizabeth)};
    
    public static Sprite[] Elizabeth_left  = {new Sprite(32,0,2,SpriteSheet.elizabeth),new Sprite(32,1,2,SpriteSheet.elizabeth),
                                              new Sprite(32,2,2,SpriteSheet.elizabeth),new Sprite(32,3,2,SpriteSheet.elizabeth)};
    //Player
    
    //LibraryLevel
    public static Sprite puerta = new Sprite(16,2,2,SpriteSheet.nivel03_library);
    public static Sprite paredLibrary = new Sprite(16,1,0,SpriteSheet.nivel01_mat);
    public static Sprite sueloLibrary = new Sprite(16,0,2,SpriteSheet.nivel03_library);
    public static Sprite estantes[]={new Sprite(16,0,0,SpriteSheet.nivel03_library),new Sprite(16,1,0,SpriteSheet.nivel03_library),
                                     new Sprite(16,0,1,SpriteSheet.nivel03_library),new Sprite(16,1,1,SpriteSheet.nivel03_library)};
    public static Sprite guardia[]= new Sprite[4];
    public static Sprite libro;
    
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
    public int getSIZE(){
        return SIZE;
    
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
