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
public class Football extends Zombie {

    public Football(int x, int y, PImage[] idle, PImage[] eat) {
        super(1580, 100, 10, 12, 52, x, y, 1580, idle, eat);
    }
    
    public Football(Zombie z) {
        super(z);
    }
}
