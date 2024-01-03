package com.mygdx.game.World;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockID;
import com.mygdx.game.Blocks.BlockTextures;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Player.Player;

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

    /** */
    public enum Direction {LEFT, RIGHT, UP, DOWN}

    int spawnPointID = 0;

    /**
     * Constructs a new World of Blocks and populates the arraylist
     *
     */
    public World(){
        BlockTextures.initBlockTextures();

        chunks = new ArrayList<Chunk>();
        chunks = TerrainGenerator.newWorld();
    }

    /**
     * Called every frame to update world blocks. Used to expand world as player explores.
     *
     * @param player used to pass onto methods like expandWorld
     */
    public void update(Player player){

        TerrainGenerator.chunkExpander(player.getPosition(), chunks);

    }

    /**
     * Draws all the blocks in the {@code blocks} list
     *
     * @param batch the SpriteBatch to draw sprites onto screen
     */
    public void draw(SpriteBatch batch, Player player){

       for(int i = 0; i < chunks.size(); i++)
           if(chunks.get(i).isVisible(player.getPosition(), player.getViewPort()))
                chunks.get(i).draw(batch, player.getPosition(), player.getViewPort());

    }

    /**
     *  Finds a spawn point for the player on top of a top layer block and
     *  returns the coordinates. Has error handling for out of bounds index.
     *
     * @return a vector2 with x and y coordinate
     */
    public Vector2 spawnPoint(){
        try{
            return chunks.get(0).getSpawnPoint();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ERROR: Chunks index out of bounds for spawn point");
        }
        catch(Exception e){
            System.out.println("ERROR: error in spawn point");
        }

        return new Vector2(0,0);
    }

    public static ArrayList<Chunk> getChunks(){
        return chunks;
    }



}
