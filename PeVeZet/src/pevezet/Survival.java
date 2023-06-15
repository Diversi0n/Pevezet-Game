/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet;

import pevezet.plants.Wallfire;
import pevezet.plants.GreatWall;
import pevezet.plants.TwinFlowers;
import pevezet.plants.FlamingThreepeater;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import pevezet.combine.*;
import pevezet.plants.*;
import pevezet.zombies.*;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author tasya
 */
public class Survival extends PApplet {
    //Settings
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;
    
    //Asset
    private PImage bg, sun, pick, pausemenu, shovelImg, over;
    private String lagu = "src/assets/Map/GrasswalkTheme.wav";
    private PlayMusic ThemeSong = new PlayMusic(lagu);
    
    //Menu
    private static int time, delay = 1000, now;
    private GUIButton grass, pool;
    private boolean playgrass, playpool, pause, gameover;
    
    //Gameplay
    //choosePlant, zombies, bullets -> arraylist untuk semua plant, zombie, dan peluru
    //plants, zombieActive, bulletActive -> arraylist untuk plant, zombie, dan peluru yang sedang digunakan
    private ArrayList<Plant> choosePlant, plants, combinePlant;
    private ArrayList<Zombie> zombies;
    private ArrayList<Zombie>[] zombieActive;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet>[] bulletActive;
    private Tile[][] tiles;
    private LawnMower[] lawnMower;
    //suns -> sunflower yang pasti dibuat tetapi dihide (dipakai untuk mengeluarkan sun dari langit)
    //plantFood -> button plant food, food -> jumlah plant food
    private Plant select, suns;
    private GUIButton shovel, plantFood;
    private PImage[] plantFoodImg;
    private boolean shovelSelect, pickplant, plantFoodSelect;
    private int playerSun, food;
    
    public void settings(){
        size(WIDTH,HEIGHT);
    } 
    
