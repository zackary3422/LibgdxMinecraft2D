package com.mygdx.game.World;

import com.mygdx.game.GameEngine.Movement;
import com.mygdx.game.World.Chunks.Chunk;
import com.mygdx.game.World.Chunks.ForestChunk;

import java.util.ArrayList;

public class ChunkGenerator {

    public static int currentChunkID = 0;


    /** */
    public static void createStartingChunk(ArrayList<Chunk> chunks){

        chunks.clear();

        chunks.add(new ForestChunk(0, 30));
    }

    /**
     * Will expand the world in a certain direction based on previous biomes size
     */
    public static void chunkExpander(ArrayList<Chunk> chunks, Movement.Direction direction){

        if(chunks.isEmpty()) {
            createStartingChunk(chunks);
            return;
        }

        if(direction != Movement.Direction.LEFT && direction != Movement.Direction.RIGHT) {
            System.out.println("ERROR: (ChunkGenerator) Invalid direction for method chunkExpander");
            return;
        }

        //Generate a biomes for new chunk
        Biome.BiomeTypes newBiome = Biome.generateBiome(chunks, direction);

        //Initialize chunk variables
        int xPosition = 0;
        int sideChunksHeight = 0;

        //Set variables based on the direction
        if(direction == Movement.Direction.RIGHT){
            xPosition = chunks.get(chunks.size()-1).getXPosition() + Chunk.blocksDimension.width;
            sideChunksHeight = chunks.get(chunks.size()-1).getRightTopBlock();
        }
        else {
            xPosition = chunks.get(0).getXPosition() - Chunk.blocksDimension.width;
            sideChunksHeight = chunks.get(0).getLeftTopBlock();
        }

        //Generate new chunk based on arguments
        Chunk newChunk = generateChunk(xPosition, sideChunksHeight, newBiome);

        //Create a new chunk and add it
        if(direction == Movement.Direction.RIGHT)
            chunks.add(chunks.size(), newChunk);

        else {
            newChunk.flip();

            chunks.add(0, newChunk);
        }

    }


    /**
     * Generates new chunk based on
     */
    public static Chunk generateChunk(int xPosition, int sideChunksHeight, Biome.BiomeTypes biome){

        if(biome == Biome.BiomeTypes.FOREST)
            return new ForestChunk(xPosition, sideChunksHeight);


        return null;
    }










}
