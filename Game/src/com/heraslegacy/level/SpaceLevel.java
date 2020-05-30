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
import static com.heraslegacy.main.Game.screen;
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

    protected int bluecoli = 0xff3900ff;
    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private Font spaceFont = Fuente.spaceFont;
    private boolean variaB[] = new boolean[4];// 0 para los cambios, 1 para perder, 2 para ganar, 3 para el delay
    private LocalTime dy = LocalTime.now();
    private LocalTime now = LocalTime.now();
    private int life = 2, j = 0, sw = 0, suena = 0;
    int indiceCambio;
    private Color colorTexto = Color.WHITE;
    private Sound sonido[] = {
        new Sound(Sound.win),
        new Sound(Sound.change),
        new Sound(Sound.bakSpa),
        new Sound(Sound.loose)
    };
    private static Texto textSpace[] = {
        new Texto("¡Todo se arregló!", Game.width / 2, Game.height / 2, false),
        new Texto("¡LOS CONTROLES FALLAN!", Game.width / 2, Game.height / 2, false),
        new Texto("!FELICIDADES!", Game.width / 2, Game.height / 2, false),
        new Texto("!Has llegado a la luna!", Game.width / 2, Game.height / 2, false),
        new Texto("FIN DEL JUEGO", Game.width / 2, Game.height / 2, false),
        new Texto("Presiona R para reiniciar", Game.width / 2, Game.height / 2, false),
        new Texto("¡INTENTA DE NUEVO!", Game.width / 2, Game.height / 2, false)
    };

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

        if (tiles[x + y * width] == Colors.yellow.getColor()) {
            return Tile.spacePices[6];
        }

        if (tiles[x + y * width] == Colors.white.getColor()) {
            return Tile.spaceMeteor[0];
        }

        if (tiles[x + y * width] == Colors.kindblue.getColor() && tiles[x - 1 + y * width] == Colors.kindblue.getColor()) {
            return Tile.spaceMeteor[2];
        }

        if (y > 0 && tiles[x + y * width] == Colors.kindblue.getColor() && tiles[x + (y - 1) * width] == Colors.kindblue.getColor()) {
            return Tile.spaceMeteor[5];
        }

        if (tiles[x + y * width] == Colors.kindblue.getColor()) {
            return Tile.spaceMeteor[1];
        }

        if (tiles[x + y * width] == Colors.kindred.getColor() && tiles[x + 1 + y * width] == Colors.kindred.getColor()) {
            return Tile.spaceMeteor[3];
        }

        if (y > 0 && tiles[x + y * width] == Colors.kindred.getColor() && tiles[x + (y - 1) * width] == Colors.kindred.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.kindred.getColor()) {
            return Tile.spaceMeteor[4];
        }

        if (tiles[x + y * width] == Colors.kindColdplay.getColor() && tiles[x + (y + 1) * width] == Colors.kindColdplay.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.kindColdplay.getColor() && tiles[x - 1 + y * width] == Colors.kindColdplay.getColor()) {
            return Tile.spaceMeteor[8];
        }

        if (tiles[x + y * width] == Colors.kindColdplay.getColor()) {
            return Tile.spaceMeteor[7];
        }

        if (tiles[x + y * width] == Colors.kindblue2.getColor() && tiles[x + (y + 1) * width] == Colors.kindblue2.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.kindblue2.getColor() && tiles[x + 1 + y * width] == Colors.kindblue2.getColor()) {
            return Tile.spaceMeteor[9];
        }

        if (tiles[x + y * width] == Colors.kindblue2.getColor()) {
            return Tile.spaceMeteor[12];
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
            ex.printStackTrace();
            System.out.println("No se pudo cargar el archivo del nivel.");
        }
    }

    @Override
    public void mecanica() {

        if (!variaB[0]) {
            indiceCambio = 1;
        } else {
            indiceCambio = 0;
        }
        if (sw == 0) {
            sonido[2].play();
            sw = 2;
        }
        player.animación();
        int res = dy.minusSeconds(LocalTime.now().getSecond()).getSecond();
        if (res == 50 && !variaB[2] && !variaB[1]) {
            textSpace[0].setVisible(indiceCambio, textSpace);//Implementar aviso cada 25s
            sonido[1].stop();
            sonido[2].stop();
            sonido[1].changeVolume(0);
            sonido[2].changeVolume(0);
            sonido[2 - indiceCambio].play();
            player.setTipo(indiceCambio);
            variaB[0] = !variaB[0];
            dy = LocalTime.now();
        } else if (res == 55 && !variaB[2] && !variaB[1]) {
            sonido[3].stop();
            time();
            textSpace[0].setVisible(false);
            textSpace[1].setVisible(false);
            textSpace[4].setVisible(false);
        }
        if (player.getCollisionP() && player.getDirectionalTile().tipo == TipoTile.GAME_OVER || variaB[1]) {
            player.setTipo(2);
            sonido[1].stop();
            sonido[2].stop();
            sonido[3].changeVolume(0);
            sonido[3].play();
            delay();
            j = now.minusSeconds(LocalTime.now().getSecond()).getSecond();
            variaB[1] = true;
            if (life > 0) {
                textSpace[0].setVisible(6, textSpace);
                if (j == 58) {
                    restar();
                }
            } else {
                //Habria que verificar si quiere volver a intentar o se puede hacer por vidas :D 
                if (j == 58) {
                    textSpace[0].setVisible(5, textSpace);
                } else if (j == 0) {
                    textSpace[3].setVisible(4, textSpace);
                } else if (j == 56) {
                    now = LocalTime.now();
                }
                sw = -10;
            }

        } else if (tilesCollision[(player.getX() >> 4) + (player.getY() >> 4) * width] == Colors.bluecoli.getColor() && !variaB[2]) {
            //Se le indica que ganó, ya no se hace nada y se termina el juego
            player.setTipo(2);
            sonido[1].stop();
            sonido[2].stop();
            delay();
            j = now.minusSeconds(LocalTime.now().getSecond()).getSecond();
            suena++;
            time();
            if (j == 0) {
                textSpace[0].setVisible(2, textSpace);
            } else if (j == 58) {
                textSpace[0].setVisible(3, textSpace);
            }
            if (j == 54) {
                sonido[0].stop();
                variaB[2] = true;
                textSpace[2].setVisible(false);
            }

        }

    }

    @Override
    public void time() {
        if (sw == 3) {
            sw = 0;
        }
        if (suena == 1) {
            sonido[0].play();
        }
        if (suena == 6) {
            suena = 100;
        }
    }

    @Override
    public void restar() {
        if (variaB[1] && life > 0) {
            restart();
            textSpace[6].setVisible(false);
            sw = 3;
            life--;
        }

        if (sw == -10) {
            restart();
            life = 3;
            textSpace[5].setVisible(false);
            sw = 0;
        }
    }

    private void delay() {

        if (!variaB[3]) {
            now = LocalTime.now();
            variaB[3] = true;
            System.out.println("Entré");
        }

    }

    private void restart() {
        player.setX(Game.width / 2);
        player.setY(Game.height / 2);
        variaB[1] = false;
        variaB[2] = false;
        variaB[3] = false;
        player.setTipo(0);
        variaB[0] = false;
        sonido[1].stop();
        sonido[2].stop();
        dy = LocalTime.now();
    }

    @Override
    public boolean cambio() {
        return variaB[2];
    }

    @Override
    public void configPlayer(Level level) {

        player = new Player(Game.width / 2, Game.height / 2);
        player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
        Sound p = new Sound(Sound.propulsion);
        p.changeVolume(-10);
        player.setAjustes(24, -7, -12, -11, 12, 24, p);
        player.setTipo(0);
        player.setLevel(level);
        player.setLatencia(400);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Texto[] getText() {
        return textSpace;
    }

    @Override
    public void setText(String c) {
    }

    @Override
    public Color getColor() {
        return colorTexto;
    }

    @Override
    public Level levelCambio() {
        Lobby.levels[2] = true;
        return new Level("/levels/lobby/lobby.png", "/levels/lobby/collisionlobby.png", new Lobby());
    }

    @Override
    public Font getFont() {
        return this.spaceFont;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
        screen.renderSprite(true, 112 * 16, 16 * 16, Sprite.moon);
    }

    @Override
    public void render() {

    }
}
