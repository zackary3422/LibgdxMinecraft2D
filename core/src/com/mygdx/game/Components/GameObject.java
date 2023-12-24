package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GameObject {

    /** The x and y coordinates*/
    private Vector2 position;

    /** The width and height*/
    private Dimension<Float> dimension;

    /** The ID for the game object*/
    private int ID;

    /** The boolean for whether this block is collidable or not*/
    private boolean collidable;


    private boolean isVisible;

    /** The box collider to detect collisions*/
    public Box2D box2D;

    /** The sprite used to represent the stone block image*/
    private Sprite sprite;

    public GameObject(Vector2 position, Sprite sprite, boolean collidable, int ID) {



    }
}
