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
   
    public static void init(){
        spaceFont=loadFont("/fonts/RobotoMono-Light.ttf", 20);
    } 
    public static Font loadFont(String path, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,Font.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (IOException | FontFormatException ex) {        
            return null;
        }
    }
}
