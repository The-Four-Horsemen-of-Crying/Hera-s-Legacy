/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

/**
 *
 * @author HP
 */
public class Texto {
    private String text;
    private int posx;
    private int posy;
    private boolean visible;
    
    public Texto(String text, int posx, int posy, boolean visible) {
        this.text = text;
        this.posx = posx;
        this.posy = posy;
        this.visible = visible;
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
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
  
}
