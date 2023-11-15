package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Block;


public class DirtBlock extends Block implements BlockAttributes{

    /**
     * Constructs a dirt block with coordinates.
     *
     * @param position the position of the block
     */
    public DirtBlock(Vector2 position){
        super(position, new Sprite(BlockTextures.getDirtBlock()), BlockID.DIRTBLOCK, true);
    }

}
