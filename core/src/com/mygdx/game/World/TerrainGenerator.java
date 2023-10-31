package com.mygdx.game.World;

import com.mygdx.game.Blocks.*;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Player.Player;
import java.util.Random;
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
    static final int[] DIRT_LEVEL = {31, GRASS_LEVEL[0]-1};
    static final int DIRT_LAYERS = 4;

    /** The levels in which stone blocks are created*/
    static final int[] STONE_LEVEL = {0, DIRT_LEVEL[0]-1};

    /** The number of blocks the world gets expanded by*/
    static final int EXPANSION_SIZE = 10;


    /**
     * Creates a new world in an 2D arraylist and populates it according to terrain generation.
     *
     * @param width width of the world in blocks
     * @param height height of the world in blocks
     * @return 2D arraylist of blocks of the new world
     */
    public static ArrayList<ArrayList<Block>> createWorld(int width, int height){

        ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();


        //Create world full of empty blocks
        for(int i = 0; i < height; i++) {
            //adds new row
            blocks.add(new ArrayList<Block>());
            for (int j = 0; j < width; j++)
                blocks.get(i).add(new EmptyBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));
        }

        populateGrass(blocks);
        populateDirt(blocks);
        populateStone(blocks);


        return blocks;
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

    /**
     *
     *
     */
    public static void populateGrass(ArrayList<ArrayList<Block>> blocks){

        Random rand = new Random();

        int topLayerHeight = GRASS_LEVEL[0];
        int topLayerLength = rand.nextInt(3, 5);

        ArrayList<Integer> layerHeights = new ArrayList<Integer>();

        //Create grass layer heights
        for(int i = 0; i < blocks.get(0).size(); i++) {

            layerHeights.add(topLayerHeight);
            topLayerLength--;

            if(topLayerLength == 0){
                topLayerHeight += rand.nextInt(-1, 2);
                topLayerLength = rand.nextInt(2, 4);
            }

        }

        //Add grass
        for(int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(0).size(); j++)
                if(layerHeights.get(j) == i)
                    blocks.get(i).set(j, new GrassBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));



    }



    public static void populateDirt(ArrayList<ArrayList<Block>> blocks){

        //Add
        for(int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(0).size(); j++){
               if(i > blocks.size()-4)
                    break;

               if(blocks.get(i+1).get(j).getBlockType() == Block.BlockType.GRASSBLOCK)
                   for(int z = 0; z < DIRT_LAYERS; z++) {
                       blocks.get(i - z).set(j, new DirtBlock(j * Block.BLOCK_LENGTH, (i - z) * Block.BLOCK_LENGTH));
                   }

            }

    }

    public static void populateStone(ArrayList<ArrayList<Block>> blocks){

        for(int i = blocks.size() - 2; i >= 0; i--){
            for(int j = 0; j < blocks.get(0).size(); j++){


                boolean aboveBlockCondition = blocks.get(i+1).get(j).getBlockType() == Block.BlockType.DIRTBLOCK ||
                                              blocks.get(i+1).get(j).getBlockType() == Block.BlockType.STONEBLOCK;

                boolean notDirtBlock = blocks.get(i).get(j).getBlockType() != Block.BlockType.DIRTBLOCK;

                if(aboveBlockCondition && notDirtBlock)
                    blocks.get(i).set(j, new StoneBlock(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));

            }
        }

    }

    public static void populateTrees(){



    }

    public static void populateOres(){



    }


}
