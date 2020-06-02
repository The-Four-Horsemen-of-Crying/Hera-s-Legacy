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
    private final Clip clip;
    private FloatControl volume;
    public static Clip de;
    public static Clip fail;
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
   
    public Sound (Clip clip){
        this.clip=clip;
        volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    
     public void loop(){//Se reinicia la musica constantemente.
        clip.setFramePosition(0);//Vuelve al inicio.
        clip.loop(Clip.LOOP_CONTINUOUSLY);
     }

     public void play(){
       clip.setFramePosition(0);
       clip.start();
     }
     
     public void stop(){
         clip.stop();
     }
     
     public int getFraemPosition(){
         return clip.getFramePosition();
     }
     public void changeVolume(float f){
         volume.setValue(f);
     }
     
    public static void init(){//Es llamada 1 sola vez cuando se crea la clase Game, para añadir más sonidos se deben cargar aquí. 
        de=loadSound("/sonidos/aerith.wav");
        fail = loadSound("/sonidos/fail.wav");
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


