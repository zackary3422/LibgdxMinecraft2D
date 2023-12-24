package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;

public class Input {

    /** The player movement speed*/
    private float playerSpeed = 200;

    /** The speed multiplier when sprinting*/
    private float shiftMultiplier = 1.9f;

    /** The vertical force applied when jumping*/
    static final float jumpForce = 7f;

    /**
     * Checks for player input
     *
     * @param player reference to player to change position
     */
    public void keyboardInput(Player player){

        //Gets accurate player speed based on delta time
        float deltaSpeed = (Gdx.graphics.getDeltaTime() * player.getPlayerSpeed());

        //Shift Key
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.SHIFT_LEFT))
            deltaSpeed *= player.getShiftMultiplier();


        /* ----- Keys ----- */

        //SPACE Key
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE) && player.box2D.bottomCollision(player.box2D)){
            player.setVerticalVelocity(Player.jumpForce);
        }


        //A Key
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A))
            player.setHorizontalVelocity(-1 * deltaSpeed);

        //D Key
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D))
            player.setHorizontalVelocity(deltaSpeed);


    }

    public boolean isMovementKeysPressed(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D);
    }

}
