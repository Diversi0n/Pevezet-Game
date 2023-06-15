/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet.plants;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class CoconutCannon extends Plant {
    private int cocoCtr, coolTime;
    private PImage[] attack;
    public CoconutCannon(int x, int y, PImage[] idle, PImage seed, PImage[] attack) {
        super(300, 300, 5, 750, idle, seed, x, y, 25, 300);
        this.cocoCtr = 0;
        this.coolTime = 500;
        this.attack = attack;
        this.ctr = 0;
    }
    
    public CoconutCannon(Plant p) {
        super(p);
        this.cocoCtr = 0;
        this.coolTime = 500;
        this.attack = ((CoconutCannon)p).attack;
        this.ctr = 0;
    }
    
    public void drawCoolDown(PApplet app){
        if(ctr > -1)
        {
//            if(this instanceof GreatWall)
//                app.image(idle[ctr / 5 % idleSize],x,y, 120, 240);
//            else
                app.image(attack[17],x,y, 120, 120);
//            ctr %= 5 * idleSize;
        }
    }
    
    public boolean drawAttack(PApplet app){
        
//        System.out.println(ctr + " " + Math.abs(ctr/5) + " " + cocoCtr);
//        if(ctr > -1)
//        {
//            if(this instanceof GreatWall)
//                app.image(idle[ctr / 5 % idleSize],x,y, 120, 240);
//            else
                if(Math.abs(ctr/5) > 17 && cocoCtr == 1) {
                    if(Math.abs(ctr/5) == 20) {
                        this.cocoCtr--;
                        this.ctr = 0;
                    }
                    else
                        app.image(attack[Math.abs(ctr/5)],x,y, 120, 120);
                }
                else {
                    app.image(attack[Math.min(Math.abs(ctr/5), 17)],x,y, 120, 120);
                }
//                if(Math.min(Math.abs(ctr/5), 17) == 17 && this.cocoCtr == 0)
//                    this.cocoCtr = this.coolTime;
                if(Math.abs((ctr+1)/5) == 13 && Math.abs(ctr/5) == 14)
                    return true;
                else if(Math.min(Math.abs(ctr/5), 17) == 17) {
//                    System.out.println("masuk " + this.cocoCtr);
                    if(this.cocoCtr > 1) {
                        this.cocoCtr--;
                        if(this.cocoCtr == 1)
                            this.ctr = -90; 
                    }
//                    if(this.cocoCtr == 0)
//                        this.ctr = 0;
                }
//            ctr %= 5 * idleSize;
//        }
        return false;
    }
    
    public int getCocoCtr() {
        return cocoCtr;
    }

    public void setCocoCtr(int cocoCtr) {
        this.cocoCtr = cocoCtr;
    }
    
    public void addCoco(ArrayList<Bullet> bulletActive, Bullet bullet) {
        bulletActive.add(new Bullet(bullet, x + 100, this.y + 30, bullet.getBullet().getWidth(), y));
        this.cocoCtr = this.coolTime;
//        cocoCtr = 1;
    }
}
