package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World.World;

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


    /* ----- COLLISION DETECTION ----- */

    /**
     * Checks if given box2D is colliding with this box2D
     * @param box2D the box2D to check if this box2D is colliding with
     * @return boolean determining if given box2D is colliding with box2D
     */
    public boolean isColliding(Box2D box2D){

        //Get parameters values
        float boxX = box2D.getX();
        float boxY = box2D.getY();
        float boxWidth = box2D.getWidth();
        float boxHeight = box2D.getHeight();

        //Find which parts of this box collider are overlapping (x & y)
        boolean xOverlap = (position.x <= boxX + boxWidth && position.x >= boxX) ||
                           (position.x + dimension.width <= boxX + boxWidth && position.x + dimension.width >= boxX);
        boolean yOverlap = (position.y <= boxY + boxHeight && position.y >= boxY) ||
                           (position.y + dimension.height <= boxY + boxHeight && position.y + dimension.height >= boxY);

        //Find which parts of the other box collider are overlapping (x & y)
        boolean xOverlap2 = (boxX <= position.x + dimension.width && boxX >= position.x) ||
                            (boxX + boxWidth <= position.x + dimension.width && boxX + boxWidth >= position.x);
        boolean yOverlap2 = (position.y >= boxY + boxHeight && position.y <= boxY) ||
                (position.y + dimension.height >= boxY + boxHeight && position.y + dimension.height <= boxY);


        return (xOverlap || xOverlap2) && (yOverlap || yOverlap2);
    }

    /**
     * Checks if given box2D is colliding with the left side of this box2D
     * @param box2D the box2D to check if it's colliding with the left side of this box2D
     * @return boolean determining if given box2D is colliding on left side of this box2D
     */
    public boolean leftCollision(Box2D box2D){

        //Get parameters values
        float boxX = box2D.getX();
        float boxY = box2D.getY();
        float boxWidth = box2D.getWidth();
        float boxHeight = box2D.getHeight();

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (position.x <= boxX + boxWidth && position.x >= boxX);
        boolean yOverlap = (position.y <= boxY + boxHeight && position.y >= boxY) ||
                           (position.y + dimension.height <= boxY + boxHeight && position.y + dimension.height >= boxY);

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap2 = (boxX <= position.x + dimension.width && boxX >= position.x);
        boolean yOverlap2 = (boxY <= position.y + dimension.height && boxY >= position.y) ||
                            (boxY + boxHeight <= position.y + dimension.height && boxY + boxHeight >= boxY);

        return (xOverlap || xOverlap2) && (yOverlap || yOverlap2);
    }


    /**
     * Checks if given box2D is colliding with the right side of this box2D
     * @param box2D the box2D to check if it's colliding with the right side of this box2D
     * @return boolean determining if given box2D is colliding on right side of this box2D
     */
    public boolean rightCollision(Box2D box2D){

        //Get parameters values
        float boxX = box2D.getX();
        float boxY = box2D.getY();
        float boxWidth = box2D.getWidth();
        float boxHeight = box2D.getHeight();

        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (position.x + dimension.width <= boxX + boxWidth && position.x + dimension.width >= boxX);
        boolean yOverlap = (position.y <= boxY + boxHeight && position.y >= boxY) ||
                           (position.y + dimension.height <= boxY + boxHeight && position.y + dimension.height >= boxY);

        //Find which parts of the other box collider are overlapping (x & y)
        boolean xOverlap2 = (boxX + boxWidth <= position.x + dimension.width && boxX + boxWidth >= position.x);
        boolean yOverlap2 = (boxY <= position.y + dimension.height && boxY >= position.y) ||
                            (boxY + boxHeight <= position.y + dimension.height && boxY + boxHeight >= position.y);

        return (xOverlap || xOverlap2) && (yOverlap || yOverlap2);
    }


    /**
     * Checks if given box2D is colliding with the bottom side of this box2D
     * @param box2D the box2D to check if it's colliding with the bottom side of this box2D
     * @return boolean determining if given box2D is colliding on bottom side of this box2D
     */
    public boolean bottomCollision(Box2D box2D){

        //Get parameter values
        float x = box2D.getX();
        float y = box2D.getY();
        float width = box2D.getWidth();
        float height = box2D.getHeight();


        //Find which parts of this box collider are overlapping (x & y)
        boolean xOverlap = (position.x <= x + width && position.x >= x) ||
                           (position.x + dimension.width <= x + width && position.x + dimension.width >= x);
        boolean yOverlap = (position.y + dimension.height <= y + height && position.y + dimension.height >= y);

        //Find which parts of the other box collider are overlapping (x & y)
        boolean xOverlap2 = (x <= position.x + dimension.width && x >= position.x) ||
                            (x + width <= position.x + dimension.width && x + width >= position.x);
        boolean yOverlap2 = (y + height <= position.y + dimension.height && y + height >= position.y);

        return (xOverlap || xOverlap2) && (yOverlap || yOverlap2);
    }

    /**
     * Checks if given box2D is colliding with the top side of this box2D
     * @param box2D the box2D to check if it's colliding with the top side of this box2D
     * @return boolean determining if given box2D is colliding on top side of this box2D
     */
    public boolean topCollision(Box2D box2D){

        //Get parameters values
        float boxX = box2D.getX();
        float boxY = box2D.getY();
        float boxWidth = box2D.getWidth();
        float boxHeight = box2D.getHeight();


        //Find which parts of the box collider are overlapping (x & y)
        boolean xOverlap = (position.x <= boxX + boxWidth && position.x >= boxX) ||
                (position.x + dimension.width <= boxX + boxWidth && position.x + dimension.width >= boxX);
        boolean yOverlap = (position.y <= boxY + boxHeight && position.y >= boxY);

        //Find which parts of the other box collider are overlapping (x & y)
        boolean xOverlap2 = (boxX >= position.x + dimension.width && boxX >= position.x) ||
                (boxX + boxWidth <= position.x + dimension.width && boxX + boxWidth >= position.x);
        boolean yOverlap2 = (boxY <= position.y + dimension.height && boxY >= position.y);

        return (xOverlap || xOverlap2) && (yOverlap || yOverlap2);

    }


    /* ----- Collision Resolution ------ */

    /**
     * Calculate the shortest distance to move box's out of each other using the SAT to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     * @param box2D the colliding block to calculate minimum distance to move out of
     * @return the minimum distance required to move the box's out of each other
     */
    public float satPushDistance(Box2D box2D)
    {

        //Calculate X Overlap
        float min1 = position.x;
        float max1 = position.x + dimension.width;
        float min2 = box2D.getX();
        float max2 = box2D.getX() + box2D.getWidth();
        float xOverlap = getOverlap(min1, max1, min2, max2);

        //Calculate Y Overlap
        min1 = position.y;
        max1 = position.y + dimension.height;
        min2 = box2D.getY();
        max2 = box2D.getY() + box2D.getHeight();
        float yOverlap = getOverlap(min1, max1, min2, max2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
        {
            //Move Left
            if (position.x < box2D.getX())
            {
                return -1 * xOverlap;
            }
            //Move Right
            else
            {
                return xOverlap;
            }
        }

        //If Y-Overlap is shorter move vertically out of block
        else if (xOverlap > yOverlap)
        {
            //Move Down
            if (position.y < box2D.getY())
            {
                return -1 * yOverlap;
            }
            //Move Up
            else
            {
                return yOverlap;
            }
        }


        return -1;
    }

    /**
     * Find the direction for the shortest distance ot move out of.
     * @param box2D the colliding block to calculate distance to move out of
     * @return the direction of the minimum distance
     */
    public World.Direction satPushDirection(Box2D box2D)
    {
        //Calculate X Overlap
        float min1 = position.x;
        float max1 = position.x + dimension.width;
        float min2 = box2D.getX();
        float max2 = box2D.getX() + box2D.getWidth();
        float xOverlap = getOverlap(min1, max1, min2, max2);

        //Calculate Y Overlap
        min1 = position.y;
        max1 = position.y + dimension.height;
        min2 = box2D.getY();
        max2 = box2D.getY() + box2D.getHeight();
        float yOverlap = getOverlap(min1, max1, min2, max2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
        {
            //Move Left
            if (position.x < box2D.getX())
            {
                return World.Direction.LEFT;
            }
            //Move Right
            else
            {
                return World.Direction.RIGHT;
            }
        }

        //If Y-Overlap is shorter move vertically out of block
        else if (xOverlap > yOverlap)
        {
            //Move Down
            if (position.y < box2D.getY())
            {
                return World.Direction.DOWN;
            }
            //Move Up
            else
            {
                return World.Direction.UP;
            }
        }

        return null;
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

    public static float getOverlap(float min1, float max1, float min2, float max2){

        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
    }

    /* ----- MUTATORS ----- */

    /**
     * Sets a new position for this Box2D.
     * @param newPosition the new position for this box2D
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;
    }

}
