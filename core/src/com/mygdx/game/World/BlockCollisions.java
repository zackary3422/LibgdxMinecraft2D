package com.mygdx.game.World;

import com.mygdx.game.Blocks.Block;
import com.mygdx.game.GameEngine.Box2D;
import com.mygdx.game.World.Chunks.Chunk;

import java.util.ArrayList;

public class BlockCollisions {


    /**
     * Finds all blocks that are colliding with the given box collider and
     * return them as a list.
     *
     * @param box2D the box collider that will be checked for collisions with all the blocks
     */
    public static ArrayList<Block> getCollidingBlocks(Block[][] blocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        int columnLength = blocks.length;
        int rowLength = blocks[0].length;

        for (int i = 0; i < columnLength; i++)
            for (int j = 0; j < rowLength; j++)
                if (blocks[i][j].box2D.isColliding(box2D))
                    colliders.add(blocks[i][j]);

        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the left of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for left collisions
     * @return a arraylist of blocks that are colliding with the left side of the box collider
     */
    public static ArrayList<Block> getLeftCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (box2D.leftCollision(block.box2D))
                colliders.add(block);


        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the right of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for right collisions
     * @return a arraylist of blocks that are colliding with the right side of the box collider
     */
    public static ArrayList<Block> getRightCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (box2D.rightCollision(block.box2D))
                colliders.add(block);

        return colliders;
    }

    /**
     * Gets all the blocks that are colliding with the top of the provided box collider.
     *
     * @param collidingBlocks the list of blocks colliding with box2D
     * @param box2D the box collider to be checked for top collisions
     * @return a arraylist of blocks that are colliding with the top of the box collider
     */
    public static ArrayList<Block> getTopCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (box2D.topCollision(block.box2D))
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
    public static ArrayList<Block> getBottomCollisions(ArrayList<Block> collidingBlocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();

        for (Block block : collidingBlocks)
            if (box2D.bottomCollision(block.box2D))
                colliders.add(block);



        return colliders;
    }

    /** Filter out blocks that aren't collidable from a list of colliding blocks
     *
     * @param collidingBlocks the list of blocks to be filtered
     */
    public static void filter(ArrayList<Block> collidingBlocks){

        for (int i = 0; i < collidingBlocks.size(); i++)
            if(!collidingBlocks.get(i).collidable)
            {
                collidingBlocks.remove(i);
                i--;
            }


    }

}
