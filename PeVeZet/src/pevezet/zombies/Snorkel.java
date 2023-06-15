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
public class Snorkel extends Zombie {

    public Snorkel(int x, int y, PImage[] idle, PImage[] eat) {
        super(180, 100, 2, 63, 33, x, y, 180, idle, eat);
    }

    @Override
    public void drawEat(PApplet app) {
        if(ctr > -1)
        {
//            System.out.println(ctr + " " + speed + " " + eatSize);
            app.image(eat[ctr / (4*speed) % eatSize],x,y-40, 210, 210);
            ctr %= 4 * speed * eatSize;
        }
    }

    @Override
    public void drawIdle(PApplet app) {
        if(ctr > -1)
        {
            app.image(idle[ctr / (2*speed) % idleSize],x,y-40, 210, 210);
            ctr %= 2 * speed * idleSize;
        }
    }
    
    public Snorkel(Zombie z) {
        super(z);
    }
}
