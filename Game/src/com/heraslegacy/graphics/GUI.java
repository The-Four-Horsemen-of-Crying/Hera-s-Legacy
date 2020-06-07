/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import static com.heraslegacy.main.Game.screen;
/**
 *
 * @author Domain
 */
public class GUI {
    protected Sprite fondo;
    protected int cordX, cordY;
    protected Button botones[];
    protected boolean visible;

    public GUI(Sprite fondo, int cordX, int cordY, boolean visible) {
        this.fondo = fondo;
        this.cordX = cordX;
        this.cordY = cordY;
        this.visible = visible;
    }
    
    public void uptade(){
        if(visible){
            splitAll();
            mouseOptionsTracker();
        }
    }
    
    public void mouseOptionsTracker(){
    }

    protected void splitAll(){
        screen.renderSprite(false, cordX, cordY, fondo);
        for (Button boton : botones) {
            boton.split();
        }
    }

    protected void setBotones(Button...botones) {
        this.botones = botones;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
