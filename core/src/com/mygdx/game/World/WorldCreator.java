package com.mygdx.game.World;

import com.mygdx.game.Blocks.*;
import java.util.ArrayList;

/**
 * The {@code WorldCreator} class is a tool for generating and expanding the world's
 * arraylist of blocks.
 *
 */
public class WorldCreator {

    /** The levels in which grass blocks are created*/
    static final int[] GRASS_LEVEL = {36, 36};

    /** The levels in which dirt blocks are created*/
    static final int[] DIRT_LEVEL = {31, 35};

    /** The levels in which stone blocks are created*/
    static final int[] STONE_LEVEL = {0, 30};


    /**
     * Populates the blocks list with blocks
     *
     * @param blocks list of blocks that is to be populated
     * @param width width of the world to be populated
     * @param height height of the world to be populated
     */
    public static void populate(ArrayList<ArrayList<Block>> blocks, int width, int height){


        for(int i = 0; i < height; i++) {
            //adds new row
            blocks.add(new ArrayList<Block>());

            for (int j = 0; j < width; j++) {

                //Adds new block based on block levels
                if(i >= GRASS_LEVEL[0] && i <= GRASS_LEVEL[1])
                    blocks.get(i).add(new GrassBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));
                else if(i >= DIRT_LEVEL[0] && i <= DIRT_LEVEL[1])
                    blocks.get(i).add(new DirtBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));
                else if(i >= STONE_LEVEL[0] && i <= STONE_LEVEL[1])
                    blocks.get(i).add(new StoneBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));
                else
                    blocks.get(i).add(new EmptyBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));


            }
        }

    }


    public static void populateTrees(){



    }

    public static void populateOres(){



    }


}
