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
public class GreatWall extends Plant {
    
//    private int wallfireCtr;

    public GreatWall(int x, int y, PImage[] idle) {
        super(0, 10000, 0, 0, idle, null, x, y, 63, 10000);
//        wallfireCtr = 0 ; 
    }
    
    public GreatWall(Plant p) {
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
    
}