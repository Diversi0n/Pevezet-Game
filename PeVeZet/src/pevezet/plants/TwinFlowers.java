/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import pevezet.plants.Bullet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class TwinFlowers extends Plant {
    private int sunCtr;

    public TwinFlowers(int x, int y, PImage[] idle) {
        super(0, 300, 0, 0, idle, null, x, y, 26, 300);
        this.sunCtr = 0;
    }
    
    public TwinFlowers(Plant p) {
        super(p);
        this.sunCtr = 0;
    }
    
    public void addSun(ArrayList<Bullet> bulletActive, Bullet bullet, int x, int y) {
        if(x == -1) {
            x = this.x + 40;
            y = this.y + 40;
        }
        bulletActive.add(new Bullet(bullet, x, this.y - 10, 100, y));
        bulletActive.add(new Bullet(bullet, x + 50, this.y - 10, 100, y + 10));
        sunCtr = 1;
    }

    public int getSunCtr() {
        return sunCtr;
    }

    public void setSunCtr(int sunCtr) {
        this.sunCtr = sunCtr;
    }
}
