/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class GreatWall extends Plant {    

    public GreatWall(int x, int y, PImage[] idle) {
        super(0, 10000, 0, 0, idle, null, x, y, 63, 10000);
    }
    
    public GreatWall(Plant p) {
        super(p);
    }
}