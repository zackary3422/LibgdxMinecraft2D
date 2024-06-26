package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.*;

import java.util.ArrayList;


/**
 * The {@code Block} abstract class represents a block in Minecraft.
 * Its main use is to be used as a way to define more specific blocks like
 * stone or grass blocks.
 */
public abstract class Block extends GameObject {


    /** This is the length of a block on all sides. All block assets have same lengths so this just uses the dirt block to get the length*/
    public static final float LENGTH = new Sprite(new Texture("DirtBlock.jpg")).getWidth();

    //MAYBE ENFORCE BLOCK'S POSITION BY FORCING POSITION TO JUST BE TWO INTS OF X AND Y

    /** The general ID for a block*/
    public static ID id = new ID();




    /**
     * Constructs a new block and initializes the variables.
     * @param position the x & y position
     * @param sprite the sprite or graphical representation for the block
     * @param id the block ID
     */
    public Block(Vector2 position, Sprite sprite, ID id){

        super(position, sprite, id);

        addID(Block.id);
        makeCollidable();
    }

    /***/
    public static boolean isTopLayer(ArrayList<ID> idList){

        for(ID idElement : idList) {
            if (id == GrassBlock.id)
                return true;
        }

        return false;
    }

    /**
     * Creates and returns a new block with provided position based on id
     * @param id the block to create
     * @param position the position of the new block
     * @return a new block based on id and position
     *  */
    public static Block getBlock(ID id, Vector2 position){

        if(id.equals(GrassBlock.id))
            return new GrassBlock(position);
        if(id.equals(DirtBlock.id))
            return new DirtBlock(position);
        if(id.equals(StoneBlock.id))
            return new StoneBlock(position);

        return new GrassBlock(position);

    }




}
