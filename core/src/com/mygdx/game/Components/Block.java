package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



/**
 * The {@code Block} abstract class represents a block in Minecraft.
 * Its main use is to be used as a way to define more specific blocks like
 * stone or grass blocks.
 */
public abstract class Block {

    /** The x and y coordinates for the block*/
    private Vector2 position;

    /** The width and height of the block*/
    private Dimension<Float> dimension;

    /** The ID for the block*/
    public int ID;

    /** The boolean for whether this block is collidable or not*/
    boolean collidable;

    /** The box collider to detect collisions*/
    public Box2D box2D;

    /** The sprite used to represent the stone block image*/
    Sprite sprite;

    /** This is the general length for both sides of a block*/
    public static final float BLOCK_LENGTH = 45;


    /**
     * Constructs a new block and initializes the variables.
     *
     * @param position the x & y position
     * @param sprite the sprite or graphical representation for the block
     * @param ID the block ID
     * @param collidable determine if variable is collidable with world
     */
    public Block(Vector2 position, Sprite sprite, int ID, boolean collidable){


        //Initialize sprite
        this.sprite = sprite;

        setPosition(position);

        if(sprite == null)  //No sprite
            dimension = new Dimension(BLOCK_LENGTH, BLOCK_LENGTH);
        else
            dimension = new Dimension(sprite.getWidth(), sprite.getHeight());


        //Set block ID
        this.ID = ID;

        this.collidable = collidable;
        box2D = new Box2D(position, dimension);
    }


    /**
     * Draws a sprite onto screen if sprite isn't null
     * @param batch used to draw sprite onto screen
     */
    public void draw(SpriteBatch batch){

        //Error handling
        if(sprite == null)
            return;

        batch.begin();
        sprite.draw(batch);
        batch.end();
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



    /* ----- ACCESSORS ----- */


     /** @return the x-coordinate of the block*/
    public float getX(){
        return position.x;
    }

    /** @return the y-coordinate of the block*/
    public float getY(){
        return position.y;
    }

    /** @return the vector position of block*/
    public Vector2 getVector(){
       return position;
    }

    /** @return the center x-position of block*/
    public float getCenterX(){return position.x + (dimension.width / 2);}

    /** @return the center y-position of block*/
    public float getCenterY(){return position.y + (dimension.height / 2);};

    /** @return the block width*/
    public float getWidth(){
        return dimension.width;
    }

    /** @return the block height*/
    public float getHeight(){
        return dimension.height;
    }

    /** @return the ID for the block*/
    public int getID(){
        return ID;
    }

    /** @return the boolean for if this block is collidable*/
    public boolean isCollidable(){
        return collidable;
    }
}

