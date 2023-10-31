package com.mygdx.game.World;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockID;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Player.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 *  The {@code World} class represents all the blocks in the Minecraft world. This class will store
 *  all the blocks in a 2D arraylist and this class can be used to access the worlds blocks
 *  and to edit them.
 */

public class World {

    /** The list of all the chunks in the world*/
    public static ArrayList<Chunk> chunks;

    /** The size of a chunk(segment of the world) in blocks*/
    public static final Dimension CHUNK_SIZE = new Dimension(16, 60);

    /***/
    public enum Direction {LEFT, RIGHT, UP, DOWN}


    /**
     * Constructs a new World of Blocks and populates the arraylist
     *
     */
    public World(){
        chunks = new ArrayList<Chunk>();

        chunks = TerrainGenerator.newWorld();
    }



    /**
     * Called every frame to update world blocks. Used to expand world as player explores.
     *
     * @param player used to pass onto methods like expandWorld
     */
    public void update(Player player){

        //TerrainGenerator.expandWorld(blocks, player);

    }
    /**
     * Draws all the blocks in the {@code blocks} list
     *
     * @param batch the SpriteBatch to draw sprites onto screen
     */
    public void draw(SpriteBatch batch, Player player){

       for(int i = 0; i < chunks.size(); i++)
           chunks.get(i).draw(batch, player);

    }

    /**
     *  Finds a spawn point for the player on top of a grass block and
     *  returns the coordinates
     *
     * @return a float array with x and y coordinate
     */
    public Vector2 spawnPoint(){



        return new Vector2(0,0);
    }


}
