package com.mygdx.game.World;

import com.mygdx.game.Blocks.*;
import com.mygdx.game.Player.Player;

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

    /** The number of blocks the world gets expanded by*/
    static final int EXPANSION_SIZE = 10;

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

    /**
     * Expands world when player gets close to edge of world.
     *
     * @param blocks the list of blocks
     * @param player reference to player
     */
    public static void expandWorld(ArrayList<ArrayList<Block>> blocks, Player player){

      /** The index for the end block of the list*/
      int size = blocks.get(0).size() - 1;

      //Expand world to the left
      if(player.getX() - Window.width < blocks.get(0).get(0).getX())
          expandLeft(blocks);

      //Expand world to the right
      if(player.getX() + Window.width > blocks.get(0).get(size).getX())
          expandRight(blocks);


    }

    /**
     * Expands the list of blocks on the left side.
     *
     * @param blocks the list of blocks
     */
    public static void expandLeft(ArrayList<ArrayList<Block>> blocks){

        for(int i = 0; i < WorldBlocks.WORLD_HEIGHT; i++) {
            for (int j = 0; j < EXPANSION_SIZE; j++) {

                //Get the left most blocks x-coordinate
                float newX = blocks.get(i).get(0).getX() - Block.BLOCK_LENGTH;

                //Adds new block based on block levels
                if(i >= GRASS_LEVEL[0] && i <= GRASS_LEVEL[1])
                    blocks.get(i).add(0, new GrassBlock(newX, i * Block.BLOCK_LENGTH));
                else if(i >= DIRT_LEVEL[0] && i <= DIRT_LEVEL[1])
                    blocks.get(i).add(0, new DirtBlock(newX, i * Block.BLOCK_LENGTH));
                else if(i >= STONE_LEVEL[0] && i <= STONE_LEVEL[1])
                    blocks.get(i).add(0, new StoneBlock(newX, i * Block.BLOCK_LENGTH));
                else
                    blocks.get(i).add(0, new EmptyBlock(newX, i * Block.BLOCK_LENGTH));

            }
        }

    }

    /**
     * Expands the list of blocks on the right side.
     *
     * @param blocks the list of blocks
     */
    public static void expandRight(ArrayList<ArrayList<Block>> blocks){

        for(int i = 0; i < WorldBlocks.WORLD_HEIGHT; i++){
            for(int j = 0; j < EXPANSION_SIZE; j++){

                //Update size
                int size = blocks.get(i).size();

                //Get the right most blocks x-coordinate
                float newX = blocks.get(i).get(size - 1).getX() + Block.BLOCK_LENGTH;

                //Adds new block based on block levels
                if(i >= GRASS_LEVEL[0] && i <= GRASS_LEVEL[1])
                    blocks.get(i).add(size, new GrassBlock(newX, i * Block.BLOCK_LENGTH));
                else if(i >= DIRT_LEVEL[0] && i <= DIRT_LEVEL[1])
                    blocks.get(i).add(size, new DirtBlock(newX, i * Block.BLOCK_LENGTH));
                else if(i >= STONE_LEVEL[0] && i <= STONE_LEVEL[1])
                    blocks.get(i).add(size, new StoneBlock(newX, i * Block.BLOCK_LENGTH));
                else
                    blocks.get(i).add(size, new EmptyBlock(newX, i * Block.BLOCK_LENGTH));

            }
        }

    }


    public static void populateTrees(){



    }

    public static void populateOres(){



    }


}
