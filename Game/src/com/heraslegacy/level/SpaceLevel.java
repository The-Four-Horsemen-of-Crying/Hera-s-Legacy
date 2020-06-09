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
 * @author Domain
 */
public class SpaceLevel implements levelStrategy {

    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private final Font SPACEFONT = Fuente.greekFont;
    private boolean variaB[] = new boolean[5];// 0 para los cambios, 1 para perder, 2 para ganar, 3 para el delay
    private LocalTime dy = LocalTime.now();
    private LocalTime now = LocalTime.now();
    private int life = 3, j = 0, sw = 0, suena = 0;
    private int indiceCambio;
    private final Color COLORTEXTO = Color.WHITE;
    private final Sound SOUNDS[] = {
        new Sound(Sound.win),
        new Sound(Sound.change),
        new Sound(Sound.bakSpa),
        new Sound(Sound.loose)
    };
    private final Texto TEXTSPACE[] = {
        new Texto("Todo se arreglo!", 0, false, Sprite.katherine_down[0]),
        new Texto("LOS CONTROLES FALLAN!", 0, false, Sprite.katherine_down[0]),
        new Texto("FELICIDADES!", 0, false, Sprite.katherine_down[0]),
        new Texto("Has llegado a la luna!", 0, false, Sprite.katherine_down[0]),
        new Texto("FIN DEL JUEGO", 0, false, Sprite.katherine_down[0]),
        new Texto("Presiona R para reiniciar", 0, false, Sprite.katherine_down[0]),
        new Texto("INTENTA DE NUEVO!", 0, false, Sprite.katherine_down[0])
    };

    @Override
    public void update() {
    }

    @Override
    public Tile getTile(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.LIME.getColor()) {
            return Tile.spacePices[0];
        }

        if (tiles[x + y * width] == Colors.BLUE.getColor()) {
            return Tile.spacePices[1];
        }

        if (tiles[x + y * width] == Colors.RED.getColor()) {
            return Tile.spacePices[2];
        }

