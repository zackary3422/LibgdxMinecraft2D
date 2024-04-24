package com.mygdx.game.GameEngine;

public class Box {


    private float x;
    private float y;
    private float width;
    private float height;

    public Box(float x, float y, float width, float height){
        setPosition(x, y);
        setDimension(width, height);
    }

    public float getX(){return x;}

    public float getY(){return y;}

    public float getWidth(){return width;}

    public float getHeight(){return height;}

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setDimension(float width, float height){
        this.width = width;
        this.height = height;
    }
}
