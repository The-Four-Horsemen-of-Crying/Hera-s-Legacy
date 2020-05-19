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
import com.heraslegacy.level.tile.TipoTile;
import com.heraslegacy.main.Game;
import java.awt.Color;
import java.awt.Font;
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
    private Font spaceFont = Fuente.spaceFont;
    private boolean cambio=false, loose=false, win=false;
    private LocalTime dy= LocalTime.now();
    private Sound bk= new Sound(Sound.bakSpa);
    private Sound c= new Sound(Sound.change);
    private String text;
    private int j=0;
    private Color colorTexto= Color.WHITE;
    private static Texto textSpace[]= {
        new Texto("¡LOS CONTROLES FALLAN!", Game.width/2, Game.height/2, false),
        new Texto("!Felicidades, has llegado a la luna!",Game.width/2, Game.height/2,false),
        new Texto("FIN DEL JUEGO",Game.width/2, Game.height/2,false)
    };


    @Override
    public void update() {
                 bk.loop();
            bk.changeVolume((float) 6.002);
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
        int i;

        if(!cambio){
            i=1;
        }else{
            i=0;
        }
        player.animación();
        int res=dy.minusSeconds(LocalTime.now().getSecond()).getSecond();
        if(res==45 && !win && !loose){
           textSpace[0].setVisible(true);//Implementar aviso cada 25s
            c.changeVolume((float)-50);
            c.play();
            player.setTipo(i);
             cambio=!cambio;
            dy=LocalTime.now();
        }else if(res==55 && !win && !loose){
            textSpace[0].setVisible(false);
        }
        if(player.getCollisionP()&& player.getDirectionalTile().tipo==TipoTile.GAME_OVER && !loose){
            textSpace[0].setVisible(false);
                textSpace[2].setVisible(true);//Habria que verificar si quiere volver a intentar o se puede hacer por vidas :D
                player.setTipo(2);
                loose=true;
        }else if(tilesCollision[(player.getX()>>4)+(player.getY()>>4)*width]== Colors.bluecoli.getColor() && !win ){
             textSpace[0].setVisible(false);
            textSpace[1].setVisible(true);//Se le indica que ganó, ya no se hace nada y se termina el juego
            player.setTipo(2);
            j++;
            if(j>800){
                win=true;
                bk.stop();
                 textSpace[1].setVisible(false);
            }
            
        }
        

    }

    @Override
    public void time() {
    }

    @Override
    public void restar() {
        if(loose){
    player.setX(Game.width/2);
    player.setY(Game.height/2);
    loose=false;
    win=false;
    player.setTipo(0);
     textSpace[2].setVisible(false);
        }
    }

    @Override
    public boolean cambio() {
       return win;
    }

    @Override
    public void configPlayer(Level level) {
        
        player = new Player(Game.width / 2, Game.height / 2);
        player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
        player.setAjustes(24, -7, -12, -11, 12,24);
        player.setTipo(0);
        player.setLevel(level);
        player.setLatencia(400);
    }
    
    @Override
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public Texto[] getText(){
       return textSpace;
    }

    @Override
    public void setText(String c) {
        this.text="";
    }

    @Override
    public Color getColor() {
       return colorTexto;
    }

    @Override
    public Level levelCambio() {
        Lobby.levels[2]=true;
        return new Level("/levels/lobby/lobby.png","/levels/lobby/collisionlobby.png",new Lobby());
    }

    @Override
    public Font getFont() {
        return this.spaceFont;
    }
}
