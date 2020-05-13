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
import com.heraslegacy.manager.KeyBoard;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;

/**
 *
 * @author Domain
 */
public class SpaceLevel implements levelStrategy {

    protected int bluecoli=0xff3900ff;
    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private boolean cambio = false, loose=false, win=false;
    private LocalTime dy= LocalTime.now();
    private Sound bk= new Sound(Sound.bakSpa);
    private Sound c= new Sound(Sound.change);
    private String text="";

    @Override
    public void update() {
          bk.loop();
            bk.changeVolume((float) -80);
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
        
        if (tiles[x + y * width] == Colors.kindblue.getColor() && tiles[x - 1 + y * width] == Colors.kindblue.getColor())return Tile.spaceMeteor[2];
         
        if (y>0&&tiles[x + y * width] == Colors.kindblue.getColor() && tiles[x + (y-1) * width] == Colors.kindblue.getColor())return Tile.spaceMeteor[5];
        
        if (tiles[x + y * width] == Colors.kindblue.getColor())  return Tile.spaceMeteor[1];
        
        if (tiles[x + y * width] == Colors.kindred.getColor() && tiles[x + 1 + y * width] == Colors.kindred.getColor())  return Tile.spaceMeteor[3];
        
        if (y>0&&tiles[x + y * width] == Colors.kindred.getColor() && tiles[x + (y-1) * width] == Colors.kindred.getColor())  return Tile.spaceMeteor[6];
        
        if (tiles[x + y * width] == Colors.kindred.getColor())  return Tile.spaceMeteor[4];
        
        if (tiles[x + y * width] == Colors.kindColdplay.getColor() && tiles[x + (y+1) * width] == Colors.kindColdplay.getColor())return Tile.spaceMeteor[6];
        
        if (tiles[x + y * width] == Colors.kindColdplay.getColor() && tiles[x - 1 + y * width] == Colors.kindColdplay.getColor())return Tile.spaceMeteor[8];
        
        if (tiles[x + y * width] == Colors.kindColdplay.getColor())  return Tile.spaceMeteor[7];
        
        if (tiles[x + y * width] == Colors.kindblue2.getColor() && tiles[x + (y+1) * width] == Colors.kindblue2.getColor())return Tile.spaceMeteor[6];
        
        if (tiles[x + y * width] == Colors.kindblue2.getColor() && tiles[x + 1 + y * width] == Colors.kindblue2.getColor())return Tile.spaceMeteor[9];
        
        if (tiles[x + y * width] == Colors.kindblue2.getColor())  return Tile.spaceMeteor[12];
        
        if (tiles[x + y * width] == Colors.naranjaMecanica.getColor())  return Tile.niceStuff[0];
        
        if (tiles[x + y * width] == Colors.purplePoe.getColor())  return Tile.niceStuff[1];
        
        
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
       
          
        
        LocalTime res=dy.minusSeconds(LocalTime.now().getSecond());
        if(res.getSecond()==45 && !win && !loose){
            text= "Cambio de controles";//Implementar aviso cada 25s
            c.changeVolume((float)-80);
            c.play();
            cambio=!cambio;
            player.setTipo(1);
            dy=LocalTime.now();
        }else if(res.getSecond()==55 && !win && !loose){
            setText("");
        }
        if(tilesCollision[(player.getX()>>4)+(player.getY()>>4)*width]==Colors.red.getColor() && !loose){
                text="loos do u wann restart?";//Habria que verificar si quiere volver a intentar o se puede hacer por vidas :D
                player.setTipo(2);
                loose=true;
        }else if(tilesCollision[(player.getX()>>4)+(player.getY()>>4)*width]== Colors.bluecoli.getColor() && !win ){
            text="win";//Se le indica que ganó, ya no se hace nada y se termina el juego
            win=true;
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
        
        return win;
    }

    @Override
    public void configPlayer(int x, int y, KeyBoard input, Sprite[] up, Sprite[] down, Sprite[] rigth, Sprite[] left, int tipo,Level level) {
        
        player = new Player(x, y, input);
        player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
        player.setAjustes(24, -7, -12, -11, 12,24);
        player.setTipo(tipo);
        player.setLevel(level);
    }
    
    @Override
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public String getText(){
        return text;
    }

    @Override
    public void setText(String c) {
        this.text="";
    }
}
