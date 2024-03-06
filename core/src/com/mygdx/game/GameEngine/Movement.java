package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Movement {

    /** */
    public enum Direction {LEFT, RIGHT, UP, DOWN}

    /** */ //<-------------------- DELETE THIS OR REFACTOR IT OR SOMETHING
    public float horizontalVelocity;

    /** */
    public float verticalVelocity;

    /** */
    public Movement(){
        horizontalVelocity = 0;
        verticalVelocity = 0;
    }

    /** */
    public Movement(float horizontalVelocity, float verticalVelocity){
        this.horizontalVelocity = horizontalVelocity;
        this.verticalVelocity = verticalVelocity;
    }

    /** */
    public Vector2 move(Vector2 position){
        return new Vector2(position.x + horizontalVelocity, position.y + verticalVelocity);
    }

    /** */
    public static Vector2 move(Vector2 position, float angle, float velocity){

        float newX = position.x + getDeltaSpeed((float)(Math.cos(angle) * velocity));

        float newY = position.y + getDeltaSpeed((float)(Math.sin(angle) * velocity));

        return new Vector2(newX, newY);
    }

    /** */
    public static Vector2 move(Vector2 position, Direction direction, float velocity){

        float angle = 0;

        switch(direction){
            case LEFT:
                angle = (float)Math.PI;
                break;
            case RIGHT:
                angle = 0;
                break;
            case UP:
                angle = (float) (Math.PI / 2);
                break;
            case DOWN:
                angle = (float) ((Math.PI * 3) / 2);
                break;
        }

        float newX = position.x + getDeltaSpeed((float)(Math.cos(angle) * velocity));

        float newY = position.y + getDeltaSpeed((float)(Math.sin(angle) * velocity));

        return new Vector2(newX, newY);
    }

    /** */
    public static float getDeltaSpeed(float speed){
        return speed * Gdx.graphics.getDeltaTime();
    }


}
