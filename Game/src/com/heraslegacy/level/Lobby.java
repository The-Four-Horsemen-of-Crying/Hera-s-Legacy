/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jony1
 */
public class Lobby implements levelStrategy{
    protected int width;
    protected int height;
    protected int[] tiles;
    protected int[] tilesCollision;
    protected int red = 0xffff0000;
    protected int fuchsia = 0xffff00ff;
    protected int lime = 0xff00ff00;
    protected int blue = 0xff0000ff;
    protected int yellow = 0xffffff00;
    protected int white = 0xffffffff;
    protected int kindblue = -9411424;//RGB:666699
    protected int kindred = -2358749;
    protected int kindColdplay = -5991936;
    protected int kindblue2 = -16724531;
    protected int kindgreenday=-16730112;
    protected int purplePoe=-9699234;
    protected int naranjaMecanica=-1411778;
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;
        
        if (tiles[x + y * width] == lime)       return Tile.specialMarmolFloor;
        if (tiles[x + y * width] == blue)       return Tile.pikes;
        if (tiles[x + y * width] == red)        return Tile.columnas[1];      
        if (tiles[x + y * width] == kindblue)   return Tile.columnas[0];
        if (tiles[x + y * width] == fuchsia)    return Tile.marmolFloor[0];     
        if (tiles[x + y * width] == yellow)     return Tile.marmolWall[0];
        if (tiles[x + y * width] ==kindColdplay)return Tile.marmolFloor[1];
        if (tiles[x + y * width] ==kindgreenday)return Tile.marmolWall[1];
        if (tiles[x + y * width] ==purplePoe)   return Tile.marmolWall[2];
        if (tiles[x + y * width] ==naranjaMecanica)return Tile.marmolFloor[3];
        if (tiles[x + y * width] ==kindblue2)return Tile.techo;
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
        
}
