/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.entity.movil;


import com.Domain.prototype.entity.Entity;
import com.Domain.prototype.graphics.Sprite;


public abstract class Movil extends Entity {
    
    protected Sprite sprite;
    protected int direccion = 0; //0=up||1=rigth||2=down||3=left
    protected boolean moving = false;
    protected int ani=0,ani2=0;
    
    public void move(int xMove, int yMove){
        if(yMove<0)direccion=0;//si se mueve a abajo
        if(xMove>0)direccion=1;//" " derecha
        if(yMove>0)direccion=2;//" " arriba
        if(xMove<0)direccion=3;//" " izquierda
        if(ani%2==0){
            ani2++;
        }
        ani++;
        
        if(!collision()){
            x+=xMove;   //on entity
            y+=yMove;   //on entity
        }
    }
    
    public void uptade(){
    }
    
    private boolean collision(){
        return false;
    }
    public void render(){
    }
}
