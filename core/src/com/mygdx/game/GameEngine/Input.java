package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;

public abstract class Input {

    //Probs gonna delte this class


    /** The game object for the input to act upon*/
    protected GameObject target;

    public void setTarget(GameObject object){
        this.target = object;
    }

    public abstract void update();

    /* ----- PRESSING KEYS ----- */

    public boolean pressing_W(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W);
    }

    public boolean pressing_A(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A);
    }

    public boolean pressing_S(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S);
    }

    public boolean pressing_D(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D);
    }

    public boolean pressing_LEFT_SHIFT(){
        return Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.SHIFT_LEFT);
    }

    /* ----- JUST PRESSED KEYS ----- */

    public boolean justPressed_SPACE(){
        return Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE);
    }

    public boolean justPressed_MOUSE(){
        return Gdx.input.justTouched();
    }
}
