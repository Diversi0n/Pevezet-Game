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
public class Peashooter extends Plant {
    private int peaCtr;

    public Peashooter(int x, int y, PImage[] idle, PImage seed) {
        super(100, 300, 5, 18, idle, seed, x, y, 30, 300);
    }
    
    public Peashooter(Plant p) {
        super(p);
    }

    public int getPeaCtr() {
        return peaCtr;
    }

    public void setPeaCtr(int peaCtr) {
        this.peaCtr = peaCtr;
    }
    
    public void addPea(ArrayList<Bullet> bulletActive, Bullet bullet) {
        bulletActive.add(new Bullet(bullet, x + 100, this.y + 30, 25, y));
        peaCtr = 1;
    }
}
