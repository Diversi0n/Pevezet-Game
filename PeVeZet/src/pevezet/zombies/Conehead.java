/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.zombies;

import processing.core.PApplet;
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
    
    @Override
    public void drawEat(PApplet app) {
        if(ctr > -1)
        {
            app.image(eat[ctr / (4*speed) % eatSize],x,y-20, 170, 170);
            ctr %= 4 * speed * eatSize;
        }
    }

    @Override
    public void drawIdle(PApplet app) {
        if(ctr > -1)
        {
            app.image(idle[ctr / (2*speed) % idleSize],x,y-20, 170, 170);
            ctr %= 2 * speed * idleSize;
        }
    }
}
