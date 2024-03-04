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

        gameObjects = new ArrayList<>();
        objectsWithInput = new ArrayList<>();
        objectsWithLogic = new ArrayList<>();
        objectsToBeDrawn = new ArrayList<>();
        objectColliders = new ArrayList<>();

        players = new ArrayList<>();

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

        updateCollisions();

        Time.incrementTime();

        System.out.println(Gdx.graphics.getFramesPerSecond());
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
        if(objectsToBeDrawn.isEmpty())
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

    /** */
    public static void makeCollidable(GameObject object){

        if(object == null)
            throw new NullPointerException("Can't add null object to engine colliders list");

        objectColliders.add(object);
    }

    /**
     * Detects all collisions and informs each of the game objects with a list of all objects colliding with it.
     */
    public static void updateCollisions(){

        //Clear out all colliding objects lists
        for(GameObject object : objectColliders)
            object.collidingObjects.clear();

        int k = 0;

        int size = objectColliders.size();

        GameObject currentObject;
        GameObject temp2;

        //Loop through all objects and update their colliding objects list
        for(int i = 0; i < size-1; i++){

            currentObject = objectColliders.get(i);

            //Update current objects list
            if(currentObject.updateCollisions)
                for(int j = i+1; j < size; j++){
                    temp2 = objectColliders.get(j);

                    if(Box2D.isColliding(currentObject, temp2)){
                        currentObject.collidingObjects.add(temp2);

                        if(temp2.updateCollisions)
                            temp2.collidingObjects.add(currentObject);
                        k++;
                    }
                }
            //Only update others objects list
            else
                for(int j = i+1; j < size; j++){
                    temp2 = objectColliders.get(j);

                    if(temp2.updateCollisions)
                        if(Box2D.isColliding(currentObject, temp2))
                            temp2.collidingObjects.add(currentObject);
                    k++;
                }



            //Fill up the colliding objects

            //Send results to current object

            //Might need two lists. One for objects that are just collidable. One for the objects the have logic when colliding?

            /*
            * So we take one objects and find all blocks colliding with it and send results to that object.
            * Then we add that object to list of colliding objects of all the objects it was colliding with.
            * Then we remove that object from the list because we found all colliding objects with it and notified all the objects colliding with it.
            * Should optimize it by a lot
            * DON'T CREATE A NEW LIST. JUST ITERATE THROUGH EACH ONE INTEAD OF DELETING. IT WILL TAKE CARE OF ITESELF
            * */

        }

        System.out.println(k);


    }



    /** Dispose of all resources*/
    public void dispose(){
        window.batch.dispose();
    }

}
