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
import com.heraslegacy.manager.KeyBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;

/**
 *
 * @author HP
 */
public class Fantasma implements levelStrategy {

    protected int bluecoli = 0xff3900ff;
    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private final Font spaceFont = Fuente.spaceFont;
    private final Color colorTexto = Color.WHITE;
    private boolean cambio = false;
    private int cont[] = new int[3];
    private int indiceLevel;
    private LocalTime dt = LocalTime.now();
    private LocalTime dm = LocalTime.now();
    private final Texto[] a = {
        new Texto("Hola", Game.width / 2, 300, true),
        new Texto("Puta :)", Game.width / 2, 300, false),
        new Texto("3", Game.width / 2, 300, false),
        new Texto("4", Game.width / 2, 300, false),
        new Texto("5", Game.width / 2, 300, false),
        new Texto("", Game.width / 2, 300, false)};
    private final Tile piso[] = {
        Tile.spacePices[3],
        Tile.woodFloor,
        Tile.floorL1,
    };
    private final Tile pared[] = {
        Tile.spacePices[0],
        Tile.woodWall,
        Tile.estanterias[0]
    };

    public Fantasma(int indiceLevel) {
        this.indiceLevel = indiceLevel;//La idea es utilizar esto para hacer tipo un switch
    }

    @Override
    public void update(){
        
    }

    @Override
    public Tile getTile(int x, int y) {
        //Hay que modificiar para decidir que tiles cargar o dejarlas todas en un mismo spritesheet
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.yellow.getColor()) {
            return pared[indiceLevel];
        } else {
            return piso[indiceLevel];
        }

    }

    @Override
    public boolean getCollision(int x, int y) {
        return true;
    }

    @Override
    public void loadLevel(String path, String pathCollision) {
        //Hay que modificar para que se carguen las distintas imagenes 
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
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }

    @Override
    public void time() {
        
        cont[0] = dt.minusSeconds(LocalTime.now().getSecond()).getSecond();//MAnejo del tiempo del nivel
        cont[1] = dm.minusSeconds(LocalTime.now().getSecond()).getSecond();//MAnejo del tiempo de los mensajes
       
        if (cont[1] == 57) {//Mostrar mensajes
            cont[1] = 0;
            if (cont[2] <= 4) {
                cont[2]++;
                a[0].setVisible(cont[2], a);
            }
            cont[1]=0;
            dm= LocalTime.now();
        }
    }

    @Override
    public void mecanica() {
        if(indiceLevel==0)player.animación();
        time();
        if (cont[0] == 50) {
            cambio = true;// Aquí se dice cuando se "Acabo" la presentación
        }
        
    }

    @Override
    public void restar() {
    }

    @Override
    public boolean cambio() {
        return cambio;
    }

    @Override
    public void configPlayer(Level level) {
        //Buscar la manera de decidir cual cargar
        int latencia= 30;
        Sound p = new Sound(Sound.walk);
        player = new Player(Game.width / 2, Game.height / 2);
        switch (indiceLevel) {
            case 0:
                player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
                latencia=400;
                p = new Sound(Sound.propulsion);
                break;
            case 1:
                player.setSprites(Sprite.Elizabeth_up, Sprite.Elizabeth_down, Sprite.Elizabeth_rigth, Sprite.Elizabeth_left);
                break;
            case 2:
                player.setSprites(Sprite.Elizabeth_up, Sprite.Elizabeth_down, Sprite.Elizabeth_rigth, Sprite.Elizabeth_left);
                break;
        }
        
        p.changeVolume(-10);
        player.setAjustes(24, -7, -12, -11, 12, 24, p);
        player.setTipo(3);
        player.setLevel(level);
        player.setLatencia(latencia);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Texto[] getText() {
        return a;
    }

    @Override
    public void setText(String c) {
    }

    @Override
    public Color getColor() {
       Color color = Color.WHITE;
       if(indiceLevel!=0){
           color= Color.BLACK;
       }
        return color;
    }

    @Override
    public Level levelCambio() {
        KeyBoard.setKeysStatic(false, KeyEvent.VK_ESCAPE);
        //Se debería implementar un codigo parecido al deLobby para decir que cambio hacer
        Level game = new Level("/levels/level02/level2.png", "/levels/level02/collisionlevel2.png", new Fantasma(indiceLevel));
        switch (indiceLevel) {
            case 0:
                game = new Level("/levels/level02/level2.png", "/levels/level02/collisionlevel2.png", new SpaceLevel());
                break;
            case 1:
                game = new Level("/levels/level01/level1.png", "/levels/level01/collisionlevel1.png", new MathLevel());
                break;
            case 2:
                game = new Level("/levels/level03/nivel3.png", "/levels/level03/nivel3COLLITION.png", new LibraryLevel());
                break;
        }
        return game;
    }

    @Override
    public Font getFont() {
        return this.spaceFont;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
    }

    @Override
    public void render() {
        
    }

    @Override
    public void backWithoutWin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
