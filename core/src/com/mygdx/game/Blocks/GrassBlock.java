package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Block;

public class GrassBlock extends Block implements BlockAttributes{


    public GrassBlock(Vector2 position){
        super(position, new Sprite(BlockTextures.getGrassBlock()), BlockID.GRASSBLOCK, true);

    }

}
