package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

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

        //W Key
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            player.setVerticalVelocity(Player.jumpForce);
            player.setPosition(new Vector2(player.getX(), player.getY() + deltaSpeed));
        }

        //D Key
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            player.setPosition(new Vector2(player.getX() + deltaSpeed, player.getY()));

        //A Key
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.setPosition(new Vector2(player.getX() - deltaSpeed, player.getY()));

        //S Key
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.setPosition(new Vector2(player.getX(), player.getY() - deltaSpeed));

    }





}
