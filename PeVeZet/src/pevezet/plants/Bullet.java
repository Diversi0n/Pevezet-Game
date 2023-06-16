/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import pevezet.GUIButton;
import pevezet.zombies.Zombie;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Bullet {
    private GUIButton bullet;
    private int dmg, idleSize, ctr;
    private String type;
    private PImage[] idle;
    private boolean change;

    public Bullet(GUIButton bullet, int dmg, String type, PImage[] idle, int idleSize) {
        this.bullet = bullet;
        this.dmg = dmg;
        this.type = type;
        this.idle = idle;
        this.idleSize = idleSize;
        this.ctr = -1;
        this.change = false;
    }
    
    public Bullet(Bullet b, int x, int y, int width, int height) {
        this.bullet = new GUIButton(b.bullet);
        this.dmg = b.dmg;
        this.type = b.type;
        this.idle = b.idle;
        this.idleSize = b.idleSize;
        this.ctr = -1;
        this.bullet.setX(x);
        this.bullet.setY(y);
        this.bullet.setWidth(width);
        this.bullet.setHeight(height);
        this.change = false;
    }

    public GUIButton getBullet() {
        return bullet;
    }

    public int getDmg() {
        return dmg;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PImage[] getIdle() {
        return idle;
    }

    public void setIdle(PImage[] idle) {
        this.idle = idle;
    }

    public int getCtr() {
        return ctr;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }
    
    public void drawIdle(PApplet app) {
        if(ctr > -1) {
            if(type.equals("Fire"))
                app.image(idle[ctr / 5 % idleSize],bullet.getX() - bullet.getWidth(),bullet.getY() - 12, 2 * bullet.getWidth(), bullet.getWidth());
            else
                app.image(idle[ctr / 5 % idleSize],bullet.getX(),bullet.getY(), bullet.getWidth(), bullet.getWidth());
            ctr %= 5 * idleSize;
        }
    }
    
    public boolean damage(ArrayList<Zombie> zombie) {
        if(zombie.size() > 0) {
            for(Zombie i : zombie) {
                if(this.type.equals("Coconut")) {
                    if(this.bullet.getX() <= i.getX() + 6 && i.getX() <= this.bullet.getX() && this.bullet.getY() > i.getY() && this.bullet.getY() < i.getY() + 115) {
                        this.bullet.setX(this.bullet.getX() + 6);
                        i.setHp(i.getHp() - dmg); 
                        if(i.getHp() <= 0)
                            zombie.remove(i);
                        return true;
                    }
                }
                else if(this.bullet.getX() <= i.getX() + 100 && i.getX() + 100 <= this.bullet.getX() + this.bullet.getWidth()) {
                    i.setHp(i.getHp() - dmg); 
                    if(i.getHp() <= 0)
                        zombie.remove(i);
                    else if(this.type.equals("Freeze"))
                        i.setWalkCtr(-2);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }
}
