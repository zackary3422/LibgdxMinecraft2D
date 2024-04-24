package com.mygdx.game.World.Chunks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.GameEngine.ID;
import com.mygdx.game.GameEngine.Range;

import java.util.Random;

public class PopulateBlocks {


    /**
     * Creates a
     *
     */
    public static void createSingleLayer(Block[][] blocks, ID blockID, int startingHeight, Range layerLengthRange, Range heightRange){

        Random rand = new Random();

        //Current grass block height
        int currentHeight = startingHeight;

        //Length of that grass block height
        int layerLength = rand.nextInt(layerLengthRange.max - layerLengthRange.min) + layerLengthRange.min;


        for(int i = 0; i < blocks[0].length; i++){

            //Set new grass block
            blocks[currentHeight][i] = Block.getBlock(blockID, new Vector2(i * Block.LENGTH, currentHeight * Block.LENGTH));

            layerLength--;

            //Set new layer length and height
            if(layerLength <= 0){
                layerLength = rand.nextInt(layerLengthRange.max - layerLengthRange.min) + layerLengthRange.min;

                //Add or subtract 1 to height
                if(currentHeight >= heightRange.max)
                    currentHeight--;
                else if(currentHeight <= heightRange.min)
                    currentHeight++;
                else
                    currentHeight += rand.nextInt(2) == 0 ? -1 : 1;// -1 - 1 //KEEP THIS AS IS OR REVERT?

            }
        }

    }

    /** */
    public static void createUnderLayer(Block[][] blocks, ID targetBlock, ID underBlock, Range blockRange){

        Random rand = new Random();

        for(int i = 0; i < blocks.length-1; i++)
            for(int j = 0; j < blocks[0].length; j++)

                if(blocks[i][j] != null && blocks[i-1][j] == null && blocks[i][j].hasMatchingID(targetBlock)) {
                    int blockDepth = i - (rand.nextInt(blockRange.max - blockRange.min + 1) + blockRange.min) - 1;
                    blockDepth = Math.max(blockDepth, 0);

                    for (int k = i - 1; k > blockDepth; k--)
                        blocks[k][j] = Block.getBlock(underBlock, new Vector2(i * Block.LENGTH, k * Block.LENGTH));
                }


    }

    /** */
    public static void createUnderLayer(Block[][] blocks, ID targetBlock, ID underBlock, int maxDepth){

        for(int i = 0; i < blocks.length-1; i++)
            for(int j = 0; j < blocks[0].length; j++)

                if(blocks[i][j] != null && blocks[i-1][j] == null && blocks[i][j].hasMatchingID(targetBlock)) {

                    for (int k = i - 1; k > maxDepth; k--)
                        blocks[k][j] = Block.getBlock(underBlock, new Vector2(i * Block.LENGTH, k * Block.LENGTH));
                }
    }




    /** */
    public static void generateCaves(Chunk[] chunks){

    }





}
