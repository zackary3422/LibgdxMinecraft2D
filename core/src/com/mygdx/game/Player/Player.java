package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Blocks.Block;
import com.mygdx.game.Components.Box2D;
import com.mygdx.game.World.BlockCollisions;
import com.mygdx.game.World.WorldBlocks;

import java.util.ArrayList;

/**
 * The {@code Player} class represents the player. This class handles player input and
 * drawing them onto the screen.
 */

public class Player {

    /** Players coordinates and dimensions*/
    float x, y, width, height;

    /** The sprite of the player to draw it*/
    Sprite sprite;

    /** The keyboard for player keyboard input*/
    Keyboard keyboard;

    /** The box collider for the player*/
    public Box2D box2D;

    /** The camera of the player*/
    OrthographicCamera camera;

    /** The player movement speed*/
    private float playerSpeed = 160;

    /** The speed multiplier when sprinting*/
    private float shiftMultiplier = 1.9f;

    /***/
    static final float jumpForce = 220f;

    /***/
    float verticalVelocity = 0;

    /***/
    float gravity = -380f;

    /***/
    public ArrayList<Block> collidingBlocks;

    /**
     * Constructs a new Player and initializes variables and sets position of sprite.
     *
     * @param spawnPoint the x and y coordinates for the spawn point of the player
     * @param camera the camera for the player
     */
    public Player(float[] spawnPoint, OrthographicCamera camera){

        this.camera = camera;

        sprite = new Sprite(new Texture("Steve.png"));
        keyboard = new Keyboard();

        //Set player position
        x = spawnPoint[0];
        y = spawnPoint[1];


        box2D = new Box2D(x, y, width, height);
        setPosition(x, y);

        width = sprite.getWidth();
        height = sprite.getHeight();

        box2D = new Box2D(x, y, width, height);
    }

    /**
     * Executes necessary logic for player class to work. Handles keyboard input.
     */
    public void update(){

        keyboard.update(this);
        applyGravity();
        updateCollidingBlocks();
        setPosition(x, y);
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
     * Subtracts the vertical velocity
     *
     */
    public void applyGravity(){
        verticalVelocity += gravity * Gdx.graphics.getDeltaTime();
    }

    /**
     *
     *
     */
    public void applyVelocity(){

        if(BlockCollisions.bottomCollisions(collidingBlocks, box2D).isEmpty())
            y += verticalVelocity * Gdx.graphics.getDeltaTime();
        else {
            verticalVelocity = 0;
            setPosition(x, BlockCollisions.bottomCollisions(collidingBlocks, box2D).get(0).getY() + BlockCollisions.bottomCollisions(collidingBlocks, box2D).get(0).getHeight());
        }


    }

    /**
     *
     *
     */
    public void updateCollidingBlocks(){
        collidingBlocks = BlockCollisions.collidingBlocks(box2D);

    }

    /**
     * Sets a new position for player coordinates, sprite, and camera.
     *
     * @param x new x-coordinate for player
     * @param y new y-coordinate for player
     *   */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
        camera.position.set(x, y,0);
        box2D.setPosition(x, y);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getCenterX(){return x + (width / 2);}

    public float getCenterY(){return y + (height / 2);}

    public float getPlayerSpeed(){
        return playerSpeed;
    }

    public float getShiftMultiplier(){
        return shiftMultiplier;
    }

    public float getVerticalVelocity(){
        return verticalVelocity;
    }

    public void setVerticalVelocity(float velocity){
        verticalVelocity = velocity;
    }
}
