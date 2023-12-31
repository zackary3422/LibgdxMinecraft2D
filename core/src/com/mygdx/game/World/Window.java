package com.mygdx.game.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Blocks.BlockTextures;
import com.mygdx.game.Player.Player;

/**
 * The {@code Window} class draws sprites onto screen and handles game logic.
 *
 */
public class Window {

    World world;
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

        //Camera initialize
        camera = new OrthographicCamera(width, height);
        camera.position.set(0,0,0);

        //Setting window dimension
        Window.width = width;
        Window.height = height;

        //Initializing objects
        world = new World();
        player = new Player(world.spawnPoint(), camera);

    }

    /**
     * Draws all sprites onto screen
     *
     * @param batch used to draw sprites onto screen
     */
    public void draw(SpriteBatch batch){

        world.draw(batch, player);
        player.draw(batch);


        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Calls all necessary update methods
     *
     */
    public void update(){

        player.update();
        world.update(player);
        camera.update();

    }



}
