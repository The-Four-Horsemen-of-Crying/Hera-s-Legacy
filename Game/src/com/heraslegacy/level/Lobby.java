/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.level;

import com.heraslegacy.entity.Player;
import com.heraslegacy.graphics.Colors;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.graphics.Texto;
import com.heraslegacy.level.tile.Tile;
import com.heraslegacy.main.Game;
import java.awt.Color;
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
    private final Color colorTexto= Color.WHITE;
    private Game game;
    private Texto textLobby[]= {
    
    };
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.pikes;
        
        if (tiles[x + y * width] == Colors.lime.getColor())            return Tile.specialMarmolFloor;
        if (tiles[x + y * width] == Colors.blue.getColor())            return Tile.pikes;
        if (tiles[x + y * width] == Colors.red.getColor())             return Tile.columnas[1];      
        if (tiles[x + y * width] == Colors.kindblue.getColor())        return Tile.columnas[0];
        if (tiles[x + y * width] == Colors.fuchsia.getColor())         return Tile.marmolFloor[0];     
        if (tiles[x + y * width] == Colors.yellow.getColor())          return Tile.marmolWall[0];
        if (tiles[x + y * width] == Colors.kindColdplay.getColor())    return Tile.marmolFloor[1];
        if (tiles[x + y * width] == Colors.kindgreenday.getColor())    return Tile.marmolWall[1];
        if (tiles[x + y * width] == Colors.purplePoe.getColor())       return Tile.marmolWall[2];
        if (tiles[x + y * width] == Colors.naranjaMecanica.getColor()) return Tile.marmolFloor[3];
        if (tiles[x + y * width] == Colors.kindblue2.getColor())       return Tile.techo;
        System.out.println(tiles[x + y * width]);
        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y) {
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.lime.getColor()) {
            nivelCase = 1;
            return true;
        }
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.blue.getColor()) {
            nivelCase = 2;
            return true;
        }
        if (tilesCollision[(x >> 4) + (y >> 4) * width] == Colors.red.getColor()) {
            nivelCase = 3;
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
    public void mecanica() {
        switch(nivelCase){
            case 1:
                game.setLevel(new Level("/levels/level02/level2.png","/levels/level02/collisionlevel2.png",new SpaceLevel()));
                break;
            case 2:
                game.setLevel(new Level("/levels/level01/level1.png","/levels/level01/collisionlevel1.png",new MathLevel()));
                break;
            case 3:
                game.setLevel(new Level("/levels/level03/nivel3.png","/levels/level03/nivel3COLLITION.png",new LibraryLevel()));
                break;
        }
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
    public void configPlayer(Level level) {
        player = new Player(Game.width / 2, Game.height / 2);
        player.setSprites(Sprite.Elizabeth_up, Sprite.Elizabeth_down, Sprite.Elizabeth_rigth, Sprite.Elizabeth_left);
        player.setAjustes(14, 8, 12, 3, 16, 16);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return colorTexto;
    }
    
    public void setGame(Game game){
        this.game=game;
    }
}
