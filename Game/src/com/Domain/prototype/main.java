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
        Game game = new Game(50,50);
        
        //Game game2=new Game(100,100); Dos hilos no pueden trabajar con un mismo objeto 
        
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
        
//        
//        game2.frame.setResizable(false);
//        game2.frame.setTitle("Hera's Legacy2");
//        //genera como al objeto como componente del Frame (Gracias a la extensión Canvas)
//        game2.frame.add(game);
//        //Mantiene las dimensiones del componente game
//        game2.frame.pack();
//        //Cierra el proceso cuando cierras la ventana
//        game2.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //Centra la ventana
//        game2.frame.setLocationRelativeTo(null);
//        game2.frame.setVisible(true);
        
        game.start();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        //game2.start();
    }
}
