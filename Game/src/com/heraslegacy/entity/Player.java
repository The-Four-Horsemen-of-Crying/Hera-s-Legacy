
package com.heraslegacy.entity;


import com.heraslegacy.graphics.Screen;
import com.heraslegacy.graphics.Sound;
import com.heraslegacy.graphics.Sprite;
import com.heraslegacy.level.SpaceLevel;
import com.heraslegacy.main.Game;
import com.heraslegacy.manager.KeyBoard;

public class Player extends Mov{
    private Sprite sprite;
    private Sprite up[];
    private Sprite rigth[];
    private Sprite left[];
    private Sprite down[];
    private int tipo=0;
    private int ajusteCentroX, ajusteCentroY;
    private int xDireccion = 0, yDireccion = 0, lastDirx;
    
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    
    @Override
    public void update(){
            xDireccion=0;
        yDireccion=0;
    
      
        
        switch(tipo){
            case 1:              
                if(KeyBoard.up) yDireccion++;
                if(KeyBoard.down) yDireccion--;
                if(KeyBoard.right) xDireccion--;
                if(KeyBoard.left) xDireccion++;
                break;
            case 0: 
                if(KeyBoard.up) yDireccion--;
                if(KeyBoard.down) yDireccion++;
                if(KeyBoard.right) xDireccion++;
                if(KeyBoard.left) xDireccion--;
            break;
        }
        
        if(xDireccion!=0||yDireccion!=0) {
            move(xDireccion, yDireccion);
        }
        
    }
    
    @Override
    public void render(Screen screen){
        
        if(direction == 0){
            if(level.getLevelstrategy() instanceof SpaceLevel){
                if(lastDirx==1){
                    sprite =left[ani2 & 3];
                }else{
                    sprite= rigth[ani2& 3];
                }
            }else{
                sprite= up[ani2& 3];
            }
        }
        if(direction == 1) {
            sprite = rigth[ani2 & 3];
            lastDirx=0;
        }
        if(direction == 2) {
        if(level.getLevelstrategy() instanceof SpaceLevel){
                if(lastDirx==1){
                    sprite =left[ani2 & 3];
                }else{
                    sprite= rigth[ani2& 3];
                }
            }else{
                sprite= down[ani2& 3];
            }
        }
        if(direction == 3) {
            sprite = left[ani2 & 3];
            lastDirx=1;
        }
        
        Game.activarMecanica=level.getCollision(x, y);    //MECANICA QUE DEPENDE DEL NIVEL
        screen.renderPlayer(x - ajusteCentroX, y - ajusteCentroY, sprite);
    }
    
    public void setSprites(Sprite[] up, Sprite[] down, Sprite[] rigth, Sprite[] left){
        this.up=up;
        this.down=down;
        this.left=left;
        this.rigth=rigth;
    }
    public void setTipo(int b){
      tipo=b;
    }

    public void setAjustes(int ajusteX1, int ajusteX2, int ajusteY1, int ajusteY2, int ajusteCentroX, int ajusteCentroY, Sound move) {
        this.ajusteX1 = ajusteX1;
        this.ajusteX2 = ajusteX2;
        this.ajusteY1 = ajusteY1;
        this.ajusteY2 = ajusteY2;
        this.ajusteCentroX = ajusteCentroX;
        this.ajusteCentroY = ajusteCentroY;
        this.move=move;
    }

    public boolean getCollisionP(){
        return this.collision(xDireccion, yDireccion);
    }
}
