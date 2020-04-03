
package com.Domain.prototype.level;


import static com.Domain.prototype.Game.scale;
import static com.Domain.prototype.Game.screen;
import static com.Domain.prototype.graphics.Sprite.hoja;
import static com.Domain.prototype.graphics.Sprite.lapiz;
import static com.Domain.prototype.graphics.Sprite.shit;
import com.Domain.prototype.input.Mouse;
import com.Domain.prototype.level.tile.Tile;


public class Level01 extends Level {
    private int mesa=0;
    private Mouse p;
    int i=0;
    
    
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
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == yellow){
             mesa=0;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == blue){
             mesa=1;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == fuchsia){
             mesa=2;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == lime){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=3;
             return true;
         }
         
         return false;
     }
     
    @Override
    public void mecanica() {
        screen.renderSprite(false,screen.width/2-hoja[0].getWidth()/2,screen.height/2-hoja[0].getHeight()/2, hoja[mesa]);
        //screen.renderSprite(false, 0, 0, shit);
        System.out.println(hoja[mesa].getWidth());
        
        
//        if((p.mouseX>screen.width*scale-hoja[mesa].getWidth()*scale&&p.mouseX<screen.width*scale-((hoja[mesa].getWidth()+22)*scale)/2)
//         &&(p.mouseY>245&&p.mouseY<432)){
            screen.renderSprite(false,p.mouseX/scale,(p.mouseY/scale)-6,lapiz);
            
            if(p.click){
                int x = (int)Math.floor(p.mouseX/scale);
                int y = (int)Math.floor(p.mouseY/scale);
               
                i++;
                //System.out.println(p.mouseX+"     ||      "+p.mouseY);
                shit.pixels[i]=0;
            }
//        }
        
        
//        if((pincel.getMouseX()>452&&pincel.getMouseX()<639)&&(pincel.getMouseY()>245&&pincel.getMouseY()<432)){
//            System.out.println(pincel.getMouseX()+"     ||      "+pincel.getMouseY());
//            screen.renderSprite(false,pincel.getMouseX()/3,pincel.getMouseY()/3,lapiz);
//            
//            if(pincel.getMouseP()>0){
//                System.out.println(pincel.getMouseX()+"     ||      "+pincel.getMouseY());
//                lapiz.pixels[0]=0;
//            }
//        }
    }
}   