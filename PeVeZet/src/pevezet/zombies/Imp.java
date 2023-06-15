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
public class Imp extends Zombie {
    int yawal, changeY, changeX;
    public Imp(int x, int y, PImage[] idle, PImage[] eat) {
        super(180, 100, 2, 30, 76, x, y, 180, idle, eat);
    }
    
    public Imp(Zombie z, int x, int y, int yawal, int changeY, int changeX) {
        super(z);
        this.x = x;
        this.y = y;
        this.yawal = yawal;
        this.changeY = changeY;
        this.changeX = changeX;
    }
    
    public boolean jalan() {
        if(this.y == yawal)
            return true;
        else
            return false;
    }

    public int getYawal() {
        return yawal;
    }

    public void setYawal(int yawal) {
        this.yawal = yawal;
    }

    public int getChangeY() {
        return changeY;
    }

    public void setChangeY(int changeY) {
        this.changeY = changeY;
    }

    public int getChangeX() {
        return changeX;
    }

    public void setChangeX(int changeX) {
        this.changeX = changeX;
    }
    
    
}
