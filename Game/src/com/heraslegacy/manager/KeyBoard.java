package com.heraslegacy.manager;

import com.heraslegacy.level.Lobby;
import com.heraslegacy.level.MathLevel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
//import javafx.scene.input.KeyCode;

public class KeyBoard implements KeyListener {

    private static boolean keys[] = new boolean[500];
    private static boolean keysStatic[] = new boolean[500];
    public static boolean up, down, left, right,restart,delete,enter, space,soltado,coma,escape;
    public static int rate = 2;
    public void uptade() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        restart = keys[KeyEvent.VK_R];
        space = keys[KeyEvent.VK_SPACE];
        delete = keys[KeyEvent.VK_BACK_SPACE];
        enter = keys[KeyEvent.VK_ENTER];
        escape = keysStatic[KeyEvent.VK_ESCAPE];
        coma = keys[KeyEvent.VK_COMMA]||keys[KeyEvent.VK_DECIMAL];

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<500){
            keys[e.getKeyCode()] = true;
            keysStatic[e.getKeyCode()]= keysStatic[e.getKeyCode()]?false:true;
            if(!Lobby.levels[1])MathLevel.assignConcaAnsw(MathLevel.numberInput());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<500){
            keys[e.getKeyCode()] = false;
            rate=2;
        }
    }
    
    public static boolean getKeys(int i){
        return KeyBoard.keys[i];
    }

    public static void setKeysStatic(boolean bool, int ind) {
        KeyBoard.keysStatic[ind] = bool;
    }
    public static boolean getKeysStatic(int i){
        return KeyBoard.keysStatic[i];
    }
}
