package com.mygdx.game.World.Chunks;

import com.mygdx.game.Blocks.DirtBlock;
import com.mygdx.game.Blocks.GrassBlock;
import com.mygdx.game.Blocks.StoneBlock;
import com.mygdx.game.GameEngine.Range;
import com.mygdx.game.World.Biome;
import com.mygdx.game.World.PopulateBlocks;

public class ForestChunk extends Chunk{


    public final Range heightRange = new Range(25, 38);
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
         PopulateBlocks.createTopLayer(blocks, GrassBlock.id, sideHeight, layerLength, heightRange);

        //Populate Dirt
        PopulateBlocks.createUnderLayer(blocks, GrassBlock.id, DirtBlock.id, new Range(4, 5));
        //PopulateBlocks.populateDirt(blocks, 4, GrassBlock.id);

        //Populate Stone
        PopulateBlocks.createUnderLayer(blocks, DirtBlock.id, StoneBlock.id, 0);
        PopulateBlocks.populateStone(blocks, DirtBlock.id);
    }



}
