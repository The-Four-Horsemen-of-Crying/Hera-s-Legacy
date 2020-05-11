/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

/**
 *
 * @author ejose
 */
public enum Colors {
    red                 (0xffff0000),
    higdarkred          (0xffbc021e),
    darkred             (0xffdc0223),
    lessdarkred         (0xffff0026),
    fuchsia             (0xffff00ff),
    lime                (0xff00ff00),
    blue                (0xff0000ff),
    clearblue           (0xff00efff),
    yellow              (0xffffff00),
    orange              (0xfff9c302),
    white               (0xffffffff),
    green               (0xff00ff00),
    kindblue            (-9411424),
    kindred             (-2358749),
    kindblue2           (-16724531),
    black               (0xff000000),
    kindColdplay        (-5991936),
    kindgreenday        (-16730112),
    purplePoe           (-9699234),
    naranjaMecanica     (-1411778);
    bluecoli            (0xff3900ff);
    
    private final int color;
    
    Colors(int s){
        color=s;
    }
    
    public int getColor(){
        return color;
    }
}
