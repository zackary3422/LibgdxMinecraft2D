package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World.BlockCollisions;
import com.mygdx.game.Components.Box2D;
import com.mygdx.game.Components.Dimension;

public class Keyboard {

    /**
     * Checks for player keyboard input
     *
     * @param player reference to player to change position
     */
    public void keyboardInput(Player player){

        //Gets accurate player speed based on delta time
        float deltaSpeed = (Gdx.graphics.getDeltaTime() * player.getPlayerSpeed());

        //Shift Key
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            deltaSpeed *= player.getShiftMultiplier();


        /* ----- Keys ----- */


        //SPACE Key
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.box2D.bottomCollision(player.box2D)){
            player.setVerticalVelocity(Player.jumpForce);
        }


        //A Key
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.setHorizontalVelocity(-1 * deltaSpeed);

        //D Key
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            player.setHorizontalVelocity(deltaSpeed);


    }

    public boolean isMovementKeysPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D);
    }

}
