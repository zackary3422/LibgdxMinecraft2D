package com.mygdx.game.World;

import com.mygdx.game.Blocks.Block;
import com.mygdx.game.Components.Box2D;

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

        for (ArrayList<Block> block : WorldBlocks.blocks) {
            for (int j = 0; j < WorldBlocks.blocks.get(0).size(); j++) {
                if (block.get(j).box2D != null && block.get(j).box2D.isColliding(box2D))
                    colliders.add(block.get(j));
            }
        }

        return colliders;
    }

    /**
     *
     */
    public static ArrayList<Block> leftCollisions(ArrayList<Block> blocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();


        for (Block block : blocks)
            if (block.box2D != null && block.box2D.leftCollision(box2D))
                colliders.add(block);



        return colliders;
    }

    /**
     *
     */
    public static ArrayList<Block> rightCollisions(ArrayList<Block> blocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();


        for (Block block : blocks)
            if (block.box2D != null && block.box2D.rightCollision(box2D))
                colliders.add(block);


        return colliders;
    }

    /**
     *
     */
    public static ArrayList<Block> topCollisions(ArrayList<Block> blocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();


        for (Block block : blocks)
            if (block.box2D != null && block.box2D.topCollision(box2D))
                colliders.add(block);



        return colliders;
    }

    /**
     *
     *
     */
    public static ArrayList<Block> bottomCollisions(ArrayList<Block> blocks, Box2D box2D){

        ArrayList<Block> colliders = new ArrayList<Block>();


        for (Block block : blocks)
            if (block.box2D != null && block.box2D.bottomCollision(box2D))
                colliders.add(block);



        return colliders;
    }


}
