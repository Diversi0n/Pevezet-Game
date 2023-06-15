/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Albert
 */
public class PlayMusic {
    String filepath;
    Clip clip;
    long clipTimePosition;
    
    public PlayMusic(String filepath) {
        this.filepath = filepath;
    }
    
    public void PlayMusic(){
        try{
            File musicpath = new File(filepath);
            if(musicpath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread.sleep(100);
                long clipTimePosition = clip.getMicrosecondPosition();
            }
            else{
                System.out.println("File doesn't exist.");
            }
        }
        catch(Exception e){
            
        }
    }
    
    public void stop(){
        clip.stop();
    }
    
    public void pause(){
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();
    }
    
    public void resume(){
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
    }
}
