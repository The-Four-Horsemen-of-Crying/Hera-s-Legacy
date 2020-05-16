package com.heraslegacy.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javafx.scene.input.KeyCode;

public class KeyBoard implements KeyListener {

    private boolean keys[] = new boolean[500];
    private boolean keysStatic[] = new boolean[500];
    public static boolean up, down, left, right,restart,delete,enter, space;
    public static boolean numbers[]= {false,false,false,false,false,false,false,false,false,false};
    
    public void uptade() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        restart = keys[KeyEvent.VK_R];
        space = keys[KeyEvent.VK_SPACE];
        delete = keys[KeyEvent.VK_DELETE];
        enter = keys[KeyEvent.VK_ENTER];
        if(!numbers[1])numbers[1] = keysStatic[KeyEvent.VK_1];
        if(!numbers[2])numbers[2] = keysStatic[KeyEvent.VK_2];
        if(!numbers[3])numbers[3] = keysStatic[KeyEvent.VK_3];
        if(!numbers[4])numbers[4] = keysStatic[KeyEvent.VK_4];
        if(!numbers[5])numbers[5] = keysStatic[KeyEvent.VK_5];
        if(!numbers[6])numbers[6] = keysStatic[KeyEvent.VK_6];
        if(!numbers[7])numbers[7] = keysStatic[KeyEvent.VK_7];
        if(!numbers[8])numbers[8] = keysStatic[KeyEvent.VK_8];
        if(!numbers[9])numbers[9] = keysStatic[KeyEvent.VK_9];
        if(!numbers[0])numbers[0] = keysStatic[KeyEvent.VK_0];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<500){
            keys[e.getKeyCode()] = true;
            keysStatic[e.getKeyCode()]=true;//!keysStatic[e.getKeyCode()]?true:false; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<500){
            keysStatic[e.getKeyCode()]=false;
            keys[e.getKeyCode()] = false;
        }
    }

}
