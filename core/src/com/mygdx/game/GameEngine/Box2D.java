package com.mygdx.game.GameEngine;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World.World;

public class Box2D {



    /* ----- COLLISION DETECTION ----- */


    /** *///IMPLEMENT MORE WAY FOR CHECKING FOR COLLISIONS
    public static boolean isColliding(GameObject object1, GameObject object2){

        //Get parameters values
        float x1 = object1.getPosition().x;
        float x2 = x1 + object1.getDimension().width;
        float x3 = object2.getPosition().x;
        float x4 = x3 + object2.getDimension().width;

        boolean xOverlap = getOverlap(x1, x2, x3, x4) != 0;

        //Get parameters values
        float y1 = object1.getPosition().y;
        float y2 = y1 + object1.getDimension().height;
        float y3 = object2.getPosition().y;
        float y4 = y3 + object2.getDimension().height;

        boolean yOverlap = getOverlap(y1, y2, y3, y4) != 0;


        return xOverlap && yOverlap ;
    }

    /** */
    public static boolean rightCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2) && (rightX1 < rightX2 && rightX1 > leftX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2);

        return xOverlap && yOverlap;
    }

    public static boolean leftCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2) && (leftX1 > leftX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2);

        return xOverlap && yOverlap;
    }

    /** */
    public static boolean bottomCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2) && bottomY1 >= bottomY2;

        return xOverlap && yOverlap;
    }

    /** */
    public static boolean topCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get Overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2) && (topY1 < topY2 && topY1 > bottomY2);

        return xOverlap && yOverlap;
    }


    /* ----- Collision Resolution ------ */


    /**
     *
     */
    public Vector2 getCollisionFreePos(GameObject collidableObject, GameObject collidingObject){

        //If objects aren't colliding then return same position
        if(!isColliding(collidableObject, collidingObject))
            return collidableObject.getPosition();

        //Direction to push this box2D out of the other one
        Movement.Direction direction = axisPushDirection(collidableObject, collidingObject);

        float pushDistance = axisPushDistance(collidableObject, collidingObject);

        float x = collidableObject.getPosition().x;
        float y = collidableObject.getPosition().y;

        if(direction == Movement.Direction.LEFT || direction == Movement.Direction.RIGHT)
            x += pushDistance;
        else
            y += pushDistance;

        return new Vector2(x, y);
    }

    /**
     * Calculate the shortest distance to move box's out of each other using the SAT method to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     * @param collidableObject
     * @param collidingObject
     * @return
     */
    public static float axisPushDistance(GameObject collidableObject, GameObject collidingObject) {

        //Calculate X Overlap
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float xOverlap = getOverlap(leftX1, rightX1, leftX2, rightX2);

        //Calculate Y Overlap
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;
        float yOverlap = getOverlap(bottomY1, topY1, bottomY2, topY2);


        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
            //Move Left
            if (leftX1 < leftX2)
                return -1 * xOverlap;

            //Move Right
            else
                return xOverlap;

        //If Y-Overlap is shorter move vertically out of block
        else if (xOverlap > yOverlap)
            //Move Down
            if (bottomY1 < bottomY2)
                return -1 * yOverlap;

            //Move Up
            else
                return yOverlap;

        return 0;
    }

    /**
     * Find the direction for the shortest distance ot move out of.
     * @param
     * @return the direction of the minimum distance
     */
    public Movement.Direction axisPushDirection(GameObject collidableObject, GameObject collidingObject) {

        //Calculate X Overlap
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float xOverlap = getOverlap(leftX1, rightX1, leftX2, rightX2);

        //Calculate Y Overlap
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;
        float yOverlap = getOverlap(bottomY1, topY1, bottomY2, topY2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap < yOverlap)
            //Move Left
            if (leftX1 < leftX2)
                return Movement.Direction.LEFT;

            //Move Right
            else
                return Movement.Direction.RIGHT;

        //If Y-Overlap is shorter move vertically out of block
        else if (xOverlap > yOverlap)

            //Move Down
            if (bottomY1 < bottomY2)
                return Movement.Direction.DOWN;
            //Move Up
            else
                return Movement.Direction.UP;


        return null;
    }

    /**
     * Takes in two lines and returns the amount of overlap or ZERO if there is no overlap
     * The min's represent the left point of the line and the max's represent the right point
     * */
    public static float getOverlap(float min1, float max1, float min2, float max2){
        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
    }

    /**
     * Takes in two line coordinates and returns if they are overlapping.
     * The min's represent the left point of the lines and the max's represent the right point of the lines.
     */
    public static boolean isOverlapping(float min1, float max1, float min2, float max2){
        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2)) != 0;
    }

}
