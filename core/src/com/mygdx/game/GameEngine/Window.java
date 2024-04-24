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
import com.mygdx.game.Blocks.Block;

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

    /** */
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
     * Draws all-game objects onto the screen
     */
    public void draw(ArrayList<GameObject> gameObjects){

        batch.begin();

        //Draw all objects in the list to screen
        for(GameObject object : gameObjects)
            if (object.isVisible() && inView(object))
                object.getSprite().draw(batch);

        //Draw FPS counter
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), camera.position.x - 470, camera.position.y + 350);

        batch.end();

        //Update camera
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    /**
     * Takes in a object and returns whether that object is within the cameras view.
     */
    public static boolean inView(GameObject object){

        //Get objects coordinates
        float objectLeftX = object.getPosition().x;
        float objectRightX = object.getPosition().x + object.getDimension().width;
        float objectBottomY = object.getPosition().y;
        float objectTopY = object.getPosition().y + object.getDimension().height;

        //Small offset to some objects out of view, so they are already loaded for when the player moves
        float offset = 45;

        //Get cameras coordinates
        float cameraLeftX = camera.position.x - (camera.viewportWidth / 2) - offset;
        float cameraRightX = camera.position.x + (camera.viewportWidth / 2) + offset;
        float cameraBottomY = camera.position.y - (camera.viewportHeight / 2) - offset;
        float cameraTopY = camera.position.y + (camera.viewportHeight / 2) + offset;

        //Find which parts of the object is the player seeing
        boolean xOverlap = Box2D.isOverlapping(objectLeftX, objectRightX, cameraLeftX, cameraRightX);
        boolean yOverlap = Box2D.isOverlapping(objectBottomY, objectTopY, cameraBottomY, cameraTopY);

        return xOverlap && yOverlap;
    }






}
