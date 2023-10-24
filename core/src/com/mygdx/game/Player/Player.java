package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    /** Players coordinates and dimensions*/
    float x, y, width, height;

    /** The sprite of the player to draw it*/
    Sprite sprite;

    /** The keyboard for player keyboard input*/
    Keyboard keyboard;

    /** The camera of the player*/
    OrthographicCamera camera;

    /** The player movement speed*/
    private float playerSpeed = 180;

    /** The speed multiplier when sprinting*/
    private float shiftMultiplier = 1.5f;

    /**
     * Constructs a new Player and initializes variables and sets position of sprite.
     *
     * @param spawnPoint the x and y coordinates for the spawn point of the player
     * @param camera the camera for the player
     */
    public Player(float[] spawnPoint, OrthographicCamera camera){

        this.camera = camera;
        System.out.println(camera.viewportWidth);
        sprite = new Sprite(new Texture("Steve.png"));
        keyboard = new Keyboard();

        //Set player position
        x = spawnPoint[0];
        y = spawnPoint[1];
        setPosition(x, y);

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    /**
     * Executes necessary logic for player class to work. Handles keyboard input.
     */
    public void update(){

        keyboard.update(this);
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
}
