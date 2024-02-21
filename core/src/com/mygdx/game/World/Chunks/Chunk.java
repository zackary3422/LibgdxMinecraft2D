package com.mygdx.game.World.Chunks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.GameEngine.Dimension;
import com.mygdx.game.World.Biome;

public abstract class Chunk {

    /** The size of a chunk (segment of the world) in blocks*/
    public static final Dimension<Integer> blocksDimension = new Dimension<Integer>(16, 60);

    /** The size of a chunk in pixels*/
    public static final Dimension<Float> dimension = new Dimension<Float>(blocksDimension.width * Block.BLOCK_LENGTH, blocksDimension.height * Block.BLOCK_LENGTH);

    /** The position of the chunk in the world in terms of blocks*/
    int xPosition;

    /** Array of all the blocks in the chunk*/
    public Block[][] blocks = new Block[blocksDimension.height][blocksDimension.width];

    /** The biome of this chunk*/
    Biome.BiomeTypes biome;

    /** The ID of the chunk for identification*/
    private int id;


    /**
     * Constructs a new chunk with a list of blocks and a biome type.
     * @param biome the biome type of this chunk
     */
    public Chunk(Biome.BiomeTypes biome, int xPosition){

        this.biome = biome;
        this.xPosition = xPosition;

    }

    /**
     * Populates the chunks blocks with NULL
     */
    public void emptyChunk(){

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[0].length; j++)
                blocks[i][j] = null;

    }

    /***
     * Flips all the blocks in the chunk in both the array and in position
     */
    public void flip(){

        //
        Block leftBlock;
        Block rightBlock;

        //
        int columnLength = blocks.length;
        int rowLength = (blocks[0].length / 2);

        //
        for(int i = 0; i < columnLength; i++){
            for(int j = 0; j < rowLength; j++){

                //
                int rightX = blocks[0].length - (j + 1);

                //
                leftBlock = blocks[i][j];
                rightBlock = blocks[i][rightX];

                //Flip blocks position
                if(leftBlock != null)
                    leftBlock.setPosition(createPosition(xPosition + rightX, i));

                if(rightBlock != null)
                    rightBlock.setPosition(createPosition(xPosition + j, i));


                //Flip blocks in array
                blocks[i][j] = blocks[i][rightX];
                blocks[i][rightX] = leftBlock;

            }
        }

    }


    /* ----- ACCESSORS ----- */

    /** */ //MAYBE REMOVE BOTH THESE FUNCTION AND HAVE THEM IN JUST PLAIN CODE OR NOT
    public int getLeftTopBlock(){

        for(int i = blocksDimension.height - 1; i >= 0; i--)
            if(blocks[i][0] != null)
                return i;

        return 0;
    }
        //Make sure to account for other blocks like leaves or grass
    /** */
    public int getRightTopBlock(){

        for(int i = blocksDimension.height - 1; i >= 0; i--)
            if(blocks[i][blocks[0].length - 1] != null)
                return i;

        return 0;
    }

    /** */
    public float getLeftMostX(){
        return xPosition * Block.BLOCK_LENGTH;
    }

    /** */
    public float getRightMostX(){
        return (xPosition + blocksDimension.width) * Block.BLOCK_LENGTH;
    }

    /** */
    public Vector2 getSpawnPoint(){


        for(int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks[0].length; j++) {
                if (Block.isTopLayer(blocks[i][j].idList)) {
                    Block block = blocks[i][j];
                    return new Vector2(block.getPosition().x, block.getPosition().y + block.getDimension().height);
                }
            }

        //
        return new Vector2(0,0);
    }

    /** @return the block with the given coordinates*/
    public Block getBlock(int x, int y){
        return blocks[y][x];
    }

    /** @return the chunks biome*/
    public Biome.BiomeTypes getBiome(){
        return biome;
    }

    public int getXPosition(){
        return xPosition;
    }

    /* ----- MUTATORS ----- */

    /** */
    public void setBlock(int x, int y, Block block){
        blocks[y][x] = block;
    }

    //IMPLEMENT ABILITY TO MOVE THE ENTIRE CHUNK AND ALL BLOCKS IN IT

    /** */
    public void moveChunk(int xPosition){

        Block temp;

        this.xPosition = xPosition;

        for(int i = 0; i < blocksDimension.height; i++){ // <-- IS USING BLOCKS.LENGTH FOR ARRAY BAD?
            for(int j = 0; j < blocksDimension.width; j++){

                temp = blocks[i][j];

                if(temp != null)
                    temp.setPosition(new Vector2(((j + xPosition) * Block.BLOCK_LENGTH), temp.getPosition().y));

            }
        }

    }


    public static Vector2 createPosition(int x, int y){
        return new Vector2(x * Block.BLOCK_LENGTH, y * Block.BLOCK_LENGTH);
    }



}
