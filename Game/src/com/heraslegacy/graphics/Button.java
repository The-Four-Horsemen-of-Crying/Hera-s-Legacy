/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import static com.heraslegacy.main.Game.scale;
import static com.heraslegacy.main.Game.screen;
import com.heraslegacy.manager.Mouse;

/**
 *
 * @author Domain
 */
public class Button {
    private Sprite [] images;
    private int x,y,x1,y1,indiImage=0;

    public Button(Sprite image,Sprite superImage, int x, int y,int fixX, int fixY){
        images = new Sprite[2];
        this.images[0]=image;
        this.images[1]=superImage;
        this.x = x;
        this.y = y;
        this.x1=x+image.getWidth()-fixX;
        this.y1=y+image.getHeight()-fixY;
    }


    public Sprite getImage(int i) {
        return images[i];
    }

    public int getX() {
        return x;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getY() {
        return y;
    }

    public void setIndiImage(int indiImage) {
        this.indiImage = indiImage;
    }
    
    
    public boolean onZone(){
        int mX=Mouse.mouseX;
        int mY=Mouse.mouseY;
        boolean b = mX<x1*scale&&mX>x*scale&&mY<y1*scale&&mY>y*scale;
        indiImage = b?1:0;
        //System.out.println(mX + "   ||  " + x + "   ||  " + x1); 
        return b;      
    }

    void split() {
        screen.renderSprite(false, x, y, images[indiImage]);
    }
    
}
