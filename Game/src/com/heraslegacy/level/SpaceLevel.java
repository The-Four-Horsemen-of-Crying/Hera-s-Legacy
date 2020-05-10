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
import com.heraslegacy.main.Game;
import com.heraslegacy.manager.KeyBoard;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Domain
 */
public class SpaceLevel implements levelStrategy {

    
    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private int i=0;
    private Player player;
    private boolean running = false;

    @Override
    public void update() {
    }

    @Override
    public Tile getTile(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height)  return Tile.spacePices[3];
        

        if (tiles[x + y * width] == Colors.lime.getColor())  return Tile.spacePices[0];
        
        if (tiles[x + y * width] == Colors.blue.getColor())  return Tile.spacePices[1];
        
        if (tiles[x + y * width] == Colors.red.getColor())   return Tile.spacePices[2];
        
        if (tiles[x + y * width] == Colors.fuchsia.getColor())  return Tile.spacePices[3];
        
        if (tiles[x + y * width] == Colors.yellow.getColor())  return Tile.spacePices[5];
        
        if (tiles[x + y * width] == Colors.white.getColor())  return Tile.spaceMeteor[0];
        
        return Tile.spacePices[3];
    }

    @Override
    public boolean getCollision(int x, int y) {

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
    public void mecanica() {
        i++;
        if(i>=5000){
            System.out.println("Cambio de controles");
            running=!running;
            player.setTipo(running);
            i=0;
        }
        

    }

    @Override
    public void time() {
    }

    @Override
    public void restar() {

    }

    @Override
    public boolean cambio() {

        return false;
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
