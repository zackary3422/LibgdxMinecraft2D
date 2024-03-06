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
    private static ArrayList<GameObject> gameObjects;

    /** */
    public static ArrayList<Player> players;

    /** List of game objects that will be drawn to screen*/
    private static ArrayList<GameObject> objectsToBeDrawn;

    /** Objects with defined input*/
    private static ArrayList<GameObject> objectsWithInput;

    /** Objects with defined logic to be updated */
    private static ArrayList<GameObject> objectsWithLogic;

    /** Objects in list will have their colliding objects list updated*/
    private static ArrayList<GameObject> objectColliders;

    /** Objects in list will be able to be collided with*/
    private static ArrayList<GameObject> objectCollidables;

    /** */
    public Engine(int width, int height){

        gameObjects = new ArrayList<>();
        objectsWithInput = new ArrayList<>();
        objectsWithLogic = new ArrayList<>();
        objectsToBeDrawn = new ArrayList<>();
        objectColliders = new ArrayList<>();
        objectCollidables = new ArrayList<>();

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

        objectCollidables.add(object);
    }

    /** */
    public static void addObjectCollider(GameObject object){
        if(object == null)
            System.out.println("ERROR: NULL POINTER EXCEPTION when trying to add object to update colliders list");

        objectColliders.add(object);
    }

    /**
     * Detects all collisions and informs each of the game objects with a list of all objects colliding with it.
     */
    public static void updateCollisions(){

        //Clear out colliding objects list
        for(GameObject object : objectColliders)
            object.collidingObjects.clear();


        //Add Spacial partitioning here
        //Arraylist of arraylist

        //Adds colliding objects to object colliders, colliding lists
        for(GameObject objectCollider : objectColliders)

            for(GameObject collidableObject : objectCollidables) {
                if (Box2D.isColliding(objectCollider, collidableObject) && objectCollider != collidableObject)
                    objectCollider.collidingObjects.add(collidableObject);
            }




    }



    /** Dispose of all resources*/
    public void dispose(){
        window.batch.dispose();
    }

}
