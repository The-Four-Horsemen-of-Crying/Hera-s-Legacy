/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.level.Level;
import com.heraslegacy.level.Lobby;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.Mouse;

/**
 *
 * @author Domain
 */
public class Welcome extends GUI{

    private Level level;
    private Sprite pageBackG = Sprite.b;
    private int page=0;
    public Welcome(){
        super(Sprite.b, 0, 0, true);
        this.level=level;
        setBotones(
            new Button(Sprite.botonesGUI[6],Sprite.botonesGUI[6], 0*86+10,screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[7],Sprite.botonesGUI[7], 1*86+20, screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[8],Sprite.botonesGUI[8], 2*86+30, screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[8],Sprite.botonesGUI[8], 0*86+10, 0,0,10)
        );
    }
    
    @Override
        public void mouseOptionsTracker(){
            if(page==0){
                if(botones[0].onZone()){
                    if(Mouse.click){
                        level=new Level("/levels/lobby/lobby.png", "/levels/lobby/collisionlobby.png", new Lobby());
                        level.configPlayer();
                    }
                }
                if(botones[1].onZone()){
                    if(Mouse.click){
                        page=1;
                    }
                }
                if(botones[2].onZone()){
                    if(Mouse.click)System.exit(0);
                }
            }
            else if (page==1){
                if(botones[3].onZone()){
                    if(Mouse.click)page = 0;
                }
            }
    }

    public Level levelStar() {
        return this.level;
    }
    
    @Override
    protected void splitAll(){
        switch(page){
            case 0:
                screen.renderSprite(false, cordX, cordY, fondo);
                for (int i = 0; i <= 2; i++) {
                    botones[i].split();
                }
            break;
            case 1:
                screen.renderSprite(false, cordX, cordY, pageBackG);
                for (int i = 3; i < botones.length; i++) {
                    botones[i].split();
                }
                break;
        }
    }
}
