package com.mygdx.game.World.Chunks;

import com.mygdx.game.Blocks.DirtBlock;
import com.mygdx.game.Blocks.GrassBlock;
import com.mygdx.game.Blocks.StoneBlock;
import com.mygdx.game.GameEngine.Range;
import com.mygdx.game.World.Biome;

public class ForestChunk extends Chunk{

    /** The min and max of the forest top layer height. Can raise or lower chunk height*/
    public final Range heightRange = new Range(40, 50);

    /** The length of each layer of blocks before going up or down*/
    public final Range layerLength = new Range(2, 10);


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
         PopulateBlocks.createSingleLayer(blocks, GrassBlock.id, sideHeight, layerLength, heightRange);

        //Populate Dirt
        PopulateBlocks.createUnderLayer(blocks, GrassBlock.id, DirtBlock.id, new Range(3, 4));

        //Populate Stone
        PopulateBlocks.createUnderLayer(blocks, DirtBlock.id, StoneBlock.id, 0);
    }



}
