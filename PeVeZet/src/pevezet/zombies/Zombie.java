/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.zombies;

import pevezet.Tile;
import pevezet.plants.*;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public abstract class Zombie {
    //Sprite Position
    protected int x;
    protected int y;
    
    //Game Object Sprite
    protected PImage[] idle, eat;
    
    //Zombie spec
    protected int hp, dmg, speed, ctr, idleSize, eatSize, health, walkCtr, slowCtr;
    public Zombie(int hp, int dmg, int speed, int idleSize, int eatSize, int x, int y, int health, PImage[] idle, PImage[] eat) {
        this.hp = hp;
        this.dmg = dmg;
        this.speed = speed;
        this.idleSize = idleSize;
        this.eatSize = eatSize;
        this.ctr = -1;
        this.x = x;
        this.y = y;
        this.health = health;
        this.idle = idle;
        this.walkCtr = 0;
        this.eat = eat;
    }
    
    public Zombie(Zombie z) {
        this.hp = z.hp;
        this.dmg = z.dmg;
        this.speed = z.speed;
        this.idleSize = z.idleSize;
        this.eatSize = z.eatSize;
        this.ctr = -1;
        this.x = z.x;
        this.y = z.y;
        this.health = z.health;
        this.idle = z.idle;
        this.walkCtr = 0;
        this.eat = z.eat;
    }
    
    public void drawIdle(PApplet app){
        if(ctr > -1)
        {
            app.image(idle[ctr / (2*speed) % idleSize],x,y, 150, 150);
            ctr %= 2 * speed * idleSize;
        }
    }
    
    public void drawEat(PApplet app){
        if(ctr > -1)
        {
//            System.out.println(ctr + " " + speed + " " + eatSize);
            app.image(eat[ctr / (4*speed) % eatSize],x,y, 150, 150);
            ctr %= 4 * speed * eatSize;
        }
    }
    
    public boolean makan(Tile t) {
        if(t.hasPlant() && !(t.getPlant() instanceof CherryBomb) && !(t.getPlant() instanceof TangleKelp)) {
            if(x < t.getTile().getX() + 75)
                return true;
        }
        return false;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public void setIdleSize(int idleSize) {
        this.idleSize = idleSize;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getWalkCtr() {
        return walkCtr;
    }

    public void setWalkCtr(int walkCtr) {
        this.walkCtr = walkCtr;
    }

    public PImage[] getEat() {
        return eat;
    }

    public void setEat(PImage[] eat) {
        this.eat = eat;
    }

    public int getEatSize() {
        return eatSize;
    }

    public void setEatSize(int eatSize) {
        this.eatSize = eatSize;
    }
    
    
}
