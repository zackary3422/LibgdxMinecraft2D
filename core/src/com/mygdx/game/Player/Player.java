package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    /** Players coordinates and dimensions*/
    float x, y, width, height;

    Sprite sprite;
    Keyboard keyboard;
    OrthographicCamera camera;

    /** */
    public Player(float[] spawnPoint, OrthographicCamera camera){

        this.camera = camera;

        sprite = new Sprite(new Texture("Steve.png"));
        keyboard = new Keyboard();

        //Set player position
        x = spawnPoint[0];
        y = spawnPoint[1];
        setPosition(x, y);
    }

    /** */
    public void update(){

        keyboard.update(this);

    }

    /** */
    public void draw(SpriteBatch batch){
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /** */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
        camera.position.set(x, y,0);

    }

}
