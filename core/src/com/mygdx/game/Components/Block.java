package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



/**
 * The {@code Block} abstract class represents a block in Minecraft.
 * Its main use is to be used as a way to define more specific blocks like
 * stone or grass blocks.
 */
public abstract class Block extends GameObject{


    /** This is the general length for both sides of a block*/
    public static final float BLOCK_LENGTH = 45;


    /**
     * Constructs a new block and initializes the variables.
     *
     * @param position the x & y position
     * @param sprite the sprite or graphical representation for the block
     * @param ID the block ID
     */
    public Block(Vector2 position, Sprite sprite, int ID){


        super(position, sprite, ID);

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

    public Box2D getBox2D(){
        return box2D;
    }
}

