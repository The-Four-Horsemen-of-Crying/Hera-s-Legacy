/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.entity.movil;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.graphics.Sprite;
import com.Domain.prototype.input.KeyBoard;

/**
 *
 * @author Domain
 */
public class Player extends Movil{
    private KeyBoard input;
    private Sprite sprite;
    
    
    public Player(KeyBoard input){
        this.input = input;
    }
    
    public Player(int x, int y, KeyBoard input){
        this.x=x;//on Movil
        this.y=y;//on Movil
        this.input=input;
    }
    
    public void uptade(){//Sólo controla direcciones
        int xDireccion = 0;
        int yDireccion = 0;
           
        if(input.up)yDireccion--;
        if(input.down)yDireccion++;
        if(input.right)xDireccion++;
        if(input.left)xDireccion--;
        
        
        
        if(xDireccion!=0||yDireccion!=0) move(xDireccion,yDireccion);
    }
    public void render(Screen screen){
        
        if(direccion==0)sprite = Sprite.player_up[ani2&3];
        if(direccion==1)sprite = Sprite.player_rigth[ani2&3];
        if(direccion==2)sprite = Sprite.player_down[ani2&3];
        if(direccion==3)sprite = Sprite.player_left[ani2&3];
        screen.renderPlayer(x-16,y-16,sprite);
    }
}
