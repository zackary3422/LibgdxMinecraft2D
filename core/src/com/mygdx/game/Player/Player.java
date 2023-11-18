package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Block;
import com.mygdx.game.Components.Box2D;
import com.mygdx.game.Components.Dimension;
import com.mygdx.game.Blocks.BlockCollisions;

import java.util.ArrayList;

/**
 * The {@code Player} class represents the player. This class handles player input and
 * drawing them onto the screen.
 */

public class Player {

    /** Players coordinates and dimension*/
    Vector2 position;
    Dimension<Float> dimension;
    Dimension<Float> viewPort;

    /** The sprite of the player to draw it*/
    Sprite sprite;

    /** The keyboard for player keyboard input*/
    Keyboard keyboard;

    /** The box collider for the player*/
    public Box2D box2D;

    /** The camera of the player*/
    OrthographicCamera camera;

    /** The player movement speed*/
    private float playerSpeed = 200;

    /** The speed multiplier when sprinting*/
    private float shiftMultiplier = 1.9f;

    /** The vertical force applied when jumping*/
    static final float jumpForce = 200f;

    /** The players vertical velocity*/
    float verticalVelocity = 0;

    /** Gravity applied to players velocity*/
    float gravity = -480f;

    /** The list of blocks that are colliding with player*/
    public ArrayList<Block> collidingBlocks;

    /**
     * Constructs a new Player and initializes variables and sets position of sprite.
     *
     * @param spawnPoint the x and y coordinates for the spawn point of the player
     * @param camera the camera for the player
     */
    public Player(Vector2 spawnPoint, OrthographicCamera camera){

        this.camera = camera;
        viewPort = new Dimension<Float>(camera.viewportWidth, camera.viewportHeight);

        sprite = new Sprite(new Texture("Steve.png"));
        keyboard = new Keyboard();

        //Set player position
        position = new Vector2();
        position.x = spawnPoint.x;
        position.y = spawnPoint.y;

        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        box2D = new Box2D(position, dimension);
        setPosition(position);

        box2D = new Box2D(position, dimension);
    }

    /**
     * Executes necessary logic for player class to work. Handles keyboard input.
     */
    public void update(){

        keyboard.keyboardInput(this);
        applyGravity();
        updateCollidingBlocks();
        setPosition(position);
        applyVelocity();

    }

    /**
     * Draws players sprite onto screen
     *
     * @param batch used to draw sprite onto screen
     */
    public void draw(SpriteBatch batch){
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /**
     * Subtracts the vertical velocity to apply gravity
     *
     */
    public void applyGravity(){
        verticalVelocity += gravity * Gdx.graphics.getDeltaTime();
    }

    /**
     * Apply velocity to y-coordinate and stop movement if bottom of player is colliding with a block.
     */
    public void applyVelocity(){


        //Move player down if there's nothing below
        if(BlockCollisions.bottomCollisions(collidingBlocks, box2D).isEmpty())
            position.y += verticalVelocity * Gdx.graphics.getDeltaTime();
        else
        {//Stop player from moving down and move player to bottom blocks position
            verticalVelocity = 0;
            setPosition(new Vector2(position.x, BlockCollisions.bottomCollisions(collidingBlocks, box2D).get(0).getY() + BlockCollisions.bottomCollisions(collidingBlocks, box2D).get(0).getHeight()));
        }


    }

    /**
     * Update collidingBlocks list for new blocks colliding with player.
     */
    public void updateCollidingBlocks(){

        //Get list of blocks colliding with player
        collidingBlocks = BlockCollisions.collidingBlocks(box2D);

        //Filter out un-collidable blocks
        BlockCollisions.filter(collidingBlocks);
    }

    /**
     * Sets a new position for player coordinates, sprite, and camera.
     *
     * @param newPosition the vector for the new position of player
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;
        sprite.setPosition(position.x, position.y);
        camera.position.set(position.x, position.y,0);
        box2D.setPosition(position);
    }


    /* ----- ACCESSORS ----- */
    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getCenterX(){return position.x + (dimension.width / 2);}

    public float getCenterY(){return position.y + (dimension.height / 2);}

    public float getPlayerSpeed(){
        return playerSpeed;
    }

    public float getShiftMultiplier(){
        return shiftMultiplier;
    }

    public float getVerticalVelocity(){
        return verticalVelocity;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Dimension<Float> getViewPort(){
        return viewPort;
    }

    /* ----- MUTATORS ----- */
    public void setVerticalVelocity(float velocity){
        verticalVelocity = velocity;
    }
}
