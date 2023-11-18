package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.*;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Player.Player;
import java.util.Random;
import java.util.ArrayList;

/**
 * The {@code TerrainGenerator} class is a tool for generating and expanding the world's
 * arraylist of blocks.
 *
 */
public class TerrainGenerator {

    /* ----- TERRAIN GENERATION VARS ----- */

    /** The levels in which grass blocks are created*/
    static final int[] GRASS_LEVEL = {32, 45};

    /** The number of dirt blocks to put under a grass block*/
    static final int DIRT_LAYERS = 4;

    /** The levels in which dirt blocks are created*/
    static final int[] DIRT_LEVEL = {GRASS_LEVEL[0]-1 - DIRT_LAYERS, GRASS_LEVEL[0]-1};

    /** The levels in which stone blocks are created*/
    static final int[] STONE_LEVEL = {0, DIRT_LEVEL[0]-1};

    /**The types of biomes in the world*/
    public enum Biomes {FOREST, MOUNTAIN, DESSERT}

    /** The percentile chance of a new biome being of certain biome. Ex: Biomes forest has 50% chance to spawn*/
    public static float[] BiomesSpawnChance = {0.5f, 0.3f, 0.2f};

    /** The minimum number of chunks a biome needs before switching to a new one*/
    public static final int minBiomeChunkSize = 6;



    /**
     * Creates a new world
     * @return the arraylist of chunks of the new world
     */
    public static ArrayList<Chunk> newWorld(){

        ArrayList<Chunk> chunks = new ArrayList<Chunk>();

        chunks.add(generateForestChunk(GRASS_LEVEL[0], 0));

        //Generate right side of world
        for(int i = 0; i < 6; i++){
            addChunk(chunks, generateForestChunk(chunks.get(chunks.size()-1).getRightSideTopLayer(), chunks.get(0).getID() + 1), World.Direction.RIGHT);
        }

        //Generate left side of world
        for(int i = 0; i < 6; i++){
            addChunk(chunks, generateForestChunk(chunks.get(0).getLeftSideTopLayer(), chunks.get(0).getID() - 1), World.Direction.LEFT);
        }

        //Return new
        return chunks;
    }


    /* ----- Chunk Creator and Modifiers ----- */

    /**
     * Generates a chunk (segment of world)
     * @param biome the biome of the chunk to be generated
     * @param sideTopLayer the top layer of the chunk it will be linked to
     * @return the chunk that is generated
     */
    public static Chunk generateChunk(Biomes biome, int sideTopLayer, int ID) {


        if(biome == Biomes.FOREST)
            return generateForestChunk(sideTopLayer, ID);


        return null;
    }

    /**
     * Adds a chunk onto an arraylist of chunks and updates the block positions so the chunk is connected to the next one.
     * If chunk is being added to left then it's reflected across the middle vertical axis .
     * @param chunkList the chunk list where the new chunk is being added to
     * @param linkChunk the chunk that is added to the chunkList
     * @param direction the side of the list where the new chunk will be added
     */
    public static void addChunk(ArrayList<Chunk> chunkList, Chunk linkChunk, World.Direction direction){

        if(linkChunk == null){
            System.out.println("ERROR: Tried to add a null chunk");
            return;
        }

        //Vars used for changing position of block
        float newX;
        Block tempBlock;

        //Adds the linkChunk to the right side of the chunkList
        if(direction == World.Direction.RIGHT){

            //Used as a reference point to move all blocks in linkChunk to the right side of the right most chunk
            float rightMostX = chunkList.get(chunkList.size()-1).getRightMostX();

            //Loop through to change block positions
            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = 0; j < Chunk.CHUNK_SIZE.width; j++){

                    tempBlock = linkChunk.getBlock(j, i);
                    newX = ((j + 1) * Block.BLOCK_LENGTH) + rightMostX;

                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            //Add chunk to the right most side of the chunk list
            chunkList.add(chunkList.size(), linkChunk);
        }

        else if(direction == World.Direction.LEFT){

            //Used as a reference point to move all blocks in linkChunk to the left side of the left most chunk
            float leftMostX = chunkList.get(0).getLeftMostX();

            //Loop through to change block positions
            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = Chunk.CHUNK_SIZE.width-1; j >= 0; j--){

                    tempBlock = linkChunk.getBlock(j, i);
                    newX = leftMostX - ((Chunk.CHUNK_SIZE.width - j) * Block.BLOCK_LENGTH);

                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            //Flip chunk to mirror to the left
            flipChunk(linkChunk);

            //Add modified chunk to left most side of the chunk list
            chunkList.add(0, linkChunk);
        }



    }

