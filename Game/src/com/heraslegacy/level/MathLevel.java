
package com.heraslegacy.level;
import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.graphics.Sprite;
import static com.heraslegacy.graphics.Sprite.hoja;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.tile.Tile;
import static com.heraslegacy.main.Game.height;
import static com.heraslegacy.main.Game.width;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class MathLevel implements levelStrategy {
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private int mesa=0;
    private final int respuestas[] = {0, 1, 4, 0};
    private boolean resueltos[] = {false, false, false, false};
    private Mouse mouse;
    private KeyBoard key;
    private int numeroAnterior;
    boolean boo;
    private Player player;
    
    @Override
    public void update(){   
    }
    
    @Override
    public Tile getTile(int x, int y){

        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;

        if (tiles[x + y * width] == Colors.red.getColor())     return Tile.woodFloor;
        if (tiles[x + y * width] == Colors.fuchsia.getColor()) return Tile.woodWall;

        if (tiles[x + y * width] == Colors.lime.getColor()   && tiles[x+1+y*width] == Colors.blue.getColor())   return Tile.mesa[0];
        if (tiles[x + y * width] == Colors.blue.getColor()   && tiles[x-1+y*width] == Colors.lime.getColor())   return Tile.mesa[1];
        if (tiles[x + y * width] == Colors.yellow.getColor() && tiles[x+1+y*width] == Colors.white.getColor()) return Tile.mesa[2];
        if (tiles[x + y * width] == Colors.white.getColor()  && tiles[x-1+y*width] == Colors.yellow.getColor()) return Tile.mesa[3];

        if (tiles[x + y * width] == Colors.blue.getColor()   && tiles[x+1+y*width] == Colors.lime.getColor())   return Tile.silla[0];
        if (tiles[x + y * width] == Colors.lime.getColor()   && tiles[x-1+y*width] == Colors.blue.getColor())   return Tile.silla[1];   
        if (tiles[x + y * width] == Colors.white.getColor()  && tiles[x+1+y*width] == Colors.yellow.getColor()) return Tile.silla[2];
        if (tiles[x + y * width] == Colors.yellow.getColor() && tiles[x-1+y*width] == Colors.white.getColor())  return Tile.silla[3];

        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y){

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.yellow.getColor() && !this.resueltos[0]){
            mesa = 0;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.fuchsia.getColor() && !this.resueltos[1]){
            mesa = 1;
            return true;
        }         

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.lime.getColor() && !this.resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
            mesa = 2;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.blue.getColor() && !this.resueltos[3]){
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

        if((Mouse.mouseX > screen.width  * scale / 2) && 
                (Mouse.mouseX < screen.width  * scale / 2 + (hoja[mesa].getWidth() - 22) * scale / 2 ) &&
                (Mouse.mouseY > screen.height * scale / 2) && 
                (Mouse.mouseY < screen.height * scale / 2 + (hoja[mesa].getHeight()- 22) * scale / 2 )){
            
            if(Mouse.clickSwitch){
                while(thisNum < KeyBoard.numbers.length){
                    if(KeyBoard.numbers[thisNum]){
                        if(numeroAnterior != thisNum)KeyBoard.numbers[numeroAnterior] = false;                        
                        screen.renderSprite(false, screen.width / 2 + Sprite.fonts01[thisNum].getWidth() / 2,
                                screen.height / 2 + Sprite.fonts01[thisNum].getHeight() / 2, Sprite.fonts01[thisNum]);  
                        numeroAnterior = thisNum;

                        //read answer
                        if(KeyBoard.enter&&thisNum == respuestas[mesa]){
                            System.out.println("Respuesta correcta.");
                            resueltos[mesa] = true;
                            KeyBoard.numbers[thisNum] = false;
                        }
                        else if(KeyBoard.enter&&thisNum!=respuestas[mesa]){
                            System.out.println("Respuesta incorrecta.");
                            KeyBoard.numbers[thisNum] = false;
                        }
                    }
                    thisNum++;
                }                
            }
            else screen.renderSprite(false, screen.width / 2 - 2, screen.height / 2 + Sprite.fonts01[thisNum].getHeight() / 2,
                    Sprite.frasesLvl01[0]);
        }
        else {
            Mouse.clickSwitch = false;
            for (int i = 0; i < KeyBoard.numbers.length; i++) {
                KeyBoard.numbers[i] = false;
            }
        }
        
    }
    @Override
    public void time(){
            
    }
        
    @Override
    public void restar(){
            
    }
    
    @Override
    public boolean cambio(){
        boo = true;
        for (boolean re : resueltos) {
            boo = boo && re;
        }
        return boo;
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(width / 2, height / 2);
        player.setSprites(Sprite.Elizabeth_up, Sprite.Elizabeth_down, Sprite.Elizabeth_rigth, Sprite.Elizabeth_left);
        player.setAjustes(14, 8, 12, 3,16,16);
        player.setLatencia(30);
        player.setTipo(0);
        player.setLevel(level);
        
    }
    
    @Override
    public Player getPlayer(){
        return player;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void setText(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}   