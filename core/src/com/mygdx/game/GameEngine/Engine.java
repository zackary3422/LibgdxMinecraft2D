package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Components.GameObject;
import com.mygdx.game.Components.Time;
import com.mygdx.game.Player.Player;
import com.mygdx.game.World.Window;

import java.util.ArrayList;


public class Engine {


    /** */
    public Window window;

    /** */
    ArrayList<Input> inputs;

    /** Collisions*/

    //Objects to be drawn
    ArrayList<GameObject> gameObjects;

    ArrayList<Player> players;



    public Engine(int width, int height){

        window = new Window(width, height);

    }

    /** */
    public void update(){

        window.draw(gameObjects);




        Time.incrementTime();

        System.out.println(Gdx.graphics.getFramesPerSecond());
    }




    public void add(GameObject object){
        gameObjects.add(object);
    }

    public void add(Player player){
        players.add(player);
    }

    public void delete(GameObject object) {
        if(!gameObjects.remove(object))
            System.out.println("ERROR: game object not found within window class");
    }

}
