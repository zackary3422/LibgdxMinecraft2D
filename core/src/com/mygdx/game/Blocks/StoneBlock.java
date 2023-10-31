package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Block;

/**
 * The {@code StoneBlock} class represents a stone block using the Block parent class.
 *
 */

public class StoneBlock extends Block implements BlockAttributes{

    /**
     * Constructs a new stone block
     *
     * @param position the position of the block
     */
    public StoneBlock(Vector2 position){
        super(position, new Sprite(new Texture("StoneBlock.png")), BlockID.STONEBLOCK, true);

    }


}
