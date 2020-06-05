/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.level.Fantasma;
import com.heraslegacy.level.Level;
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
public class MenuGUI extends GUI{


    private levelStrategy actualLevel;

    public MenuGUI(){
        super(Sprite.menuGUI, screen.width/2-75, screen.height/2-75, false);
        setBotones(
            new Button(Sprite.botonesGUI[0],Sprite.botonesGUI[1], screen.width/2-43, screen.height/2-4*16,0,10),
            new Button(Sprite.botonesGUI[2],Sprite.botonesGUI[3], screen.width/2-43, screen.height/2-2*16,0,10),
            new Button(Sprite.botonesGUI[4],Sprite.botonesGUI[5], screen.width/2-43, screen.height/2-0*16,0,10)
        );
    }
    
    public void uptade(levelStrategy theLevel){
        actualLevel=theLevel;
        if(!(actualLevel instanceof Fantasma))visible=KeyBoard.escape;
        if(visible){
            splitAll();
            mouseOptionsTracker();
        }
    }
    
    @Override
    public void mouseOptionsTracker(){
    
        if(botones[0].onZone()){
            if(Mouse.click){
                KeyBoard.setKeysStatic(false, KeyEvent.VK_ESCAPE);
            }
        }
        if(botones[1].onZone()){
            if(Mouse.click){
                actualLevel.backWithoutWin();
                if(actualLevel==null)System.out.println("asdasd");
                KeyBoard.setKeysStatic(false, KeyEvent.VK_ESCAPE);
            }
        }
        if(botones[2].onZone()){
            if(Mouse.click)System.exit(0);
        }
    }
}
