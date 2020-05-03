
package com.heraslegacy.entity;

import com.heraslegacy.graphics.Screen;
import com.heraslegacy.level.Level;
import com.heraslegacy.level.tile.Tile;

public abstract class Entity {
    private boolean removed = false, moving = false;   
    protected int ani = 0, ani2 = 0, direction = 0, x, y;
    protected Level level;
    
    public void remove(){
        removed = true;
    }
    
    public boolean isRemoved(){
        return removed;
    }
    public void init(Level level){
        this.level = level;
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }    
    
    public void update(){
    }
    
    public void move(int xMove, int yMove){
        if(yMove < 0) direction = 0; // abajo
        if(xMove > 0) direction = 1; // derecha
        if(yMove > 0) direction = 2; // arriba
        if(xMove < 0) direction = 3; // izquierda

        
        if(!collision(xMove, 0)){
            animación();
            x += xMove;
        }        
        if(!collision(0, yMove)){
            animación();
            y += yMove;
        }
    }
    
    private boolean collision(int xMove, int yMove){
        boolean solid = false;
        for (int corners = 0; corners < 4; corners++) {
            int xLimit =((x + xMove) + corners % 2 * 14 - 8) / 16;
            int yLimit =(((y + yMove) + corners / 2 * 12 + 3) / 16);
            Tile nextTile = level.getTile(xLimit, yLimit);
            if(nextTile.solid()) solid = true;            
        }
        return  solid;
    }       
    
    public void render(Screen screen){  
    }
    
    public void animación(){            
        if(ani % 30 == 0){
            ani2++;
            ani = 0;
        }
        ani++;
    }  
        
}
 