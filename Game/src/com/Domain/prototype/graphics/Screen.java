/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.graphics;

import static java.lang.System.currentTimeMillis;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Domain
 */
public class Screen {
    
    private int width;
    private int height;
    private final int MAP_SIZE=64;
    private final int MAP_SIZE_MINUS=MAP_SIZE-1;
    public int[] pixels;
    private int tilesIndex;
    private int[] Tiles= new int[MAP_SIZE*MAP_SIZE];
    private Random random = new Random();
    private int speed=10;
    
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        for (int i = 0; i < MAP_SIZE*MAP_SIZE; i++) {
            Tiles[i]=random.nextInt(0xFFFFFF);//Llenar cada espacio del vector con un color
        }
    }

    //Metodo para limpiar los pixeles que estaban antes
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void render(int xOutScreen, int yOutScreen) {
        
        for (int y = 0; y < height; y++) {
            int yy = y + yOutScreen*speed;
                                                //if (y >= height || y < 0) break;
            for (int x = 0; x < width; x++) {
                int xx = x+xOutScreen*speed;
                                                //if (x >= width || x < 0) break;
                
                tilesIndex=((xx>>4)& MAP_SIZE_MINUS)+((yy>>4)&MAP_SIZE_MINUS)*MAP_SIZE;//Same shit that (x/16)+(y/16)*64 but faster
                pixels[x + y * width] = Tiles[tilesIndex];
            }
        }
    }
}


//tilesIndex es un valor que definirá el tamaño del tile