package com.mygdx.game.Components;

public class Box2D {

    /** The position of the box collider*/
    float x, y;

    /** The dimensions of the box collider*/
    float width, height;

    /**
     *
     */
    public Box2D(float x, float y, float width, float height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     *
     *
     */
    public boolean isColliding(Box2D box2D){

        //Get parameters values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (x <= x2 + width2 && x >= x2) ||
                           (x + width <= x2 + width2 && x + width >= x2);
        boolean yOverlap = (y <= y2 + height2 && y >= y2) ||
                           (y + height <= y2 + height2 && y + height >= y2);

        return xOverlap && yOverlap;
    }

    /**
     *
     */
    public boolean leftCollision(Box2D box2D){

        //Get parameters values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (x <= x2 + width2 && x >= x2);
        boolean yOverlap = (y <= y2 + height2 && y >= y2) ||
                (y + height <= y2 + height2 && y + height >= y2);

        return xOverlap && yOverlap;
    }


    /**
     *
     */
    public boolean rightCollision(Box2D box2D){

        //Get parameter values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (x + width <= x2 + width2 && x + width >= x2);
        boolean yOverlap = (y <= y2 + height2 && y >= y2) ||
                           (y + height <= y2 + height2 && y + height >= y2);

        return xOverlap && yOverlap;
    }


    /**
     *
     */
    public boolean bottomCollision(Box2D box2D){

        //Get parameter values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();


        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (x <= x2 + width2 && x >= x2) ||
                           (x + width <= x2 + width2 && x + width >= x2);
        boolean yOverlap = (y + height <= y2 + height2 && y + height >= y2);


        return xOverlap && yOverlap;
    }

    /**
     *
     *  */
    public boolean topCollision(Box2D box2D){

        //Get parameter values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();


        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (x <= x2 + width2 && x >= x2) ||
                (x + width <= x2 + width2 && x + width >= x2);
        boolean yOverlap = (y <= y2 + height2 && y >= y2);

        return xOverlap && yOverlap;

    }



    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

}
