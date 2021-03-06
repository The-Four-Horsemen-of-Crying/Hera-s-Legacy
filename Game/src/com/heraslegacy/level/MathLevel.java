
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
import static com.heraslegacy.main.Game.scale;


public class MathLevel implements levelStrategy {
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private final Font MATHLEVELFONT= Fuente.spaceFontSmaller;
    private final Color COLORTEXTO= Color.BLACK;
    private Player player;
    private Random r = new Random();
    private boolean [] bools=new boolean[5];
    private final float RATE_MESSAGE = 3;
    private float showMessage=-1; 
    private int answerLength = 0;
    private int indiceMesa, mesa=0;
    private final Sound SOUNDS [] = {new Sound(Sound.math_Theme),new Sound(Sound.bookSound)};
    private final float[] RESPUESTAS = {0,6, 32, 6.28f, 22,28, 0, 1, 3.14f,4};
    private int [] ejercicios = {0,0,0,0};
    private boolean resueltos[] = {false, false, false, false};
    private String concaAnsw;
    
    private final Texto TEXTMATH[]= {
        new Texto("Click", screen.width/2*scale+65, screen.height/2*scale+70, false), 
        new Texto("Introduce", screen.width/2*scale+40, screen.height/2*scale+30, false),
        new Texto("Respuesta", screen.width/2*scale+40, screen.height/2*scale+70, false),
        new Texto("", screen.width/2*scale+100, screen.height/2*scale+100, false),
        new Texto("Un calculo excepcional", 0, false,Sprite.hera_down[0]),
        new Texto("Wow! me sorprenden sus capacidades", 0, false,Sprite.hera_down[0]),
        new Texto("Muy buena su respuesta", 0, false,Sprite.hera_down[0]),
        new Texto("Creo que ella debería replantearlo", 0, false,Sprite.hera_down[0]),
        new Texto("Tal vez se confundio con un numero", 0, false,Sprite.hera_down[0])
    };
    
    public MathLevel(){
        concaAnsw = "";
        answerLength = 0;
        bools = new boolean[4];
        SOUNDS[0].changeVolume(0);
        SOUNDS[0].loop();

    }
    
    @Override
    public void update(){
    }
    
