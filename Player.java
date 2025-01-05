// The class that holds the player's game progress
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package town.flow.of.time.people;

import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author daves
 */
public class Player {
    private LinkedList<Boolean> musicUnlock;
    private boolean musicPending;
    private int line;
    private boolean mainMenuDelayCompleted;
    private String choice1;
    // The currentIcon and currentMusic are so if the player backs out to the title scrren it will remember the music and icons that were present during there most recent scene
    private LinkedList<ImageIcon> currentIcon;
    private String currentMusic;
    private boolean completeGame;
    
    public Player(){
        this.line = 3;
        this.currentIcon = new LinkedList<ImageIcon>();
        this.currentIcon.add(null);
        this.currentIcon.add(null);
        this.musicPending = false;
        this.mainMenuDelayCompleted = false;
        this.musicUnlock = new LinkedList<Boolean>();
        this.completeGame = true;
        for(int i = 0; i <= 9; i++){
            this.musicUnlock.add(false);
        }
    }
    
    //Updates the array that the player has unlocked new music.
    public void musicUpdate(int musicIndex){
        this.musicUnlock.remove(musicIndex);
        this.musicUnlock.add(musicIndex,true);
    }
    
    public Boolean getIndex(int index){
        return this.musicUnlock.get(index);
    }
    
    public int getLine(){
        return this.line;
    }
    
    public void lineAdd(){
        line++;
    }
    
    public void lineSubtract(){
        line--;
    }
    
    public void setLine(int lineNumber){
        this.line = lineNumber;
    }
    public void setCurrentIcon(ImageIcon iconBackground, ImageIcon nagisa){
        this.currentIcon.remove(0);
        this.currentIcon.add(0,iconBackground);
        this.currentIcon.remove(1);
        this.currentIcon.add(1,nagisa);
    }
    
    // Method overloading
    public void setCurrentIcon(ImageIcon nagisa){
        this.currentIcon.remove(1);
        this.currentIcon.add(1,nagisa);
    }
    
    public void setCurrentMusic(String filePath){
        this.currentMusic = filePath;
    }
    
    public void setMainMenuDelayCompleted(Boolean TF){
        this.mainMenuDelayCompleted = TF;
    }
    
    public void setMusicPending(Boolean TF){
        this.musicPending = TF;
    }
    
    public ImageIcon getCurrentIconBackground(){
        return this.currentIcon.get(0);
    }
    
    public ImageIcon getCurrentIconNagisa(){
        return this.currentIcon.get(1);
    }
    
    public boolean getMusicPending(){
        return this.musicPending;
    }
    
    public boolean getMainMenuDelayCompleted(){
        return this.mainMenuDelayCompleted;
    }
    
    public void playCurrentMusic(Music music){
        if(this.currentMusic != null){
            music.playMusicLoop(this.currentMusic);
        }
    }
    
    public void setChoice1(String choice){
        this.choice1 = choice;
    }
    
    public String getChoice1(){
        return this.choice1;
    }
    
    public boolean getCompleteGame(){
        return this.completeGame;
    }
    
    public void setCompleteGame(boolean TF){
        this.completeGame = TF;
    }
}