/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet;

import java.util.ArrayList;
import pevezet.zombies.Zombie;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class LawnMower {
    private int x, y, width, height, idleSize, ctr, awal;
    private PImage[] idle;

    public LawnMower(int awal, int y, PImage[] idle) {
        this.x = awal;
        this.y = y;
        this.width = 100;
        this.height = 100;
        this.idleSize = 5;
        this.ctr = 0;
        this.idle = idle;
        this.awal = awal;
    }
    
    public void drawIdle(PApplet app){
        if(this.x != awal) {
            this.x += 8;
            this.ctr++;
        }
        if(ctr == 0)
            app.image(idle[ctr],x,y);
        else if(ctr > -1)
        {
            app.image(idle[ctr % idleSize + 1],x,y);
            ctr %= idleSize;
        }
    }
    
    public void drawPoolCleaner(PApplet app){
        if(this.x != awal)
            this.x += 8;
        if(this.x < 70 || this.x > 1190)
            app.image(idle[0],x,y);
        else
            app.image(idle[1],x,y);
    }
    
    public void lindes(ArrayList<Zombie> zombieActive) {
        for(int i = 0; i < zombieActive.size(); i++) {
            if(zombieActive.get(i).getX() < this.x + 40) {
                zombieActive.remove(i);
                this.x++;
                i--;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCtr() {
        return ctr;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }
}
