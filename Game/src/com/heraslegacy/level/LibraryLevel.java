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
import java.awt.Color;
import java.awt.Font;
import java.time.LocalTime;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ejose
 */
public class LibraryLevel implements levelStrategy{
    //escenario
    //personaje
    //mecanica
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private Font LibraryFont = Fuente.spaceFont;
    private boolean libros[] ={false, false, false, false};
    private final int zoneColor[] ={Colors.yellow.getColor(),Colors.blue.getColor(),Colors.white.getColor(),Colors.darkred.getColor()};//Los colores que diferencian cada zona
    private final int visualRange[] ={Colors.orange.getColor(),Colors.lessdarkred.getColor(),Colors.clearblue.getColor(),Colors.green.getColor()};//Los colores de a que lugar están viendo
    private int zone;
    private int pattern[]={0,1,2,3},direction=0;
    private LocalTime ant= LocalTime.now();
    private Player player;
    public Sound fail = new Sound(Sound.fail);
    private final Color colorTexto= Color.WHITE;
    private Texto textLibrary[]= {
    
    };

    @Override
    public void update() {
        
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)                 return Tile.pikes;
        if (tiles[x + y * width] == Colors.yellow.getColor())           return Tile.puertaS[zone];
        if (tiles[x + y * width] == Colors.blue.getColor())             return Tile.puertaE[zone];
        if (tiles[x + y * width] == Colors.fuchsia.getColor())          return Tile.paredLibrary;
        if (tiles[x + y * width] == Colors.white.getColor())            return Tile.sueloLibrary;
        if (tiles[x + y * width] == Colors.red.getColor())              return Tile.estanterias[0];
        if (tiles[x + y * width] == Colors.higdarkred.getColor())       return Tile.estanterias[1];
        if (tiles[x + y * width] == Colors.darkred.getColor())          return Tile.estanterias[2];
        if (tiles[x + y * width] == Colors.lessdarkred.getColor())      return Tile.estanterias[3];
        //if (tiles[x + y * width] == Colors.green.getColor())            return Tile.libro;
        for (int i = 0; i < 4; i++) {
            if (tiles[x + y * width] == Colors.black.getColor()
              &&tilesCollision[x + (y-1) * width] == visualRange[i])    return Tile.estanterias[pattern[i]];
        }
        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y) {
        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.fuchsia.getColor()){
            libros[zone]=true;
            tilesCollision[(x>>4)+(y>>4)*width] = zoneColor[zone];
            Tile.puertaS[zone].setSolid(false);
        }
        
        for (int i = 0; i < 4; i++) {
            if (tilesCollision[(x>>4)+(y>>4)*width] == zoneColor[i]){
                zone=i;
                if(Tile.puertaE[zone].solid==false && libros[zone]==true) Tile.puertaE[zone].setSolid(true);
                System.out.println(zone);
                return false;
            }
        }
        
        return true;
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
    public void time() {
        LocalTime rest = LocalTime.now().minusSeconds(ant.getSecond());
        if(rest.getSecond()>=10){
            ant=LocalTime.now();
            direction++;
            
            if(direction==4) direction=0;
            
            for (int i = 0; i < 4; i++) {
                pattern[i]++;
                if(pattern[i]==4) pattern[i]=0;
            }
        }
    }

    @Override
    public void mecanica() {
        if(Tile.puertaS[zone].solid==false){ Tile.puertaS[zone].setSolid(true);}
        time();
        if (tilesCollision[(player.getX()>>4)+(player.getY()>>4)*width] == visualRange[direction]){
            fail.play();
            restar();
        }
    }

    @Override
    public void restar() {
        for (int i = 0; i < 4; i++) {
            libros[i]=false;
            Tile.puertaE[i].setSolid(false);
            pattern[i]=i;
        }
        direction=0;
        player.setX(25);
        player.setY(400);
        loadLevel("/levels/level03/nivel3.png","/levels/level03/nivel3COLLITION.png");
    }

    @Override
    public boolean cambio() {
        return false;
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(25, 400);
        player.setSprites(Sprite.Elizabeth_up, Sprite.Elizabeth_down, Sprite.Elizabeth_rigth, Sprite.Elizabeth_left);
        player.setAjustes(14, 8, 12, 3, 16 ,16, new Sound(Sound.walk));
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
        return textLibrary;
    }

    @Override
    public void setText(String c) {
        
    }

    @Override
    public Color getColor() {
        return colorTexto;
    }

    @Override
    public Level levelCambio() {
        Lobby.levels[3]=true;
        return new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
    }

    @Override
    public Font getFont() {
        return this.LibraryFont;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
    }

    @Override
    public void render() {
        
    }

    @Override
    public void backWithoutWin() {
        //Hacer condicion de volver true;
    }
    
}
