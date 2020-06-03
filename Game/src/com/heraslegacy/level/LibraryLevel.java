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
import java.awt.Color;
import java.awt.Font;
import java.time.LocalTime;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ejose
 */
public class LibraryLevel implements levelStrategy{
    //escenario
    //personaje
    //mecanica
    private int width;
    private int height;
    private int[] tiles; 
    private int[] tilesCollision;
    private final Font LIBRARYFONT = Fuente.spaceFont;
    private boolean libros[] ={false, false, false, false};
    private boolean win = true;
    private final int[] ZONECOLOR ={Colors.yellow.getColor(),Colors.blue.getColor(),Colors.white.getColor(),Colors.darkred.getColor()};//Los colores que diferencian cada zona
    private final int[] VISUALRANGE ={Colors.orange.getColor(),Colors.lessdarkred.getColor(),Colors.clearblue.getColor(),Colors.green.getColor()};//Los colores de a que lugar están viendo
    private int zone;
    private int pattern[],direction=0;
    private int text1 = 2, text2 = 10;
    private LocalTime ant = LocalTime.now();
    private LocalTime ant2= LocalTime.now();
    private Player player;
    private final Sound sound[] = {new Sound(Sound.backgroundLB), new Sound(Sound.fail), new Sound(Sound.pickup)};
    private Color colorTexto= Color.MAGENTA;
    private final Texto textLibrary[]= {
        new Texto("Necesito reunir 4 libros a escondidas",50,100,true),            //0
        
        new Texto("",50,100,false),                                                //1
        
        new Texto("Cuidado con los guardias Dorothy",50,100,false),                //2
        new Texto("Tú puedes Dorothy!",50,100,false),                              //3
        new Texto("Con esos libros puedo mejorar",50,100,false),                   //4
        new Texto("Manual... IBM...",50,100,false),                                //5
        new Texto("Debo seguir buscando",50,100,false),                            //6
        new Texto("Necesito esos libros",50,100,false),                            //7
        new Texto("Debo evitar perder mi trabajo",50,100,false),                   //8
        new Texto("He de aprender sobre esas maquinas",50,100,false),              //9
        
        new Texto("QUÉ HACE? FUERA DE AQUÍ",50,100,false),                         //10
        new Texto("¡LARGO!",50,100,false),                                         //11
        new Texto("¡No quiero verla por aquí!",50,100,false),                      //12
        new Texto("Me pareces conocida, largo de aquí",50,100,false),              //13
        new Texto("¿Otra vez? Ya conoces el camino",50,100,false),                 //14
        new Texto("Ya sabes que cerramos, cierto?",50,100,false),                  //15
        new Texto("No es gracioso...",50,100,false),                               //16
        new Texto("¿Necesitas ayuda?",50,100,false),                               //17
        new Texto("Si quieres te acompañamos",50,100,false),                       //18
        new Texto("A la proxima trae algo de tomar",50,100,false),                 //19
        new Texto("Ya basta de juegos",50,100,false),                              //20
        new Texto("No te podemos ver aquí",50,100,false),                          //21
        new Texto("You are filled with DETERMINATION <3",50,100,false)             //22
    };

    public LibraryLevel(){
        this.pattern = new int[]{0, 3, 2, 1};
        sound[0].loop();
    }
    
    @Override
    public void update() {
        LocalTime text = LocalTime.now().minusSeconds(ant2.getSecond());
        if (text.getSecond() >= 5) {
            hide();
        }
        if (text.getSecond() >= 30) {
            hide();
            colorTexto = Color.MAGENTA;
            ant2 = LocalTime.now();
            textLibrary[text1].setVisible(true);
            text1++;
            if (text1 > 9) {
                text1 = 3;
            }
        }

    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)                 return Tile.pikes;
        if (tiles[x + y * width] == Colors.yellow.getColor())           return Tile.puertaS[zone];
        if (tiles[x + y * width] == Colors.blue.getColor())             return Tile.puertaE[zone];
        if (tiles[x + y * width] == Colors.fuchsia.getColor())          return Tile.paredLibrary;
        if (tiles[x + y * width] == Colors.red.getColor())              return Tile.estanterias[0];
        if (tiles[x + y * width] == Colors.higdarkred.getColor())       return Tile.estanterias[1];
        if (tiles[x + y * width] == Colors.darkred.getColor())          return Tile.estanterias[2];
        if (tiles[x + y * width] == Colors.lessdarkred2.getColor())     return Tile.estanterias[3];
        if (tiles[x + y * width] == Colors.green.getColor())            return Tile.libro;
        
