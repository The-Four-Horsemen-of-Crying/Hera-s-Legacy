/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.KeyBoard;
import java.awt.Color;
import static com.heraslegacy.main.Game.SCALE;

/**
 *
 * @author HP
 */
public class Texto {
    private String text;
    private int posx;
    private int posy;
    private boolean visible,backGroundActive;
    private Sprite backBox= Sprite.dialogBox;
    private Sprite subject;
    private Fuente fuente;
    private Color color;
    
    public Texto(String text, int posx, int posy, boolean visible) {
        this.text = text;
        this.posx = posx;
        this.posy = posy;
        this.visible = visible;
        backBox=null;
    }
    
    public Texto(String text, int jumpCordY, boolean visible, Sprite subject){
        this.text=text;
        this.posx=10;
        this.visible=visible;
        this.posy=screen.height*SCALE-backBox.getHeight()*SCALE/2+30*jumpCordY;
        backGroundActive=visible;
        this.subject=subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public boolean isVisible() {
        return visible&&!KeyBoard.escape;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        if(backBox!=null)this.backGroundActive=visible;
        
        }
    public void setVisible(int i, Texto[]s){
        for (int j = 0; j < s.length; j++) {
            if(j!=i){
            s[j].setVisible(false);
            }else{
            s[j].setVisible(true);
            }
        }
    }

    public void showIfActive() {
        if(visible&&backGroundActive&&!KeyBoard.escape){
            screen.renderSprite(false, 0,screen.height-backBox.getHeight()-subject.getHeight()/2 , subject);  //Cuando se añada
            screen.renderSprite(false, 0, screen.height-backBox.getHeight(), backBox);
        }
    }
  
}
