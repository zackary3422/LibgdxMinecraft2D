package com.mygdx.game.Blocks;

import com.mygdx.game.Components.Block;
import com.mygdx.game.Components.Box2D;
import com.mygdx.game.World.Chunk;
import com.mygdx.game.World.World;

import java.util.ArrayList;

public class BlockCollisions {


    /**
     * Finds all blocks that are colliding with the given box collider and
     * return them as a list.
     *
     * @param box2D the box collider that will be checked for collisions with all the blocks
     */
    public static ArrayList<Block> collidingBlocks(Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for(Chunk chunk : World.chunks){
            for (int i = 0; i < chunk.chunkBlocks.size(); i++) {
                for (int j = 0; j < chunk.chunkBlocks.get(0).size(); j++)
                {
                    if (chunk.chunkBlocks.get(i).get(j).box2D.isColliding(box2D))
                        colliders.add(chunk.getBlock(j, i));
                }
            }
        }



        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the bottom of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for left collisions
     * @return a arraylist of blocks that are colliding with the left side of the box collider
     */
    public static ArrayList<Block> leftCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (block.box2D.leftCollision(box2D))
                colliders.add(block);


        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the bottom of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for left collisions
     * @return a arraylist of blocks that are colliding with the left side of the box collider
     */
    public static ArrayList<Block> rightCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (block.box2D.rightCollision(box2D))
                colliders.add(block);

        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the bottom of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for top collisions
     * @return a arraylist of blocks that are colliding with the top of the box collider
     */
    public static ArrayList<Block> topCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (block.box2D.topCollision(box2D))
                colliders.add(block);

        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the bottom of the provided box collider.
     *
     * @param collidingBlocks the list of blocks that are colliding with box collider
     * @param box2D the box collider to be checked for bottom collisions
     * @return a arraylist of blocks that are colliding with the bottom of the box collider
     */
    public static ArrayList<Block> bottomCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (block.box2D.bottomCollision(box2D))
                colliders.add(block);


        return colliders;
    }

    /** Filter out blocks that aren't collidable from a list of colliding blocks
     *
     * @param collidingBlocks the list of blocks to be filtered
     */
    public static void filter(ArrayList<Block> collidingBlocks){

        for (int i = 0; i < collidingBlocks.size(); i++)
            if(!collidingBlocks.get(i).isCollidable())
            {
                collidingBlocks.remove(i);
                i--;
            }


    }

}
