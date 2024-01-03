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
import com.mygdx.game.World.BlockCollisions;
import com.mygdx.game.World.World;

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

    /** The input for player input */
    Input input;

    /** The box collider for the player*/
    public Box2D box2D;

    /** The players vertical velocity*/
    float verticalVelocity = 0;

    /** The players horizontal velocity*/
    float horizontalVelocity = 0;

    /** Gravity applied to players velocity*/
    float gravity = -20f;

    /** The list of blocks that are colliding with player*/
    public ArrayList<Block> collidingBlocks;

    public ArrayList<Block> bottomCollidingBlocks;


    /**
     * Constructs a new Player and initializes variables and sets position of sprite.
     *
     * @param spawnPoint the x and y coordinates for the spawn point of the player
     */
    public Player(Vector2 spawnPoint){

        //Init player sprite
        sprite = new Sprite(new Texture("Steve.png"));

        //Init player input
        input = new Input();

        //Set player position
        position = new Vector2();
        position.x = spawnPoint.x;
        position.y = spawnPoint.y;

        //Set dimensions of player
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        //Init box collider of player
        box2D = new Box2D(position, dimension);

        //Set player position
        setPosition(position);
    }

    /**
     * Executes necessary logic for player class to work.
     */
    public void update(){

        //Get input
        input.keyboardInput(this);

        //Get blocks player is currently colliding with
        updateCollidingBlocks();

        //Move player out of blocks
        collisionUpdate();

        //Apply velocities to move player
        applyVelocities();

        //Update players position
        setPosition(position);
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
     * Move player out of block using the SAT to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     */
    public void collisionUpdate()
    {

        //Loop through all blocks colliding with player
        for (Block collidingBlock : collidingBlocks) {


            //Get push distance and direction
            World.Direction satDirection = box2D.satPushDirection(collidingBlock.getBox2D());
            float pushDistance = box2D.satPushDistance(collidingBlock.getBox2D());

            //Push horizontally
           if(satDirection == World.Direction.RIGHT || satDirection == World.Direction.LEFT)
               setPosition(new Vector2(position.x + pushDistance, position.y));

           //Push vertically
           if(satDirection == World.Direction.DOWN || satDirection == World.Direction.UP)
               setPosition(new Vector2(position.x , position.y + pushDistance));

            //Find blocks colliding with player in new position
            updateCollidingBlocks();
        }

    }

    /**
     * Apply Vertical Velocities
     */
    public void applyVelocities()
    {

        //Adds negative gravity to player vertical velocity moving it downwards
        verticalVelocity += gravity * Gdx.graphics.getDeltaTime();

        //Set vertical velocity to zero if there's bottom collision and player is moving downward
        if(!BlockCollisions.getBottomCollisions(collidingBlocks, box2D).isEmpty() && verticalVelocity < 0)
            verticalVelocity = 0;

        //Set horizontal velocity to zero if there's bottom collision and player isn't pressing key
        if(!BlockCollisions.getBottomCollisions(collidingBlocks, box2D).isEmpty() && !input.isMovementKeysPressed())
            horizontalVelocity = 0;

        //Apply velocities to players position
        setPosition(new Vector2(position.x + horizontalVelocity, position.y + verticalVelocity));
    }

    /**
     * Update collidingBlocks list for new blocks colliding with player.
     */
    public void updateCollidingBlocks()
    {

        //Get list of blocks colliding with player
        collidingBlocks = BlockCollisions.getCollidingBlocks(box2D);



        //Filter out un-collidable blocks
        BlockCollisions.filter(collidingBlocks);
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

    public float getVerticalVelocity(){
        return verticalVelocity;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Dimension<Float> getViewPort(){
        return viewPort;
    }

    public Dimension<Float> getDimension(){return dimension;}

    public float getWidth(){
        return dimension.width;
    }

    public float getHeight(){
        return dimension.height;
    }


    /* ----- MUTATORS ----- */
    public void setVerticalVelocity(float velocity){
        verticalVelocity = velocity;
    }

    public void setHorizontalVelocity(float velocity){
        horizontalVelocity = velocity;
    }

    /**
     * Sets a new position for player coordinates, sprite, box2D, and camera.
     * @param newPosition the vector for the new position of player
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;
        sprite.setPosition(position.x, position.y);
        box2D.setPosition(position);
    }

}