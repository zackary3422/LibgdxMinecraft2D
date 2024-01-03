package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.GameID;

public class GameObject {

    /** The x and y coordinates*/
    protected Vector2 position;

    /** The width and height*/
    protected  Dimension<Float> dimension;

    /** The ID for the game object*/
    protected GameID.ID ID;

    /** The boolean for whether this block is collidable or not*/
    protected boolean collidable;

    /** Determines if other players or entities can collide with this object*/
    protected boolean isVisible;

    /** The box collider to detect collisions and collision resolution*/
    protected Box2D box2D;

    /** The sprite used to represent the stone block image*/
    protected Sprite sprite;

    public GameObject(Vector2 position, Sprite sprite, GameID.ID ID) {

        this.ID = ID;

    }


    public Sprite getSprite(){
        return sprite;
    }

    /**
     * Sets a new position for the block and sets a new position for sprite
     * @param newPosition new Vector2 position
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;

        if(box2D != null)
            box2D.setPosition(position);

        if(sprite != null)
            sprite.setPosition(position.x, position.y);
    }

    /**
     * Determines if a block is within the view range of the player
     * @return if block is visible to player
     */
    public boolean isVisible(Vector2 playerPosition, Dimension<Float> viewPort){

        //Get parameters values with a offset value
        float width2 = viewPort.width + 20;
        float height2 = viewPort.height + 50;
        float x2 = playerPosition.x - width2 / 2;
        float y2 = playerPosition.y - height2 / 2;


        //Find which parts of block is the player seeing
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2) ||
                (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2) ||
                (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);

        return xOverlap && yOverlap;
    }


}
