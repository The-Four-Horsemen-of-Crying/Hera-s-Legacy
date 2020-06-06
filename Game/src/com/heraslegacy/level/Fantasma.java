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
    private final Font SPACEFONT = Fuente.trans;
    private final Color COLORTEXTO = Color.WHITE;
    private boolean cambio = false;
    private int cont[] = new int[3];
    private int indiceLevel, maxMensajes;
    private LocalTime dt = LocalTime.now();
    private LocalTime dm = LocalTime.now();
    private final Texto a[];
    private final Texto mensajeSpace[]={
        new Texto("Katherine Jonhson, la calculadora humana.", Game.width / 2, 300, true),
        new Texto("En 1969, gracias a los calculos manuales de Katherine", Game.width / 2, 300, false),
        new Texto("se logró sincronizar la nave de aterrizaje con", Game.width / 2, 300, false),
        new Texto("el módulo de comando en orbita y gracias a esto", Game.width / 2, 300, false),
        new Texto("la tripulación del Apolo 11 regresó a la tierra", Game.width / 2, 300, false),
        new Texto("En una entrevista Katherine declaró:", Game.width / 2, 300, false),
        new Texto("\"Había hecho los cálculos y sabía que eran correctos...", Game.width / 2, 300, false),
        new Texto("pero era como conducir, cualquier cosa podía pasar\"", Game.width / 2, 300, false),
        new Texto("Hoy en día podemos decir que los calculos de Katherine", Game.width / 2, 300, false),
        new Texto("Estuvieron bien.", Game.width / 2, 300, false),
        new Texto("A partir de ahora, eres un astronauta", Game.width / 2, 300, false),
        new Texto("Debes llevar la nave a la luna y confiar en Katherine", Game.width / 2, 300, false), 
        new Texto("Regresarás a salvo, exito en la misión.", Game.width / 2, 300, false),
        new Texto("Recuerda:", Game.width / 2, 300, false),
        new Texto("\"En un tiempo donde todos pensaban en cómo llegar...", Game.width / 2, 300, false),
        new Texto("y nadie en cómo regresar, Jonhson se adelantó, supo como volver\"", Game.width / 2, 300, false)
    };
    private final Texto mensajeMath[]={
        new Texto("Katherin Johnson, la calculadora humana.", Game.width / 2, 300, true),
        new Texto("Trabajaba en la aeronáutica como \"calculadora humana\"", Game.width / 2, 300, false),
        new Texto("y realizaba los calculos requeridos de manera manual", Game.width / 2, 300, false),
        new Texto("Además, realizó los cálculos que enviaron a los ", Game.width / 2, 300, false),
        new Texto("astronautas al espacio entre 1960 y 1969", Game.width / 2, 300, false),
        new Texto("Fue profesora y siempre mostró curiosidad,", Game.width / 2, 300, false),
        new Texto("siempre sorprendía a todos con su astucia.", Game.width / 2, 300, false),
        new Texto("Katherine siempre quería saber el por qué de todo", Game.width / 2, 300, false),
        new Texto("Ahora eres Katherine y podrás vivir ", Game.width / 2, 300, false),
        new Texto("una parte muy pequeña de su trabajo.", Game.width / 2, 300, false),
        new Texto("Deberas hacer calculos sencillos, pero recuerda", Game.width / 2, 300, false),
        new Texto("Katherine y su equipo de trabajo eran más efecientes", Game.width / 2, 300, false),
        new Texto("que una calculadora.", Game.width / 2, 300, false)
            

    };
    private final Texto mensajeLibrary[]={
        new Texto("Dorothy Vaughan, la primera supervisora.", Game.width / 2, 300, true),
        new Texto("Fue una profesora de matemáticas", Game.width / 2, 300, false),
        new Texto("que se convirtió en una de las principales", Game.width / 2, 300, false),
        new Texto("ingenieras matemáticas en los comienzos de ", Game.width / 2, 300, false),
        new Texto("la industria aeroespacial.", Game.width / 2, 300, false),
        new Texto("Era segregada por ser de color ", Game.width / 2, 300, false),
        new Texto("aún así logró ser ascendida a gerente del area oeste", Game.width / 2, 300, false),
        new Texto("de Computación. Con su ascenso se convirtió", Game.width / 2, 300, false),
        new Texto("en una defensora dedica de las empleadas", Game.width / 2, 300, false),
        new Texto("Dorothy hacía cálculos manuales junto con otras mujeres.", Game.width / 2, 300, false),
        new Texto("Cuando la tecnología avanzó, su trabjo se vió en riesgo", Game.width / 2, 300, false),
        new Texto("las computadoras iban a reemplazarla, por lo que decidió", Game.width / 2, 300, false), 
        new Texto("hacerse experta en FORTRAN para así poder usar las computadoras.", Game.width / 2, 300, false),
        new Texto("Para aprender tuvo que escabullirse a la biblioteca,", Game.width / 2, 300, false),
        new Texto("leer a escondidas y ocultarse de los guardias.", Game.width / 2, 300, false),
        new Texto("Ahora eres Dorothy y debes buscar los libros que necesitas.", Game.width / 2, 300, false),
        new Texto("¡Evita que los guardias te vean!", Game.width / 2, 300, false)
    };
    private final Tile[] PISO = {
        Tile.spacePices[3],
        Tile.woodFloor,
        Tile.floorL1,};
    private final Tile[] PARED = {
        Tile.spacePices[0],
        Tile.woodWall,
        Tile.estanterias[0]
    };

    public Fantasma(int indiceLevel) {
        this.indiceLevel = indiceLevel;//La idea es utilizar esto para hacer tipo un switch
        switch(indiceLevel){
            default:
                this.maxMensajes=16;
                A = new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i]=this.MENSAJESPACE[i];
                }
                break;
            case 1:
                this.maxMensajes=13;
                a= new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i]=this.MENSAJEMATH[i];
                }
                break;
            case 2:
                this.maxMensajes=17;
                a= new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i]=this.MENSAJELIBRARY[i];
                }
                break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public Tile getTile(int x, int y) {
        //Hay que modificiar para decidir que tiles cargar o dejarlas todas en un mismo spritesheet
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.spacePices[3];
        }

        if (tiles[x + y * width] == Colors.yellow.getColor()) {
            return PARED[indiceLevel];
        } else {
            return PISO[indiceLevel];
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
            if (cont[2] <= maxMensajes){
                cont[2]++;
                A[0].setVisible(cont[2], A);
                
            }
            cont[1] = 0;
            dm = LocalTime.now();
        }
    }

    @Override
    public void mecanica() {
        if (indiceLevel == 0) {
            player.animación();
        }
        time();
        if (cont[0] == 59-maxMensajes*3) {
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
        int latencia = 30;
        Sound p = new Sound(Sound.walk);
        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
        switch (indiceLevel) {
            case 0:
                player.setSprites(Sprite.apolo_up, Sprite.apolo_down, Sprite.apolo_rigth, Sprite.apolo_left);
                latencia = 400;
                p = new Sound(Sound.propulsion);
                break;
            case 1:
                player.setSprites(Sprite.katherine_up, Sprite.katherine_down, Sprite.katherine_rigth, Sprite.katherine_left);
                break;
            case 2:
                player.setSprites(Sprite.dorothy_up, Sprite.dorothy_down, Sprite.dorothy_rigth, Sprite.dorothy_left);
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
        Texto text[]= this.MENSAJESPACE;
        switch(indiceLevel){
            case 1:
                text= this.MENSAJEMATH;
                break;
            case 2:
                text= this.MENSAJELIBRARY;
        }
        return text;
    }

    @Override
    public void setText(String c) {
    }

    @Override
    public Color getColor() {
        Color color = Color.WHITE;
        if (indiceLevel != 0) {
            color = Color.BLACK;
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
        return this.SPACEFONT;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
    }

    @Override
    public void render() {

    }

    @Override
    public void backWithoutWin() {
        
    }

    @Override
    public void uptadeTexto() {

    }

}
