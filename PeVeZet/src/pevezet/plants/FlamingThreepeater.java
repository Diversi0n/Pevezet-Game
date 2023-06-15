/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import pevezet.combine.Combine;
import pevezet.plants.Bullet;
import pevezet.zombies.Zombie;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class FlamingThreepeater extends Plant {
    private int peaCtr;

    public FlamingThreepeater(int x, int y, PImage[] idle) {
        super(0, 300, 0, 18, idle, null, x, y, 16, 300);
    }
    
    public FlamingThreepeater(Plant p) {
        super(p);
    }
    
//    public void attack(int y, int x, ArrayList<Zombie>[] zombies) {
//        for(Zombie  i : zombies[y]) {
//            
//        }
//    }

    public int getPeaCtr() {
        return peaCtr;
    }

    public void setPeaCtr(int peaCtr) {
        this.peaCtr = peaCtr;
    }
    
    public void addPea(ArrayList<Bullet> bulletActive, Bullet bullet, int y) {
        bulletActive.add(new Bullet(bullet, x + 100, y + 30, 25, y));
        peaCtr = 1;
    }
}
