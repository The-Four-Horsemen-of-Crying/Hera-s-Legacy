
package com.Domain.prototype.level;


import static com.Domain.prototype.Game.scale;
import static com.Domain.prototype.Game.screen;
import com.Domain.prototype.graphics.Sprite;
import static com.Domain.prototype.graphics.Sprite.hoja;
import com.Domain.prototype.input.KeyBoard;
import com.Domain.prototype.input.Mouse;
import com.Domain.prototype.level.tile.Tile;


public class Level01 extends Level {
    private int mesa=0;
    private final int respuestas[]={0,1,4,0};
    private boolean resueltos[]={false,false,false,false};
    private Mouse mouse;
    private KeyBoard key;
    private int numeroAnterior;
    
    public Level01(String path, String pathCollision) {

        super(path, pathCollision);

    }

    
    @Override
     public Tile getTile(int x, int y){
         
        if(x<0 || y<0||x>=width||y>=height) return Tile.pikes;
        //System.out.println(x+"  ||  "+y+"   ||  "+width+"   ||  "+height);    Imprimase para mayor entendimiento del recorrido del level1.png
        
        if (tiles[x + y * width] == red)    return Tile.woodFloor;
        if (tiles[x + y * width] == fuchsia)return Tile.woodWall;
        
        if (tiles[x + y * width] == lime    &&    tiles[x+1+y*width] == blue)   return Tile.mesa[0];
        if (tiles[x + y * width] == blue    &&    tiles[x-1+y*width] == lime)   return Tile.mesa[1];
        if (tiles[x + y * width] == yellow  &&    tiles[x+1+y*width] == white ) return Tile.mesa[2];
        if (tiles[x + y * width] == white   &&    tiles[x-1+y*width] == yellow) return Tile.mesa[3];
        
        if (tiles[x + y * width] == blue    &&    tiles[x+1+y*width] == lime)   return Tile.silla[0];
        if (tiles[x + y * width] == lime    &&    tiles[x-1+y*width] == blue)   return Tile.silla[1];   
        if (tiles[x + y * width] == white   &&    tiles[x+1+y*width] == yellow) return Tile.silla[2];
        if (tiles[x + y * width] == yellow  &&    tiles[x-1+y*width] == white)  return Tile.silla[3];
        
        return Tile.pikes;
     }
     
    @Override
     public boolean getCollision(int x, int y){
         //if(x<0 || y<0||x>=width||y>=height)return false;
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == yellow && !resueltos[0]){
             mesa=0;
             return true;
         }
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == fuchsia && !resueltos[1]){
             mesa=1;
             return true;
         }         
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == lime && !resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=2;
             return true;
         }
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == blue && !resueltos[3]){
             mesa=3;
             return true;
         }


         mouse.clickSwitch=false;
         for (int i = 0; i < key.numbers.length; i++) {
             key.numbers[i]=false;
         }
        
         return false;
     }
     
    @Override
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
        };
        
    
    
    }
}   