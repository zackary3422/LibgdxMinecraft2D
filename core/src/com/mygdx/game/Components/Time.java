package com.mygdx.game.Components;

import com.badlogic.gdx.Gdx;

public class Time {

    /** The time in one day (seconds)*/
    public static final float timeInDay = 600;
    public static float currentTime;

    /**
     *
     *  */
    public static void incrementTime(){
        currentTime += Gdx.graphics.getDeltaTime();
        currentTime = currentTime >= timeInDay ? currentTime % timeInDay : currentTime;
    }

    /**
     *
     *  */
    public static float getCurrentTime(){
        return currentTime;
    }

}
