package com.mygdx.game.World.Chunks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.Blocks.DirtBlock;
import com.mygdx.game.Blocks.GrassBlock;
import com.mygdx.game.Blocks.StoneBlock;
import com.mygdx.game.GameEngine.Dimension;
import com.mygdx.game.GameEngine.Movement;
import com.mygdx.game.GameEngine.Range;
import com.mygdx.game.World.Biome;
import com.mygdx.game.World.Populator;

import java.util.Random;

public class ForestChunk extends Chunk{




    /** */
    public ForestChunk(int xPosition, int sideChunksHeight){

        super(Biome.BiomeTypes.FOREST, xPosition);

        generateChunk(sideChunksHeight);

        moveChunk(xPosition);

    }

    /** */
    public void generateChunk(int sideHeight){

        //Empty Chunk
        emptyChunk();

        //Create Top Layer
        Populator.populateGrass(blocks, 7, sideHeight, new Range(3, 5));

        //Populate Dirt
        Populator.populateDirt(blocks, 4, GrassBlock.id);

        //Populate Stone
        Populator.populateStone(blocks, DirtBlock.id);

    }

    /***/
    public static int[] generateGrassHeights(int startingHeight, int layerLengthRange){

        Random rand = new Random();

        int[] grassHeights;

        //Current grass block height
        int currentHeight = startingHeight;

        //Length of that grass block height
        int layerLength = rand.nextInt(layerLengthRange.max - layerLengthRange.min) + layerLengthRange.min;


        for(int i = 0; i < Chunk.blocksDimension.width; i++){

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




}
