/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.level.tile.Tile;
import java.time.LocalTime;
import com.heraslegacy.manager.KeyBoard;

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
    protected int zoneColor[] ={0xff000000,0xff000000,0xff000000,0xff000000};//Los colores que diferencian cada zona
    protected int visualRange[] ={0xff000000,0xff000000,0xff000000,0xff000000};//Los colores de a que lugar están viendo
    protected int black = 0xff000000;//Este es la puerta de entrada
    protected int gray;//Por favor Dilan, este es la  puerta de salida
    protected int green;//Por favor Dilan, este es donde está el libro
    private int zone;
    private int direction=0;
    private LocalTime ant= LocalTime.now();
    private Player player;
    //IA
    //BOnito

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;
        if (tiles[x + y * width] == black)     return Tile.puertaE[zone];
        if (tiles[x + y * width] == gray)      return Tile.puertaS[zone];
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getCollision(int x, int y) {
        if (tilesCollision[(x>>4)+(y>>4)*width] == green){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
    }

    @Override
    public void restar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
