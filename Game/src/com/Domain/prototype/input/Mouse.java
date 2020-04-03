/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Domain.prototype.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Domain
 */
public class Mouse extends MouseAdapter{
    public static int mouseX=-1;
    public static int mouseY=-1;
    public static boolean click=false;
       
     @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)click=true;
    }

    
     @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)click=false;
    }
    
     @Override
    public void mouseWheelMoved(MouseWheelEvent e){}

    
     @Override
    public void mouseDragged(MouseEvent e){
        mouseX=e.getX();
        mouseY=e.getY();
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        mouseX=e.getX();
        mouseY=e.getY();
    }
    
}
