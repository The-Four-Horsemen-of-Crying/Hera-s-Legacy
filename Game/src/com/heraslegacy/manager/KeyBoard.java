package com.heraslegacy.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javafx.scene.input.KeyCode;

public class KeyBoard implements KeyListener {

    private static boolean keys[] = new boolean[500];
    private static boolean keysStatic[] = new boolean[500];
    public static boolean up, down, left, right,restart,delete,enter, space,soltado,coma,escape;
    public static boolean numbers[]= new boolean[10];
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
        numbers[1] = keys[KeyEvent.VK_1]||keys[KeyEvent.VK_NUMPAD1];
        numbers[2] = keys[KeyEvent.VK_2]||keys[KeyEvent.VK_NUMPAD2];
        numbers[3] = keys[KeyEvent.VK_3]||keys[KeyEvent.VK_NUMPAD3];
        numbers[4] = keys[KeyEvent.VK_4]||keys[KeyEvent.VK_NUMPAD4];
        numbers[5] = keys[KeyEvent.VK_5]||keys[KeyEvent.VK_NUMPAD5];
        numbers[6] = keys[KeyEvent.VK_6]||keys[KeyEvent.VK_NUMPAD6];
        numbers[7] = keys[KeyEvent.VK_7]||keys[KeyEvent.VK_NUMPAD7];
        numbers[8] = keys[KeyEvent.VK_8]||keys[KeyEvent.VK_NUMPAD8];
        numbers[9] = keys[KeyEvent.VK_9]||keys[KeyEvent.VK_NUMPAD9];
        numbers[0] = keys[KeyEvent.VK_0]||keys[KeyEvent.VK_NUMPAD0];
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
            System.out.println("presed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<500){
            keys[e.getKeyCode()] = false;
            rate=2;
            System.out.println("released");
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
