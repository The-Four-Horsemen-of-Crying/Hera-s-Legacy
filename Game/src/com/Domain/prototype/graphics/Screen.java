/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.graphics;

import com.Domain.prototype.level.tile.Tile;
import static java.lang.System.currentTimeMillis;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Screen {

    public int width;
    public int height;
    private final int MAP_SIZE = 64;
    public int[] pixels;
    public int xOffSet;
    public int yOffset;

    //Testing Phase
    private int tilesIndex;
    private int[] Tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();
    private final int MAP_SIZE_MINUS = MAP_SIZE - 1;
    private int speed = 1;
    

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

//        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
//            Tiles[i] = random.nextInt(0xFFFFFF);//Llenar cada espacio del vector con un color
//        }
    }

    //Metodo para limpiar los pixeles que estaban antes
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

//    public void render(int xOutScreen, int yOutScreen) {
//
//        for (int y = 0; y < height; y++) {
//
//            int yy = y - yOutScreen;    //y + yOutScreen;
//            if (yy >= height || yy < 0) {
//                continue;
//            }
//
//            for (int x = 0; x < width; x++) {
//
//                int xx = x - xOutScreen; //x + xOutScreen 
//                if (xx >= width || xx < 0) {
//                    continue;
//                }
//                    //tilesIndex=((xx>>4)& MAP_SIZE_MINUS)+((yy>>4)&MAP_SIZE_MINUS)*MAP_SIZE;   //Same shit that (x/16)+(y/16)*64 but faster
//
//                pixels[xx + yy * width] = Sprite.floor_mat.pixels[(x & 15) + (y & 15) * Sprite.floor_mat.SIZE]; //Sprite.floor_mat.pixels[(xx&15)+(yy&15)*Sprite.floor_mat.SIZE];
//            }
//        }
//    }

    public void renderTile(int xPosition, int yPosition, Tile tile) {
        xPosition-=xOffSet;
        yPosition-=yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < 0 || xAbsolute >= width || yAbsolute < 0 || yAbsolute > height);//yAbsolute>width
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    //calcular el espacio que avanza el usuario hacia fuera de la ventana.
    public void setOffset(int xOffset, int yOffset){
        this.xOffSet=xOffset;
        this.yOffset=yOffset;
    }
}


//tilesIndex es un valor que definirá el tamaño del tile
