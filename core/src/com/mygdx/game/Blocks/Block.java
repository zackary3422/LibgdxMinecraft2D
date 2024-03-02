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
    public static final float BLOCK_LENGTH = new Sprite(new Texture("DirtBlock.jpg")).getWidth();

    //MAYBE ENFORCE BLOCKS POSITION BY FORCING POSITION TO JUST BE TWO INTS OF X AND Y

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




}
