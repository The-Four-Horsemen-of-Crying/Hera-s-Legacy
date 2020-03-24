/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.entity.movil;


import static com.Domain.prototype.Game.height;
import static com.Domain.prototype.Game.screen;
import static com.Domain.prototype.Game.activarMecanica;
import com.Domain.prototype.entity.Entity;
import com.Domain.prototype.level.tile.Mesa;
import com.Domain.prototype.level.tile.Tile;



public abstract class Movil extends Entity {
    
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
        for (int corners = 0; corners < 4; corners++) {
            int xLimit =((x+xMove)+corners%2*14-8)/16;
            int yLimit =(((y+yMove)+corners/2*12+3)/16);
            Tile nextTile = level.getTile(xLimit,yLimit);
            //Tile notNextButClose = level.getCollision(xMove, yMove);
            
//            if (notNextButClose == Mesa.mesa||notNextButClose == Mesa.mesa2||notNextButClose == Mesa.mesa3||notNextButClose == Mesa.mesa4){ //if(level.mecanica()==false){System.exit(0);}}
//                        //Enviar bool a Game
//                //System.out.println("es mesa");
//                        
//            }
            if(nextTile.solid()) solid = true;
            
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
