package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The {@code Block} abstract class represents a block in Minecraft.
 * Its main use is to be used as a way to define more specific blocks like
 * stone or grass blocks.
 */
public abstract class Block {

    /** The x and y coordinates for the block*/
    private float x, y;

    /** This is the general length for both sides of a block*/
    public static final float BLOCK_LENGTH = 45;

    /** The width and height of the block*/
    private float width, height;

    /**
     *
     *  */
    public enum BlockType {GRASSBLOCK, DIRTBLOCK, STONEBLOCK, EMPTYBLOCK}
    private BlockType blockType;

    /**The sprite used to represent the stone block image*/
    Sprite sprite;

    /**
     * Constructs a new block and initializes the variables.
     *
     * @param x x-coordinate of the block
     * @param y y-coordinate of the block
     * @param sprite the sprite or graphical representation for the block
     */
    public Block(float x, float y, Sprite sprite, BlockType blockType){

        //Initialize sprite
        this.sprite = sprite;
        sprite.setPosition(x, y);
        this.blockType = blockType;

        //Set coordinates and dimensions
        width = sprite.getWidth();
        height = sprite.getHeight();
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new block without a sprite and can't be drawn onto screen.
     *
     * @param x x-coordinate for block
     * @param y y-coordinate for block
     */
    public Block(float x, float y){

        sprite = null;
        blockType = BlockType.EMPTYBLOCK;

        //Set coordinates and dimensions
        width = Block.BLOCK_LENGTH;
        height = Block.BLOCK_LENGTH;
        this.x = x;
        this.y = y;
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
     * Sets a new position for the block and sets a new position for sprite
     *
     * @param x new x-position for block
     * @param y new y-position for block
     */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;

        if(sprite != null)
            sprite.setPosition(x, y);
    }

    /**
     * Overrides toString method to return the block type.
     *
     * @return the block type
     */
    @Override
    public String toString(){
        return blockType.toString();
    }

    /**
     * Returns the x-coordinate of the player
     *
     * @return the x-coordinate
     */
    public float getX(){
        return x;
    }

    /** */
    public float getY(){
        return y;
    }
}
