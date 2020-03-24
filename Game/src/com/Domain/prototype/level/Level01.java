
package com.Domain.prototype.level;

import static com.Domain.prototype.Game.screen;
import static com.Domain.prototype.graphics.Sprite.hoja;
import com.Domain.prototype.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Level01 extends Level {
    private int mesa=0;
    public Level01(String path, String pathCollision) {

        super(path, pathCollision);

    }

    @Override
    protected void loadLevel(String path, String pathCollision) {
        try {
            BufferedImage image = ImageIO.read(Level01.class.getResource(path));
            BufferedImage imageCollision = ImageIO.read(Level01.class.getResource(pathCollision));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            tilesCollision= new int[w*h];

            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar level file!");
        }
    }
    
    @Override
     public Tile getTile(int x, int y){
        
        if(x<0 || y<0||x>=width||y>=height) return Tile.pikes;
        //System.out.println(x+"  ||  "+y+"   ||  "+width+"   ||  "+height);    Imprimase para mayor entendimiento del recorrido del level1.png
        
        if (tiles[x + y * width] == 0xffff0000)return Tile.floor;
        if (tiles[x + y * width] == 0xffff00ff)return Tile.wall;//ff00ff
        
        if (tiles[x + y * width] == 0xff00ff00  &&    tiles[x+1+y*width] == 0xff0000ff)return Tile.mesa;
        if (tiles[x + y * width] == 0xff0000ff  &&    tiles[x-1+y*width] == 0xff00ff00)return Tile.mesa2;
        if (tiles[x + y * width] == 0xffffff00  &&    tiles[x+1+y*width] == 0xffffffff)return Tile.mesa3;
        if (tiles[x + y * width] == 0xffffffff  &&    tiles[x-1+y*width] == 0xffffff00)return Tile.mesa4;
        
        if (tiles[x + y * width] == 0xff0000ff  &&    tiles[x+1+y*width] == 0xff00ff00)return Tile.silla;
        if (tiles[x + y * width] == 0xff00ff00  &&    tiles[x-1+y*width] == 0xff0000ff)return Tile.silla2;   
        if (tiles[x + y * width] == 0xffffffff  &&    tiles[x+1+y*width] == 0xffffff00)return Tile.silla3;
        if (tiles[x + y * width] == 0xffffff00  &&    tiles[x-1+y*width] == 0xffffffff)return Tile.silla4;
        
        return Tile.pikes;
     }
     
    @Override
     public boolean getCollision(int x, int y){
         //if(x<0 || y<0||x>=width||y>=height)return false;
         
         if (tilesCollision[(x>>4)+(y>>4)*width] == 0xffffff00){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=0;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == 0xff0000ff){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=1;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == 0xffff00ff){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=2;
             return true;
         }
         if (tilesCollision[(x>>4)+(y>>4)*width] == 0xffffff00){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
             mesa=3;
             return true;
         }
         
         return false;
     }
     
    @Override
    public void mecanica(int x, int y) {
        screen.renderSprite(true,x,y, hoja[0]);
        
    }
}   