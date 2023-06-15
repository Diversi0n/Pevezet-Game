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

    public boolean isLempar() {
        return lempar;
    }

    public void setLempar(boolean lempar) {
        this.lempar = lempar;
    }
    
    
}
