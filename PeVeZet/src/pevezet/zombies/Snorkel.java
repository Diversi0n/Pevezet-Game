/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.zombies;

import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Snorkel extends Zombie {

    public Snorkel(int x, int y, PImage[] idle, PImage[] eat) {
        super(180, 100, 2, 63, 33, x, y, 180, idle, eat);
    }
    
    public Snorkel(Zombie z) {
        super(z);
    }
}
