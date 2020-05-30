/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.level.Level;
import com.heraslegacy.level.Lobby;
import com.heraslegacy.level.SpaceLevel;
import com.heraslegacy.level.levelStrategy;
import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.KeyBoard;
import com.heraslegacy.manager.Mouse;
import java.awt.event.KeyEvent;

/**
 *
 * @author Domain
 */
public class MenuGUI {
    private Sprite fondo = Sprite.menuGUI;
    private int cordX=screen.width/2-75, cordY=screen.height/2-75;
    Button botones[] = {
        new Button(Sprite.botonesGUI[0],Sprite.botonesGUI[0], screen.width/2-43, screen.height/2-4*16,0,10),
        new Button(Sprite.botonesGUI[1],Sprite.botonesGUI[1], screen.width/2-43, screen.height/2-2*16,0,10)
    };
    private boolean visible, isLobby;
    private levelStrategy actualLevel;
    
    public void uptade(Level actualLevel){
        this.actualLevel = actualLevel.levelstrategy;
        visible=KeyBoard.escape;
        if(visible){
            splitAll();
            mouseOptionsTracker();
        }
    }
    
    public void mouseOptionsTracker(){
        
        if(botones[0].onZone()){  
            if(Mouse.click){
                KeyBoard.setKeysStatic(false, KeyEvent.VK_ESCAPE);
            }
        }
        if(botones[1].onZone()){
            if(Mouse.click){
                if(!(actualLevel instanceof Lobby))((SpaceLevel)actualLevel).win=true;
            }
        }
    }

    private void splitAll(){
        screen.renderSprite(false, cordX, cordY, fondo);
        for (Button boton : botones) {
            boton.split();
        }
    }

    public void setIsLobby(boolean isLobby) {
        this.isLobby = isLobby;
    }
}
