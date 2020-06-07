/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Fuente {
    public static Font spaceFont;
    public static Font spaceFontSmaller;
    public static Font greekFont;
    public static Font trans;
   
    public static void init(){
        spaceFont=loadFont("/fonts/RobotoMono-Light.ttf", 35);
        spaceFontSmaller=loadFont("/fonts/RobotoMono-Light.ttf", 20);
        greekFont=loadFont("/fonts/Olympus.ttf", 35);
        trans= loadFont("/fonts/AbrilFatface-Regular.ttf",25);
    } 
    public static Font loadFont(String path, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,Font.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (IOException | FontFormatException ex) {        
            return null;
        }
    }
}
