/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.manager.KeyBoard;
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
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;
        
        if (tiles[x + y * width] == Colors.lime.color())            return Tile.specialMarmolFloor;
        if (tiles[x + y * width] == Colors.blue.color())            return Tile.pikes;
        if (tiles[x + y * width] == Colors.red.color())             return Tile.columnas[1];      
        if (tiles[x + y * width] == Colors.kindblue.color())        return Tile.columnas[0];
        if (tiles[x + y * width] == Colors.fuchsia.color())         return Tile.marmolFloor[0];     
        if (tiles[x + y * width] == Colors.yellow.color())          return Tile.marmolWall[0];
        if (tiles[x + y * width] == Colors.kindColdplay.color())    return Tile.marmolFloor[1];
        if (tiles[x + y * width] == Colors.kindgreenday.color())    return Tile.marmolWall[1];
        if (tiles[x + y * width] == Colors.purplePoe.color())       return Tile.marmolWall[2];
        if (tiles[x + y * width] == Colors.naranjaMecanica.color()) return Tile.marmolFloor[3];
        if (tiles[x + y * width] == Colors.kindblue2.color())       return Tile.techo;
        System.out.println(tiles[x + y * width]);
        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y) {
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
    public void time() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mecanica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
