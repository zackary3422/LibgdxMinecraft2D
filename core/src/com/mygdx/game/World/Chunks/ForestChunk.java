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




}
