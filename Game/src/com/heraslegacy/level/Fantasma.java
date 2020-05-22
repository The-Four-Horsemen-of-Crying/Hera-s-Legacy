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
import java.awt.Color;
import java.awt.Font;
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
    private Color colorTexto = Color.WHITE;
    private boolean cambio = false;
    private int c, cM, tM = 0;
    private Texto a[] = {
        new Texto("1", Game.width / 2, 300, false),
        new Texto("2", Game.width / 2, 300, false),
        new Texto("3", Game.width / 2, 300, false),
        new Texto("4", Game.width / 2, 300, false),
        new Texto("5", Game.width / 2, 300, false)};

    @Override
    public void update() {
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.lime.getColor()) {
            return Tile.spacePices[0];
        }

        if (tiles[x + y * width] == Colors.blue.getColor()) {
            return Tile.spacePices[1];
        }

        if (tiles[x + y * width] == Colors.red.getColor()) {
            return Tile.spacePices[2];
        }

        if (tiles[x + y * width] == Colors.fuchsia.getColor()) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.naranjaMecanica.getColor()) {
            return Tile.niceStuff[0];
        }

        if (tiles[x + y * width] == Colors.purplePoe.getColor()) {
            return Tile.niceStuff[1];
        }

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
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }

    @Override
    public void time() {
        c = LocalTime.now().minusSeconds(c).getSecond();
        tM = LocalTime.now().minusSeconds(tM).getSecond();
        if (tM == 5) {
            tM = 0;
            if (cM <= 4) {
                a[cM].setVisible(cM, a);
                cM++;
            }
        }
    }

    @Override
    public void mecanica() {
        time();
        if (c == 30) {
            cambio = true;
        }
    }

    @Override
    public void restar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cambio() {
        return cambio;
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(Game.width / 2, Game.height / 2);
        player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
        Sound p = new Sound(Sound.propulsion);
        p.changeVolume(-10);
        player.setAjustes(24, -7, -12, -11, 12, 24, p);
        player.setTipo(3);
        player.setLevel(level);
        player.setLatencia(400);
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
        return this.colorTexto;
    }

    @Override
    public Level levelCambio() {
        Lobby.levels[2] = true;
        return new Level("/levels/level02/level2.png", "/levels/level02/collisionlevel2.png", new SpaceLevel());
    }

    @Override
    public Font getFont() {
        return this.spaceFont;
    }

    @Override
    public void sobreRender() {
    }

}
