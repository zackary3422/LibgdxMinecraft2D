package com.mygdx.game.GameEngine;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World.World;

public class Box2D {

    /** The position of the box collider*/
    public Vector2 position;

    /** The dimensions of the box collider*/
    public Dimension<Float> dimension;

    /**
     * Constructs a new box2D from given position and dimension.
     * @param position the position of the box2D
     * @param dimension the dimension of the box2D
     */
    public Box2D(Vector2 position, Dimension<Float> dimension){

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
        float boxX = box2D.position.x;
        float boxY = box2D.position.y;
        float boxWidth = box2D.dimension.width;
        float boxHeight = box2D.dimension.height;

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

    /** *///IMPLEMENT MORE WAY FOR CHECKING FOR COLLISIONS
    public boolean isColliding(){
        return false;
    }

    /**
     * Checks if given box2D is colliding with the left side of this box2D
     * @param box2D the box2D to check if it's colliding with the left side of this box2D
     * @return boolean determining if given box2D is colliding on left side of this box2D
     */
    public boolean leftCollision(Box2D box2D){

        //Get parameters values
        float boxX = box2D.position.x;
        float boxY = box2D.position.y;
        float boxWidth = box2D.dimension.width;
        float boxHeight = box2D.dimension.height;

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
        float boxX = box2D.position.x;
        float boxY = box2D.position.y;
        float boxWidth = box2D.dimension.width;
        float boxHeight = box2D.dimension.height;

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
        float x = box2D.position.x;
        float y = box2D.position.y;
        float width = box2D.dimension.width;
        float height = box2D.dimension.height;

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
        float boxX = box2D.position.x;
        float boxY = box2D.position.y;
        float boxWidth = box2D.dimension.width;
        float boxHeight = box2D.dimension.height;


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
     *
     */
    public Vector2 getCollisionFreePos(Box2D box2D){

        //Direction to push this box2D out of the other one
        Movement.Direction direction = axisPushDirection(box2D);

        if(!isColliding(box2D))
            return null;

        //Horizontal push
        if(direction == Movement.Direction.LEFT || direction == Movement.Direction.RIGHT){
            return new Vector2(position.x + axisPushDistance(box2D), position.y);
        }
        //Vertical push
        else{
            return new Vector2(position.x, position.y + axisPushDistance(box2D));
        }

    }

    /**
     * Calculate the shortest distance to move box's out of each other using the SAT method to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     * @param box2D the colliding box to calculate minimum distance to move out of
     * @return the minimum distance required to move the box's out of each other
     */
    public float axisPushDistance(Box2D box2D) {

        //Calculate X Overlap
        float min1 = position.x;
        float max1 = position.x + dimension.width;
        float min2 = box2D.position.x;
        float max2 = box2D.position.x + box2D.dimension.width;
        float xOverlap = getOverlap(min1, max1, min2, max2);

        //Calculate Y Overlap
        min1 = position.y;
        max1 = position.y + dimension.height;
        min2 = box2D.position.y;
        max2 = box2D.position.y + box2D.dimension.height;
        float yOverlap = getOverlap(min1, max1, min2, max2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
        {
            //Move Left
            if (position.x < box2D.position.x)
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
            if (position.y < box2D.position.y)
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
     * @param box2D the colliding box to calculate distance to move out of
     * @return the direction of the minimum distance
     */
    public Movement.Direction axisPushDirection(Box2D box2D) {

        //Calculate X Overlap
        float min1 = position.x;
        float max1 = position.x + dimension.width;
        float min2 = box2D.position.x;
        float max2 = box2D.position.x + box2D.dimension.width;
        float xOverlap = getOverlap(min1, max1, min2, max2);

        //Calculate Y Overlap
        min1 = position.y;
        max1 = position.y + dimension.height;
        min2 = box2D.position.y;
        max2 = box2D.position.y + box2D.dimension.height;
        float yOverlap = getOverlap(min1, max1, min2, max2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
        {
            //Move Left
            if (position.x < box2D.position.x)
            {
                return Movement.Direction.LEFT;
            }
            //Move Right
            else
            {
                return Movement.Direction.RIGHT;
            }
        }

        //If Y-Overlap is shorter move vertically out of block
        else if (xOverlap > yOverlap)
        {
            //Move Down
            if (position.y < box2D.position.y)
            {
                return Movement.Direction.DOWN;
            }
            //Move Up
            else
            {
                return Movement.Direction.UP;
            }
        }

        return null;
    }

    /** */
    public static float getOverlap(float min1, float max1, float min2, float max2){

        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
    }


}
