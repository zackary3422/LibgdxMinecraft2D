package com.mygdx.game.World;

import com.mygdx.game.GameEngine.Movement;
import com.mygdx.game.World.Chunks.Chunk;
import com.mygdx.game.World.Chunks.ForestChunk;

import java.util.ArrayList;

public class ChunkGenerator {

    public static int currentChunkID = 0;


    /** The minimum number of chunks a biome needs before switching to a new one*/
    public static final int minBiomeChunkSize = 6;

    /** */
    public static void createStartingChunk(ArrayList<Chunk> chunks){

        currentChunkID = 0;
        chunks.clear();

        chunks.add(new ForestChunk(0, 30));
    }

    /**
     * Will expand the world in a certain direction based on previous biome size
     */
    public static void chunkExpander(ArrayList<Chunk> chunks, Movement.Direction direction){

        Biome.BiomeTypes newBiome = Biome.generateBiome(chunks, direction);

        //
        int xPosition = chunks.get(chunks.size()-1).getXPosition() + Chunk.blocksDimension.width;
        int sideChunksHeight = 0;

        //Set Variables
        if(direction == Movement.Direction.RIGHT){
            xPosition = chunks.get(chunks.size()-1).getXPosition() + Chunk.blocksDimension.width;
            sideChunksHeight = chunks.get(chunks.size()-1).getRightTopBlock();
        }
        else if(direction == Movement.Direction.LEFT){
            xPosition = chunks.get(0).getXPosition() - Chunk.blocksDimension.width;
            sideChunksHeight = chunks.get(0).getLeftTopBlock();
        }

        //
        Chunk newChunk = generateChunk(xPosition, sideChunksHeight, newBiome);

        //Create new chunk and add it
        if(direction == Movement.Direction.RIGHT){

            chunks.add(chunks.size(), newChunk);}
        else if (direction == Movement.Direction.LEFT){
            newChunk.flip();

            chunks.add(0, newChunk);
        }else
            System.out.println("ERROR: NOT A VALID DIRECTION IN CHUNK EXPANDER");

    }


    /**
     *
     */
    public static Chunk generateChunk(int xPosition, int sideChunksHeight, Biome.BiomeTypes biome){

        if(biome == Biome.BiomeTypes.FOREST)
            return new ForestChunk(xPosition, sideChunksHeight);


        return null;
    }

    /** */
    public void linkChunk(ArrayList<Chunk> chunkList, Chunk chunk, Movement.Direction direction){



    }

    /** */
    public static int getSideChunkHeight(ArrayList<Chunk> chunks, Movement.Direction direction){

        if(direction == Movement.Direction.RIGHT)
            return chunks.get(chunks.size()-1).getRightTopBlock();
        else if(direction == Movement.Direction.LEFT)
            return chunks.get(0).getLeftTopBlock();

        return -1;
    }








}
