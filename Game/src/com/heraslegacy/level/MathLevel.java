
package com.heraslegacy.level;
import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import com.heraslegacy.graphics.Fuente;
import com.heraslegacy.graphics.Sound;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.graphics.Sprite;
import static com.heraslegacy.graphics.Sprite.hoja;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import static com.heraslegacy.main.Game.SCALE;


public class MathLevel implements levelStrategy {
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private Font mathLevelFont= Fuente.spaceFont;
    private final Color colorTexto= Color.BLACK;
    private Player player;
    private Random r = new Random();
    private boolean [] bools=new boolean[4];
    private final float RATE_MESSAGE = 3;
    private float showMessage=-1; 
    private int answerLength = 0, indiceMesa, mesa=0;
    private Sound sounds [] = {new Sound(Sound.math_Theme),new Sound(Sound.bookSound)};
    private final float respuestas[] = {0,6, 32, 6.28f, 22,28, 0, 1, 3.14f,4};
    private int [] ejercicios = {0,0,0,0};
    private boolean resueltos[] = {false, false, false, false};
    
    private Texto textMath[]= {
        new Texto("Click", screen.width/2*SCALE+45, screen.height/2*SCALE+70, false), 
        new Texto("Introduce", screen.width/2*SCALE+3, screen.height/2*SCALE+30, false),
        new Texto("Respuesta", screen.width/2*SCALE+3, screen.height/2*SCALE+70, false),
        new Texto("", screen.width/2*SCALE+100, screen.height/2*SCALE+100, false),
        new Texto("Carlitos, Estás haciendo esa vaina mal", 0, false,Sprite.hera_down[0]),
        new Texto("Excelente Carlitos, sigue así!", 0, false,Sprite.hera_down[0]),
        new Texto("GGWP", 0, false,Sprite.hera_down[0]),
        new Texto("Intenta con otra respuesta", 0, false,Sprite.hera_down[0])
    };
    

    
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
        if(System.nanoTime()/1000000000>showMessage)takeOffReponseMessages();
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

        for (int i = 0; i <= 4; i++) {
            textMath[i].setVisible(false);
        }
//        for (Texto T : textMath ) {
//            T.setVisible(false);
//            
//        }
        bools[3]=false;
        condicionesIni();
        Mouse.clickSwitch=false;
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
            sounds[0].loop();
            sounds[0].changeVolume(0);
            addEjercicios();
            Mouse.clickSwitch=false;
            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }
    
    @Override
    public void mecanica() {
        changeFont(2);
        if(!bools[3]){
            sounds[1].play();
            bools[3]=true;
        }
        screen.renderSprite(false, screen.width / 2 - hoja[1].getWidth() / 2, screen.height / 2 - hoja[1].getHeight() / 2,
                hoja[mesa]);

        if ((Mouse.mouseX > screen.width * SCALE / 2)
                && (Mouse.mouseX < screen.width * SCALE / 2 + (hoja[mesa].getWidth() - 22) * SCALE / 2)
                && (Mouse.mouseY > screen.height * SCALE / 2)
                && (Mouse.mouseY < screen.height * SCALE / 2 + (hoja[mesa].getHeight() - 22) * SCALE / 2)) {

            if (Mouse.clickSwitch) {
                textMath[0].setVisible(false);
                textMath[1].setVisible(true);
                textMath[2].setVisible(true);
                textMath[3].setVisible(true);
                
                if (KeyBoard.rate == 2 && answerLength < 7) {
                    String concaAnsw = numberInput();
                    textMath[3].setText(textMath[3].getText() + concaAnsw);
                    if (!concaAnsw.isEmpty()) {
                        textMath[3].setPosx(textMath[3].getPosx() - 9);
                        answerLength++;
                    }
                } else if (answerLength >= 7) {
                    textMath[4].setVisible(true);
                }
                
                if(KeyBoard.rate==Integer.MIN_VALUE)KeyBoard.rate=0;
                if (KeyBoard.delete) {
                    condicionesIni();
                }
                //read answer
                if (KeyBoard.enter) {
                    String reponse = textMath[3].getText();
                    if (!reponse.isEmpty()) {
                        float f_reponse = Float.parseFloat(reponse);
                        
                        if (f_reponse == respuestas[mesa]) {
                            showReponse(r.nextInt(2)+5);
                            resueltos[indiceMesa] = true;
                            changeFont(1);
                            condicionesIni();
                        } 
                        
                        else if (f_reponse != respuestas[mesa]) {
                            showReponse(6);
                            condicionesIni();
                        }
                    }
                }
            }
            else{
                textMath[1].setVisible(false);
                textMath[2].setVisible(false);
                textMath[0].setVisible(true);
                condicionesIni();
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
        for (boolean re : resueltos) {
            if(!re)return false || bools[2];
        }
        bools[1]=true;
        return true;
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
        player.setSprites(Sprite.katherine_up, Sprite.katherine_down, Sprite.katherine_rigth, Sprite.katherine_left);
        player.setAjustes(14, 8, 12, 3,16,16, new Sound(Sound.walk));
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return colorTexto;
    }

    @Override
    public Level levelCambio() {
        sounds[0].stop();
        if(bools[1])Lobby.levels[1]=true;
        return new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
    }
    private void addEjercicios(){
            for (int i = 0; i < ejercicios.length; i++) {
            int random = r.nextInt(9)+1;
            if(!nonRepeated(random)){
                ejercicios[i]=random;
            }
            else i--;
        }
    
    }
    private boolean nonRepeated(int i){
        for (int j = 0; j < ejercicios.length; j++) {
            if(ejercicios[j]==i)return true;
        }
        return false;
    }

    private String numberInput() {
        KeyBoard.rate--;
            if (KeyBoard.numbers[1])return "1";
            if (KeyBoard.numbers[2])return "2";           
            if (KeyBoard.numbers[3])return "3";            
            if (KeyBoard.numbers[4])return "4";
            if (KeyBoard.numbers[5])return "5";
            if (KeyBoard.numbers[6])return "6";
            if (KeyBoard.numbers[7])return "7";
            if (KeyBoard.numbers[8])return "8";
            if (KeyBoard.numbers[9])return "9";          
            if (KeyBoard.numbers[0])return "0";
            if (KeyBoard.coma&&!bools[0]&&!textMath[3].getText().isEmpty()){
                answerLength--;
                bools[0]=true;
                return ".";
            }
        return "";
    }

    @Override
    public Font getFont() {
        return this.mathLevelFont;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
    }

    @Override
    public void render() {
        
    }
    
    private void condicionesIni() {
        textMath[3].setText("");
        textMath[3].setPosx(screen.width / 2 * SCALE + 100);
        answerLength = 1;
        textMath[4].setVisible(false);
        bools[0] = false;
    }

    private void showReponse(int i) {
            textMath[i].setVisible(true);
            showMessage=RATE_MESSAGE+System.nanoTime()/1000000000;
    }

    private void takeOffReponseMessages() {
        for (int i = 5; i < textMath.length; i++) {
            textMath[i].setVisible(false);
        }
    }

    @Override
    public void backWithoutWin() {
        bools[2]=true;
    }

    @Override
    public void uptadeTexto() {
        for (Texto text : textMath) {
            text.showIfActive();
        } 
    }
    public void changeFont(int i){
        if(i==1)mathLevelFont=Fuente.spaceFontSmaller;
        else mathLevelFont= Fuente.spaceFont; 
    }
}   