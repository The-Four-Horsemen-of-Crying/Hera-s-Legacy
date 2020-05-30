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
    private Sprite image,superImage;
    private int x,y,x1,y1;

    public Button(Sprite image,Sprite superImage, int x, int y,int fixX, int fixY) {
        this.image = image;
        this.superImage=superImage;
        this.x = x;
        this.y = y;
        this.x1=x+image.getWidth()-fixX;
        System.out.println(x1-x);
        this.y1=y+image.getHeight()-fixY;
    }


    public Sprite getImage() {
        return image;
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
    
    
    public boolean onZone(){
        int mX=Mouse.mouseX;
        int mY=Mouse.mouseY;
        //System.out.println(mX + "   ||  " + x + "   ||  " + x1); 
        return mX<x1*scale&&mX>x*scale&&mY<y1*scale&&mY>y*scale;      
    }

    void split() {
        screen.renderSprite(false, x, y, image);
    }
    
}
