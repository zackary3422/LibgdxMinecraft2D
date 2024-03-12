package com.mygdx.game.World;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.Blocks.DirtBlock;
import com.mygdx.game.Blocks.GrassBlock;
import com.mygdx.game.Blocks.StoneBlock;
import com.mygdx.game.GameEngine.ID;
import com.mygdx.game.GameEngine.Range;

import java.util.Random;

public class Populator {


    //MAYBE instead of under block ID and other vars just require an array of ints on where to put each block and generate that
    //Array in chunk class

    /**
     *
     */
    public static void populateGrass(Block[][] blocks, int heightRange, int startingHeight, Range layerLengthRange){

        Random rand = new Random();

        //Current grass block height
        int currentHeight = startingHeight;

        //Length of that grass block height
        int layerLength = rand.nextInt(layerLengthRange.max - layerLengthRange.min) + layerLengthRange.min;


        for(int i = 0; i < blocks[0].length; i++){

            //Set new grass block
            blocks[currentHeight][i] = new GrassBlock(new Vector2(i * Block.BLOCK_LENGTH, currentHeight * Block.BLOCK_LENGTH));

            layerLength--;

            //Set new layer length and height
            if(layerLength <= 0){
                layerLength = rand.nextInt(layerLengthRange.max - layerLengthRange.min) + layerLengthRange.min;

                //RAND FOR UP 1 or down 1 block within height range
                if(currentHeight >= heightRange + startingHeight)
                    currentHeight--;
                else if(currentHeight <= startingHeight - heightRange)
                    currentHeight++;
                else
                    currentHeight += rand.nextInt(3) - 1;

            }
        }
    }

    /**
     *
     */
    public static void populateDirt(Block[][] blocks, int layerDepth, ID underBlockID){

        for(int i = blocks.length-2; i > 0; i--){
            for(int j = 0; j < blocks[0].length; j++){

                //Place dirt blocks under target block
                if(blocks[i][j] != null && blocks[i][j].hasMatchingID(underBlockID)){
                    for(int k = i - 1; k >= i - layerDepth; k--){

                        if(k <= 0)
                            break;

                        blocks[k][j] = new DirtBlock(new Vector2(j * Block.BLOCK_LENGTH, k * Block.BLOCK_LENGTH));
                    }
                }

            }
        }

    }

    /**
     *
     * */
    public static void populateStone(Block[][] blocks, ID underBlockID){

        for(int i = blocks.length - 2; i >= 0; i--){
            for(int j = 0; j < blocks[0].length; j++){

                if(blocks[i+1][j] != null && (blocks[i+1][j].hasMatchingID(underBlockID) || blocks[i+1][j].hasMatchingID(StoneBlock.id)) && blocks[i][j] == null)
                    blocks[i][j] = new StoneBlock(new Vector2(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH));


            }
        }

    }

    /** */
    public static void generateCaves(Block[][] blocks){

    }



}
