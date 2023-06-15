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
public class Conehead extends Zombie {

    public Conehead(int x, int y, PImage[] idle, PImage[] eat) {
        super(550, 100, 2, 21, 11, x, y, 550, idle, eat);
    }
    
    public Conehead(Zombie z) {
        super(z);
    }
}
