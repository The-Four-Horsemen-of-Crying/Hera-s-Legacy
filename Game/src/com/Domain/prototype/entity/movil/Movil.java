/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.entity.movil;


import com.Domain.prototype.entity.Entity;
import com.Domain.prototype.graphics.Sprite;
import com.Domain.prototype.level.tile.Tile;


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

        
        if(!collision(xMove,0)){
            animación();
            x+=xMove;   //on entity
        }        
        if(!collision(0,yMove)){
            animación();
            y+=yMove;   //on entity
        }
    }
    
    public void uptade(){
    }
    
    private boolean collision(int xMove, int yMove){
        boolean solid=false;
        for (int c = 0; c < 3; c++) {
            int xLimit =((x+xMove)+c%2*14-8)/16;
            int yLimit =(((y+yMove)+c/2*12+3)/16);
            Tile nextTile = level.getTile(xLimit,yLimit);
            if(nextTile.solid()) solid = true;
            System.out.println(solid);
        }
        
        
        
        return  solid;
    }
    public void render(){
    }
    
    public void animación(){
            
            if(ani%30==0){
                ani2++;
                ani=0;
            }
            ani++;
    }
}
