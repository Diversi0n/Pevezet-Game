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
public class Wallnut extends Plant {    

    public Wallnut(int x, int y, PImage[] idle, PImage seed) {
        super(50, 4000, 20, 0, idle, seed, x, y, 15, 4000);
    }
    
    public Wallnut(Plant p) {
        super(p);
    }
}