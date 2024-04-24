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
    private float playerSpeed = 300;

    /*** The speed multiplier when sprintin */
    private float shiftMultiplier = 1.9f;

    /*** The vertical force applied when jumping*/
    static final float jumpForce = 250f;


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

        //Move player out of blocks
        // collisionUpdate();

    }

    /**
     *
     */
    @Override
    public void input() {

        for(GameObject object : collidingObjects)
            setPosition(Box2D.getCollisionFreePos(this, object));


        //Key input with collision prevention
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Box2DFilter.getRightCollisions(collidingObjects, this).isEmpty())
            setPosition(Movement.move(getPosition(), Movement.Direction.RIGHT, playerSpeed));

        if (Gdx.input.isKeyPressed(Input.Keys.A) && Box2DFilter.getLeftCollisions(collidingObjects, this).isEmpty())
            setPosition(Movement.move(getPosition(), Movement.Direction.LEFT, playerSpeed));

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !Box2DFilter.getBottomCollisions(collidingObjects, this).isEmpty())
            movement.verticalVelocity = jumpForce;

        if(Gdx.input.isTouched())
            leftMouseClick();

        collisionUpdate();

        Window.camera.position.set(getPosition().x, getPosition().y, 0);

        //Update the camera position smoothly
        //float cameraX = MathUtils.lerp(Window.camera.position.x, getPosition().x, 0.1f);
        //float cameraY = MathUtils.lerp(Window.camera.position.y, getPosition().y, 0.1f);
        //Window.camera.position.set(cameraX, cameraY, 0);
    }

    /**
     * Move player out of block using the SAT to separate block based on the shortest axis it would take
     * to move a colliding object out of another object.
     */
    public void collisionUpdate() {


        //Update collisions list
        Engine.updateCollisions();

        for(GameObject object : collidingObjects)
            setPosition(Box2D.getCollisionFreePos(this, object));

        //Apply velocities
        setPosition(movement.move(getPosition()));

        //gravity
        movement.verticalVelocity -= Movement.getDeltaSpeed(gravity);

        //Update collisions list
        Engine.updateCollisions();

        //Update position based on bottom collision
        ArrayList<GameObject> bottomCollisions = Box2DFilter.getBottomCollisions(collidingObjects, this);

        //Pushed player up
        for(GameObject object : collidingObjects) {
            if(Box2D.axisPushDirection(this, object) == Movement.Direction.UP) {
                setPosition(new Vector2(getPosition().x, bottomCollisions.get(0).getTopY()));
                movement.verticalVelocity = 0;
            }
        }


    }

    public void leftMouseClick(){


        //Get mouse coordinates
        float mouseX = Gdx.input.getX() - ((float) Gdx.graphics.getWidth() / 2);
        float mouseY = ((float) Gdx.graphics.getHeight() / 2);

        if(Gdx.input.getY() < Gdx.graphics.getHeight() / 2)
            mouseY += (float) Gdx.graphics.getHeight() / 2 - Gdx.input.getY();
        else
            mouseY +=  -1 * ((float) Gdx.graphics.getHeight() / 2 - Gdx.input.getY());

        mouseX += Window.camera.position.x;
        mouseY += Window.camera.position.y;


        //System.out.println("MOUSE X: " + mouseX);
        System.out.println("MOUSE Y: " + mouseY);
        System.out.println("CAMERAY: " + Window.camera.position.y);

        //Convert mouse coordinates to level coordinates


    }


    public String toString(){
        return "Player Object";
    }

}
