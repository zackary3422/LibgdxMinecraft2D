package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Keyboard {

    /**
     *
     *
     */
    public void update(Player player){

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setPosition(player.x, player.y + 4f);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setPosition(player.x + 4f, player.y);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.setPosition(player.x - 4, player.y);

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.setPosition(player.x, player.y - 4);

    }



}
