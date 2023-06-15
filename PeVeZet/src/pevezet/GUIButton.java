/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pevezet;

import java.awt.Color;

/**
 *
 * @author LENOVO
 */
public class GUIButton {
    private int x,y;
    private int width,height;
    private Color bgcolor;

    public GUIButton(int x, int y, int width, int height, Color bgcolor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bgcolor = bgcolor;
    }
    
    public GUIButton(GUIButton btn) {
        this.x = btn.x;
        this.y = btn.y;
        this.width = btn.width;
        this.height = btn.height;
        this.bgcolor = btn.bgcolor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(Color bgcolor) {
        this.bgcolor = bgcolor;
    }
    
    
    
}
