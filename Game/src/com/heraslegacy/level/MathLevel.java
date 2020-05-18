
package com.heraslegacy.level;
import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.graphics.Sprite;
import static com.heraslegacy.graphics.Sprite.hoja;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.main.Game;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class MathLevel implements levelStrategy {
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private int mesa=0;
    private Random r = new Random();
    private final int respuestas[] = {0,0, 1, 4, 0,0, 1, 4, 0,0, 1, 4, 0};
    private int [] ejercicios = {0,0,0,0};
    private boolean resueltos[] = {false, false, false, false};
    boolean boo;
    int indiceMesa;
    private Player player;
    private Texto textMath[]= { new Texto("Click para responder", screen.width/2+10, screen.height/2+5, false), new Texto("Introduce tu respuesta", screen.width/2+10, screen.height/2+5, false),
    new Texto("", screen.width/2+10, screen.height/2+5, false)
    };
    private final Color colorTexto= Color.WHITE;

    
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
            
            mesa = ejercicios[0];
            indiceMesa=0;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.fuchsia.getColor() && !this.resueltos[1]){
            
            mesa = ejercicios[1];
            indiceMesa=1;
            return true;
        }         

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.lime.getColor() && !this.resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
            mesa = ejercicios[2];
            indiceMesa = 2;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.blue.getColor() && !this.resueltos[3]){
            mesa = ejercicios[3];
            indiceMesa = 3;
            return true;
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
            uso();
            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }
    
    @Override
    public void mecanica() {
        
        screen.renderSprite(false, screen.width / 2 - hoja[1].getWidth() / 2, screen.height / 2 - hoja[1].getHeight() / 2,
                hoja[mesa]);

        if((Mouse.mouseX > screen.width  * scale / 2) && 
                (Mouse.mouseX < screen.width  * scale / 2 + (hoja[mesa].getWidth() - 22) * scale / 2 ) &&
                (Mouse.mouseY > screen.height * scale / 2) && 
                (Mouse.mouseY < screen.height * scale / 2 + (hoja[mesa].getHeight()- 22) * scale / 2 )){
            
            if(Mouse.clickSwitch){
                        textMath[0].setVisible(false);
                        textMath[1].setVisible(true);
                        
                        textMath[2].setVisible(true);
                        textMath[2].setText(textMath[2].getText()+numberInput());
                        //read answer
                        if(KeyBoard.enter&&Integer.parseInt(textMath[2].getText()) == respuestas[mesa]){
                            System.out.println("Respuesta correcta.");
                            resueltos[indiceMesa] = true;
                            
                        }
                        else if(KeyBoard.enter&&Integer.parseInt(textMath[2].getText())!=respuestas[mesa]){
                            System.out.println("Respuesta incorrecta.");
                            
                        }
                    }
            else{
                textMath[1].setVisible(false);
                textMath[0].setVisible(true);
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
        player = new Player(Game.width / 2, Game.height / 2);
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
    public Texto[] getText() {
        return textMath;
    }

    @Override
    public void setText(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return colorTexto;
    }

    @Override
    public Level levelCambio() {
        Lobby.levels[1]=true;
        return new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
    }
    private void uso(){
            for (int i = 0; i < ejercicios.length; i++) {
            int random = r.nextInt(10)+1;
            if(!nonRepeated(random)){
                ejercicios[i]=random;
                System.out.println(random);
            
            }    
        }
    
    }
    private boolean nonRepeated(int i){
        for (int j = 0; j < ejercicios.length; j++) {
            if(ejercicios[j]==i)return true;
        }
        return false;
    }

    private String numberInput() {
        if(KeyBoard.one)return "1";
        if(KeyBoard.doix){
            System.out.println("0");
            return "2";
        }
        if(KeyBoard.trois)return "3";
        if(KeyBoard.quatre)return "4";
        if(KeyBoard.cinq)return "5";
        if(KeyBoard.six)return "6";
        if(KeyBoard.sept)return "7";
        if(KeyBoard.huit)return "8";
        if(KeyBoard.neuf)return "9";
        if(KeyBoard.zero)return "0";
        
        return "";
    }
}   