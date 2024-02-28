package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Blocks.DirtBlock;

import java.util.ArrayList;


/**
 *
 */
public class Engine {


    /** */
    public static Window window;

    /** List of all game objects within game*/
    public static ArrayList<GameObject> gameObjects;

    /** */
    public static ArrayList<Player> players;

    /** List of game objects that will be drawn to screen*/
    public static ArrayList<GameObject> objectsToBeDrawn;

    /** Objects with defined input*/
    public static ArrayList<GameObject> objectsWithInput;

    /** Objects with defined logic to be updated */
    public static ArrayList<GameObject> objectsWithLogic;

    /** */
    public static ArrayList<GameObject> objectColliders;

    /** */
    public Engine(int width, int height){

        gameObjects = new ArrayList<GameObject>();
        objectsWithInput = new ArrayList<GameObject>();
        objectsWithLogic = new ArrayList<GameObject>();
        objectsToBeDrawn = new ArrayList<GameObject>();

        players = new ArrayList<Player>();

        window = new Window(width, height);
    }

    /* ----- ENGINE UPDATERS ----- */

    /** */
    public void update(){

        //Draw every game object onto the screen
        window.draw(objectsToBeDrawn);

        //Updates all game objects input
        inputUpdate();

        //Updates all game objects logic
        logicUpdate();

        Time.incrementTime();

       // System.out.println(Gdx.graphics.getFramesPerSecond());
    }

    /** */
    public void inputUpdate(){

        for(GameObject input : objectsWithInput)
            input.input();

    }

    /** */
    public void logicUpdate(){
        for(GameObject object : objectsWithLogic)
            object.logic();
    }


    /* ----- LIST CONTROL METHODS ----- */

    /** */
    public static void add(GameObject object){
        gameObjects.add(object);
    }

    /** */
    public static void addInput(GameObject object){
        objectsWithInput.add(object);
    }

    /** */
    public static void addLogic(GameObject object){
        objectsWithLogic.add(object);
    }

    /** */
    public static void startDrawing(GameObject object){

        if(object.sprite == null)
            return;

        int size = objectsToBeDrawn.size();

        //Insert object into list based on priority drawing
        for(int i = 0; i < size; i++){

            if(object.getDrawPriority() <= objectsToBeDrawn.get(i).getDrawPriority()) {
                objectsToBeDrawn.add(i, object);
                return;
            }
            else if(i == size-1){
                objectsToBeDrawn.add(object);
                return;
            }
        }

        objectsToBeDrawn.add(object);


    }

    /**
     * Reinsert object with its new draw priority back into the draw list
     * @param object the object that will be reinserted back into the objectsToBeDrawn list
     * */
    public static void updateDrawPriority(GameObject object) {

        try {
            //Remove object from drawing list and don't update
            if (!objectsToBeDrawn.remove(object))
                throw new Exception("ERROR: Can't update draw priority. Object not in the objectsToBeDrawn list");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }

        //If list is empty
        if(objectsToBeDrawn.size() == 0)
            objectsToBeDrawn.add(object);

        int size = objectsToBeDrawn.size();

        //Insert object into list based on priority drawing
        for(int i = 0; i < size; i++){

            if(object.getDrawPriority() <= objectsToBeDrawn.get(i).getDrawPriority()) {
                objectsToBeDrawn.add(i, object);
                return;
            }
            else if(i == size-1){
                objectsToBeDrawn.add(object);
                return;
            }
        }

    }

    /** */
    public static void stopDrawing(GameObject object){
        objectsToBeDrawn.remove(object);
    }

    /** Removes game object from all Engine lists to be collected by garbage collector*/
    public static void delete(GameObject object) {
        gameObjects.remove(object);
        objectsWithInput.remove(object);
        objectsWithLogic.remove(object);
        objectsToBeDrawn.remove(object);
    }

    /** Set focus of camera on game object? */
    public void setFocus(GameObject objectFocus){

    }

    /**
     * Detects all collisions and informs each of the game objects with a list of all objects colliding with it.
     */
    public static void updateCollisions(){


        for(GameObject object : objectColliders){



        }


    }



    /** Dispose of all resources*/
    public void dispose(){
        window.batch.dispose();
    }

}
