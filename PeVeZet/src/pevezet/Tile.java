/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet;

import java.awt.Color;
import java.util.ArrayList;
import pevezet.plants.*;

public class Tile {
    private GUIButton tile;
    private ArrayList<Plant> plant;
    protected boolean isWater, hasLily,plantable; 
    
    public Tile(int y, int x, int height, int width, int startx, int starty) {
        tile = new GUIButton(startx+(width+1)*x,starty+(height+1)*y,width,height, new Color(0,255,0));
        this.plantable = true;
        this.plant = new ArrayList();
        this.isWater = false;
    }
    
    public GUIButton getTile() {
        return this.tile;
    }
    
    public boolean hasPlant(){
        return plant.size() > 0 ; 
    }
    
    // return plant terakhir
    // kalau ada lily return taneman atase
    // kalau gada ya ngembaliin taneman itu
    public Plant getPlant() {
        return this.plant.get(this.plant.size()-1); 
    }
    
    // return arraylist plant
    public ArrayList<Plant> getAllPlants(){
        return this.plant;
    }
    
    public void setPlant(Plant plant) {
        if(plant == null)
            this.plant.remove(this.plant.size()-1);
        else
            this.plant.set(this.plant.size()-1, plant);
    }
    
    public void addPlant(Plant plant) {
        this.plant.add(plant);
    }
    
    public void deletePlant (){
        this.plant.remove(this.plant.size()-1);
    }

    public boolean isWater() {
        return isWater;
    }
    
    public void setIsWater(boolean isWater) {
        this.isWater = isWater;
    }

    public boolean isPlantable() {
        return plantable;
    }

    public void setPlantable(boolean plantable) {
        this.plantable = plantable;
    }
    
}
