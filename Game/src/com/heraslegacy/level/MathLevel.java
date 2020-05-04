
package com.heraslegacy.level;


import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import static com.heraslegacy.graphics.Sprite.hoja;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class MathLevel implements levelStrategy {
    protected int width;
    protected int height;
    protected int[] tiles; 
    protected int[] tilesCollision;
    protected int red = 0xffff0000;
    protected int fuchsia = 0xffff00ff;
    protected int lime = 0xff00ff00 ;
    protected int blue = 0xff0000ff;
    protected int yellow = 0xffffff00;
    protected int white = 0xffffffff;
    private int mesa=0;
    private final int respuestas[] = {0, 1, 4, 0};
    private boolean resueltos[] = {false, false, false, false};
    private Mouse mouse;
    private KeyBoard key;
    private int numeroAnterior;
    boolean boo;
    
    @Override
    public void update(){
        
    }
    
    @Override
    public Tile getTile(int x, int y){

        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;

        if (tiles[x + y * width] == red)     return Tile.woodFloor;
        if (tiles[x + y * width] == fuchsia) return Tile.woodWall;

        if (tiles[x + y * width] == lime   && tiles[x+1+y*width] == blue)   return Tile.mesa[0];
        if (tiles[x + y * width] == blue   && tiles[x-1+y*width] == lime)   return Tile.mesa[1];
        if (tiles[x + y * width] == yellow && tiles[x+1+y*width] == white ) return Tile.mesa[2];
        if (tiles[x + y * width] == white  && tiles[x-1+y*width] == yellow) return Tile.mesa[3];

        if (tiles[x + y * width] == blue   && tiles[x+1+y*width] == lime)   return Tile.silla[0];
        if (tiles[x + y * width] == lime   && tiles[x-1+y*width] == blue)   return Tile.silla[1];   
        if (tiles[x + y * width] == white  && tiles[x+1+y*width] == yellow) return Tile.silla[2];
        if (tiles[x + y * width] == yellow && tiles[x-1+y*width] == white)  return Tile.silla[3];

        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y){

        if (tilesCollision[(x>>4)+(y>>4)*width] == yellow && !this.resueltos[0]){
            mesa = 0;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == fuchsia && !this.resueltos[1]){
            mesa = 1;
            return true;
        }         

        if (tilesCollision[(x>>4)+(y>>4)*width] == lime && !this.resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
            mesa = 2;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == blue && !this.resueltos[3]){
            mesa = 3;
            return true;
        }


        mouse.clickSwitch=false;
        for (int i = 0; i < key.numbers.length; i++) {
            key.numbers[i] = false;
        }

        return false;
     }
     
    @Override
    public void loadLevel(String path, String pathCollision) {
        try {
            BufferedImage image = ImageIO.read(MathLevel.class.getResource(path));
            BufferedImage imageCollision = ImageIO.read(MathLevel.class.getResource(pathCollision));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            tilesCollision = new int[w * h];

            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }
    
    @Override
    public void mecanica() {
        int thisNum = 0;
        screen.renderSprite(false, screen.width / 2 - hoja[0].getWidth() / 2, screen.height / 2 - hoja[0].getHeight() / 2,
                hoja[mesa]);

        if((mouse.mouseX > screen.width  * scale / 2) && 
                (mouse.mouseX < screen.width  * scale / 2 + (hoja[mesa].getWidth() - 22) * scale / 2 ) &&
                (mouse.mouseY > screen.height * scale / 2) && 
                (mouse.mouseY < screen.height * scale / 2 + (hoja[mesa].getHeight()- 22) * scale / 2 )){
            
            if(mouse.clickSwitch){
                while(thisNum < key.numbers.length){
                    if(key.numbers[thisNum]){
                        if(numeroAnterior != thisNum)key.numbers[numeroAnterior] = false;                        
                        screen.renderSprite(false, screen.width / 2 + Sprite.fonts01[thisNum].getWidth() / 2,
                                screen.height / 2 + Sprite.fonts01[thisNum].getHeight() / 2, Sprite.fonts01[thisNum]);  
                        numeroAnterior = thisNum;

                        //read answer
                        if(key.enter&&thisNum == respuestas[mesa]){
                            System.out.println("Respuesta correcta.");
                            resueltos[mesa] = true;
                            key.numbers[thisNum] = false;
                        }
                        else if(key.enter&&thisNum!=respuestas[mesa]){
                            System.out.println("Respuesta incorrecta.");
                            key.numbers[thisNum] = false;
                        }
                    }
                    thisNum++;
                }                
            }
            else screen.renderSprite(false, screen.width / 2 - 2, screen.height / 2 + Sprite.fonts01[thisNum].getHeight() / 2,
                    Sprite.frasesLvl01[0]);
        }
        else {
            mouse.clickSwitch = false;
            for (int i = 0; i < key.numbers.length; i++) {
                key.numbers[i] = false;
            }
        }
        
    }
    @Override
    public void time(){
            
    }
        
    @Override
    public void restar(){
            
    }
    
    public boolean cambio(){
        boo = true;
        for (boolean re : resueltos) {
            boo = boo && re;
        }
        return boo;
    }
}   