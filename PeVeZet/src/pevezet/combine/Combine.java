/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.combine;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public abstract class Combine {
    //Sprite Position
    protected int x;
    protected int y;
    
    //Game Object Sprite
    protected PImage[] idle;
    protected PImage seed;
    
    //Plant spec
    //Hp adalah nyawa sekarang, health adalah nyawa utuh
    protected int hp, dmg, timer, ctr, idleSize;

    public Combine(int hp, int dmg, PImage[] idle, int x, int y, int idleSize) {
        this.hp = hp;
        this.dmg = dmg;
        this.idle = idle;
        this.seed = seed;
        this.timer = 0;
        this.ctr = -1;
        this.x = x;
        this.y = y;
        this.idleSize = idleSize;
    }
    
    public Combine(Combine p) {
        this.hp = p.hp;
        this.dmg = p.dmg;
        this.idle = p.idle;
        this.seed = p.seed;
        this.timer = 0;
        this.ctr = -1;
        this.x = p.x;
        this.y = p.y;
        this.idleSize = p.idleSize;
    }
    
    public void drawIdle(PApplet app){
        if(ctr > -1)
        {
            app.image(idle[ctr / 5 % idleSize],x,y, 120, 120);
            ctr %= 5 * idleSize;
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

    public PImage[] getIdle() {
        return idle;
    }

    public void setIdle(PImage[] idle) {
        this.idle = idle;
    }

    public PImage getSeed() {
        return seed;
    }

    public void setSeed(PImage seed) {
        this.seed = seed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    
    public int getCtr() {
        return ctr;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }
    
    public int getIdleSize() {
        return idleSize;
    }
}
