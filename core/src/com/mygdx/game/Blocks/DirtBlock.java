package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class DirtBlock extends Block{

    /**
     * Constructs a dirt block with coordinates.
     *
     * @param x x-coordinate for block
     * @param y y-coordinate for block
     */
    public DirtBlock(float x, float y){
        super(x, y, new Sprite(new Texture("DirtBlock.jpg")), BlockType.DIRTBLOCK);
    }

}
