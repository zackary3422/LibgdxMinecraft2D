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

    /** The levels in which dirt blocks are created*/
    static final int[] DIRT_LEVEL = {31, GRASS_LEVEL[0]-1};
    static final int DIRT_LAYERS = 4;

    /** The levels in which stone blocks are created*/
    static final int[] STONE_LEVEL = {0, DIRT_LEVEL[0]-1};

    /**The types of biomes in the world*/
    public enum Biomes{FOREST, DESSERT, SNOW}

    //Create positional chunk
    //generate a chunk
    //link a chunk
    //then add to list


    /**
     * Creates a new world
     *
     * @return arraylist of chunks
     */
    public static ArrayList<Chunk> newWorld(){

        ArrayList<Chunk> chunks = new ArrayList<Chunk>();

        chunks.add(generateForestChunk(GRASS_LEVEL[0]));

        for(int i = 0; i < 9; i++){
            int topLayer = chunks.get(i).getSideTopLayers()[0];
            linkChunk(chunks, generateChunk(Biomes.FOREST, topLayer), World.Direction.LEFT);
          //  addChunk(chunks, generateForestChunk(chunks.get(0).getSideTopLayers()[1]), World.Direction.RIGHT);
        }


        return chunks;
    }


    /**
     * Generates a chunk (segment of world)
     *
     */
    public static Chunk generateChunk(Biomes biome, int sideTopLayer) {


        if(biome == Biomes.FOREST)
            return generateForestChunk(sideTopLayer);


        return null;
    }

    public static void linkChunk(ArrayList<Chunk> chunkList, Chunk linkChunk, World.Direction direction){

        float newX;
        Block tempBlock;

        if(direction == World.Direction.RIGHT){

            float rightMostX = chunkList.get(chunkList.size()-1).getSideX()[1];

            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = 0; j < Chunk.CHUNK_SIZE.width; j++){

                    tempBlock = linkChunk.getBlock(j, i);
                    newX = ((j + 1) * Block.BLOCK_LENGTH) + rightMostX;

                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            chunkList.add(chunkList.size(), linkChunk);
        }

        else if(direction == World.Direction.LEFT){

            float leftMostX = chunkList.get(0).getSideX()[0];


            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = Chunk.CHUNK_SIZE.width-1; j >= 0; j--){

                    tempBlock = linkChunk.getBlock(j, i);
                    newX = leftMostX - ((Chunk.CHUNK_SIZE.width - j) * Block.BLOCK_LENGTH);

                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            flipChunk(linkChunk);
            chunkList.add(0, linkChunk);
        }



    }

    public static void flipChunk(Chunk chunk){

        Block tempBlock;


        for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++) {
            for (int j = 0; j < Chunk.CHUNK_SIZE.width / 2; j++) {

                //Flip Blocks Positions
                Vector2 tempPosition = chunk.getBlock(j, i).getVector();
                chunk.getBlock(j, i).setPosition(chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i).getVector());
                chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i).setPosition(tempPosition);


                //Flip Block in List
                tempBlock = chunk.getBlock(j, i);

                chunk.setBlock(j, i, chunk.getBlock((Chunk.CHUNK_SIZE.width-1) - j, i));

                chunk.setBlock((Chunk.CHUNK_SIZE.width-1) - j, i, tempBlock);

            }
        }

    }


    /**
     * Adds chunk onto a list of chunks
     *
     */
    public static void addChunk(ArrayList<Chunk> chunksList, Chunk chunk, World.Direction direction){

        float newX;
        Block tempBlock;

        if(direction == World.Direction.RIGHT){

            float rightMostX = chunksList.get(chunksList.size()-1).getSideX()[1];

            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = 0; j < Chunk.CHUNK_SIZE.width; j++){

                    tempBlock = chunk.getBlock(j, i);
                    newX = ((j + 1) * Block.BLOCK_LENGTH) + rightMostX;

                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            chunksList.add(chunksList.size(), chunk);
        }
        else if(direction == World.Direction.LEFT){

            float leftMostX = chunksList.get(0).getSideX()[0];


            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = Chunk.CHUNK_SIZE.width-1; j >= 0; j--){

                    tempBlock = chunk.getBlock(j, i);
                    newX = leftMostX - ((Chunk.CHUNK_SIZE.width - j) * Block.BLOCK_LENGTH);


                    tempBlock.setPosition(new Vector2(newX, tempBlock.getY()));
                }
            }

            //Flip Across Y-Axis
            for(int i = 0; i < Chunk.CHUNK_SIZE.height; i++){
                for(int j = Chunk.CHUNK_SIZE.width-1; j >= 0; j--){

                    // tempBlock = chunk.getBlock(j, i);



                }
            }

            chunksList.add(0, chunk);
        }


    }


























    public static void chunkExpander(Player player, ArrayList<Chunk> chunks){



    }







    /* ----- CHUNK BIOME GENERATORS ----- */

    public static Chunk generateForestChunk(int sideTopLayer){

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
        populateGrass(blocks, sideTopLayer);
        populateDirt(blocks);
        populateStone(blocks);


        return new Chunk(blocks, Biomes.FOREST);
    }




    /* ----- POPULATES ----- */

    /**
     *
     *
     */
    public static void populateGrass(ArrayList<ArrayList<Block>> blocks, int prevTopLayer){

        Random rand = new Random();

        int topLayerHeight = prevTopLayer;
        int topLayerLength = rand.nextInt(3, 5);

        ArrayList<Integer> layerHeights = new ArrayList<Integer>();

        //Create grass layer heights
        for(int i = 0; i < blocks.get(0).size(); i++) {

            layerHeights.add(topLayerHeight);
            topLayerLength--;

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

    public static void populateDirt(ArrayList<ArrayList<Block>> blocks){

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

    public static void populateStone(ArrayList<ArrayList<Block>> blocks){

        for(int i = blocks.size() - 2; i >= 0; i--){
            for(int j = 0; j < blocks.get(0).size(); j++){


                boolean belowDirt = blocks.get(i+1).get(j).getID() == BlockID.DIRTBLOCK ||
                                              blocks.get(i+1).get(j).getID() == BlockID.STONEBLOCK;

                boolean notDirtBlock = blocks.get(i).get(j).getID() != BlockID.DIRTBLOCK;

                if(belowDirt && notDirtBlock)
                    blocks.get(i).set(j, new StoneBlock(new Vector2(j * Block.BLOCK_LENGTH, i * Block.BLOCK_LENGTH)));

            }
        }

    }

    public static void populateTrees(ArrayList<ArrayList<Block>> blocks){



    }

    public static void populateOres(ArrayList<ArrayList<Block>> blocks){



    }

    public void populateShrubs(ArrayList<ArrayList<Block>> blocks){



    }


}
