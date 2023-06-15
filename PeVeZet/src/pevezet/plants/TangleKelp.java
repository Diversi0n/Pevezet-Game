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
public class TangleKelp extends Plant {
    
    private int tangleCtr, startAttack;
    private boolean attack;
    private ArrayList<Zombie> zombieTerkena;
    private PImage[] attackImg = new PImage[100];
    
    public TangleKelp(int x, int y, PImage[] idle, PImage seed, PImage[] attack) {
        super(25, 0, 20, 1800, idle, seed, x, y, 25, 300);
        setUp();
        
        this.attackImg = attack;
    }
    
    public TangleKelp(Plant p) {
        super(p);
        setUp();
        
        this.attackImg = ((TangleKelp)p).attackImg;
    }
    
    private void setUp(){
        this.tangleCtr = 0;
        zombieTerkena = new ArrayList();
        this.attack = false;
    }

    private void counter(){
        if (this.ctr == 124) this.tangleCtr++;
        System.out.println(this.tangleCtr);
    }
    
    public boolean attack(ArrayList<Zombie> zombies, Tile tile){
        counter();
        if (!attack){
            cekAdaZombie(zombies);  
        } else {
            this.setIdle(attackImg);
            if (this.startAttack + 1 == this.tangleCtr){
                zombies.removeAll(zombieTerkena);
                
                zombieTerkena.clear();
                tile.deletePlant();
                return true;
            }
        }
        return false;
    }
    
    private void cekAdaZombie(ArrayList<Zombie> zombies){
        for (Zombie z : zombies){
            if (this.x - 10 == z.getX()){
                zombieTerkena.add(z);
            }
        }
        
        if (zombieTerkena.size() > 0){
            startAttack = this.tangleCtr;
            this.attack = true;
        }
    }
    
}
