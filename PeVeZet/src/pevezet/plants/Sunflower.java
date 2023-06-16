/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Sunflower extends Plant {
    private int sunCtr;

    public Sunflower(int x, int y, PImage[] idle, PImage seed) {
        super(50, 300, 5, 0, idle, seed, x, y, 33, 300);
        this.sunCtr = 0;
    }
    
    public Sunflower(Plant p) {
        super(p);
        this.sunCtr = 0;
    }
    
    public void addSun(ArrayList<Bullet> bulletActive, Bullet bullet, int x, int y) {
        if(x == -1) {
            x = this.x + 40;
            y = this.y + 40;
        }
        bulletActive.add(new Bullet(bullet, x, this.y - 10, 100, y));
        sunCtr = 1;
    }

    public int getSunCtr() {
        return sunCtr;
    }

    public void setSunCtr(int sunCtr) {
        this.sunCtr = sunCtr;
    }
}
