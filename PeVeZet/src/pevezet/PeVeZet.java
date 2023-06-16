/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pevezet;

import java.awt.Color;
import processing.core.PApplet;
import static processing.core.PConstants.PI;
import processing.core.PImage;

/**
 *
 * @author Albert
 */
public class PeVeZet extends PApplet{
    //Settings
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;
    
    //Asset
    public PImage bg;
    public String lagu = "src/assets/Mainmenu/MainMenu.wav";
    public PlayMusic ThemeSong = new PlayMusic(lagu);
    
    //Menu
    public GUIButton adventure, survival ,exit;
    public boolean cekexit = false, cekadv = false, ceksurv = false;
    
    public void settings(){
        size(WIDTH,HEIGHT);
    } 
    
    public void setup(){
        frameRate(FPS);
        adventure = new GUIButton(660,115,500,90, new Color(255,255,0));
        survival = new GUIButton(660,245,500,90, new Color(255,255,0));
        exit = new GUIButton(1150,570,100,90, new Color(255,255,0));
        bg = loadImage("src/assets/Mainmenu/Main_Menu_PVZ.png");
        ThemeSong.PlayMusic();
    }
    
    public void draw(){  
        update(mouseX, mouseY, adventure);
        update(mouseX, mouseY, survival);
        background(bg);
        
        // Tombol Hover Adventure
        if(overRect(670,115,500,90)){
            noStroke();
            fill(255, 255, 255, 10);
            rotate((float) (PI/32.0));
            rect(670,30,500,110);
            fill(255, 255, 255, 15);
            rotate((float) (PI/90.0));
            rect(1000,-16,160,10);
        }
        
        // Tombol Hover Survival
        if(overRect(660,245,500,90)){
            noStroke();
            fill(255, 255, 255, 20);
            rotate((float) (PI/32.0));
            rect(660,150,500,90);
        }
        
        // Tombol Hover Exit
        if(overRect(1150,570,100,90)){
            fill(255, 255, 255, 20);
            noStroke();
            rect(1140,590,110,60);
        }

    }
    
    void update(int x, int y, GUIButton b) {
        if (overRect(adventure.getX(), adventure.getY(), adventure.getWidth(), adventure.getHeight()) ) {
            cekadv = true;
        } 
        else if(overRect(survival.getX(), survival.getY(), survival.getWidth(), survival.getHeight())) {
            ceksurv = true;
        }
        else if(overRect(exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight())){
            cekexit = true;
        }
        else {
            cekadv = false;
            ceksurv = false;
            cekexit = false;
        }
    }
    
    boolean overRect(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
            return true;
        }
        else {
            return false;
        }   
    }
    
    public void mousePressed(){
        if(cekexit){
            exit();
        }
        else if(cekadv){
            String[] args = {"AdventureRun"};
            PApplet.runSketch(args, new Adventure());
            ThemeSong.stop();
            surface.setVisible(false);
        }
        else if(ceksurv){
            String[] args = {"SurvivalRun"};
            PApplet.runSketch(args, new Survival());
            ThemeSong.stop();
            surface.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        PApplet.main("pevezet.PeVeZet");
    }
}
