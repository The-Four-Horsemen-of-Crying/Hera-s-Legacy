/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import com.heraslegacy.graphics.Fuente;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.main.Game;
import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.KeyBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jony1
 */
public class Lobby implements levelStrategy{
    private int width;
    private int height;
    private int nivelCase;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private Font lobbyFont = Fuente.spaceFont;
    private final Color COLORTEXTO= Color.WHITE;
    int ani[]={0,0};
    private boolean [] boolSounds={false,false};
    private boolean changeLevel=false, agradecimiento=false;
    private Sound sounds[] = {new Sound(Sound.lobby_Theme), new Sound(Sound.lobby_portalSound), new Sound(Sound.buttonAlert_0)};
    public static boolean levels[]= {false,false,false};
    private Texto textLobby[]= {
        new Texto("Ya se encuentra en el Lobby", 0, false, Sprite.hera_down[0])
    
    };
    
    @Override
    public void update() {
        
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;
        
        if (tiles[x + y * width] == Colors.lime.getColor())            return Tile.specialMarmolFloor;
        if (tiles[x + y * width] == Colors.blue.getColor())            return Tile.pikes;
        if (tiles[x + y * width] == Colors.red.getColor())             return Tile.columnas[1];      
        if (tiles[x + y * width] == Colors.kindblue.getColor())        return Tile.columnas[2];
        if (tiles[x + y * width] == Colors.fuchsia.getColor())         return Tile.marmolFloor[0];     
        if (tiles[x + y * width] == Colors.yellow.getColor())          return Tile.marmolWall[0];
        if (tiles[x + y * width] == Colors.kindColdplay.getColor())    return Tile.marmolFloor[1];
        if (tiles[x + y * width] == Colors.kindgreenday.getColor())    return Tile.marmolWall[1];
        if (tiles[x + y * width] == Colors.purplePoe.getColor())       return Tile.marmolWall[2];
        if (tiles[x + y * width] == Colors.naranjaMecanica.getColor()) return Tile.marmolFloor[3];
        if (tiles[x + y * width] == Colors.kindblue2.getColor())       return Tile.techo;
        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y) {
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.naranjaMecanica.getColor()){
            agradecimiento=true;
            return true;
        }
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.lime.getColor()) {
            nivelCase = 0;
            return !levels[0];
        }
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.blue.getColor()) {
            nivelCase = 1;
            return !levels[1];
        }
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.red.getColor()) {
            nivelCase = 2;
            return !levels[2];
        }
        
        
        agradecimiento=false;
        boolSounds[0]=false;
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
            image.getRGB(0, 0, w, h, tiles, 0, w);
            imageCollision.getRGB(0, 0, w, h, tilesCollision, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }

    @Override
    public void time() {
       
    }

    @Override
    public void mecanica(){
        if(!agradecimiento){
            if(!boolSounds[0]){
                sounds[2].play();
                boolSounds[0]=true;
            }
            if(KeyBoard.enter)changeLevel=true;
        }
        else screen.renderSprite(false,screen.width/2-75, screen.height/2-50, Sprite.agradecimiento[1]);
    }
    

    @Override
    public void restar() {
        
    }

    @Override
    public boolean cambio() {
        
        return changeLevel;
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(15*16, 6*16);
        player.setSprites(Sprite.hera_up, Sprite.hera_down, Sprite.hera_rigth, Sprite.hera_left);
        player.setAjustes(14, 8, 13, 0, 16, 16, new Sound(Sound.walk));
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
        return textLobby;
    }

    @Override
    public void setText(String c) {
        
    }

    @Override
    public Color getColor() {
        return COLORTEXTO;
    }
   

    @Override
    public Level levelCambio() {
        Level game = new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
        sounds[0].stop();
        game = (new Level("/levels/fantasma/nivelFantasma.png","/levels/fantasma/nivelFantasma.png",new Fantasma(nivelCase)));
        return game;
    }

    @Override
    public Font getFont() {
        return this.lobbyFont;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll){
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4);
        int x1 = (xScroll + screen.width+16) >> 4;
        int y0 = (yScroll >> 4);
        int y1 = (yScroll + screen.height+16) >> 4;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                if(getTile(x,y)==Tile.columnas[2])Tile.columnas[0].render(x, y);
            }
        }
    }

    @Override
    public void render() {
            ani[1]++;
            if(ani[1]%160==0){
                ani[0]++;
                if(ani[1]==240)ani[1]=0;
            }
                screen.renderSprite(true, 7*16, 10*16, Sprite.portales[0][ani[0]&2]);
                screen.renderSprite(true, 14*16, 10*16, Sprite.portales[1][ani[0]&2]);
                screen.renderSprite(true, 21*16, 10*16, Sprite.portales[2][ani[0]&2]);
                screen.renderSprite(true,14*16, 1*16, Sprite.agradecimiento[0]);
    }

    @Override
    public void backWithoutWin() {
        textLobby[0].setVisible(true);
    }

    @Override
    public void uptadeTexto() {
        for (Texto text : textLobby) {
            text.showIfActive();
        }
    }
}
