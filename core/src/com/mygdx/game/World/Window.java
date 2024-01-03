package com.mygdx.game.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Blocks.BlockTextures;
import com.mygdx.game.Components.GameObject;
import com.mygdx.game.Player.Player;
import java.util.ArrayList;

/**
 * The {@code Window} class handles drawing every game objects onto the screen
 *
 */
public class Window {

    public SpriteBatch batch;

    public static OrthographicCamera camera;

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

        batch = new SpriteBatch();

    }


    /**
     *
     */
    public void draw(ArrayList<GameObject> gameObjects){

        batch.begin();

        for(GameObject object : gameObjects)
            object.getSprite().draw(batch);

        batch.end();

        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }


    //Input Function that takes in keyboard interface

}
