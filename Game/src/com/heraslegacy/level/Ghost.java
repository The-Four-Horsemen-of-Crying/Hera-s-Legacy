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
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.KeyBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import static com.heraslegacy.main.Game.scale;

/**
 *
 * @author HP
 */
public class Ghost implements levelStrategy {

    private int width;
    private int height;
    private int[] tiles;
    private int[] tilesCollision;
    private Player player;
    private final Font SPACEFONT = Fuente.trans;
    private boolean cambio = false;
    private int cont[] = new int[3];
    private int indiceLevel, maxMensajes;
    private LocalTime dt = LocalTime.now();
    private LocalTime dm = LocalTime.now();
    private int y = screen.height * scale;
    private final Sound SOUND[]={new Sound(Sound.creditos_Theme), new Sound(Sound.fantasma_Theme)};
    private final Texto A[];
    private final Texto MENSAJESPACE[] = {
        new Texto("Katherine Jonhson, la calculadora humana.", Game.WIDTH / 2, 300, true),
        new Texto("En 1969, gracias a los cálculos manuales de Katherine", Game.WIDTH / 2, 300, false),
        new Texto("se logró sincronizar la nave de aterrizaje", Game.WIDTH / 2, 300, false),
        new Texto("con el módulo de comando en órbita y", Game.WIDTH / 2, 300, false),
        new Texto("gracias a esto la tripulación del Apolo 11", Game.WIDTH / 2, 300, false),
        new Texto("fue capaz de regresar a la tierra.", Game.WIDTH / 2, 300, false),
        new Texto("En una entrevista Katherine declaró:", Game.WIDTH / 2, 300, false),
        new Texto("\"Había hecho los cálculos y sabía que eran correctos...", Game.WIDTH / 2, 300, false),
        new Texto("pero era como conducir, cualquier cosa podía pasar\"", Game.WIDTH / 2, 300, false),
        new Texto("Hoy en día podemos decir que los cálculos de Katherine", Game.WIDTH / 2, 300, false),
        new Texto("Estuvieron bien.", Game.WIDTH / 2, 300, false),
        new Texto("A partir de ahora, eres un astronauta", Game.WIDTH/ 2, 300, false),
        new Texto("Debes llevar la nave a la luna y confiar en Katherine", Game.WIDTH / 2, 300, false), 
        new Texto("Regresarás a salvo, éxito en la misión.", Game.WIDTH / 2, 300, false),
        new Texto("Recuerda:", Game.WIDTH/ 2, 300, false),
        new Texto("\"En un tiempo donde todos pensaban en cómo llegar...",Game.WIDTH / 2, 300, false),
        new Texto("y nadie en cómo regresar... ",Game.WIDTH/ 2, 300, false),
        new Texto("Jonhson se adelantó, supo cómo volver\"",Game.WIDTH/ 2, 300, false)
    };
    private final Texto MENSAJEMATH[]={
        new Texto("Katherine Johnson, la calculadora humana.", Game.WIDTH / 2, 300, true),
        new Texto("Trabajaba en la aeronáutica como \"calculadora humana\"",Game.WIDTH / 2, 300, false),
        new Texto("y realizaba los cálculos requeridos de manera manual",Game.WIDTH/ 2, 300, false),
        new Texto("Además, realizó los cálculos que, entre 1960 y 1969,", Game.WIDTH / 2, 300, false),
        new Texto("enviaron a los astronautas al espacio.", Game.WIDTH/ 2, 300, false),
        new Texto("Fue profesora y siempre mostró curiosidad,", Game.WIDTH/ 2, 300, false),
        new Texto("siempre sorprendía a todos con su astucia.", Game.WIDTH / 2, 300, false),
        new Texto("Katherine siempre quería saber el porqué de todo", Game.WIDTH/ 2, 300, false),
        new Texto("Ahora eres Katherine y podrás vivir", Game.WIDTH / 2, 300, false),
        new Texto("una parte muy pequeña de su trabajo.", Game.WIDTH / 2, 300, false),
        new Texto("Deberás hacer cálculos sencillos, pero recuerda", Game.WIDTH / 2, 300, false),
        new Texto("Katherine y su equipo de trabajo", Game.WIDTH / 2, 300, false),
        new Texto("eran más eficientes que una calculadora.", Game.WIDTH / 2, 300, false)
            

    };
    private final Texto MENSAJELIBRARY[] = {
        new Texto("Dorothy Vaughan, la primera supervisora.", Game.WIDTH / 2, 300, true),
        new Texto("Fue una profesora de matemáticas", Game.WIDTH / 2, 300, false),
        new Texto("que se convirtió en una de las principales", Game.WIDTH / 2, 300, false),
        new Texto("ingenieras matemáticas en los comienzos de", Game.WIDTH / 2, 300, false),
        new Texto("la industria aeroespacial.", Game.WIDTH / 2, 300, false),
        new Texto("Era segregada por ser de color,",Game.WIDTH / 2, 300, false),
        new Texto("aun así, logró ser ascendida a gerente del área oeste", Game.WIDTH/ 2, 300, false),
        new Texto("de Computación. Con su ascenso se convirtió", Game.WIDTH/ 2, 300, false),
        new Texto("en una defensora dedicada de las empleadas", Game.WIDTH/ 2, 300, false),
        new Texto("Dorothy hacía cálculos manuales junto con otras mujeres.", Game.WIDTH/ 2, 300, false),
        new Texto("Cuando la tecnología avanzó, su trabajo se vio en riesgo", Game.WIDTH / 2, 300, false),
        new Texto("las computadoras iban a reemplazarla, por lo que decidió", Game.WIDTH / 2, 300, false), 
        new Texto("hacerse experta en FORTRAN para así", Game.WIDTH/ 2, 300, false),
        new Texto("poder usar las computadoras.", Game.WIDTH/ 2, 300, false),
        new Texto("Para aprender tuvo que escabullirse a la biblioteca,", Game.WIDTH / 2, 300, false),
        new Texto("leer a escondidas y ocultarse de los guardias.", Game.WIDTH / 2, 300, false),
        new Texto("Ahora eres Dorothy y debes buscar los libros que necesitas.", Game.WIDTH / 2, 300, false),
        new Texto("¡Evita que los guardias te vean!", Game.WIDTH / 2, 300, false)
    };
    private final Texto CREDITOS[] = {
        new Texto("Hera's Legacy", Game.WIDTH / 2, y , true),
        new Texto("Directors:", Game.WIDTH / 2, y +2*25, true),
        new Texto("Jonatha Arias", Game.WIDTH / 2, y + 3 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 4 * 25, true),
        new Texto("Enrique Niebles", Game.WIDTH / 2, y + 5 * 25, true),
        new Texto("Enrique Miranda", Game.WIDTH / 2, y + 6 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 7 * 25, true),
        new Texto("Game Desing: ", Game.WIDTH / 2, y +  9* 25, true),
        new Texto("Jonatha Arias", Game.WIDTH / 2, y + 10 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 11 * 25, true),
        new Texto("Enrique Niebles", Game.WIDTH / 2, y + 12 * 25, true),
        new Texto("Enrique Miranda", Game.WIDTH / 2, y + 13 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 14 * 25, true),
        new Texto("Sound:", Game.WIDTH / 2, y + 16 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 17 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 18 * 25, true),
        new Texto("Programming:", Game.WIDTH / 2, y + 20 * 25, true),
        new Texto("Jonatha Arias", Game.WIDTH / 2, y + 21 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 22 * 25, true),
        new Texto("Enrique Niebles", Game.WIDTH / 2, y + 23 * 25, true),
        new Texto("Enrique Miranda", Game.WIDTH / 2, y + 24 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 25 * 25, true),
        new Texto("Art:", Game.WIDTH / 2, y + 27 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 28 * 25, true),
        new Texto("Debug:", Game.WIDTH / 2, y + 30 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 31 * 25, true),
        new Texto("Testing team:", Game.WIDTH / 2, y + 33* 25, true),
        new Texto("Enrique Miranda", Game.WIDTH / 2, y + 34 * 25, true),
        new Texto("Alvaro Cabrera", Game.WIDTH / 2, y + 35 * 25, true),
        new Texto("Dilan Triana", Game.WIDTH / 2, y + 36 * 25, true),
        new Texto("Documentation:", Game.WIDTH / 2, y + 38 * 25, true),
        new Texto("Enrique Niebles", Game.WIDTH / 2, y + 39 * 25, true),
        new Texto("Desing:", Game.WIDTH / 2, y + 41 * 25, true),
        new Texto("Enrique Miranda", Game.WIDTH / 2, y + 42 * 25, true),
        new Texto("Manager:", Game.WIDTH / 2, y + 44 * 25, true),
        new Texto("Jonatha Arias", Game.WIDTH / 2, y + 45 * 25, true),
        new Texto("Sound Track:", Game.WIDTH / 2, y + 47 * 25, true),
        new Texto("Final Fantasy IX-Crossing Those Hills", Game.WIDTH / 2, y + 48 * 25, true),
        new Texto("Final Fantasy VII-One Winged Angel", Game.WIDTH / 2, y + 49 * 25, true),     
        new Texto("Final Fantasy VII-Prelude", Game.WIDTH / 2, y + 50 * 25, true),
        new Texto("Final Fantasy VII-Victory Fanfare", Game.WIDTH / 2, y + 51 * 25, true),
        new Texto("Phoenix Wright-Age, Regret, Reward", Game.WIDTH / 2, y + 52 * 25, true),     
        new Texto("Pokemon Blue/Red-Pallet town", Game.WIDTH / 2, y + 53 * 25, true),     
        new Texto("The Legend of Zelda:Ocarina of Time- Temple of Time", Game.WIDTH / 2, y + 54 * 25, true),     
        new Texto("Agradecemos de corazon a todos los que participaron en esto", Game.WIDTH / 2, y + 58 * 25, true),     
        new Texto("Te agradecemos mucho más a ti por jugar.", Game.WIDTH / 2, y + 59 * 25, true),     
        new Texto("En honor a:", Game.WIDTH / 2, y + 63 * 25, true),     
        new Texto("Katherine Johnson y Dorothy Vaughan", Game.WIDTH / 2, y + 64 * 25, true),     
        new Texto("\"Ama lo que haces y entonces, darás lo mejor de ti\"", Game.WIDTH / 2, y + 65 * 25, true),     
        new Texto("-Katherine Johnson", Game.WIDTH / 2+200, y + 66 * 25, true),             
        new Texto("Este proyecto fue realizado con fines academicos", Game.WIDTH / 2, y + 69 * 25, true),             
        new Texto("Realizado para: ", Game.WIDTH / 2, y + 70 * 25, true),                     
    };
    private final Tile[] PISO = {
        Tile.spacePices[3],
        Tile.woodFloor,
        Tile.floorL1,
        Tile.spacePices[3]};
    private final Tile[] PARED = {
        Tile.spacePices[0],
        Tile.woodWall,
        Tile.estanterias[0],
        Tile.spacePices[1]

    };

