package com.mygdx.game.World;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.GameEngine.Movement;
import com.mygdx.game.GameEngine.Player;
import com.mygdx.game.World.Chunks.Chunk;
import com.mygdx.game.GameEngine.Window;

import java.awt.*;
import java.util.ArrayList;

/**
 *  The {@code World} class represents all the blocks in the Minecraft world. This class will store
 *  all the blocks in a 2D arraylist and this class can be used to access the worlds blocks
 *  and to edit them.
 */
public class World{


    /** The list of all the chunks in the world*/
    public static ArrayList<Chunk> chunks;

    /** The size of a chunk(segment of the world) in blocks*/
    public static final Dimension CHUNK_SIZE = new Dimension(16, 60);


    /**
     * Constructs a new World of Blocks and populates the arraylist
     */
    public World(){

        chunks = new ArrayList<Chunk>();
        ChunkGenerator.createStartingChunk(chunks);
    }


    /** */
    public void worldExpansion(Player player){

        //Find which side needs to be expanded if necessary
        //Generate that chunk and link it to arraylist

        if(player.getPosition().x - com.mygdx.game.GameEngine.Window.camera.viewportWidth < (chunks.get(0).getXPosition() * Block.BLOCK_LENGTH)){//Left
            ChunkGenerator.chunkExpander(chunks, Movement.Direction.LEFT);
        } else if(player.getPosition().x + com.mygdx.game.GameEngine.Window.camera.viewportWidth > (chunks.get(chunks.size()-1).getXPosition() * Block.BLOCK_LENGTH)){
            ChunkGenerator.chunkExpander(chunks, Movement.Direction.RIGHT);
        }


    }

    /**
     * Sets all chunks not within range of the camera to be invisible
     *
     */
    public static void makeChunksInvisible(){



    }

    /**
     *  Finds a spawn point for the player on top of a top layer block and
     *  returns the coordinates our prints out an error based on exception sets spawn point to (0,0)
     *
     * @return a vector2 with x and y coordinate
     */
    public Vector2 getSpawnPoint(){
        try{
            return chunks.get(0).getSpawnPoint();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ERROR: (World) Chunks index out of bounds for spawn point");
        }
        catch(NullPointerException e){
            System.out.println("ERROR: (World) Null Pointer - Chunks haven't been created for spawn point");
        }
        catch(Exception e){
            System.out.println("ERROR: (World) Exception for spawn point");
        }

        return new Vector2(0,0);
    }





}
