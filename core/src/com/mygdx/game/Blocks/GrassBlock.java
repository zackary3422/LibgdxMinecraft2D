package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GrassBlock extends Block{


    public GrassBlock(float x, float y){
        super(x, y, new Sprite(new Texture("GrassBlock.jpg")), BlockType.GRASSBLOCK);

    }

}
