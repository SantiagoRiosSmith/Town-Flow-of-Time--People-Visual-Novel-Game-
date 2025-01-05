// The class for the playing and stopping of music in the game
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package town.flow.of.time.people;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author daves
 */
public class Music {
    // This instance variable is so the code can reference the music that is currently playing and so it can be stopped and changed at any time
    private Clip audio;
    
    public void playMusic(String filePath){
        try{
            File musicPath = new File(filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            this.audio = AudioSystem.getClip();
            this.audio.open(audioInput);
            this.audio.start(); 
        }
        catch(Exception ex){
            System.out.println(filePath);
            ex.printStackTrace();
        }
    }
    
    public void stopMusic(){
        this.audio.stop();
    }
    
    public void playMusicLoop(String filePath){
        try{
            File musicPath = new File(filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            this.audio = clip;
            this.audio.open(audioInput);
            this.audio.start(); 
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String toString(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
