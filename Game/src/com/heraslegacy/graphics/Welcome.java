/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import com.heraslegacy.level.Level;
import com.heraslegacy.level.Lobby;
import com.heraslegacy.main.Game;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.Mouse;
import javax.swing.JFrame;

/**
 *
 * @author Domain
 */
public class Welcome extends GUI{

    private Level level;
    private Sprite pageBackG = Sprite.startMenu[1];
    private int page=0, timeCounter=0;
    
    
    public Welcome(){
        super(Sprite.startMenu[0], 0, 0, true);
        setBotones(
            new Button(Sprite.botonesGUI[6],Sprite.botonesGUI[7], 0*86+10,screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[8],Sprite.botonesGUI[9], 1*86+20, screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[10],Sprite.botonesGUI[11], 2*86+30, screen.height-2*16,0,10),
            new Button(Sprite.botonesGUI[12],Sprite.botonesGUI[13], 0*86+10, 0,0,10)
        );
    }
    
    @Override
        public void mouseOptionsTracker(){
        if(timeCounter==0){
            if (page == 0) {
                if (botones[0].onZone()) {
                    if (Mouse.click) {
                        level = new Level("/levels/lobby/lobby.png", "/levels/lobby/collisionlobby.png", new Lobby());
                        level.configPlayer();
                    }
                }
                if (botones[1].onZone()) {
                    if (Mouse.click)page = 1;

                }
                if (botones[2].onZone()) {
                    if (Mouse.click)System.exit(0);
                } 
            }
            else if (page == 1) {
                if (botones[3].onZone()) 
                    if (Mouse.click)page = 0;
            }
        }
    }

    public Level getLevelStart() {
        return this.level;
    }
    
    @Override
    protected void splitAll(){
        if (timeCounter == 0) {
            switch (page) {
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
    
    public int passToIntro(int time){
        if(level==null)return time;
        timeCounter++;
        
        if(timeCounter%5000==0){
            time--;
            timeCounter=1;
        }
        screen.renderSprite(true, screen.width/2-75, screen.height/2-50, Sprite.startMenu[2]);
        return time;
    }
}
