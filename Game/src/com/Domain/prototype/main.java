/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype;

import javax.swing.JFrame;

/**
 *
 * @author Domain
 */
public class main {
    public static void main(String[]arg) throws InterruptedException{
        Game game = new Game();
        //Mantiene las dimensiones establecidas en el objeto, y deshabilita al usuario para cambiar la misma
        game.frame.setResizable(false);
        game.frame.setTitle("Hera's Legacy");
        //genera al objeto como componente del Frame (Gracias a la extensión Canvas)
        game.frame.add(game);
        //Mantiene las dimensiones del componente game
        game.frame.pack();
        //Cierra el proceso cuando cierras la ventana
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centra la ventana
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        

        
        game.start();
        
    }
}
