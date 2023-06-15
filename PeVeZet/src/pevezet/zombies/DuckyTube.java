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
public class DuckyTube extends Zombie {

    public DuckyTube(int x, int y, PImage[] idle, PImage[] eat) {
        super(180, 100, 2, 22, 21, x, y, 180, idle, eat);
    }
    
    public DuckyTube(Zombie z) {
        super(z);
    }
}
