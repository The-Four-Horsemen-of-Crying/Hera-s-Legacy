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
    RED                 (0xffff0000),
    HIGDARKRED          (0xffff0013),
    DARKRED             (0xffff0019),
    LESSDARKRED         (0xffff0026),
    LESSDARKRED2        (0xffff001d),
    FUCHSIA             (0xffff00ff),
    LIME                (0xff00ff00),
    BLUE                (0xff0000ff),
    CLEARBLUE           (0xff00efff),
    YELLOW              (0xffffff00),
    ORANGE              (0xfff9c302),
    WHITE               (0xffffffff),
    GREEN               (0xff00ff00),
    KINDBLUE            (-9411424),
    KINDRED             (-2358749),
    KINDBLUE2           (-16724531),
    SOMEKINDBLUE        (0xff7564a3),
    BLACK               (0xff000000),
    KINDCOLDPLAY        (-5991936),
    KINDGREENDAY        (-16730112),
    PURPLEPOE           (-9699234),
    PURPLEDARK1         (0xff950062),
    PURPLEDARK          (0xff7f0060),
    NARANJAMECANICA     (-1411778),
    BLUECOLI            (0xff3900ff),
    GOLDEN              (0xffffc400);
    
    private final int color;
    
    Colors(int s){
        color=s;
    }
    
    public int getColor(){
        return color;
    }
}
