/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.entity;

import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.manager.KeyBoard;


/**
 *
 * @author HP
 */
public class Ship extends Mov {
    private Vector2D position, velocity;
    private  Sprite sprite;
    private KeyBoard input;
    private double angle, maxv;
    public Ship(Vector2D position, Vector2D velocity,double maxv,KeyBoard input){
    this.position=position;
    this.input=input;
    this.velocity = velocity;
    angle = 0;
    this.maxv=maxv;
    }

    private boolean collision(){
    return true;
    }
    
    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
    private Vector2D getCenter(){
        return new Vector2D(position.getX()/2, position.getY()/2);
    }
}
