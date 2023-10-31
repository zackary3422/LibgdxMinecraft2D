package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;

public class Box2D {

    /** The position of the box collider*/
    Vector2 position;

    /** The dimensions of the box collider*/
    Dimension dimension;

    /**
     *
     */
    public Box2D(Vector2 position, Dimension dimension){

        this.position = position;
        this.dimension = dimension;
    }


    public void setPosition(Vector2 newPosition){
        position = newPosition;
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
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2) ||
                           (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2) ||
                           (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);

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
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2) ||
                (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);

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
        boolean xOverlap = (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2) ||
                           (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);

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
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2) ||
                           (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);


        return xOverlap && yOverlap;
    }

    /**
     *
     *
     */
    public boolean topCollision(Box2D box2D){

        //Get parameter values
        float x2 = box2D.getX();
        float y2 = box2D.getY();
        float width2 = box2D.getWidth();
        float height2 = box2D.getHeight();


        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2) ||
                (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2);

        return xOverlap && yOverlap;

    }



    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return dimension.width;
    }

    public float getHeight(){
        return dimension.height;
    }

}
