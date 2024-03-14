package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.ID;


public class DirtBlock extends Block {

    /** */
    public static final Texture DirtBlockTexture = new Texture("DirtBlock.jpg");

    /** */
    public static final ID dirtBlockID = new ID();

    /**
     * Constructs a dirt block with coordinates.
     *
     * @param position the position of the block
     */
    public DirtBlock(Vector2 position){
        super(position, new Sprite(DirtBlockTexture), dirtBlockID);
    }

    public String toString(){
        return "Dirt Block";
    }
}
