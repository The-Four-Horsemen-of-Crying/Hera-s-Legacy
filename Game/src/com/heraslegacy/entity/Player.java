
package com.heraslegacy.entity;


import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.main.Game;
import com.heraslegacy.manager.KeyBoard;

public class Player extends Mov{
    private KeyBoard input;
    private Sprite sprite;
    private Sprite up[];
    private Sprite rigth[];
    private Sprite left[];
    private Sprite down[];
    
    public Player(int x, int y, KeyBoard input){
        this.x = x;
        this.y = y;
        this.input = input;
        
    }
    
    @Override
    public void update(){
        int xDireccion = 0;
        int yDireccion = 0;
           
        if(input.up) yDireccion--;
        if(input.down) yDireccion++;
        if(input.right) xDireccion++;
        if(input.left) xDireccion--;
        
        if(xDireccion!=0||yDireccion!=0) move(xDireccion,yDireccion);
    }
    
    @Override
    public void render(Screen screen){
        
        if(direction == 0) sprite = up[ani2 & 3];
        if(direction == 1) sprite = rigth[ani2 & 3];
        if(direction == 2) sprite = down[ani2 & 3];
        if(direction == 3) sprite = left[ani2 & 3];
        Game.activarMecanica=level.getCollision(x, y);    //MECANICA QUE DEPENDE DEL NIVEL
        screen.renderPlayer(x - 16, y - 16, sprite);
    }
    
    public void setSprites(Sprite[] up, Sprite[] down, Sprite[] rigth, Sprite[] left){
        this.up=up;
        this.down=down;
        this.left=left;
        this.rigth=rigth;
    }
}