    public Ghost(int indiceLevel) {
        this.indiceLevel = indiceLevel;//La idea es utilizar esto para hacer tipo un switch
        switch (indiceLevel) {
            default:
                this.maxMensajes=MENSAJESPACE.length;
                A = new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i] = this.MENSAJESPACE[i];
                }
                break;
            case 1:
                this.maxMensajes=MENSAJEMATH.length;
                A= new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i] = this.MENSAJEMATH[i];
                }
                break;
            case 2:
                this.maxMensajes=MENSAJELIBRARY.length;
                A= new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i] = this.MENSAJELIBRARY[i];
                }
                break;
            case 3:
                this.maxMensajes = CREDITOS.length;
                A = new Texto[maxMensajes];
                for (int i = 0; i < maxMensajes; i++) {
                    A[i] = this.CREDITOS[i];
                }
                break;
        }
        SOUND[0].changeVolume(-2);
        SOUND[1].changeVolume(-2);
        if(indiceLevel!=3)SOUND[1].play();
        else SOUND[0].loop();
    }

    @Override
    public void update() {
        KeyBoard.escape=false;
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

        if (cont[1] == 57 && indiceLevel != 3 ) {//Mostrar mensajes
            if (cont[2] < maxMensajes) {
                cont[2]++;
                A[0].setVisible(cont[2], A);
            }
            cont[1] = 0;
            dm = LocalTime.now();
        } else if (indiceLevel == 3 && cont[1] == 59) {
                for (int i = 0; i < A.length; i++) {
                    A[i].setPosy(A[i].getPosy()-25);
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
        if (cont[0] == 57 - maxMensajes * 3 && indiceLevel!=3) {
            cambio = true;// Aquí se dice cuando se "Acabo" la presentación
        }
        if(indiceLevel==3 && A[maxMensajes-1].getPosy()<=0) cambio=true;

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
        player = new Player(2*16,4*16);
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
            case 3:
                player.setSprites(Sprite.hera_up, Sprite.hera_down, Sprite.hera_rigth, Sprite.hera_left);
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
        Texto text[] = this.MENSAJESPACE;
        switch (indiceLevel) {
            case 1:
                text = this.MENSAJEMATH;
                break;
            case 2:
                text = this.MENSAJELIBRARY;
                break;
            case 3:
                text = this.CREDITOS;
                break;
        }
        return text;
    }

    @Override
    public void setText(String c) {
    }

    @Override
    public Color getColor() {
        Color color = Color.WHITE;
        if (indiceLevel != 0 && indiceLevel != 1 && indiceLevel!=3) {
            color = Color.BLACK;
        }
        return color;
    }

    @Override
    public Level levelCambio() {
        KeyBoard.setKeysStatic(false, KeyEvent.VK_ESCAPE);
        //Se debería implementar un codigo parecido al deLobby para decir que cambio hacer
       
        Level game = new Level("/levels/fantasma/nivelFantasma.png","/levels/fantasma/nivelFantasma.png", new Ghost(indiceLevel));
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
            case 3:
                Game.startGame=false;
                Lobby.levels[0]=false;
                Lobby.levels[1]=false;
                Lobby.levels[2]=false;
                break;
        }
         SOUND[0].stop();
        SOUND[1].stop();
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

    @Override
    public void stopAll() {
        
    }

}
