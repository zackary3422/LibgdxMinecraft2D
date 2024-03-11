package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.World.World;

import java.util.ArrayList;

/**
 * The {@code Player} class represents the player. This class handles player input and
 * drawing them onto the screen.
 */
public class Player extends GameObject {

    /** ID to identify that this is a player object*/
    public static ID id = new ID();

    /*** The player movement speed*/
    private float playerSpeed = 700;

    /*** The speed multiplier when sprintin */
    private float shiftMultiplier = 1.9f;

    /*** The vertical force applied when jumping*/
    static final float jumpForce = 7f;


    /**
     * Constructs a new Player and initializes variables and sets position of sprite.
     */
    public Player(Sprite sprite, Vector2 position) {

        super(position, sprite, id);

        setDrawPriority(1);

        enableInput();
        enableLogic();

        makeCollidable();
        updateCollisions();
        updateCollisions = true;

        //Set dimensions of player
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        //Set player position
        setPosition(position);
        setPosition(new Vector2(0,1500));
        Window.camera.position.set(0, 1500, 0);

    }

    /**
     * Executes necessary logic for player class to work.
     */
    @Override
    public void logic() {

        //Get blocks player is currently colliding with
        updateCollidingBlocks();

        //Move player out of blocks
        collisionUpdate();

        //Apply velocities to move player
        setPosition(movement.move(getPosition()));

      //  for(GameObject object : collidingObjects)
     //      setPosition(Box2D.getCollisionFreePos(this, object));

    }

    /**
     *
     */
    @Override
    public void input() {

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            setPosition(Movement.move(getPosition(), Movement.Direction.RIGHT, playerSpeed));
          //  movement.horizontalVelocity = movement.getDeltaSpeed(playerSpeed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            setPosition(Movement.move(getPosition(), Movement.Direction.LEFT, playerSpeed));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            setPosition(Movement.move(getPosition(), Movement.Direction.UP, playerSpeed));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            setPosition(Movement.move(getPosition(), Movement.Direction.DOWN, playerSpeed));
        }

        // Update the camera position smoothly
        //float cameraX = MathUtils.lerp(Window.camera.position.x, getPosition().x, 0.1f);
        //float cameraY = MathUtils.lerp(Window.camera.position.y, getPosition().y, 0.1f);
        //Window.camera.position.set(cameraX, cameraY, 0);

        Window.camera.position.set(getPosition().x, getPosition().y, 0);
    }


    /**
     * Move player out of block using the SAT to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     */
    public void collisionUpdate() {

        //Loop through all blocks colliding with player

        /*
        for (Block collidingBlock : collidingBlocks) {

            //Sets new position moved out of current colliding block
            setPosition(box2D.getCollisionFreePos(collidingBlock.box2D));

            //Find blocks colliding with player in new position
            updateCollidingBlocks();
        }
        */


    }


    /**
     * Update collidingBlocks list for new blocks colliding with player.
     */
    public void updateCollidingBlocks() {


    }

    public String toString(){
        return "Player Object";
    }

}
