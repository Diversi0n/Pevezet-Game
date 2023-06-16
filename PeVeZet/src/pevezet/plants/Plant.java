/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public abstract class Plant {
    //Sprite Position
    protected int x;
    protected int y;
    
    //Game Object Sprite
    protected PImage[] idle;
    protected PImage seed;
    
    //Plant spec
    //Hp adalah nyawa sekarang, health adalah nyawa utuh
    protected int price, hp, recharge, dmg, timer, ctr, idleSize, health;

    public Plant(int price, int hp, int recharge, int dmg, PImage[] idle, PImage seed, int x, int y, int idleSize, int health) {
        this.price = price;
        this.hp = hp;
        this.recharge = recharge;
        this.dmg = dmg;
        this.idle = idle;
        this.seed = seed;
        this.timer = 0;
        this.ctr = -1;
        this.x = x;
        this.y = y;
        this.idleSize = idleSize;
        this.health = health;
    }
    
    public Plant(Plant p) {
        this.price = p.price;
        this.hp = p.hp;
        this.recharge = p.recharge;
        this.dmg = p.dmg;
        this.idle = p.idle;
        this.seed = p.seed;
        this.timer = 0;
        this.ctr = -1;
        this.x = p.x;
        this.y = p.y;
        this.idleSize = p.idleSize;
        this.health = p.health;
    }
    
    public void drawIdle(PApplet app){
        if(ctr > -1)
        {
            if(this instanceof GreatWall)
                app.image(idle[ctr / 5 % idleSize],x,y, 120, 240);
            else
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
    
    public int getRecharge() {
        return recharge;
    }
    
    public int getPrice() {
        return price;
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
    
    public int getHealth() {
        return health;
    }
}
