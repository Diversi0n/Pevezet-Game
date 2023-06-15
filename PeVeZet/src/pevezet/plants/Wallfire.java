/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import pevezet.Tile;
import pevezet.combine.Combine;
import pevezet.zombies.Zombie;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Wallfire extends Plant {
    
//    private int wallfireCtr;

    public Wallfire(int x, int y, PImage[] idle) {
        super(0, 6000, 0, 30, idle, null, x, y, 52, 6000);
//        wallfireCtr = 0 ; 
    }
    
    public Wallfire(Plant p) {
        super(p);
//        wallfireCtr = 0;
    }
    
    
    
//    public void dimakan(ArrayList<Zombie> z, Tile t){
//        for (int i = 0 ; i < z.size() ; i++){
//            Zombie zombie = z.get(i);
//            if (zombie.getX() < t.getTile().getX() - 70) continue;
//            
//            if (z.get(i).getX() <= t.getTile().getX()){
//                z.get(i).setX(t.getTile().getX());
//                this.hp -= 60; 
//                if (this.hp <= 0){
//                    t.deletePlant();
//                }
//            }
//        }
//    }

//    public void setWallnutCtr(int wallnutCtr) {
//        this.wallnutCtr = wallnutCtr;
//    }
//    
//    public int getWallnutCtr() {
//        return wallnutCtr;
//    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    
}