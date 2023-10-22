package com.mygdx.game.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player.Player;

/**
 *
 *
 *
 */

public class Window {

    WorldBlocks worldBlocks;
    Player player;
    OrthographicCamera camera;


    public Window(int width, int height){

        camera = new OrthographicCamera(width, height);
        camera.position.set(0,0,0);

        worldBlocks = new WorldBlocks();
        player = new Player(worldBlocks.spawnPoint(), camera);

    }


    public void draw(SpriteBatch batch){

        worldBlocks.draw(batch);
        player.draw(batch);

    }

    public void update(SpriteBatch batch){
        player.update();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }



}
