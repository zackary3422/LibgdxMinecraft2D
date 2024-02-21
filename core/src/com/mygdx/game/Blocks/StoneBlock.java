package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.GameID;
import com.mygdx.game.GameEngine.ID;

/**
 * The {@code StoneBlock} class represents a stone block using the Block parent class.
 *
 */

public class StoneBlock extends Block {

    /** */
    public static ID id = new ID();

    /** */
    public static Texture texture = new Texture("StoneBlock.png");

    /**
     * Constructs a new stone block
     *
     * @param position the position of the block
     */
    public StoneBlock(Vector2 position){
        super(position, new Sprite(texture), id);
    }

    public String toString(){
        return "Stone Block";
    }
}
