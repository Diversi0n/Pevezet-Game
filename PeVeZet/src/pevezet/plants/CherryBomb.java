/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import pevezet.Tile;
import pevezet.zombies.Zombie;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class CherryBomb extends Plant {

    private int batasKiri, batasKanan;

    public CherryBomb(int x, int y, PImage[] idle, PImage seed) {
        super(150, 0, 30, 1800, idle, seed, x, y, 13, 300);
    }

    public CherryBomb(Plant p) {
        super(p);
    }
    
    public void setBatas (Tile t, int i, int j){
        batasKiri = t.getTile().getX(); 
        if (j-1 > -1) batasKiri -= t.getTile().getWidth();
        
        batasKanan = t.getTile().getX() + t.getTile().getWidth();
        if (j+1 < 9) batasKanan += t.getTile().getWidth();
        
    }

    public void meledak(ArrayList<Zombie> zombie) {  
        
        for (Zombie z : zombie) {
            if (zombieInRange(z)) {
                zombie.remove(z);
                return;
            }
        }

    }

    private boolean zombieInRange(Zombie z) {
        return z.getX() >= batasKiri && z.getX() <= batasKanan;
    }

}
