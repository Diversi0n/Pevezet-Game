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
public class Lilypad extends Plant {

    public Lilypad(int x, int y, PImage[] idle, PImage seed) {
        super(25, 300, 10, 0, idle, seed, x, y, 22, 300);
    }
    
    public Lilypad(Plant p) {
        super(p);
    }
}
