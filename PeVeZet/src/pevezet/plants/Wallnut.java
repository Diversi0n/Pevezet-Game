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
public class Wallnut extends Plant {
    
    private int wallnutCtr;

    public Wallnut(int x, int y, PImage[] idle, PImage seed) {
        super(50, 4000, 20, 0, idle, seed, x, y, 15, 4000);
        wallnutCtr = 0 ; 
    }
    
    public Wallnut(Plant p) {
        super(p);
        wallnutCtr = 0 ; 
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

    public void setWallnutCtr(int wallnutCtr) {
        this.wallnutCtr = wallnutCtr;
    }
    
    public int getWallnutCtr() {
        return wallnutCtr;
    }
    
}