        for (int i = 0; i < 4; i++) {

            if (tiles[x + y * width] == Colors.golden.getColor()
              &&tilesCollision[x + y * width] == VISUALRANGE[i]
              &&i==direction)                                           return Tile.floorL2D;
            
            if (tiles[x + y * width] == Colors.purpleDark1.getColor()
              &&tilesCollision[x + y * width] == VISUALRANGE[i]
              &&i==direction)                                           return Tile.floorL1D;
            
            if (tiles[x + y * width] == Colors.somekindblue.getColor()
              &&tiles[x + (y+1) * width] == Colors.purpleDark.getColor()
              &&tilesCollision[x + (y-2) * width] == VISUALRANGE[i])    return Tile.guardia[pattern[i]][0];
            
            if (tiles[x + y * width] == Colors.purpleDark.getColor()
              &&tiles[x + (y+1) * width] == Colors.somekindblue.getColor()
              &&tilesCollision[x + (y-2) * width] == VISUALRANGE[i])    return Tile.guardia[pattern[i]][1];
            
            if (tiles[x + y * width] == Colors.somekindblue.getColor()
              &&tiles[x + (y-1) * width] == Colors.purpleDark.getColor()
              &&tilesCollision[x + (y-2) * width] == VISUALRANGE[i])    return Tile.guardia[pattern[i]][3];
            
            if (tiles[x + y * width] == Colors.purpleDark.getColor()
              &&tiles[x + (y-1) * width] == Colors.somekindblue.getColor()
              &&tilesCollision[x + (y-2) * width] == VISUALRANGE[i])    return Tile.guardia[pattern[i]][2];
        }
        if (tiles[x + y * width] == Colors.purpleDark1.getColor())      return Tile.floorL1;
        if (tiles[x + y * width] == Colors.golden.getColor())           return Tile.floorL2;
        
        return Tile.pikes;
    }

    @Override
    public boolean getCollision(int x, int y) {
        if (tilesCollision[(x>>4)+(y>>4)*width] == Colors.fuchsia.getColor()){
            libros[zone]=true;
            tilesCollision[(x>>4)+(y>>4)*width] = ZONECOLOR[zone];
            colorTexto = Color.GREEN;
            hide();
            ant2 = LocalTime.now();
            if (numLibros() != 0) {
                textLibrary[1].setText("Solo faltan " + numLibros() + " libros");
            }else{
                textLibrary[1].setText("Los tengo todos, ahora a salir de aquí");
            }
            textLibrary[1].setVisible(true);
            sound[2].play();
            tiles[(x>>4)+(y>>4)*width] = Colors.purpleDark1.getColor();
            Tile.puertaS[zone].setSolid(false);
        }
        
        for (int i = 0; i < 4; i++) {
            if (tilesCollision[(x>>4)+(y>>4)*width] == ZONECOLOR[i]){
                zone=i;
                if(Tile.puertaE[zone].solid==false && libros[zone]==true) Tile.puertaE[zone].setSolid(true);
                return false;
            }
        }
        
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
    public void time() {
        LocalTime rest = LocalTime.now().minusSeconds(ant.getSecond());
        if(rest.getSecond()>=4){
            ant=LocalTime.now();
            direction++;
            
            if(direction==4) direction=0;
            
            for (int i = 0; i < 4; i++) {
                pattern[i]++;
                if(pattern[i]==4) pattern[i]=0;
            }
        }
    }

    @Override
    public void mecanica() {
        if(Tile.puertaS[zone].solid==false){ Tile.puertaS[zone].setSolid(true);}
        time();
        if (tilesCollision[(player.getX()>>4)+(player.getY()>>4)*width] == VISUALRANGE[direction]){
            colorTexto = Color.BLUE;
            sound[1].play();
            hide();
            textLibrary[text2].setVisible(true);
            ant2 = LocalTime.now();
            text2++;
            if(text2>22) text2 = 10;
            restar();
        }
    }

    @Override
    public void restar() {
        for (int i = 0; i < 4; i++) {
            libros[i]=false;
            Tile.puertaE[i].setSolid(false);
        }
        pattern[0]=0; pattern[1]=3; pattern[2]=2; pattern[3]=1;
        direction=0;
        player.setX(25);
        player.setY(400);
        hide();
        textLibrary[0].setVisible(true);
        text1 = 2;
        text2 = 10;
        ant = LocalTime.now();
        ant2 = LocalTime.now();
        loadLevel("/levels/level03/nivel3.png","/levels/level03/nivel3COLLITION.png");
    }

    @Override
    public boolean cambio() {
        boolean boo = true;
        for (boolean libro : libros) {
            boo = boo && libro;
        }
        return boo && !textLibrary[2].isVisible();
    }

    @Override
    public void configPlayer(Level level) {
        player = new Player(25, 400);
        player.setSprites(Sprite.dorothy_up, Sprite.dorothy_down, Sprite.dorothy_rigth, Sprite.dorothy_left);
        player.setAjustes(14, 8, 12, 3, 16 ,16, new Sound(Sound.walk));
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
        return textLibrary;
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
            Lobby.levels[2] = win;
            sound[0].stop();
            return new Level("/levels/lobby/lobby.png", "/levels/lobby/collisionlobby.png", new Lobby());
    }

    @Override
    public Font getFont() {
        return this.LIBRARYFONT;
    }

    @Override
    public void sobreRender(int xScroll, int yScroll) {
    }

    @Override
    public void render() {
        
    }

    @Override
    public void backWithoutWin() {
        for (boolean libro : libros) {
            libro = true;
        }
        textLibrary[2].setVisible(false);
        win = false;
    }

    private boolean isShown(Texto[] textLibrary) {
        for (Texto texto : textLibrary) {
            if(texto.isVisible()) return true;
        }
        return false;
    }

    private void hide() {
        for (Texto texto : textLibrary) {
            texto.setVisible(false);
        }
    }
    
    private int numLibros(){
        int cont = 0;
        for (boolean libro : libros) {
            if(!libro) cont++;
        }
        return cont;
    }
}
