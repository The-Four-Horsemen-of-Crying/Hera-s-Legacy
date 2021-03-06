/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;
    
    public static SpriteSheet elizabeth = new SpriteSheet("/textures/Elizabeth.png", 225);//La ruta y el tamaño de todo el pack
    public static SpriteSheet apolo11 = new SpriteSheet("/textures/apolo11.png", 144);
    public static SpriteSheet hera = new SpriteSheet("/textures/persona_000.png", 128);
    public static SpriteSheet katherine = new SpriteSheet("/textures/persona_001.png", 128);
    public static SpriteSheet dorothy = new SpriteSheet("/textures/persona_003.png", 128);
    public static SpriteSheet nivel00_lobby = new SpriteSheet("/textures/textuLobby.png", 96);
    public static SpriteSheet nivel01_mat = new SpriteSheet("/textures/mini_game01.png", 96);
    public static SpriteSheet nivel02_space = new SpriteSheet("/textures/mini_game02.png", 96);
    public static SpriteSheet nivel03_library = new SpriteSheet("/textures/mini_game032.png", 96);
    public static SpriteSheet single_DrawMoon = new SpriteSheet("/textures/moon.png", 96);
    public static SpriteSheet LobbyDoors = new SpriteSheet("/textures/portales.png", 144);
    public static SpriteSheet HojasnivelGame01_mat = new SpriteSheet("/levels/level01/ejerciciosSheet2.png", 450);
    public static SpriteSheet single_Menu = new SpriteSheet("/GUI/menu.png", 150);
    public static SpriteSheet GUI = new SpriteSheet("/GUI/gui.png", 191);
    public static SpriteSheet startScreen = new SpriteSheet("/GUI/back.png", 300);
    public static SpriteSheet single_ManualPage = new SpriteSheet("/GUI/page1.png", 300);
    public static SpriteSheet single_LogoWstem = new SpriteSheet("/logos/Wstem.png",255);
    
    

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }


    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            //Leer descripción del método, se entiendebien
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
