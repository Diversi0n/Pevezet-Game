/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Wallfire extends Plant {    

    public Wallfire(int x, int y, PImage[] idle) {
        super(0, 6000, 0, 30, idle, null, x, y, 52, 6000);
    }
    
    public Wallfire(Plant p) {
        super(p);
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}