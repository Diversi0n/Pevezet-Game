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
public class Torchwood extends Plant {

    public Torchwood(int x, int y, PImage[] idle, PImage seed) {
        super(175, 300, 5, 0, idle, seed, x, y, 23, 300);
    }
    
    public Torchwood(Plant p) {
        super(p);
    }
}
