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
public class Basic extends Zombie {

    public Basic(int x, int y, PImage[] idle, PImage[] eat) {
        super(180, 60, 2, 22, 21, x, y, 180, idle, eat);
    }
    
    public Basic(Zombie z) {
        super(z);
    }
}
