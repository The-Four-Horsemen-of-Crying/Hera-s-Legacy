
package com.Domain.prototype.level;

import com.Domain.prototype.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Level01 extends Level{
    
    public Level01(String path) {
        super(path);
    }
    
     protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(Level01.class.getResource(path));
            int w=width= image.getWidth();
            int h =height= image.getHeight();
            //tiles = new Tile[w*h];
            tiles=new int[w*h];
            
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar level file!");
        }
    }
     
//     protected void generateLevel(){
//         for (int i = 0; i < levelPixels.length; i++) {
//             if(levelPixels[i]==0xff00ff44)tiles [i]= Tile.pikes;
//             if(levelPixels[i]==0xffff00ff)tiles [i]= Tile.floor;
//         }
//     }
}
