/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet spriteSheet = new SpriteSheet("/textures/spriteSheet.png", 225);//La ruta y el tamaño de todo el pack
    public static SpriteSheet nivel01_mat = new SpriteSheet("/textures/mini_game01.png", 96);

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
