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
public class Gargantuar extends Zombie {
    boolean lempar;
    public Gargantuar(int x, int y, PImage[] idle, PImage[] eat) {
        super(3000, 5000, 2, 20, 25, x, y, 3000, idle, eat);
        lempar = false;
    }
    
    public Gargantuar(Zombie z) {
        super(z);
        lempar = false;
    }

    @Override
    public void drawEat(PApplet app) {
        if(ctr > -1)
        {
//            System.out.println(ctr + " " + speed + " " + eatSize);
            app.image(eat[ctr / (4*speed) % eatSize],x,y-80, 200, 250);
            ctr %= 4 * speed * eatSize;
        }
    }

    @Override
    public void drawIdle(PApplet app) {
        if(ctr > -1){
            app.image(idle[ctr / (2*speed) % idleSize],x,y-80, 200, 250);
            ctr %= 2 * speed * idleSize;
        }
    }

    public boolean isLempar() {
        return lempar;
    }

    public void setLempar(boolean lempar) {
        this.lempar = lempar;
    }
    
    
}

