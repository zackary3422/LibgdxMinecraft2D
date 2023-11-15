package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;

public class Box2D {

    /** The position of the box collider*/
    Vector2 position;

    /** The dimensions of the box collider*/
    Dimension<Float> dimension;

    /**
     * Constructs a new box2D from given position and dimension.
     * @param position the position of the box2D
     * @param dimension the dimension of the box2D
     */
    public Box2D(Vector2 position, Dimension dimension){

        this.position = position;
        this.dimension = dimension;
    }

    /**
     * Sets a new position for this Box2D.
     * @param newPosition the new position for this box2D
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;
    }

    /**
     * Checks if this box2D is colliding with given box2D
     * @param box2D the box2D to check if this box2D is colliding with
     * @return the boolean if this block is colliding with given box2D
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


    /* ----- ACCESSORS ----- */

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
