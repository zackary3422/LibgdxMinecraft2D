package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
/**
 * The {@code Window} class handles drawing every game objects onto the screen
 *
 */
public class Window {

    /** */
    public SpriteBatch batch;

    /** */
    public static OrthographicCamera camera;

    /** */
    public Dimension<Float> viewPort;

    /** */
    public static int width, height;

    private BitmapFont font;


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

        font = new BitmapFont();

        //Setting window dimension
        Window.width = width;
        Window.height = height;

        viewPort = new Dimension<Float>(camera.viewportWidth, camera.viewportHeight);

        batch = new SpriteBatch();

    }

    /**
     * Draws all game objects onto the screen
     */
    public void draw(ArrayList<GameObject> gameObjects){

        batch.begin();

        //Draw all objects in list to screen
        for(GameObject object : gameObjects)
            if(object.isVisible() /*&& /*inCameraView(object)*/) {
                object.getSprite().draw(batch);
            }

        //Draw FPS counter
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), camera.position.x - 470, camera.position.y + 350);

        batch.end();

        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    /**
     *
     */
    public boolean inCameraView(GameObject object){

        Vector2 cameraPosition = new Vector2(camera.position.x, camera.position.y);

        //Get objects position and dimension
        Vector2 position = object.getPosition();
        Dimension<Float> dimension = object.getDimension();

        //Get parameters values with a offset value
        float width2 = viewPort.width + 20;
        float height2 = viewPort.height + 50;
        float x2 = cameraPosition.x - width2 / 2;
        float y2 = cameraPosition.y - height2 / 2;


        //Find which parts of block is the player seeing
        boolean xOverlap = (position.x <= x2 + width2 && position.x >= x2) ||
                (position.x + dimension.width <= x2 + width2 && position.x + dimension.width >= x2);
        boolean yOverlap = (position.y <= y2 + height2 && position.y >= y2) ||
                (position.y + dimension.height <= y2 + height2 && position.y + dimension.height >= y2);

        return xOverlap && yOverlap;
    }






}
