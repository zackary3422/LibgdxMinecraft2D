package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {

    /**
     * Checks for player keyboard input
     *
     * @param player reference to player to change position
     */
    public void update(Player player){

        //Gets accurate player speed based on delta time
        float deltaSpeed = (Gdx.graphics.getDeltaTime() * player.getPlayerSpeed());

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            deltaSpeed *= player.getShiftMultiplier();


        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setVerticalVelocity(Player.jumpForce);
            player.setPosition(player.x, player.y + deltaSpeed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setPosition(player.x + deltaSpeed, player.y);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.setPosition(player.x - deltaSpeed, player.y);

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.setPosition(player.x, player.y - deltaSpeed);

    }



}
