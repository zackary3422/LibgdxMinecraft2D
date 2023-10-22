package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The {@code StoneBlock} class represents a stone block using the Block parent class.
 *
 */

public class StoneBlock extends Block{

    /**
     * Constructs a new stone block
     *
     * @param x  the x-coordinate of the stone block
     * @param y  the y-coordinate of the stone block
     */
    public StoneBlock(float x, float y){
        super(x, y, new Sprite(new Texture("StoneBlock.png")), BlockType.STONEBLOCK);

    }


}
