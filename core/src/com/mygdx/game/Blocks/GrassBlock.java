package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.ID;

public class GrassBlock extends Block {

    /** */
    public static ID id = new ID();

    /** */
    public static Texture texture = new Texture("GrassBlock.jpg");

    /** */
    public GrassBlock(Vector2 position){
        super(position, new Sprite(texture), id);

    }

    public String toString(){
        return "Grass Block:";
    }

}
