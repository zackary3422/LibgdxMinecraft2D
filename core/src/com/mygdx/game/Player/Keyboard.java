package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Blocks.BlockCollisions;
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

        //SPACE Key
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !BlockCollisions.bottomCollisions(player.collidingBlocks, player.box2D).isEmpty()){
            player.setVerticalVelocity(Player.jumpForce);
            player.setPosition(new Vector2(player.getX(), player.getY() + deltaSpeed));
        }

        //D Key
        if(Gdx.input.isKeyPressed(Input.Keys.D) && BlockCollisions.rightCollisions(player.collidingBlocks, new Box2D(new Vector2(player.getX(), player.getY()+2), new Dimension(player.getDimension().width,player.getDimension().height-2))).isEmpty())
            player.setPosition(new Vector2(player.getX() + deltaSpeed, player.getY()));

        //A Key
        if(Gdx.input.isKeyPressed(Input.Keys.A) && BlockCollisions.leftCollisions(player.collidingBlocks, new Box2D(new Vector2(player.getX(), player.getY()+2), new Dimension(player.getDimension().width,player.getDimension().height-2))).isEmpty())
            player.setPosition(new Vector2(player.getX() - deltaSpeed, player.getY()));

        //S Key
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.setPosition(new Vector2(player.getX(), player.getY() - deltaSpeed));

    }





}