        if (tiles[x + y * width] == Colors.FUCHSIA.getColor()) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.YELLOW.getColor()) {
            return Tile.spacePices[6];
        }

        if (tiles[x + y * width] == Colors.WHITE.getColor()) {
            return Tile.spaceMeteor[0];
        }

        if (tiles[x + y * width] == Colors.KINDBLUE.getColor() && tiles[x - 1 + y * width] == Colors.KINDBLUE.getColor()) {
            return Tile.spaceMeteor[2];
        }

        if (y > 0 && tiles[x + y * width] == Colors.KINDBLUE.getColor() && tiles[x + (y - 1) * width] == Colors.KINDBLUE.getColor()) {
            return Tile.spaceMeteor[5];
        }

        if (tiles[x + y * width] == Colors.KINDBLUE.getColor()) {
            return Tile.spaceMeteor[1];
        }

        if (tiles[x + y * width] == Colors.KINDRED.getColor() && tiles[x + 1 + y * width] == Colors.KINDRED.getColor()) {
            return Tile.spaceMeteor[3];
        }

        if (y > 0 && tiles[x + y * width] == Colors.KINDRED.getColor() && tiles[x + (y - 1) * width] == Colors.KINDRED.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.KINDRED.getColor()) {
            return Tile.spaceMeteor[4];
        }

        if (tiles[x + y * width] == Colors.KINDCOLDPLAY.getColor() && tiles[x + (y + 1) * width] == Colors.KINDCOLDPLAY.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.KINDCOLDPLAY.getColor() && tiles[x - 1 + y * width] == Colors.KINDCOLDPLAY.getColor()) {
            return Tile.spaceMeteor[8];
        }

        if (tiles[x + y * width] == Colors.KINDCOLDPLAY.getColor()) {
            return Tile.spaceMeteor[7];
        }

        if (tiles[x + y * width] == Colors.KINDBLUE2.getColor() && tiles[x + (y + 1) * width] == Colors.KINDBLUE2.getColor()) {
            return Tile.spaceMeteor[6];
        }

        if (tiles[x + y * width] == Colors.KINDBLUE2.getColor() && tiles[x + 1 + y * width] == Colors.KINDBLUE2.getColor()) {
            return Tile.spaceMeteor[9];
        }

        if (tiles[x + y * width] == Colors.KINDBLUE2.getColor()) {
            return Tile.spaceMeteor[12];
        }

        if (tiles[x + y * width] == Colors.NARANJAMECANICA.getColor()) {
            return Tile.niceStuff[0];
        }

        if (tiles[x + y * width] == Colors.PURPLEPOE.getColor()) {
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
        if (!KeyBoard.getKeysStatic(KeyEvent.VK_ESCAPE)) {
            if (!variaB[0]) {
                indiceCambio = 1;
            } else {
                indiceCambio = 0;
            }
            if (sw == 0) {
                SOUNDS[2].play();
                sw = 2;
            }
            player.animación();
            int res = dy.minusSeconds(LocalTime.now().getSecond()).getSecond();
            if (res == 50 && !variaB[2] && !variaB[1]&& sw!=20) {
                TEXTSPACE[0].setVisible(indiceCambio, TEXTSPACE);//Implementar aviso cada 25s
                SOUNDS[1].stop();
                SOUNDS[2].stop();
                SOUNDS[1].changeVolume(0);
                SOUNDS[2].changeVolume(0);
                SOUNDS[2 - indiceCambio].play();
                player.setTipo(indiceCambio);
                variaB[0] = !variaB[0];
                dy = LocalTime.now();
            } else if (res == 55 && !variaB[2] && !variaB[1]) {
                SOUNDS[3].stop();
                time();
                TEXTSPACE[0].setVisible(false);
                TEXTSPACE[1].setVisible(false);
                TEXTSPACE[4].setVisible(false);
            }
            if ((player.getCollisionP() && player.getDirectionalSolidSnakeTile().tipo == TipoTile.GAME_OVER|| variaB[1] )&& !variaB[2]) {
                player.setTipo(2);
                SOUNDS[2].stop();
                SOUNDS[1].stop();
                SOUNDS[3].changeVolume(0);
                SOUNDS[3].play();
                delay();
                j = now.minusSeconds(LocalTime.now().getSecond()).getSecond();
                variaB[1] = true;
                if (life > 0) {
                    TEXTSPACE[0].setVisible(6, TEXTSPACE);
                    if (j == 56) {
                        restar();
                    }
                } else {
                    //Habria que verificar si quiere volver a intentar o se puede hacer por vidas :D
                    switch (j) {
                        case 58:
                            TEXTSPACE[0].setVisible(5, TEXTSPACE);
                            break;
                        case 0:
                            TEXTSPACE[3].setVisible(4, TEXTSPACE);
                            break;
                        case 56:
                            now = LocalTime.now();
                            break;
                        default:
                            break;
                    }
                    sw = -10;
                }

            } else if (tilesCollision[(player.getX() >> 4) + (player.getY() >> 4) * width] == Colors.BLUECOLI.getColor() && !variaB[2]) {
                //Se le indica que ganó, ya no se hace nada y se termina el juego
                player.setTipo(2);
                SOUNDS[1].stop();
                SOUNDS[2].stop();
                delay();
                j = now.minusSeconds(LocalTime.now().getSecond()).getSecond();
                sw=20;
                suena++;
                time();
                if (j == 0) {
                    TEXTSPACE[0].setVisible(2, TEXTSPACE);
                } else if (j == 58) {
                    TEXTSPACE[0].setVisible(3, TEXTSPACE);
                }
                if (j == 54) {
                    SOUNDS[0].stop();
                    variaB[2] = true;
                    TEXTSPACE[2].setVisible(false);
                }

            }
        }else{
            player.setTipo(2);
  
        }
    }

    @Override
    public void time() {
        if (sw == 3) {
            sw = 0;
        }
        if (suena == 1) {
            SOUNDS[0].play();
        }
        if (suena == 6) {
            suena = 100;
        }
    }

    @Override
    public void restar() {
        if (variaB[1] && life > 0) {
            restart();
            TEXTSPACE[6].setVisible(false);
            sw = 3;
            life--;
        }

        if (sw == -10) {
            restart();
            life = 3;   
            TEXTSPACE[4].setVisible(false);
            TEXTSPACE[5].setVisible(false);
            sw = 0;
        }
    }

    private void delay() {

        if (!variaB[3]) {
            now = LocalTime.now();
            variaB[3] = true;
        }

    }

    private void restart() {
        player.setX(Game.WIDTH / 2);
        player.setY(Game.HEIGHT / 2);
        variaB[1] = false;
        variaB[2] = false;
        variaB[3] = false;
        player.setTipo(0);
        variaB[0] = false;
        stopAll();
        dy = LocalTime.now();
    }

    @Override
    public boolean cambio() {
        return variaB[2] || variaB[4];
    }

    @Override
    public void configPlayer(Level level) {

        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
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
        return TEXTSPACE;
    }

    @Override
    public void setText(String c) {
    }

    @Override
    public Color getColor() {
        return COLORTEXTO;
    }

    @Override
    public Level levelCambio() {
        if(variaB[2])Lobby.levels[0] = true;
        return new Level("/levels/lobby/lobby.png", "/levels/lobby/collisionlobby.png", new Lobby());
    }

    @Override
    public Font getFont() {
        return this.SPACEFONT;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
        screen.renderSprite(true, 112 * 16, 16 * 16, Sprite.moon);
        for (int i = 0; i < life; i++) {
            screen.renderSprite(true,(16*i) + xScroll, yScroll, Sprite.life);
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void backWithoutWin() {
        stopAll();
        variaB[4] = true;
    }

    @Override
    public void uptadeTexto() {
        for (Texto text : TEXTSPACE) {
            text.showIfActive();
        }
    }

    @Override
    public void stopAll() {
        for (Sound sonido1 : SOUNDS) {
            sonido1.stop();
        }
    }
}
