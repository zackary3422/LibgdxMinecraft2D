package com.mygdx.game.Blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Block;

public class EmptyBlock extends Block implements BlockAttributes{




    /**
     * Constructs an empty block without a sprite and just a position.
     *
     * @param position the position of the block
     */
    public EmptyBlock(Vector2 position){
        super(position, null, BlockID.EMPTYBLOCK, false);

    }
}
