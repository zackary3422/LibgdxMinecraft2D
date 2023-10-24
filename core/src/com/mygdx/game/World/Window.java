package com.mygdx.game.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player.Player;

/**
 * The {@code Window} class draws sprites onto screen and handles game logic.
 *
 */
public class Window {

    WorldBlocks worldBlocks;
    Player player;

    OrthographicCamera camera;

    public static int width, height;


    /**
     * Constructs a window class that will be used for drawing sprites and calling update methods.
     *
     * @param width the width of the window
     * @param height the height of the window
     */
    public Window(int width, int height){

        camera = new OrthographicCamera(width, height);
        camera.position.set(0,0,0);

        worldBlocks = new WorldBlocks();
        player = new Player(worldBlocks.spawnPoint(), camera);

        Window.width = width;
        Window.height = height;
    }

    /**
     * Draws all sprites onto screen
     *
     * @param batch used to draw sprites onto screen
     */
    public void draw(SpriteBatch batch){

        worldBlocks.draw(batch, player);
        player.draw(batch);

        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Calls all necessary update methods
     *
     */
    public void update(){

        player.update();
        worldBlocks.update(player);
        camera.update();

    }



}
