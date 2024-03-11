package com.mygdx.game.GameEngine;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.GameEngine.Movement;

import java.util.ArrayList;

public class GameObject {

    /** The x and y coordinates*/
    private Vector2 position;

    /** The width and height*/
    protected Dimension<Float> dimension;

    /** */
    protected Movement movement;

    /** The id list is used to determine the type of game object*/
    public ArrayList<ID> idList;

    /** The boolean for whether this block is collidable or not*/
    private boolean collidable = false;

    /** Determines if object is drawn on screen*/
    private boolean visible = false;

    /** Higher draw priority means it will be drawn in front of other sprites*/
    private int drawPriority = 0;

    /** The sprite to be drawn*/
    protected Sprite sprite;

    /** Gravity applied to objects velocity*/
    float gravity = -20f;

    /** */
    ArrayList<GameObject> collidingObjects;

    /** Determines if the colliding objects list gets updated*/
    boolean updateCollisions = false;


    /* ----- CONSTRUCTORS ----- */

    private GameObject(){

        movement = new Movement();
        idList = new ArrayList<ID>();
        collidingObjects = new ArrayList<GameObject>();

        Engine.add(this);
    }

    /** */
    public GameObject(Vector2 position, Sprite sprite) {

        this();

        setPosition(position);
        this.sprite = sprite;
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());


        makeVisible();
    }

    /** */
    public GameObject(Vector2 position, Sprite sprite, ID id) {

        this();

        idList.add(id);

        this.sprite = sprite;

        setPosition(position);
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        makeVisible();
    }

    /** */
    public GameObject(Vector2 position, Dimension<Float> dimension, ID id) {

        this();

        idList.add(id);

        setPosition(position);
        this.dimension = dimension;
    }


    /* ----- CHILD DEFINED CLASSES ----- */

    /**
     * Where the game objects logic is defined by overriding this function in child classes.
     * Must call {@code enableLogic()} for function to start being updated
     */
    public void logic(){}

    /**
     * Where the game objects input is defined by overriding this function in child classes.
     * Must call {@code enableInput()} for function to start being updated
     */
    public void input(){}


    /* ----- ENGINE CONTROL METHODS ----- */

    /** */
    public void enableLogic(){
        Engine.addLogic(this);
    }

    /** */
    public void enableInput(){
        Engine.addInput(this);
    }

    /** */
    public void disableLogic(){}

    /** */
    public void disableInput(){}

    /** */
    public void makeCollidable(){

        if(!collidable) {
            collidable = true;
            Engine.makeCollidable(this);
        }
    }

    /** */
    public void updateCollisions(){

        updateCollisions = true;

        Engine.addObjectCollider(this);

    }

    /** */
    public void makeVisible(){

        if(!visible && sprite != null) {
            visible = true;
            Engine.startDrawing(this);
        }

    }

    /** */
    public void makeInvisible(){

        if(visible) {
            visible = false;
            Engine.stopDrawing(this);
        }
    }

    /** */
    public void setDrawPriority(int newPriority){

        drawPriority = newPriority;

        Engine.updateDrawPriority(this);
    }



    /* ----- ID METHODS ----- */

    /** */
    public void addID(ID id){
        idList.add(id);
    }

    /** */
    public void removeID(ID id){
        idList.remove(id);
    }

    /** */
    public boolean hasMatchingID(ID id){

        for(ID idElement : idList)
            if(idElement.equals(id))
                return true;

        return false;
    }




    /* ----- ACCESSORS ----- */

    /** *///PASSED BY REFERENCE?
    public Vector2 getPosition(){
        return position;
    }

    /** */
    public Sprite getSprite(){
        return sprite;
    }

    /** */
    public boolean isVisible(){
        return visible;
    }

    /** */
    public Dimension<Float> getDimension(){
        return dimension;
    }

    /** @return the center x-position of block*/
    public float getCenterX(){return position.x + (dimension.width / 2);}

    /** @return the center y-position of block*/
    public float getCenterY(){return position.y + (dimension.height / 2);};

    /** */
    public String toString(){
        return "toString Not Defined";
    }

    /** */
    public int getDrawPriority(){
        return drawPriority;
    }

    /** */
    public boolean isCollidable(){
        return collidable;
    }


    /* ----- MUTATORS ----- */

    /**
     * Sets a new position for the block and sets a new position for sprite
     * @param newPosition new Vector2 position
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;

        if(sprite != null)
            sprite.setPosition(position.x, position.y);
    }

    /** */



}
