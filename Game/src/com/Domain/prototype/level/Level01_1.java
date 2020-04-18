
package com.Domain.prototype.level;


import static com.Domain.prototype.Game.scale;
import static com.Domain.prototype.Game.screen;
import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.graphics.Sprite;
import static com.Domain.prototype.graphics.Sprite.hoja;
import com.Domain.prototype.input.KeyBoard;
import com.Domain.prototype.input.Mouse;
import com.Domain.prototype.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Level01_1 implements levelStrategy {
    protected int width;
    protected int height;
    protected int[] tiles; //contendrá todos los pixeles del nivel.
    protected int[] tilesCollision;
    protected int red= 0xffff0000;
    protected int fuchsia=  0xffff00ff;
    protected int lime = 0xff00ff00 ;
    protected int blue =0xff0000ff;
    protected int yellow=0xffffff00;
    protected int white= 0xffffffff;
    private int mesa=0;
    private final int respuestas[]={0,1,4,0};
    private boolean resueltos[]={false,false,false,false};
    private Mouse mouse;
    private KeyBoard key;
    private int numeroAnterior;
    
    public Level01_1() {
    }
    
    public void update(){
        
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {//x0=parteinicial de la ventana&&xf=partefinal de la ventana en x
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4);//same that xScroll/16
        int x1 = (xScroll + screen.width+16) >> 4;//Se adiciona 16 para contar con una tile en el lado derecho (width) de la ventana
        int y0 = (yScroll >> 4);
        int y1 = (yScroll + screen.height+16) >> 4; //Se adiciona 16 para contar con una tile en el lado bajo (height) de la ventana
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }
    
    @Override
     public Tile getTile(int x, int y){
         
        if(x<0 || y<0||x>=width||y>=height) return Tile.pikes;
        //System.out.println(x+"  ||  "+y+"   ||  "+width+"   ||  "+height);    Imprimase para mayor entendimiento del recorrido del level1.png
        
        if (tiles[x + y * width] == red)    return Tile.woodFloor;
        if (tiles[x + y * width] == fuchsia)return Tile.woodWall;
        
        if (tiles[x + y * width] == lime    &&    tiles[x+1+y*width] == blue)   return Tile.mesa[0];
        if (tiles[x + y * width] == blue    &&    tiles[x-1+y*width] == lime)   return Tile.mesa[0];
        if (tiles[x + y * width] == yellow  &&    tiles[x+1+y*width] == white ) return Tile.mesa[0];
        if (tiles[x + y * width] == white   &&    tiles[x-1+y*width] == yellow) return Tile.mesa[0];
        
        if (tiles[x + y * width] == blue    &&    tiles[x+1+y*width] == lime)   return Tile.silla[0];
        if (tiles[x + y * width] == lime    &&    tiles[x-1+y*width] == blue)   return Tile.silla[1];   
        if (tiles[x + y * width] == white   &&    tiles[x+1+y*width] == yellow) return Tile.silla[2];
        if (tiles[x + y * width] == yellow  &&    tiles[x-1+y*width] == white)  return Tile.silla[3];
        
        return Tile.pikes;
     }
     
    @Override
     public boolean getCollision(int x, int y){
         //if(x<0 || y<0||x>=width||y>=height)return false;
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == yellow && !this.resueltos[0]){
             mesa=0;
             return true;
         }
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == fuchsia && !this.resueltos[1]){
             mesa=1;
             return true;
         }         
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == lime && !this.resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=2;
             return true;
         }
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == blue && !this.resueltos[3]){
             mesa=3;
             return true;
         }


         mouse.clickSwitch=false;
         for (int i = 0; i < key.numbers.length; i++) {
             key.numbers[i]=false;
         }
        
         return false;
     }
     
    public void loadLevel(String path, String pathCollision) {
        try {
            BufferedImage image = ImageIO.read(Level01.class.getResource(path));
            BufferedImage imageCollision = ImageIO.read(Level01.class.getResource(pathCollision));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            tilesCollision = new int[w * h];

            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar level file!");
        }
    }
    
    public void mecanica() {
        int thisNum=0;
        screen.renderSprite(false,screen.width/2-hoja[0].getWidth()/2,screen.height/2-hoja[0].getHeight()/2, hoja[mesa]);
        //screen.renderSprite(false, 0, 0, shit);
        
        
        if((mouse.mouseX > screen.width  * scale / 2) && (mouse.mouseX < screen.width  * scale / 2 + (hoja[mesa].getWidth() - 22) * scale / 2 )
         &&(mouse.mouseY > screen.height * scale / 2) && (mouse.mouseY < screen.height * scale / 2 + (hoja[mesa].getHeight()- 22) * scale / 2 )){
            //System.out.println("good job motherfucker");
            
            if(mouse.clickSwitch){
                //System.out.println("click: on");
                //input amswer
                while(thisNum<key.numbers.length){
                      if(key.numbers[thisNum]){
                          if(numeroAnterior!=thisNum)key.numbers[numeroAnterior]=false;                        
                          screen.renderSprite(false,screen.width/2+Sprite.fonts01[thisNum].getWidth()/2,screen.height/2+Sprite.fonts01[thisNum].getHeight()/2, Sprite.fonts01[thisNum]);
                          //System.out.println(thisNum);    
                          numeroAnterior=thisNum;
                          //read answer
                          if(key.enter&&thisNum==respuestas[mesa]){
                              System.out.println("Good bitch");
                              resueltos[mesa]=true;
                              key.numbers[thisNum]=false;
                          }
                          else if(key.enter&&thisNum!=respuestas[mesa]){
                              System.out.println("Wrong Motherfucker");
                              key.numbers[thisNum]=false;
                          }
                      }
                      thisNum++;
                }
                
            }
            else screen.renderSprite(false,screen.width/2-2,screen.height/2+Sprite.fonts01[thisNum].getHeight()/2, Sprite.frasesLvl01[0]);
        }
        else {
            mouse.clickSwitch = false;
            for (int i = 0; i < key.numbers.length; i++) {
                key.numbers[i] = false;
            }
        }
    }
    
    public void time(){
            
    }
        
    public void restar(){
            
    }
    
    public boolean cambio(){
        return false;
    }
}   