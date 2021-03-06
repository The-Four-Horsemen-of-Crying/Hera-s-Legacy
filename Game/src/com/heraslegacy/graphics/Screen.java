/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.level.tile.Tile;

public class Screen {

    public int width;
    public int height;
    public int[] pixels; //Lo que se ve
    public int xOffSet;
    public int yOffset;
    

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    //Metodo para limpiar los pixeles que estaban antes
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(boolean fixed, int xPosition, int yPosition, Sprite sprite){
        if(fixed){
            xPosition-=xOffSet;
            yPosition-=yOffset;
        }
        //458 && 249
        for (int y = 0; y < sprite.getHeight(); y++) {
                int ya= y+yPosition;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa=x+xPosition;
                if(xa<0||xa>=width||ya<0||ya>=height)continue;
                int fondo = sprite.pixels[x+y*sprite.getWidth()];

                if(fondo!=0xff000000&&fondo!=0xffff00ff)pixels[xa+ya*width] = sprite.pixels[x+y*sprite.getWidth()];
            }
            
        }
        
    }
    public void renderTile(int xPosition, int yPosition, Tile tile) {
        xPosition-=xOffSet;
        yPosition-=yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xPosition;                
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;//   xAbsolute < -tile.sprite.SIZE Hace posible renderizar el apartado izquierdo de la pantalla por completo
                if(xAbsolute<0) xAbsolute=0;//Evita un outOfBounds
                int fondo = tile.sprite.pixels[x+y*tile.sprite.getWidth()];
                if(fondo!=0xffff00ff)pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    
    public void renderPlayer(int xPosition, int yPosition, Sprite sprite) {
        xPosition -= xOffSet;
        yPosition -= yOffset;
        for (int y = 0; y < sprite.getHeight(); y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < -sprite.getWidth() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height)break;//   xAbsolute < -tile.sprite.SIZE Hace posible renderizar el apartado izquierdo de la pantalla por completo
                if (xAbsolute < 0)xAbsolute = 0;//Evita un outOfBounds
                int fondo = sprite.pixels[x + y * sprite.getWidth()];
                if(fondo != 0xff000000)pixels[xAbsolute + yAbsolute * width] = sprite.pixels[x + y * sprite.getWidth()];
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
