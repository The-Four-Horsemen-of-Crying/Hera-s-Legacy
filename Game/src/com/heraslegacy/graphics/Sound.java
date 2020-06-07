/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.graphics;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author HP
 */
public class Sound {
    private final Clip CLIP;
    private FloatControl volume;
    public static Clip de;
    public static Clip change;
    public static Clip bakSpa;
    public static Clip win;
    public static Clip loose;
    public static Clip walk;
    public static Clip propulsion;
    public static Clip lobby_Theme;
    public static Clip math_Theme;
    public static Clip lobby_portalSound;
    public static Clip buttonAlert_0;
    public static Clip bookSound;
    /*Level 3*/
    public static Clip backgroundLB;
    public static Clip fail;
    public static Clip pickup;
    public static Clip fantasma_Theme;
    public static Clip creditos_Theme;
   
    public Sound (Clip clip){
        this.CLIP=clip;
        volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    
     public void loop(){//Se reinicia la musica constantemente.
        CLIP.setFramePosition(0);//Vuelve al inicio.
        CLIP.loop(Clip.LOOP_CONTINUOUSLY);
     }

     public void play(){
       CLIP.setFramePosition(0);
       CLIP.start();
     }
     
     public void stop(){
         CLIP.stop();
     }
     
     public int getFraemPosition(){
         return CLIP.getFramePosition();
     }
     public void changeVolume(float f){
         volume.setValue(f);
     }
     
    public static void init(){//Es llamada 1 sola vez cuando se crea la clase Game, para añadir más sonidos se deben cargar aquí. 
        de=loadSound("/sonidos/aerith.wav");
        change= loadSound("/sonidos/change.wav");
        bakSpa= loadSound("/sonidos/fondo.wav");
        win=loadSound("/sonidos/Win.wav");
        loose=loadSound("/sonidos/Loose.wav");
        walk=loadSound("/sonidos/caminar.wav");
        propulsion=loadSound("/sonidos/propulsion.wav");
        lobby_Theme=loadSound("/sonidos/lobbyTheme.wav");
        lobby_portalSound= loadSound("/sonidos/portalPass.wav");
        buttonAlert_0= loadSound("/sonidos/buttonSound_0.wav");
        math_Theme=loadSound("/sonidos/mathTheme.wav");
        bookSound=loadSound("/sonidos/bookSound.wav");
        /*Level 3*/
        backgroundLB = loadSound("/sonidos/level3.wav");
        fail = loadSound("/sonidos/fail.wav");
        pickup = loadSound("/sonidos/pickup.wav");
        fantasma_Theme=loadSound("/sonidos/fantasmaTheme.wav");
        creditos_Theme=loadSound("/sonidos/creditosTheme.wav");
    }
    public static Clip loadSound(String path){
          
            try {
                 Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Sound.class.getResource(path)));
                 return clip;
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
           return null;
     }
    
}


