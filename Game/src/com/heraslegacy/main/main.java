package com.heraslegacy.main;

import javax.swing.JFrame;

public class main {

    public static void main(String[] arg) throws InterruptedException {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();

    }
}
