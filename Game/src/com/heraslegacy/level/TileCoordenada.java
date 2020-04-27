
package com.heraslegacy.level;

public class TileCoordenada {
    private int x;
    private int y;
    private int[] xy = new int[2];

    public TileCoordenada(int x, int y) {
        this.x = xy[0] = x;
        this.y = xy[1] = y;    
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int[] getXY(){
        return xy;
    }
    
}
