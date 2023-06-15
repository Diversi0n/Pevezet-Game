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
public class PotatoMine extends Plant {

    private int potatoCtr, delayHilang;
    private boolean ready, meledak;
    private ArrayList<Zombie> zombiesTerkena;
    private PImage[] explode, tempIdle;

    public PotatoMine(int x, int y, PImage seed, PImage[] init, PImage[] idle, PImage[] explode) {
        super(25, 300, 30, 1800, init, seed, x, y, 8, 300);
        this.tempIdle = idle;
        this.explode = explode;
        setUp();
    }

    public PotatoMine(Plant p) {
        super(p);
        this.tempIdle = ((PotatoMine) p).tempIdle;
        this.explode = ((PotatoMine) p).explode;
        setUp();
    }

    private void setUp() {
        this.potatoCtr = 0;
        this.ready = false;
        this.delayHilang = 0;
        this.meledak = false;
        zombiesTerkena = new ArrayList();
    }

    public boolean meledak(ArrayList<Zombie> z, Tile t) {
        count();
        if (!ready) {
            keluar();
        } else {
            cekAdaZombie(z);
            if (meledak) {
                this.setIdle(explode);
                z.removeAll(zombiesTerkena);
                zombiesTerkena.clear();
                if (this.delayHilang + 5 <= this.potatoCtr) {
                    t.deletePlant();
                    return true;
                }
            }
        }
        return false;
    }

    private void count() {
        if (this.ctr == 39) {
            this.potatoCtr++;
            System.out.println(this.potatoCtr);
        }
    }

    private void keluar() {
        if (this.potatoCtr == 15) {
            this.setIdle(tempIdle);
            ready = true;
        }
    }

    private void cekAdaZombie(ArrayList<Zombie> z) {
        for (int i = 0; i < z.size(); i++) {
            if (cekPosZombie(z.get(i))) {
                zombiesTerkena.add(z.get(i));
            }
        }
        if (zombiesTerkena.size() > 0) {
            this.meledak = true;

            this.delayHilang = this.potatoCtr;
            System.out.println("delay :" + delayHilang);
        }
    }

    private boolean cekPosZombie(Zombie z) {
        return z.getX() <= this.x + 70 && z.getX() > this.x - 70;
    }

}
