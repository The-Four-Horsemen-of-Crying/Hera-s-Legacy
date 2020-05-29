/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;

/**
 *
 * @author Domain
 */
public class MenuGUI {
    private Sprite fondo;
    private Sprite[] botones;
    private int cordX, cordY;
    private boolean visible, isLobby;
    
    void uptade(){
        visible=KeyBoard.escape;
        if(visible){
            splitAll();
            mouseOptionsTracker();
        }
    }
    
    void mouseOptionsTracker(){
        
         
    }

    private void splitAll() {
        
    }

    public void setIsLobby(boolean isLobby) {
        this.isLobby = isLobby;
    }
    
    
    
}
