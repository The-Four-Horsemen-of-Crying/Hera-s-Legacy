/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.entity;

import com.Domain.prototype.graphics.Screen;
import com.Domain.prototype.level.Level;
import java.util.Random;

//Todo aquello que exista
public abstract class  Entity { 
    public int x;
    public int y;
    private boolean removed=false;
    private Level level;
    protected final Random random = new Random();

    public void uptade(){
    
    }

    public void render(Screen screen){
    
    }
    
    public void remove(){
        removed = true;
    }
    public boolean isRemoved(){
        return removed;
    }
}
 