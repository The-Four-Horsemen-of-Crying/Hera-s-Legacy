/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.level.tile.Tile;
import java.time.LocalTime;
import com.heraslegacy.manager.KeyBoard;
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
    private boolean libros[] ={false, false, false, false};
    protected int zoneColor[] ={Colors.yellow.getColor(),Colors.blue.getColor(),Colors.white.getColor(),Colors.red.getColor()};//Los colores que diferencian cada zona
    protected int visualRange[] ={Colors.yellow.getColor(),Colors.red.getColor(),Colors.kindblue2.getColor(),Colors.kindgreenday.getColor()};//Los colores de a que lugar están viendo
    protected int black = 0xff000000;//Este es la puerta de entrada
    protected int gray;//Por favor Dilan, este es la  puerta de salida
    private int zone;
    private int direction=0;
    private LocalTime ant= LocalTime.now();
    private Player player;
    public Sound fail = new Sound(Sound.fail); 

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)             return Tile.pikes;
        if (tiles[x + y * width] == Colors.black.getColor())        return Tile.puertaE[zone];
        if (tiles[x + y * width] == gray)                           return Tile.puertaS[zone];
        if (tiles[x + y * width] == Colors.fuchsia.getColor())      return Tile.paredLibrary;
        if (tiles[x + y * width] == Colors.white.getColor())        return Tile.sueloLibrary;
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
                if(!Tile.puertaE[zone].solid) Tile.puertaE[zone].setSolid(true);
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
        LocalTime rest = ant.minusMinutes(LocalTime.now().getSecond());
        if(rest.getSecond()>=10){
            ant=LocalTime.now();
            direction++;
            if(direction==4){
                direction=0;
            }
        }
    }

    @Override
    public void mecanica() {
        if(!Tile.puertaS[zone].solid) Tile.puertaS[zone].setSolid(true);
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
        }
        loadLevel("/levels/level03/nivel3.png","/levels/level03/nivel3COLLITION.png");
    }

    @Override
    public boolean cambio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configPlayer(int x, int y, KeyBoard input, Sprite[] up, Sprite[] down, Sprite[] rigth, Sprite[] left, boolean tipo,Level level) {
        player = new Player(x, y, input);
        player.setSprites(up, down, rigth, left);
        player.setTipo(tipo);
        player.setLevel(level);
    }
    
    @Override
    public Player getPlayer(){
        return player;
    }
    
}
