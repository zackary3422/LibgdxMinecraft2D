package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockID;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Components.Dimension;


/**
 * The {@code Block} abstract class represents a block in Minecraft.
 * Its main use is to be used as a way to define more specific blocks like
 * stone or grass blocks.
 */
public abstract class Block {

    /** The x and y coordinates for the block*/
    private Vector2 position;

    /** The width and height of the block*/
    private Dimension dimension;

    /** */
    public int ID;

    /** */
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
     *
     * @param batch used to draw sprite onto screen
     */
    public void draw(SpriteBatch batch){

        if(sprite == null)
            return;

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /**
     * Determines if a block is within the view range of the player
     *
     * @return if block is visible to player
     */
    public boolean isVisible(Player player){

        return Math.abs(player.getCenterX() - getCenterX()) < (com.mygdx.game.World.Window.width / 2.0) + 50;
    }


    /**
     * Sets a new position for the block and sets a new position for sprite
     *
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
     * Overrides toString method to return the block type.
     *
     * @return the block type
     */
    @Override
    public String toString(){
        return Integer.toString(ID);
    }

    /* ----- ACCESSORS ----- */

    /**
     * Returns the x-coordinate of the player
     *
     * @return the x-coordinate
     */
    public float getX(){
        return position.x;
    }

    /**
     * Returns the y-coordinate of the player
     *
     * @return the y-coordinate
     */
    public float getY(){
        return position.y;
    }


    public Vector2 getVector(){
       return position;
    }

    public float getCenterX(){return position.x + (dimension.width / 2);}

    public float getCenterY(){return position.y + (dimension.height / 2);};

    public float getWidth(){
        return dimension.width;
    }

    public float getHeight(){
        return dimension.height;
    }

    public int getID(){
        return ID;
    }

    public boolean isCollidable(){
        return collidable;
    }
}

