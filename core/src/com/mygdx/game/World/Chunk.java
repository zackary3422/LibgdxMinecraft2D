package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockID;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Chunk {

    /** The size of a chunk(segment of the world) in blocks*/
    public static final Dimension CHUNK_SIZE = new Dimension(16, 60);

    /** The list of all the blocks in the world*/
    public ArrayList<ArrayList<Block>> chunkBlocks;

    /** The biome of this chunk*/
    TerrainGenerator.Biomes biome;

    int[] sideTopLayers = {0, 0};


    public Chunk(ArrayList<ArrayList<Block>> blocks, TerrainGenerator.Biomes biome){

        chunkBlocks = blocks;
        this.biome = biome;

    }


    public void draw(SpriteBatch batch, Player player){

        for(int i = 0; i < CHUNK_SIZE.height; i++)
            for(int j = 0; j < CHUNK_SIZE.width; j++)
                if(chunkBlocks.get(i).get(j).isVisible(player))
                    chunkBlocks.get(i).get(j).draw(batch);


    }

    /**
     *
     *
     * @return a int array with left and right side top layer height values
     */
    public int[] getSideTopLayers(){

        //Left[0] and right[1] side top layers
        int[] sideLayers = new int[2];

        //Get left side top layer
        for(int i = CHUNK_SIZE.height - 1; i >= 0; i--)
            if(BlockID.isTopLayer(chunkBlocks.get(i).get(0).getID()))
                sideLayers[0] = i;

        //Get right side top layer
        for(int i = CHUNK_SIZE.height - 1; i >= 0; i--)
            if(BlockID.isTopLayer(chunkBlocks.get(i).get(CHUNK_SIZE.width - 1).getID()))
                sideLayers[1] = i;

        return sideLayers;
    }

    public float[] getSideX(){
        float[] sideX = new float[2];


        //Left most X coordinate
        sideX[0] = chunkBlocks.get(0).get(0).getX();

        //Right most X coordinate
        sideX[1] = chunkBlocks.get(0).get(chunkBlocks.get(0).size()-1).getX();

        return sideX;
    }

    public Vector2 getSpawnPoint(){

        for(int i = 0; i < chunkBlocks.size(); i++){
            for(int j = 0; j < chunkBlocks.get(0).size(); j++){
                if(BlockID.isTopLayer(chunkBlocks.get(i).get(j).getID()))
                    return chunkBlocks.get(i).get(j).getVector();
            }
        }
        //
        return new Vector2(0,0);
    }


    public Block getBlock(int x, int y){
        return chunkBlocks.get(y).get(x);
    }

    public void setBlock(int x, int y, Block block){
        chunkBlocks.get(y).set(x, block);
    }

}