    public void setup(){
        frameRate(FPS);
        //Setup game
        bg = loadImage("src/assets/Survival/Menu.png");
        pausemenu = loadImage("src/assets/Map/Pause.png");
        over = loadImage("src/assets/Map/Game Over.png");
        playgrass = false;
        playpool = false;
        pause = false;
        gameover = false;
        pickplant = false;
        time = 0;
        playerSun = 1000;
        shovel = new GUIButton(730,0,110,85, new Color(255,255,0, 0));
        shovelSelect = false;
        food = 3;
        plantFood = new GUIButton(860,0,85,85, new Color(255,255,0, 0));
        plantFoodSelect = false;
        ThemeSong.PlayMusic();
        //Load image plant food
        plantFoodImg = new PImage[100];
        for(int i = 0; i < 12; i++){
            plantFoodImg[i] = loadImage("src/assets/Map/Plant Food/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        //Button untuk pilih map
        grass = new GUIButton(270,250,280,210, new Color(255,255,0, 0));
        pool = new GUIButton(740,250,280,210, new Color(255,255,0));shovel = new GUIButton(730,0,110,85, new Color(255,255,0, 0));
    }
    
    public void loadAsset() {
        grass = new GUIButton(0,0,0,0, new Color(0,255,0));
        pool = new GUIButton(0,0,0,0, new Color(0,255,0));
        
        sun = loadImage("src/assets/Map/Sun.gif");
        pick = loadImage("src/assets/Map/Pick Plant.png");
        shovelImg = loadImage("src/assets/Map/Shovel.png");
        
        //Load Plant
        PImage[] idle = new PImage[100];
        PImage seed = new PImage();
        choosePlant = new ArrayList<>();
        plants = new ArrayList<>();
        
        seed = loadImage("src/assets/Plants/Sunflower/seed.png");
        idle = new PImage[100];
        for(int i = 0; i < 33; i++){
            idle[i] = loadImage("src/assets/Plants/Sunflower/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new Sunflower(0, 0, idle, seed));
        suns = new Sunflower(choosePlant.get(0));
        
        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/Peashooter/seed.png");
        for(int i = 0; i < 30; i++){
            idle[i] = loadImage("src/assets/Plants/Peashooter/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new Peashooter(0, 0, idle, seed));
        
        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/SnowPea/seed.png");
        for(int i = 0; i < 20; i++){
            idle[i] = loadImage("src/assets/Plants/SnowPea/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new SnowPea(0, 0, idle, seed));
        
        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/Torchwood/seed.png");
        for(int i = 0; i < 23; i++){
            idle[i] = loadImage("src/assets/Plants/Torchwood/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new Torchwood(0, 0, idle, seed));
        
        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/Wallnut/seed.png");
        for(int i = 0; i < 15; i++){
            idle[i] = loadImage("src/assets/Plants/Wallnut/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new Wallnut(0, 0, idle, seed));
        
        seed = new PImage();
        idle = new PImage[100];
        PImage[] init = new PImage[100];
        PImage[] explode = new PImage[100];

        seed = loadImage("src/assets/Plants/PotatoMine/seed.png");
        for (int i = 0; i < 8; i++) {
            init[i] = loadImage("src/assets/Plants/PotatoMine/Init.png");
            idle[i] = loadImage("src/assets/Plants/PotatoMine/idle/" + (int) Math.floor(i / 100) + "" + (int) Math.floor(i / 10 % 10) + "" + i % 10 + ".png");
            explode[i] = loadImage("src/assets/Plants/PotatoMine/Explode.png");
        }
        choosePlant.add(new PotatoMine(0, 0, seed, init, idle, explode));

        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/CherryBomb/seed.png");
        for (int i = 0; i < 13; i++) {
            idle[i] = loadImage("src/assets/Plants/CherryBomb/idle/" + (int) Math.floor(i / 100) + "" + (int) Math.floor(i / 10 % 10) + "" + i % 10 + ".png");
        }
        choosePlant.add(new CherryBomb(0, 0, idle, seed));

        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/Lilypad/seed.png");
        for (int i = 0; i < 22; i++) {
            idle[i] = loadImage("src/assets/Plants/Lilypad/idle/" + (int) Math.floor(i / 100) + "" + (int) Math.floor(i / 10 % 10) + "" + i % 10 + ".png");
        }
        choosePlant.add(new Lilypad(0, 0, idle, seed));

        seed = new PImage();
        idle = new PImage[100];
        PImage[] attack = new PImage[100];
        seed = loadImage("src/assets/Plants/TangleKelp/seed.png");
        for (int i = 0; i < 48; i++) {
            idle[i] = loadImage("src/assets/Plants/TangleKelp/idle/" + (int) Math.floor(i / 100) + "" + (int) Math.floor(i / 10 % 10) + "" + i % 10 + ".png");
            attack[i] = loadImage("src/assets/Plants/TangleKelp/attack/00" + i / 6 + ".png");
        }
        choosePlant.add(new TangleKelp(0, 0, idle, seed, attack));
        
        seed = new PImage();
        idle = new PImage[100];
        seed = loadImage("src/assets/Plants/CoconutCannon/seed.png");
        for(int i = 0; i < 27; i++){
            idle[i] = loadImage("src/assets/Plants/CoconutCannon/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        
        attack = new PImage[100];
        for(int i = 0; i < 21; i++){
            attack[i] = loadImage("src/assets/Plants/CoconutCannon/attack/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        choosePlant.add(new CoconutCannon(0, 0, idle, seed, attack));
        
        //Load Combine Plant
        combinePlant = new ArrayList<>();
        idle = new PImage[100];
        for(int i = 0; i < 16; i++){
            idle[i] = loadImage("src/assets/Combine/Flaming Threepeater/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        combinePlant.add(new FlamingThreepeater(0, 0, idle));
        
        idle = new PImage[100];
        for(int i = 0; i < 52; i++){
            idle[i] = loadImage("src/assets/Combine/Wallfire/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        combinePlant.add(new Wallfire(0, 0, idle));
        
        idle = new PImage[100];
        for(int i = 0; i < 26; i++){
            idle[i] = loadImage("src/assets/Combine/Twin Flowers/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        combinePlant.add(new TwinFlowers(0, 0, idle));
        
        idle = new PImage[100];
        for(int i = 0; i < 63; i++){
            idle[i] = loadImage("src/assets/Combine/GreatWall/idle/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        combinePlant.add(new GreatWall(0, 0, idle));
        
        //Load Bullet
        //bulletActive index 0-5 buat nyimpen peluru, index 6 buat nyimpen sun
        bullets = new ArrayList<>();
        bulletActive = new ArrayList[7];
        for(int i = 0; i < 7; i++)
            bulletActive[i] = new ArrayList<>();
        
        idle = new PImage[100];
        int idleSize = 62;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Plants/Sunflower/Sun/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        bullets.add(new Bullet(new GUIButton(0, 0, 100,0, new Color(255, 255, 0)), 25, "Sun", idle, idleSize));
        
        idle = new PImage[100];
        idleSize = 1;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Plants/Peashooter/Pea.png");
        }
        bullets.add(new Bullet(new GUIButton(0, 0, 25,0, new Color(255, 255, 0)), 18, "Pea", idle, idleSize));
        
        idle = new PImage[100];
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Plants/SnowPea/FreezePea.png");
        }
        bullets.add(new Bullet(new GUIButton(0, 0, 25,0, new Color(255, 255, 0)), 18, "Freeze", idle, idleSize));
        
        idle = new PImage[100];
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Plants/Torchwood/Fire.png");
        }
        bullets.add(new Bullet(new GUIButton(0, 0, 50,0, new Color(255, 255, 0)), 30, "Fire", idle, idleSize));
        
        idle = new PImage[100];
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Plants/CoconutCannon/Coconut.png");
        }
        bullets.add(new Bullet(new GUIButton(0, 0,75,0, new Color(255, 255, 0)), 750, "Coconut", idle, idleSize));
        
        //Load Zombie
        PImage[] eat;
        int eatSize;
        zombies = new ArrayList<>();
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 22;
        eatSize = 21;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Basic/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Basic/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Basic(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 21;
        eatSize = 11;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Conehead/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Conehead/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Conehead(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 22;
        eatSize = 21;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/DuckyTube/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/DuckyTube/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new DuckyTube(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 63;
        eatSize = 33;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Snorkel/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Snorkel/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Snorkel(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 12;
        eatSize = 52;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Football/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Football/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Football(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 20;
        eatSize = 25;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Gargantuar/walk with Imp/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Gargantuar/eat with Imp/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Gargantuar(1260, 115, idle, eat));
        
        idle = new PImage[100];
        eat = new PImage[100];
        idleSize = 30;
        eatSize = 76;
        for(int i = 0; i < idleSize; i++){
            idle[i] = loadImage("src/assets/Zombies/Imp/walk/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        for(int i = 0; i < eatSize; i++){
            eat[i] = loadImage("src/assets/Zombies/Imp/eat/"+(int)Math.floor(i/100)+""+(int)Math.floor(i/10%10)+""+i%10+".png");
        }
        zombies.add(new Imp(1260, 115, idle, eat));
    }
    
    public void setupGrass(){
        playgrass = true;
        ThemeSong.stop();
//        lagu = "src/assets/Map/GrasswalkTheme.wav";
//        ThemeSong = new PlayMusic(lagu);
//        ThemeSong.PlayMusic();
        bg = loadImage("src/assets/Map/Grasswalk.png");
        loadAsset();
        
        tiles = new Tile[5][9];
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 9; j++)
                tiles[i][j] = new Tile(i, j, 115, 128, 50, 100);
        }
        
        PImage[] idle = new PImage[100];
        lawnMower = new LawnMower[5];
        for(int i = 0; i < 5; i++) {
            idle = new PImage[100];
            idle[0] = loadImage("src/assets/Map/Lawn Mower/idle.png");
            for(int j = 0; j < 5; j++){
                idle[j + 1] = loadImage("src/assets/Map/Lawn Mower/walk/"+(int)Math.floor(j/100)+""+(int)Math.floor(j/10%10)+""+j%10+".png");
            }
            lawnMower[i] = new LawnMower(-30, 120 + i * 115, idle);
        }
        
        zombieActive = new ArrayList[5];
        for(int i = 0; i < 5; i++)
            zombieActive[i] = new ArrayList<>();
        
        now = millis();
        pickplant = true;
    }
    
    public void setupPool(){
        playpool = true;
        ThemeSong.stop();
//        lagu = "src/assets/Map/GrasswalkTheme.wav";
//        ThemeSong = new PlayMusic(lagu);
//        ThemeSong.PlayMusic();
        
        bg = loadImage("src/assets/Map/Pool.png");
        loadAsset();
        
        tiles = new Tile[6][9];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new Tile(i, j, 103, 124, 105, 100);
                if (i == 2 || i == 3) {
                    tiles[i][j].setIsWater(true);
                }
            }
        }
//        tiles = new Tile[6][9];
//        for(int i = 0; i < 6; i++)
//        {
//            for(int j = 0; j < 9; j++)
//                tiles[i][j] = new Tile(i, j, 103, 124, 105, 100);
//        }
        
        PImage[] idle = new PImage[100];
        lawnMower = new LawnMower[6];
        for(int i = 0; i < 6; i++) {
            idle = new PImage[100];
            if(i == 2 || i == 3) {
                idle[0] = loadImage("src/assets/Map/Pool Cleaner/idle.png");
                idle[1] = loadImage("src/assets/Map/Pool Cleaner/walk.png");
            } else {
                idle[0] = loadImage("src/assets/Map/Lawn Mower/idle.png");
                for(int j = 0; j < 5; j++){
                    idle[j + 1] = loadImage("src/assets/Map/Lawn Mower/walk/"+(int)Math.floor(j/100)+""+(int)Math.floor(j/10%10)+""+j%10+".png");
                }
            }
            lawnMower[i] = new LawnMower(15, 96 + i * 101, idle);
        }
        
        for(Zombie i : zombies) {
            i.setY(103);
        }
        
        zombieActive = new ArrayList[6];
        for(int i = 0; i < 6; i++)
            zombieActive[i] = new ArrayList<>();
        now = millis();
        pickplant = true;
    }
    
    public void draw(){
        if(pause) {
            return;
        }
        background(bg);
        if(gameover) {
            ThemeSong.pause();
            noStroke();
            fill(0, 0, 0, 70);
            rect(0,0,1280,720);
            image(over,160,45,950,600);
            return;
        }
        if(playgrass || playpool){
            //Hover Menu
            hoverMenuGame();
            
            //Bar Plant Food
//            fill(255,255,255);
//            noStroke();
//            rect(plantFood.getX(),plantFood.getY(),plantFood.getWidth(),plantFood.getHeight());
            image(plantFoodImg[food], plantFood.getX(),plantFood.getY());

            //Pick Plant
            if(pickplant){
                pickPlant();
            }

            //Game play
            if(!pickplant)
            {
                drawLawnMower();
                drawPlant();

                //Selected plant + recharge
                plantSeed();

                //Update time + poin
                if(millis() - now >= delay)
                    updateTime();
                fill(255, 255, 0);
                textSize(60);
                text(time, 50, 100);
                fill(0, 0, 0);
                textSize(18);
                text(playerSun, 65, 90);

                drawZombie();
                drawBullet();

                noFill();
                noStroke();
            }
        }
        else{
            //Hover pilih map
            if(overRect(270,250,280,210)){
                noStroke();
                fill(255, 255, 255, 30);
                rect(270,250,280,210);
            }
            if(overRect(740,250,280,210)){
                noStroke();
                fill(255, 255, 255, 30);
                rect(740,250,280,210);
            }
        }
    }
    
    boolean overRect(int x, int y, int width, int height){
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
            return true;
        }
        else {
            return false;
        }
    }

    public void mousePressed(){
        if(bulletActive != null && !pause) {
            for(Bullet i : bulletActive[6]) {
                if(overRect(i.getBullet().getX(), i.getBullet().getY(), i.getBullet().getWidth(), i.getBullet().getWidth()))
//                if(mouseX >= i.getBullet().getX() && mouseX <= i.getBullet().getX() + i.getBullet().getWidth() && mouseY >= i.getBullet().getY() && mouseY <= i.getBullet().getY() + i.getBullet().getWidth())
                {
                    playerSun += i.getDmg();
                    bulletActive[6].remove(i);
                    return;
                }
            }
        }
        if(overRect(grass.getX(), grass.getY(), grass.getWidth(), grass.getHeight()) && !playgrass && !playpool){
//        if(mouseX >= grass.getX() && mouseX <= grass.getX()+grass.getWidth() && mouseY >= grass.getY() && mouseY <= grass.getY()+grass.getHeight() && !playgrass && !playpool){
            setupGrass();
        }
        else if(overRect(pool.getX(), pool.getY(), pool.getWidth(), pool.getHeight()) && !playgrass && !playpool){
            setupPool();
        }
        else if(playgrass) {
            playGrass();
        } else if(playpool) {
            playPool();
        }
    }
    
    public void updateTime() {
        time++;
        now = millis();
        for(Plant i : plants) {
            if(i.getTimer() > 0)
                i.setTimer(i.getTimer() - 1);
        }
        //Add sun dari langit
        if(time > 4 && (time - 5) % 10  == 0)
        {
            Random rand = new Random();
            //heightnya dipake buat nyimpen posisi berentinya matahari
            ((Sunflower)suns).addSun(bulletActive[6], bullets.get(0),rand.nextInt(128, 1050), rand.nextInt(120, 450));
        }
        //Add sun dari sunflower
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(tiles[i][j].hasPlant() && tiles[i][j].getPlant() instanceof Sunflower)
                    ((Sunflower)tiles[i][j].getPlant()).setSunCtr(((Sunflower)tiles[i][j].getPlant()).getSunCtr() + 1);
            }
        }
        //Add zombie
        if(time == 1 || time % 20 == 0)
        {
            Random rand = new Random();
            int idx = rand.nextInt(0, 5);
//            Zombie temp = zombies.get(rand.nextInt(0, 7));
            Zombie temp = zombies.get(4);
            if(temp instanceof Basic)
                zombieActive[idx].add(new Basic(temp));
            else if(temp instanceof Conehead)
                zombieActive[idx].add(new Conehead(temp));
            else if(temp instanceof DuckyTube)
                zombieActive[idx].add(new DuckyTube(temp));
            else if(temp instanceof Football)
                zombieActive[idx].add(new Football(temp));
            else if(temp instanceof Gargantuar)
                zombieActive[idx].add(new Gargantuar(temp));
//            else if(temp instanceof Imp)
//                zombieActive[idx].add(new Imp(temp));
            else if(temp instanceof Snorkel)
                zombieActive[idx].add(new Snorkel(temp));
            temp = zombieActive[idx].get(zombieActive[idx].size() - 1);
            if(playgrass)
                temp.setY(idx*temp.getY() + 70);
            else if(playpool)
                temp.setY(idx*temp.getY() + 60);
        }
    }
    
    public boolean cekTembak(int y) {
        for(Zombie i : zombieActive[y]) {
            if(i.getX() <= 1220) {
                return true;
            }
        }
        return false;
    }
    
    public void hoverMenuGame() {
        if(overRect(1090,0,200,40)){
            noStroke();
            fill(255, 255, 255, 20);
            rect(1090,0,200,40);
        }
    }
    
    public void pickPlant() {
        image(pick, 50, 150, 450, 500);
        int ctr = 0;
        for(Plant i : choosePlant) {
            image(i.getSeed(), 89+(ctr%4*102), 205+(ctr/4*116), 68, 96);
            ctr++;
        }
        ctr = 0;
        for(Plant i : choosePlant) {
            for(Plant j : plants) {
                if(i.getClass().equals(j.getClass()))
                {
                    noStroke();
                    fill(0,0,0, 75);
                    rect(89+(ctr%4*102), 205+(ctr/4*116), 68, 96);
                }
            }
            ctr++;
        }
        ctr = 1;
        for(Plant i : plants) {
            image(i.getSeed(), 75+(ctr*90), 7, 65, 84);
            ctr++;
        }
        if(plants.size() < 5) {
            fill(0,0,0, 75);
            rect(199, 596, 150, 40, 5);
        }
        if(playgrass) {
            noStroke();
            fill(0,0,0, 175);
            rect(89, 437, 68, 96);
            rect(191, 437, 68, 96);
            rect(395, 321, 68, 96);
        }
    }
    
    public void plantSeed() {
        //buat kotak tekan plantfood
//        fill(255,255,255);
//        noStroke();
//        rect(plantFood.getX(),plantFood.getY(),plantFood.getWidth(),plantFood.getHeight());
//        image(plantFoodImg[food], plantFood.getX(),plantFood.getY());
        int ctr = 1;
        for(Plant i : plants) {
            image(i.getSeed(), 75+(ctr*90), 7, 65, 84);
            if(i.getTimer() != 0)
            {
                image(i.getSeed(), 75+(ctr*90), 7, 65, 84);
                fill(0,0,0, 75);
                noStroke();
                rect(75+(ctr*90),7 + 84 / i.getRecharge() * (i.getRecharge() - i.getTimer()),65,84 - 84 / i.getRecharge() * (i.getRecharge() - i.getTimer()));
            }
            else if(select == i)
            {
                image(i.getSeed(), 75+(ctr*90), 8, 65, 82);
                noFill();
                stroke(0,0,0);
                strokeWeight(2);
                rect(75+(ctr*90),7,65,84);
                strokeWeight(1);
            }
            ctr++;
        }
        if(shovelSelect) {
            fill(0,0,0, 75);
            noStroke();
            rect(shovel.getX(),shovel.getY(),shovel.getWidth(),shovel.getHeight());
            image(shovelImg, 726, -5, 122, 92);
        }
        if(plantFoodSelect) {
//            fill(0,0,0, 75);
//            noStroke();
//            rect(plantFood.getX(),plantFood.getY(),plantFood.getWidth(),plantFood.getHeight());
            image(plantFoodImg[food + 5], plantFood.getX(),plantFood.getY());
            image(plantFoodImg[11], plantFood.getX() + 14, plantFood.getY() + 11, 60, 65);
        }
    }
    
    public void drawLawnMower() {
        if(playgrass) {
            for(int i = 0; i < 5; i++) {
                if(lawnMower[i] != null) {
                    lawnMower[i].drawIdle(this);
                    lawnMower[i].lindes(zombieActive[i]);
                    if(lawnMower[i].getX() > 1280)
                        lawnMower[i] = null;
                }
            }
        }
        else if(playpool) {
            for(int i = 0; i < 6; i++) {
                if(lawnMower[i] != null) {
                    if(i == 2 || i == 3)
                        lawnMower[i].drawPoolCleaner(this);
                    else
                        lawnMower[i].drawIdle(this);
                    lawnMower[i].lindes(zombieActive[i]);
                    if(lawnMower[i].getX() > 1280)
                        lawnMower[i] = null;
                }
            }
        }
    }
    
    public void drawPlant() {
//        fill(0,0,0,75);
//        rect(110, 100, 1110, 600);
        int y = 5;
        if(playpool)
            y = 6;
        for(int i = 0; i < y; i++)
        {
            for(int j = 0; j < 9; j++)
            {
//                        noFill();
//                        stroke(0, 0, 0);
//                        rect(tiles[i][j].getTile().getX(),tiles[i][j].getTile().getY(),tiles[i][j].getTile().getWidth(),tiles[i][j].getTile().getHeight());
//                        fill(0,0,0,75);
                if(tiles[i][j].hasPlant())
                {
                    for (int l = 0; l < tiles[i][j].getAllPlants().size(); l++) {
                        Plant p = tiles[i][j].getAllPlants().get(l);
                        //Draw idle plant
                        if((p instanceof GreatWall && i < y - 1 && tiles[i + 1][j].hasPlant() && p == tiles[i + 1][j].getPlant()) || !(p instanceof GreatWall)) {
                            if(p instanceof CoconutCannon && p.getCtr() < 0) {
                                p.setCtr(p.getCtr() - 2);
                                if(((CoconutCannon)p).drawAttack(this))
                                    ((CoconutCannon)p).addCoco(bulletActive[i], bullets.get(4));
                            }
                            else
                                p.drawIdle(this);
                            p.setCtr(p.getCtr() + 1);
                        }
                        //Health bar plant
                        if (!(p instanceof CherryBomb) && !(p instanceof TangleKelp)) {
                            fill(255, 255, 0);
                            noStroke();
                            rect(p.getX() + 35, p.getY(), 45 * p.getHp() / p.getHealth(), 8);
                            noFill();
                            stroke(0, 0, 0);
                            strokeWeight((float) 1.5);
                            rect(p.getX() + 35, p.getY(), 45, 8);
                        }
                        //Animasi plant
                        if (p instanceof Sunflower) {
                            Sunflower sunflower = (Sunflower) p;
                            sunflower.setSunCtr(sunflower.getSunCtr() + 1);
                            if (sunflower.getSunCtr() % 500 == 0) {
                                sunflower.addSun(bulletActive[6], bullets.get(0), -1, -1);
                            }
                        } else if (p instanceof Peashooter) {
                            Peashooter peashooter = (Peashooter) p;
                            if (zombieActive[i].size() > 0 && cekTembak(i)) {
                                if (peashooter.getPeaCtr() % 180 == 0) {
                                    peashooter.addPea(bulletActive[i], bullets.get(1));
                                }
                                peashooter.setPeaCtr((peashooter.getPeaCtr() + 1) % 1000);
                            } else {
                                peashooter.setPeaCtr(0);
                            }
                        } else if (p instanceof SnowPea) {
                            SnowPea snowPea = (SnowPea) p;
                            if (zombieActive[i].size() > 0 && cekTembak(i)) {
                                if (snowPea.getPeaCtr() % 180 == 0) {
                                    snowPea.addPea(bulletActive[i], bullets.get(2));
                                }
                                snowPea.setPeaCtr((snowPea.getPeaCtr() + 1) % 1000);
                            } else {
                                snowPea.setPeaCtr(0);
                            }
                        } else if (p instanceof PotatoMine) {
                            PotatoMine potatoMine = (PotatoMine) p;
                            if (potatoMine.meledak(zombieActive[i], tiles[i][j])) {
                                break;
                            }

                        } else if (p instanceof CherryBomb) {
                            CherryBomb cherryBomb = (CherryBomb) p;
                            cherryBomb.setBatas(tiles[i][j], i, j);

                            if (cherryBomb.getCtr() == 64) {
                                cherryBomb.meledak(zombieActive[i]);
                                if (i - 1 > -1) {
                                    cherryBomb.meledak(zombieActive[i - 1]);
                                }
                                if (i + 1 < y) {
                                    cherryBomb.meledak(zombieActive[i + 1]);
                                }
                                tiles[i][j].deletePlant();
                                break;
                            }

                        } else if (p instanceof TangleKelp) {
                            TangleKelp tangleKelp = (TangleKelp) p;
                            if (tangleKelp.attack(zombieActive[i], tiles[i][j])) {
                                break;
                            }

                        } else if(p instanceof FlamingThreepeater) {
                            FlamingThreepeater flaming = (FlamingThreepeater) p;
                            if((zombieActive[i - 1].size() > 0 && cekTembak(i - 1)) || (zombieActive[i].size() > 0 && cekTembak(i)) || (zombieActive[i + 1].size() > 0 && cekTembak(i + 1))) {
                                if(flaming.getPeaCtr() % 180 == 0) {
                                    flaming.addPea(bulletActive[i - 1], bullets.get(3), tiles[i - 1][j].getTile().getY() + 5);
                                    flaming.addPea(bulletActive[i], bullets.get(3), tiles[i][j].getTile().getY() + 5);
                                    flaming.addPea(bulletActive[i + 1], bullets.get(3), tiles[i + 1][j].getTile().getY() + 5);
                                }
                                flaming.setPeaCtr((flaming.getPeaCtr() + 1) % 1000);
                            }
                            else
                                flaming.setPeaCtr(0);
                        } else if(p instanceof TwinFlowers) {
                            TwinFlowers twinflowers = (TwinFlowers) p;
                            twinflowers.setSunCtr(twinflowers.getSunCtr() + 1);
                            if(twinflowers.getSunCtr() % 500 == 0) {
                                twinflowers.addSun(bulletActive[6], bullets.get(0), -1, -1);
                            }
                        } 
    //                    else if(i < 4 && tiles[i][j].getPlant() instanceof GreatWall && tiles[i + 1][j].getPlant() == tiles[i][j].getPlant()) {
    //                        Great twinflowers = (TwinFlowers)tiles[i][j].getPlant();
    //                        twinflowers.setSunCtr(twinflowers.getSunCtr() + 1);
    //                        if(twinflowers.getSunCtr() % 500 == 0) {
    //                            twinflowers.addSun(bulletActive[5], bullets.get(0), -1, -1);
    //                        }
                    }
                }
            }
        }
    }
    
    public void drawZombie() {
        int y = 5;
        if(playpool)
            y = 6;
        for(int j = 0; j < y; j++) {
            for(int k = 0; k < zombieActive[j].size(); k++) {
                Zombie i = zombieActive[j].get(k);
                i.setCtr(i.getCtr() + 1);
                if(i.getWalkCtr() < 0) {
                    i.setWalkCtr(i.getWalkCtr() - i.getSpeed()/2);
                    if(i.getWalkCtr() % i.getSpeed() == 0)
                        i.setCtr(i.getCtr() - 1);
                }
                else
                    i.setWalkCtr(i.getWalkCtr() + i.getSpeed());
                //Cek Lawn Mower
                if(i.getX() < 40) {
                    if(lawnMower[j] == null) {
//                        fill(0, 0, 0);
//                        textSize(60);
//                        text("Game Over", 50, 100);
                        gameover = true;
                    }
                    else {
                        lawnMower[j].setX(lawnMower[j].getX() + 1);
                    }
                }
                //Cek makan
                if((i.getX()-50)/128 < 9 && i.makan(tiles[j][(i.getX()-50)/128])) {
                    i.drawEat(this);
                    if(i.getWalkCtr() % 130 == 0) {
                        Plant temp = tiles[j][(i.getX()-50)/128].getPlant();
                        temp.setHp(temp.getHp() - i.getDmg());
                        if(tiles[j][(i.getX()-50)/128].getPlant() instanceof Wallfire) {
                            i.setHp(i.getHp() - ((Wallfire)tiles[j][(i.getX()-50)/128].getPlant()).getDmg());
                            if(i.getHp() <= 0)
                                zombieActive[j].remove(k);
                        }
                        if(temp.getHp() <= 0)
                            tiles[j][(i.getX()-50)/128].deletePlant();
                    }
                }
                else {
                    if(i instanceof Gargantuar && i.getHp() <= i.getHealth()/2 && !((Gargantuar)i).isLempar()) {
                        PImage[] idle = new PImage[100], eat = new PImage[100];
                        int idleSize = 22, eatSize = 13;
                        for(int l = 0; l < idleSize; l++){
                            idle[l] = loadImage("src/assets/Zombies/Gargantuar/walk/"+(int)Math.floor(l/100)+""+(int)Math.floor(l/10%10)+""+l%10+".png");
                        }
                        for(int l = 0; l < eatSize; l++){
                            eat[l] = loadImage("src/assets/Zombies/Gargantuar/eat/"+(int)Math.floor(l/100)+""+(int)Math.floor(l/10%10)+""+l%10+".png");
                        }
                        ((Gargantuar)i).setLempar(true);
                        i.setIdleSize(idleSize);
                        i.setEatSize(eatSize);
                        i.setIdle(idle);
                        i.setEat(eat);
//                        System.out.println("ganti");
                        int x = (i.getX() - 50) / 128,  ye = (i.getY() - 100) / 115 + 1;
                        for(int a = Math.max(8, x - 1); a >= 0; a--) {
                            System.out.println(ye + " " + a);
                            if(tiles[ye][a].hasPlant()) {
                                int jarak = i.getX() - tiles[ye][a].getPlant().getX() - 20, changeY, changeX;
                                System.out.println("ketemu " + jarak);
                                if(jarak > 102) {
                                    changeY = 1;
                                    changeX = jarak/102;
                                } else
                                {
                                    changeX = 1;
                                    changeY = 102/jarak;
                                }
                                System.out.println("tambah " + zombies.get(6));
                                zombieActive[j].add(new Imp(zombies.get(6), i.getX(), i.getY() - 50, i.getY(), changeY, changeX));
                                System.out.println("posisi akhir " + (i.getY() + 70));
//                                System.out.println(changeX);
                                break;
                            }
                        }
                    }
                    i.drawIdle(this);
                }
                if(i instanceof Imp && !((Imp)i).jalan() && i.getWalkCtr() % ((Imp)i).getChangeX()== 0) {
                    i.setY(i.getY() + 2);
//                    System.out.println(i.getY());
                }
                //Zombie jalan
                if(i instanceof Imp && !((Imp)i).jalan() && i.getWalkCtr() % 3 == 0) {
                    i.setX(i.getX() - ((Imp)i).getChangeX());
                }
                else if(i.getWalkCtr() % 5 == 0 && ((i.getX()-50)/128 > 8 || !i.makan(tiles[j][(i.getX()-50)/128])))
                    i.setX(i.getX() - 1);
                if(i.getWalkCtr() < 0 && i.getWalkCtr() % 5 == 0) {
                    if(i.getWalkCtr() < -100) {
                        i.setWalkCtr(0);
                    }
                }
                else if(i.getWalkCtr() >= 0 && i.getWalkCtr() % 5 == 0) {
                    i.setWalkCtr(i.getWalkCtr() % 200);
                }
                //Health bar
                fill(255,255,0);
                noStroke();
                rect(i.getX() + 38, i.getY() - 15,45*i.getHp()/i.getHealth(),8);
                noFill();
                stroke(0, 0, 0);
                strokeWeight((float)1.5);
                rect(i.getX() + 38, i.getY() - 15,45,8);
            }
        }
    }
    
    public void drawBullet() {
        for(int j = 0; j < 7; j++) {
            for(int l = 0; l < bulletActive[j].size(); l++) {
                Bullet i = bulletActive[j].get(l);
                i.setCtr(i.getCtr() + 1);
                i.drawIdle(this);
                //Draw sun
                if(j == 6 && i.getBullet().getY() < i.getBullet().getHeight()) {
                    i.getBullet().setY(i.getBullet().getY() + 1);
                    if(i.getBullet().getY() == i.getBullet().getHeight())
                        i.setCtr(0);
                }
                else if(j == 6 && i.getBullet().getY() == i.getBullet().getHeight() && i.getCtr() == 300) {
                    bulletActive[j].remove(l);
                    l--;
                    if(l < 0)
                        break;
                    else
                        continue;
                }
                //Draw peluru
                else if(j < 6) {
                    i.getBullet().setX(i.getBullet().getX() + 6);
                    if(i.getBullet().getX() >= 1280) {
                        bulletActive[j].remove(i);
                        l--;
                        if(l < 0)
                            break;
                        else
                            continue;
                    }
                    if(!i.getType().equals("Coconut") && i.damage(zombieActive[j])) {
                        bulletActive[j].remove(i);
                        l--;
                        if(l < 0)
                            break;
                    }
                    //Pea berubah jadi Fire
                    else if(i.getType().equals("Pea") && !i.isChange()) {
                        int x = (i.getBullet().getX() - 50) / 128,  y = (i.getBullet().getY() - 100) / 115;
                        if(x < 9 && tiles[y][x].hasPlant() && tiles[y][x].getPlant() instanceof Torchwood && i.getBullet().getX() > tiles[y][x].getTile().getX() + 30) {
                            i.setIdle(bullets.get(3).getIdle());
                            i.setType("Fire");
                        }
                    }
                    //Freeze berubah jadi Pea
                    else if(i.getType().equals("Freeze") && !i.isChange()) {
                        int x = (i.getBullet().getX() - 50) / 128,  y = (i.getBullet().getY() - 100) / 115;
                        if(x < 9 && tiles[y][x].hasPlant() && tiles[y][x].getPlant() instanceof Torchwood && i.getBullet().getX() > tiles[y][x].getTile().getX() + 30) {
                            i.setIdle(bullets.get(1).getIdle());
                            i.setType("Pea");
                            i.setChange(true);
                        }
                    }
                    else if(i.getType().equals("Coconut")) {
                        i.damage(zombieActive[j]);
                    }
                }
            }
        }
    }
    
    public boolean pressMenu() {
        if(overRect(1090,0,200,40) && !pause){
            pause = true;
            ThemeSong.pause();
            noStroke();
            fill(0, 0, 0, 70);
            rect(0,0,1280,720);
            image(pausemenu,260,70,750,550);
            return true;
        }
        else if(pause && overRect(487,465,140,28)) {
            pause = false;
            ThemeSong.resume();
            return true;              
        }
        else if(pause && overRect(635,465,140,28)) {
            ThemeSong.stop();
            PApplet.main("pevezet.PeVeZet");
            surface.setVisible(false);
            return true;
        }
        else if(gameover && overRect(460,450,140,28)) {
            gameover = false;
            ThemeSong.stop();
            PApplet.main("pevezet.PeVeZet");
            surface.setVisible(false);
            return true;              
        }
        else if(gameover && overRect(660,450,140,28)) {
            gameover = false;
            ThemeSong.stop();
            if(playgrass) {
                setupGrass();
                playGrass();
            }
            else if(playpool) {
                setupPool();
                playPool();
            }
            return true;
        }
        return false;
    }
    
    public void playGrass() {
        //Menu
        if(pressMenu())
            return;
            
        //Pick Plant
        if(pickplant) {
            if(overRect(89,205,374,212)) {
//            if(mouseX >= 89 && mouseX <= 463 && mouseY >= 205 && mouseY <= 417) {
                int x = (mouseX - 89) / 101,  y = (mouseY - 205) / 115, idx = y * 4 + x;

                if(mouseX > 157+(x*102) || mouseY > 301+(y*116) || idx == 7)
                    return;
                for(Plant i : plants) {
                    if(i.getClass().equals(choosePlant.get(idx).getClass())) {
                        return;
                    }
                }
                if(plants.size() < 5) {
                    if(choosePlant.get(idx) instanceof CherryBomb)
                        plants.add(new CherryBomb(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Peashooter)
                        plants.add(new Peashooter(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof PotatoMine)
                        plants.add(new PotatoMine(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof SnowPea)
                        plants.add(new SnowPea(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Sunflower)
                        plants.add(new Sunflower(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Torchwood)
                        plants.add(new Torchwood(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Wallnut)
                        plants.add(new Wallnut(choosePlant.get(idx)));
                }
            }
            else if(overRect(165,7,425,84)) {
//            else if(mouseX >= 165 && mouseX <= 590 && mouseY >= 7 && mouseY <= 91) {
                shovelSelect = false;
                //idx = index plant yang ditekan
                int idx = (mouseX - 165) / 90;
                if(mouseX > 230+(idx*90))
                    return;
                if(idx < plants.size())
                    plants.remove(idx);
            }
            else if(overRect(200,600,150,30) && plants.size() == 5) {
//            else if(mouseX >= 200 && mouseX <= 350 && mouseY >= 600 && mouseY <= 630 && plants.size() == 5) {
                pickplant = false;
                if(playgrass)
                    playGrass();
                else if(playpool)
                    playPool();
            }
            return;
        }

        //Select plant
        if(overRect(165,7,425,84)) {
            shovelSelect = false;
            plantFoodSelect = false;
            //idx = index plant yang ditekan
            int idx = (mouseX - 165) / 90;
            if(mouseX > 230+(idx*90))
                return;
            if(idx < plants.size() && plants.get(idx).getTimer() == 0)
                select = plants.get(idx);
        }
        //Select shovel
        else if(overRect(shovel.getX(), shovel.getY(), shovel.getWidth(), shovel.getHeight()) && plants.size() == 5) {
//        else if(mouseX >= shovel.getX() && mouseX <= shovel.getX()+shovel.getWidth() && mouseY >= shovel.getY() && mouseY <= shovel.getY()+shovel.getHeight()){
            shovelSelect = true;
            plantFoodSelect = false;
            select = null;
        }
        //Select plant food
        else if(overRect(plantFood.getX(), plantFood.getY(), plantFood.getWidth(), plantFood.getHeight()) && food > 0) {
//        else if(mouseX >= shovel.getX() && mouseX <= shovel.getX()+shovel.getWidth() && mouseY >= shovel.getY() && mouseY <= shovel.getY()+shovel.getHeight()){
            plantFoodSelect = true;
            shovelSelect = false;
            select = null;
        }

        //Tanam plant
        else if(overRect(50, 100, 1160, 580)) {
//        else if(mouseX >= 50 && mouseX <= 1210 && mouseY >= 100 && mouseY <= 680) {
            //x = kolom tile, y = baris tile
            int x = (mouseX - 50) / 128,  y = (mouseY - 100) / 115;
            if(!tiles[y][x].hasPlant() && select != null && playerSun >= select.getPrice())
            {
                playerSun -= select.getPrice();
                select.setX(tiles[y][x].getTile().getX() + 5);
                select.setY(tiles[y][x].getTile().getY() + 5);
                if(select instanceof CherryBomb)
                    tiles[y][x].addPlant(new CherryBomb(select));
                else if(select instanceof CoconutCannon)
                    tiles[y][x].addPlant(new CoconutCannon(select));
                else if(select instanceof Lilypad)
                    tiles[y][x].addPlant(new Lilypad(select));
                else if(select instanceof Peashooter)
                    tiles[y][x].addPlant(new Peashooter(select));
                else if(select instanceof PotatoMine)
                    tiles[y][x].addPlant(new PotatoMine(select));
                else if(select instanceof SnowPea)
                    tiles[y][x].addPlant(new SnowPea(select));
                else if(select instanceof Sunflower)
                    tiles[y][x].addPlant(new Sunflower(select));
                else if(select instanceof TangleKelp)
                    tiles[y][x].addPlant(new TangleKelp(select));
                else if(select instanceof Torchwood)
                    tiles[y][x].addPlant(new Torchwood(select));
                else if(select instanceof Wallnut)
                    tiles[y][x].addPlant(new Wallnut(select));
                select.setTimer(select.getRecharge());
                select = null;
            }
            else if(tiles[y][x].hasPlant() && shovelSelect) {
                tiles[y][x].setPlant(null);
                shovelSelect = false;
            }
            //cek combine
            else if(tiles[y][x].hasPlant() && plantFoodSelect) {    
                Plant temp = tiles[y][x].getPlant();
                if(y < 4 && temp instanceof Torchwood) {
                    if(tiles[y + 1][x].getPlant() instanceof Wallnut) {
                        temp = combinePlant.get(1);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new Wallfire(temp));
                        tiles[y + 1][x].setPlant(null);
                        food--;
                    }
                    else if(y > 0 && tiles[y - 1][x].getPlant() instanceof Peashooter && tiles[y + 1][x].getPlant() instanceof Peashooter) {
                        temp = combinePlant.get(0);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new FlamingThreepeater(temp));
                        tiles[y - 1][x].setPlant(null);
                        tiles[y + 1][x].setPlant(null);
                        plantFoodSelect = false;
                        food--;
                    }
                }
                else if(y < 4 && temp instanceof Wallnut) {
                    if(tiles[y + 1][x].getPlant() instanceof Torchwood) {
                        temp = combinePlant.get(1);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new Wallfire(temp));
                        tiles[y + 1][x].setPlant(null);
                        plantFoodSelect = false;
                        food--;
                    }
                    else if(tiles[y + 1][x].getPlant() instanceof Wallnut) {
                        temp = combinePlant.get(3);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new GreatWall(temp));
                        tiles[y + 1][x].setPlant(tiles[y][x].getPlant());
                        plantFoodSelect = false;
                        food--;
                    }
                }
                else if(y < 4 && temp instanceof Sunflower && tiles[y + 1][x].getPlant() instanceof Sunflower) {
                    temp = combinePlant.get(2);
                    temp.setX(tiles[y][x].getTile().getX() + 5);
                    temp.setY(tiles[y][x].getTile().getY() + 5);
                    tiles[y][x].setPlant(new TwinFlowers(temp));
                    tiles[y + 1][x].setPlant(null);
                    plantFoodSelect = false;
                    food--;
                }
            }
        }
    }
    
    public void playPool() {
        //Menu
        if(pressMenu())
            return;
//            
        //Pick Plant
        if(pickplant) {
            if(overRect(89,205,374,328)) {
//                if(mouseX >= 89 && mouseX <= 463 && mouseY >= 205 && mouseY <= 533) {
                int x = (mouseX - 89) / 101,  y = (mouseY - 205) / 115, idx = y * 4 + x;
                System.out.println(idx);
                if(mouseX > 157+(x*102) || mouseY > 301+(y*116))
                    return;
                for(Plant i : plants) {
                    if(i.getClass().equals(choosePlant.get(idx).getClass())) {
                        return;
                    }
                }
                if(plants.size() < 5) {
                    if(choosePlant.get(idx) instanceof CherryBomb)
                        plants.add(new CherryBomb(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof CoconutCannon)
                        plants.add(new CoconutCannon(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Lilypad)
                        plants.add(new Lilypad(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Peashooter)
                        plants.add(new Peashooter(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof PotatoMine)
                        plants.add(new PotatoMine(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof SnowPea)
                        plants.add(new SnowPea(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Sunflower)
                        plants.add(new Sunflower(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof TangleKelp)
                        plants.add(new TangleKelp(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Torchwood)
                        plants.add(new Torchwood(choosePlant.get(idx)));
                    else if(choosePlant.get(idx) instanceof Wallnut)
                        plants.add(new Wallnut(choosePlant.get(idx)));
                }
            }
            else if(overRect(165,7,425,84)) {
//                else if(mouseX >= 165 && mouseX <= 590 && mouseY >= 7 && mouseY <= 91) {
                shovelSelect = false;
                //idx = index plant yang ditekan
                int idx = (mouseX - 165) / 90;
                if(mouseX > 230+(idx*90))
                    return;
                if(idx < plants.size())
                    plants.remove(idx);
            }
            else if(overRect(200,600,150,30) && plants.size() == 5) {
//                else if(mouseX >= 200 && mouseX <= 350 && mouseY >= 600 && mouseY <= 630 && plants.size() == 5) {
                pickplant = false;
                if(playgrass)
                    playGrass();
                else if(playpool)
                    playPool();
            }
            return;
        }
//            
        //Select plant
        if(overRect(165,7,425,84)) {
            shovelSelect = false;
            plantFoodSelect = false;
            //idx = index plant yang ditekan
            int idx = (mouseX - 165) / 90;
            if(mouseX > 230+(idx*90))
                return;
            if(idx < plants.size() && plants.get(idx).getTimer() == 0)
                select = plants.get(idx);
        }
        //Select shovel
        else if(overRect(shovel.getX(), shovel.getY(), shovel.getWidth(), shovel.getHeight()) && plants.size() == 5) {
//            else if(mouseX >= shovel.getX() && mouseX <= shovel.getX()+shovel.getWidth() && mouseY >= shovel.getY() && mouseY <= shovel.getY()+shovel.getHeight()){
            shovelSelect = true;
            plantFoodSelect = false;
            select = null;
        }
        //Select plant food
        else if(overRect(plantFood.getX(), plantFood.getY(), plantFood.getWidth(), plantFood.getHeight()) && food > 0) {
//        else if(mouseX >= shovel.getX() && mouseX <= shovel.getX()+shovel.getWidth() && mouseY >= shovel.getY() && mouseY <= shovel.getY()+shovel.getHeight()){
            plantFoodSelect = true;
            shovelSelect = false;
            select = null;
        }

        //Tanam plant
        else if(overRect(105, 100, 1115, 600)) {
            //x = kolom tile, y = baris tile
            int x = (mouseX - 105) / 124,  y = (mouseY - 100) / 103;
//            System.out.println(select);
            if (select != null && playerSun >= select.getPrice()) {

                boolean bisaDitanam = false;
                boolean tanamanTumpuk = false;

                if (!tiles[y][x].isWater() && !tiles[y][x].hasPlant()) {
                    if (!(select instanceof Lilypad) && !(select instanceof TangleKelp)) {
                        bisaDitanam = true;
                    }
                } else if (tiles[y][x].isWater) {
                    if (!tiles[y][x].hasPlant()) {
                        if (select instanceof Lilypad || select instanceof TangleKelp) {
                            bisaDitanam = true;
                        }

                    } else if (tiles[y][x].getPlant() instanceof Lilypad) {
                        if (!(select instanceof TangleKelp) && !(select instanceof PotatoMine)) {
                            bisaDitanam = true;
                            tanamanTumpuk = true;
                        }
                    }

                }

                if (bisaDitanam) {

                    playerSun -= select.getPrice();
                    select.setX(tiles[y][x].getTile().getX() + 5);
                    select.setY(tiles[y][x].getTile().getY() + 5);
                    if (select instanceof CherryBomb) {
                        tiles[y][x].addPlant(new CherryBomb(select));
                    } else if (select instanceof CoconutCannon) {
                        tiles[y][x].addPlant(new CoconutCannon(select));
                    } else if (select instanceof Lilypad) {
                        tiles[y][x].addPlant(new Lilypad(select));
                    } else if (select instanceof Peashooter) {
                        tiles[y][x].addPlant(new Peashooter(select));
                    } else if (select instanceof PotatoMine) {
                        tiles[y][x].addPlant(new PotatoMine(select));
                    } else if (select instanceof SnowPea) {
                        tiles[y][x].addPlant(new SnowPea(select));
                    } else if (select instanceof Sunflower) {
                        tiles[y][x].addPlant(new Sunflower(select));
                    } else if (select instanceof TangleKelp) {
                        tiles[y][x].addPlant(new TangleKelp(select));
                    } else if (select instanceof Torchwood) {
                        tiles[y][x].addPlant(new Torchwood(select));
                    } else if (select instanceof Wallnut) {
                        tiles[y][x].addPlant(new Wallnut(select));
                    }
                    
                    // tanaman di atas lilypad agak naik
                    if (tanamanTumpuk){
                        tiles[y][x].getPlant().setY(tiles[y][x].getPlant().getY() - 20);
                    }
                    select.setTimer(select.getRecharge());
                    select = null;

                }

            }
//            if(!tiles[y][x].hasPlant() && select != null && playerSun >= select.getPrice())
//            {
////                System.out.println("tanam");
//                playerSun -= select.getPrice();
//                select.setX(tiles[y][x].getTile().getX() + 5);
//                select.setY(tiles[y][x].getTile().getY() + 5);
//                if(select instanceof CherryBomb)
//                    tiles[y][x].setPlant(new CherryBomb(select));
//                else if(select instanceof CoconutCannon)
//                    tiles[y][x].setPlant(new CoconutCannon(select));
//                else if(select instanceof Lilypad)
//                    tiles[y][x].setPlant(new Lilypad(select));
//                else if(select instanceof Peashooter)
//                    tiles[y][x].setPlant(new Peashooter(select));
//                else if(select instanceof PotatoMine)
//                    tiles[y][x].setPlant(new PotatoMine(select));
//                else if(select instanceof SnowPea)
//                    tiles[y][x].setPlant(new SnowPea(select));
//                else if(select instanceof Sunflower)
//                    tiles[y][x].setPlant(new Sunflower(select));
//                else if(select instanceof TangleKelp)
//                    tiles[y][x].setPlant(new TangleKelp(select));
//                else if(select instanceof Torchwood)
//                    tiles[y][x].setPlant(new Torchwood(select));
//                else if(select instanceof Wallnut)
//                    tiles[y][x].setPlant(new Wallnut(select));
//                select.setTimer(select.getRecharge());
//                select = null;
//            }
            //Attack Coconut Cannon
            else if(tiles[y][x].hasPlant() && tiles[y][x].getPlant() instanceof CoconutCannon) {
                CoconutCannon coconut = (CoconutCannon)tiles[y][x].getPlant();
                if(coconut.getCocoCtr() == 0 && coconut.getCtr() >= 0) {
                    coconut.setCtr(-1);
                }
            }
            else if(tiles[y][x].hasPlant() && shovelSelect) {
                tiles[y][x].setPlant(null);
                shovelSelect = false;
            }
            //cek combine
            else if(tiles[y][x].hasPlant() && plantFoodSelect) {    
                Plant temp = tiles[y][x].getPlant();
                if(y < 4 && temp instanceof Torchwood) {
                    if(tiles[y + 1][x].hasPlant() && tiles[y + 1][x].getPlant() instanceof Wallnut) {
                        temp = combinePlant.get(1);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new Wallfire(temp));
                        tiles[y + 1][x].setPlant(null);
                        food--;
                    }
                    else if(y > 0 && tiles[y - 1][x].hasPlant() && tiles[y - 1][x].getPlant() instanceof Peashooter && tiles[y + 1][x].hasPlant() && tiles[y + 1][x].getPlant() instanceof Peashooter) {
                        temp = combinePlant.get(0);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new FlamingThreepeater(temp));
                        tiles[y - 1][x].setPlant(null);
                        tiles[y + 1][x].setPlant(null);
                        plantFoodSelect = false;
                        food--;
                    }
                }
                else if(y < 4 && temp instanceof Wallnut) {
                    if(tiles[y + 1][x].hasPlant() && tiles[y + 1][x].getPlant() instanceof Torchwood) {
                        temp = combinePlant.get(1);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new Wallfire(temp));
                        tiles[y + 1][x].setPlant(null);
                        plantFoodSelect = false;
                        food--;
                    }
                    else if(tiles[y + 1][x].hasPlant() && tiles[y + 1][x].getPlant() instanceof Wallnut && !tiles[y][x].isWater() && !tiles[y + 1][x].isWater()) {
                        temp = combinePlant.get(3);
                        temp.setX(tiles[y][x].getTile().getX() + 5);
                        temp.setY(tiles[y][x].getTile().getY() + 5);
                        tiles[y][x].setPlant(new GreatWall(temp));
                        tiles[y + 1][x].setPlant(tiles[y][x].getPlant());
                        plantFoodSelect = false;
                        food--;
                    }
                }
                else if(y < 4 && tiles[y + 1][x].hasPlant() && temp instanceof Sunflower && tiles[y + 1][x].getPlant() instanceof Sunflower) {
                    temp = combinePlant.get(2);
                    temp.setX(tiles[y][x].getTile().getX() + 5);
                    temp.setY(tiles[y][x].getTile().getY() + 5);
                    tiles[y][x].setPlant(new TwinFlowers(temp));
                    tiles[y + 1][x].setPlant(null);
                    plantFoodSelect = false;
                    food--;
                }
            }
        }
    }
}