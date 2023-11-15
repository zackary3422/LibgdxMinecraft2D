package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockID;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Components.Dimension;
import java.util.ArrayList;

public class Chunk {

    /** The size of a chunk(segment of the world) in blocks*/
    public static final Dimension<Integer> CHUNK_SIZE = new Dimension<Integer>(16, 60);

    /** The list of all the blocks in the world*/
    public ArrayList<ArrayList<Block>> chunkBlocks;

    /** The biome of this chunk*/
    TerrainGenerator.Biomes biome;


    /**
     * Constructs a new chunk with a list of blocks and a biome type.
     * @param blocks the list of blocks this chunk holds
     * @param biome the biome type of this chunk
     */
    public Chunk(ArrayList<ArrayList<Block>> blocks, TerrainGenerator.Biomes biome){

        chunkBlocks = blocks;
        this.biome = biome;
    }

    /**
     * Draws the chunk blocks onto the screen
     */
    public void draw(SpriteBatch batch, Vector2 playerPosition, Dimension<Float> viewPort){

        for(int i = 0; i < CHUNK_SIZE.height; i++)
            for(int j = 0; j < CHUNK_SIZE.width; j++)
                    if(chunkBlocks.get(i).get(j).isVisible(playerPosition, viewPort))
                        chunkBlocks.get(i).get(j).draw(batch);


    }


    public boolean isVisible(Vector2 playerPosition, Dimension<Float> viewPort){

        //Get left & right most positions of chunk
        float chunkLeftMostX = getLeftMostX();
        float chunkRightMostX = getRightMostX();

        //Get left & right most positions of players view (Offsets to reduce range a little bit)
        float playerLeftViewX = playerPosition.x - (viewPort.width / 2) - 50;
        float playerRightViewX = playerPosition.x + (viewPort.width / 2) - 190;

        //Checks if chunk is in player view
        if(chunkLeftMostX >= playerLeftViewX && chunkLeftMostX <= playerRightViewX || chunkRightMostX >= playerLeftViewX)
                 return true;

        return playerRightViewX >= chunkLeftMostX && playerRightViewX <= chunkRightMostX;


    }

    /* ----- ACCESSORS ----- */

    public int getLeftSideTopLayer(){

        //Get left side top layer
        for(int i = CHUNK_SIZE.height - 1; i >= 0; i--)
            if(BlockID.isTopLayer(chunkBlocks.get(i).get(0).getID()))
                return i;

        return 0;
    }

    public int getRightSideTopLayer(){
        //Get right side top layer
        for(int i = CHUNK_SIZE.height - 1; i >= 0; i--)
            if(BlockID.isTopLayer(chunkBlocks.get(i).get(CHUNK_SIZE.width - 1).getID()))
                return i;

        return 0;
    }

    public float getLeftMostX(){
        return chunkBlocks.get(0).get(0).getX();
    }

    public float getRightMostX(){
        return chunkBlocks.get(0).get(chunkBlocks.get(0).size()-1).getX();
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

    /** @return the block with the given coordinates*/
    public Block getBlock(int x, int y){
        return chunkBlocks.get(y).get(x);
    }

    /** @return the chunks biome*/
    public TerrainGenerator.Biomes getBiome(){
        return biome;
    }

    public void setBlock(int x, int y, Block block){
        chunkBlocks.get(y).set(x, block);
    }


}