    /**
     * Takes in a chunk and flips the chunk horizontally. This is useful for when you need to add a chunk to the left side
     * and need it flipped to mirror on the left.
     * @param chunk the chunk that will be flipped
     */
    public static void flipChunk(Chunk chunk){

        //block used for switching blocks in chunk
        Block tempBlock;

        for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++) {
            for (int j = 0; j < Chunk.CHUNK_SIZE.width / 2; j++) {

                //Flip Blocks Positions
                Vector2 tempPosition = chunk.getBlock(j, i).getVector();
                chunk.getBlock(j, i).setPosition(chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i).getVector());
                chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i).setPosition(tempPosition);

                //Flip actual block in List
                tempBlock = chunk.getBlock(j, i);//save block before switching
                chunk.setBlock(j, i, chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i));//replace block
                chunk.setBlock((Chunk.CHUNK_SIZE.width-1) - j, i, tempBlock);//replace block

            }
        }

    }

    /**
     * Adds chunks to the world as player approaches the sides of the world to keep world continous.
     * @param playerPosition the player position used to see if they're approaching edge of world
     */
    public static void chunkExpander(Vector2 playerPosition, ArrayList<Chunk> chunks){

            //Get left & right most positions of chunk
            float chunkLeftMostX = chunks.get(0).getLeftMostX();
            float chunkRightMostX = chunks.get(chunks.size()-1).getRightMostX();

            //Get left & right most positions of players view
            float playerLeftViewX = playerPosition.x - Window.width / 2.0f;
            float playerRightViewX = playerPosition.x + Window.width / 2.0f;

            //Expand left
            if(playerLeftViewX < chunkLeftMostX )
                addChunk(chunks, generateChunk(generateBiome(chunks, World.Direction.LEFT), chunks.get(0).getLeftSideTopLayer(), chunks.get(0).getID() - 1), World.Direction.LEFT);

            //Expand right
            if(playerRightViewX > chunkRightMostX)
                addChunk(chunks, generateChunk(generateBiome(chunks, World.Direction.RIGHT), chunks.get(chunks.size()-1).getRightSideTopLayer(), chunks.get(0).getID() + 1), World.Direction.RIGHT);

    }


    /* ----- Biome Creators ----- */

    /**
     * Generates a random biome and return it.
     * @return the random biome
     */
    public static Biomes randomBiome(){

        Random rand = new Random();
        float chance;
        Biomes[] biomeArray = Biomes.values();


        for(int i = 0; i < biomeArray.length; i++){
            chance = rand.nextFloat();
            if(chance <= BiomesSpawnChance[i])
                return biomeArray[i];
        }


        return Biomes.FOREST;
    }

    /**
     * Generates a biome type based on previous biome type in chunks list.
     * @param chunks the list of chunks used to see previous biome types
     * @param direction the direction of where the biome should be placed
     */
    public static Biomes generateBiome(ArrayList<Chunk> chunks, World.Direction direction){

        int currentBiomeSize = 0;

        //Determine the previous biome
        Biomes currentBiome = direction == World.Direction.LEFT ? chunks.get(0).getBiome() : chunks.get(chunks.size()-1).getBiome();

        //Get current biome size
        for(int i = chunks.size()-1; i >= 0; i--)
            if(chunks.get(i).getBiome() == currentBiome)
                currentBiomeSize++;


        //Generate new biome if minimum biome chunk size has been met or return previous biome if not
        if(currentBiomeSize > minBiomeChunkSize)
            return randomBiome();
        else
            return currentBiome;

    }


    /* ----- Biome Chunk Generator----- */

    /**
     * Creates a chunk of biome forest type.
     * @param sideTopLayer the top layer of the chunk next to where this chunk is being placed.
     * @return the generated forest chunk
     */
    public static Chunk generateForestChunk(int sideTopLayer, int ID){

        //The new world in a 2D arraylist
        ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();

        //Create world full of empty blocks
        for(int i = 0; i < World.CHUNK_SIZE.height; i++)
        {
            //adds new row
            blocks.add(new ArrayList<Block>());

            for (int j = 0; j < World.CHUNK_SIZE.width; j++)
                blocks.get(i).add(new EmptyBlock(new Vector2(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH)));
        }

        //Populate world with blocks
        populateForestGrass(blocks, sideTopLayer);
        populateDirt(blocks);
        populateStone(blocks);


        return new Chunk(blocks, Biomes.FOREST, ID);
    }



    /* ----- POPULATES ----- */

    /**
     * Adds grass layers to a 2D list of blocks for a forest biome.
     * @param blocks the 2D list of blocks to add grass blocks to.
     * @param prevTopLayer the top layer of the chunk next to these blocks
     */
    private static void populateForestGrass(ArrayList<ArrayList<Block>> blocks, int prevTopLayer){

        Random rand = new Random();

        //Height to put grass block at
        int topLayerHeight = prevTopLayer;

        //the length of a section of grass blocks
        int topLayerLength = rand.nextInt(3, 5);

        //List of all the grass block layer heights
        ArrayList<Integer> layerHeights = new ArrayList<Integer>();

        //Populate layerHeights with grass block heights
        for(int i = 0; i < blocks.get(0).size(); i++) {

            //Add current top layer height to list
            layerHeights.add(topLayerHeight);
            topLayerLength--;

            //Change current top layer height
            if(topLayerLength == 0){
                if(topLayerHeight == GRASS_LEVEL[0])
                    topLayerHeight += 1;
                else if (topLayerHeight == GRASS_LEVEL[1])
                    topLayerHeight -=1;
                else
                    topLayerHeight += rand.nextInt(-1, 2);

                topLayerLength = rand.nextInt(2, 4);
            }

        }

        //Add grass
        for(int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(0).size(); j++)
                if(layerHeights.get(j) == i)
                    blocks.get(i).set(j, new GrassBlock(new Vector2(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH)));



    }

    /**
     * Adds dirt underneath all grass blocks.
     * @param blocks the 2D list of block to add dirt blocks to.
     */
    private static void populateDirt(ArrayList<ArrayList<Block>> blocks){

        //Add
        for(int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(0).size(); j++){
               if(i > blocks.size()-4)
                    break;

               if(blocks.get(i+1).get(j).getID() == BlockID.GRASSBLOCK)
                   for(int z = 0; z < DIRT_LAYERS; z++) {
                       blocks.get(i - z).set(j, new DirtBlock(new Vector2(j * Block.BLOCK_LENGTH, (i - z) * Block.BLOCK_LENGTH)));
                   }

            }

    }

    /**
     * Adds stone underneath all dirt blocks all the way down to bottom of the blocks list.
     * @param blocks the 2D list of blocks to add stone blocks to.
     */
    private static void populateStone(ArrayList<ArrayList<Block>> blocks){

        for(int i = blocks.size() - 2; i >= 0; i--){
            for(int j = 0; j < blocks.get(0).size(); j++){

                //If above block is dirt
                boolean belowDirt = blocks.get(i+1).get(j).getID() == BlockID.DIRTBLOCK ||
                                              blocks.get(i+1).get(j).getID() == BlockID.STONEBLOCK;

                //If current block is not dirt
                boolean notDirtBlock = blocks.get(i).get(j).getID() != BlockID.DIRTBLOCK;

                //If above block is dirt and the current block is not dirt then make it stone
                if(belowDirt && notDirtBlock)
                    blocks.get(i).set(j, new StoneBlock(new Vector2(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH)));

            }
        }

    }

    private static void populateTrees(ArrayList<ArrayList<Block>> blocks){



    }

    private static void populateOres(ArrayList<ArrayList<Block>> blocks){



    }

    private void populateShrubs(ArrayList<ArrayList<Block>> blocks){



    }


}