    @Override
    public Tile getTile(int x, int y){

        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;

        if (tiles[x + y * width] == Colors.RED.getColor())     return Tile.woodFloor;
        if (tiles[x + y * width] == Colors.FUCHSIA.getColor()) return Tile.woodWall;

        if (tiles[x + y * width] == Colors.LIME.getColor()   && tiles[x+1+y*width] == Colors.BLUE.getColor())   return Tile.mesa[0];
        if (tiles[x + y * width] == Colors.BLUE.getColor()   && tiles[x-1+y*width] == Colors.LIME.getColor())   return Tile.mesa[1];
        if (tiles[x + y * width] == Colors.YELLOW.getColor() && tiles[x+1+y*width] == Colors.WHITE.getColor()) return Tile.mesa[2];
        if (tiles[x + y * width] == Colors.WHITE.getColor()  && tiles[x-1+y*width] == Colors.YELLOW.getColor()) return Tile.mesa[3];

        if (tiles[x + y * width] == Colors.BLUE.getColor()   && tiles[x+1+y*width] == Colors.LIME.getColor())   return Tile.silla[0];
        if (tiles[x + y * width] == Colors.LIME.getColor()   && tiles[x-1+y*width] == Colors.BLUE.getColor())   return Tile.silla[1];   
        if (tiles[x + y * width] == Colors.WHITE.getColor()  && tiles[x+1+y*width] == Colors.YELLOW.getColor()) return Tile.silla[2];
        if (tiles[x + y * width] == Colors.YELLOW.getColor() && tiles[x-1+y*width] == Colors.WHITE.getColor())  return Tile.silla[3];

        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y){
        if(System.nanoTime()/1000000000>showMessage)takeOffReponseMessages();
        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.YELLOW.getColor() && !this.resueltos[0]){
            mesa = ejercicios[0];
            indiceMesa=0;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.FUCHSIA.getColor() && !this.resueltos[1]){
            mesa = ejercicios[1];
            indiceMesa=1;
            return true;
        }         

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.LIME.getColor() && !this.resueltos[2]){//System.out.println("es mesa"+x+" ||  "+y);te falta un punto y coma
            mesa = ejercicios[2];
            indiceMesa = 2;
            return true;
        }

        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.BLUE.getColor() && !this.resueltos[3]){
            mesa = ejercicios[3];
            indiceMesa = 3;
            return true;
        }

        for (int i = 0; i <= 4; i++) {
            TEXTMATH[i].setVisible(false);
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
        if(!bools[3]){
            SOUNDS[1].play();
            bools[3]=true;
        }
        screen.renderSprite(false, screen.width / 2 - hoja[1].getWidth() / 2, screen.height / 2 - hoja[1].getHeight() / 2,
                hoja[mesa]);

        if ((Mouse.mouseX > screen.width * scale / 2)
                && (Mouse.mouseX < screen.width * scale / 2 + (hoja[mesa].getWidth() - 22) * scale / 2)
                && (Mouse.mouseY > screen.height * scale / 2)
                && (Mouse.mouseY < screen.height * scale / 2 + (hoja[mesa].getHeight() - 22) * scale / 2)) {

            if (Mouse.clickSwitch) {
                TEXTMATH[0].setVisible(false);
                TEXTMATH[1].setVisible(true);
                TEXTMATH[2].setVisible(true);
                TEXTMATH[3].setVisible(true);
                
                if (KeyBoard.rate == 2 && answerLength < 7) {
                    numberInput();
                    TEXTMATH[3].setText(TEXTMATH[3].getText() + concaAnsw);
                    if (!concaAnsw.isEmpty()) {
                        concaAnsw="";
                        TEXTMATH[3].setPosx(TEXTMATH[3].getPosx() - 9);
                        answerLength++;
                    }
                } else if (answerLength >= 7) {
                    TEXTMATH[4].setVisible(true);
                }
                
                if(KeyBoard.rate==Integer.MIN_VALUE)KeyBoard.rate=0;
                if (KeyBoard.delete) {
                    condicionesIni();
                }
                //read answer
                if (KeyBoard.enter) {
                    String reponse = TEXTMATH[3].getText();
                    if (!reponse.isEmpty()) {
                        float f_reponse = Float.parseFloat(reponse);
                        
                        if (f_reponse == RESPUESTAS[mesa]) {
                            showReponse(r.nextInt(2)+5);
                            resueltos[indiceMesa] = true;
                            condicionesIni();
                        } 
                        
                        else if (f_reponse != RESPUESTAS[mesa]) {
                            showReponse(r.nextInt(2)+7);
                            condicionesIni();
                        }
                    }
                }
            }
            else{
                TEXTMATH[1].setVisible(false);
                TEXTMATH[2].setVisible(false);
                TEXTMATH[0].setVisible(true);
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
        return TEXTMATH;
    }

    @Override
    public void setText(String c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return COLORTEXTO;
    }

    @Override
    public Level levelCambio() {
        SOUNDS[0].stop();
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

    private void numberInput() {
        KeyBoard.rate--;
        concaAnsw = KeyBoard.getLastKeyNumber();
        System.out.println("conca   "+concaAnsw);
        
        if(concaAnsw.equals(".")&&(bools[0]||TEXTMATH[3].getText().isEmpty()))concaAnsw="";
        if (concaAnsw.equals(".")&&!bools[0]&&!TEXTMATH[3].getText().isEmpty()){
            answerLength--;
            bools[0]=true;
        }
    }


    @Override
    public Font getFont() {
        return this.MATHLEVELFONT;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
        for (int i = 0; i < numResueltos(); i++) {
            screen.renderSprite(true,(16*i) + xScroll, yScroll, Sprite.book);
        }
    }

    @Override
    public void render() {
        
    }
    
    private void condicionesIni() {
        TEXTMATH[3].setText("");
        TEXTMATH[3].setPosx(screen.width / 2 * scale + 100);
        answerLength = 1;
        concaAnsw = "";
        TEXTMATH[4].setVisible(false);
        bools[0] = false;
    }
    
    
    private void showReponse(int i) {
        takeOffReponseMessages();
        TEXTMATH[i].setVisible(true);
        showMessage = RATE_MESSAGE + System.nanoTime() / 1000000000;
    }

    private void takeOffReponseMessages() {
        for (int i = 5; i < TEXTMATH.length; i++) {
            TEXTMATH[i].setVisible(false);
        }
    }

    @Override
    public void backWithoutWin() {
        bools[2]=true;
    }

    @Override
    public void uptadeTexto() {
        for (Texto text : TEXTMATH) {
            text.showIfActive();
        } 
    }
    
    private int numResueltos(){
        int cont = 0;
        for (boolean resuelto : resueltos) {
            if(!resuelto) cont++;
        }
        return cont;
    } 

    @Override
    public void stopAll() {
        for (Sound sonido1 : SOUNDS) {
            sonido1.stop();
        }
    }
}   