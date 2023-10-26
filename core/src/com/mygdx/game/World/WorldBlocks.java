package com.mygdx.game.World;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.Components.Box2D;
import com.mygdx.game.Player.Player;

import java.util.ArrayList;

/**
 *  The {@code WorldBlocks} class represents all the blocks in the Minecraft world. This class will store
 *  all the blocks in a 2D arraylist and this class can be used to access the worlds blocks
 *  and to edit them.
 */

public class WorldBlocks {

    /** The list of all the blocks in the world*/
    static ArrayList<ArrayList<Block>> blocks;

    /** The height of the world in blocks*/
    public static final int WORLD_HEIGHT = 60;

    /** The initial width of the world in blocks*/
    final int INITWIDTH = 40;


    /**
     * Constructs a new World of Blocks and populates the arraylist
     *
     */
    public WorldBlocks(){
        blocks = WorldCreator.createWorld(INITWIDTH, WORLD_HEIGHT);
      //  WorldCreator.populate(blocks, INITWIDTH, WORLD_HEIGHT);
    }

    /**
     * Called every frame to update world blocks. Used to expand world as player explores.
     *
     * @param player used to pass onto methods like expandWorld
     */
    public void update(Player player){

        WorldCreator.expandWorld(blocks, player);

    }
    /**
     * Draws all the blocks in the {@code blocks} list
     *
     * @param batch the SpriteBatch to draw sprites onto screen
     */
    public void draw(SpriteBatch batch, Player player){

       for(int i = 0; i < blocks.size(); i++)
           for(int j = 0; j < blocks.get(0).size(); j++)
               if (blocks.get(i).get(j).isVisible(player))
                   blocks.get(i).get(j).draw(batch);

    }

    /**
     *  Finds a spawn point for the player on top of a grass block and
     *  returns the coordinates
     *
     * @return a float array with x and y coordinate
     */
    public float[] spawnPoint(){

        for(int i = 0; i < blocks.size(); i++){
            for(int j = 0; j < blocks.get(0).size(); j++){
                if(blocks.get(i).get(j).toString().equals(Block.BlockType.GRASSBLOCK.toString()))
                    return new float[] {blocks.get(i).get(j).getX(), blocks.get(i).get(j).getY() + blocks.get(i).get(j).getHeight() + 200};
            }

        }

        return null;
    }


}
