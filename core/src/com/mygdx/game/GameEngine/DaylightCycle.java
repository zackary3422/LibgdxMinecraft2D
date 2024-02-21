package com.mygdx.game.GameEngine;

import com.badlogic.gdx.graphics.Color;

public class DaylightCycle {

    static final float PI = (float)Math.PI;

    /** Daylight times in radians*/
    public static final float[] dayTime = {0, (8*PI) / 15,                         (7*PI) / 5, 2 * PI};
    /** Nighttime in radians*/
    public static final float[] nightTime =               {(8*PI) / 15, (7*PI) / 5};

    //Day Time: 0 -8 21- 30
    // Night Time: 9 - 21

    static public Color currentDayColor(){

        float radianTime = Time.getCurrentTime() * (float)((2 * PI) / Time.timeInDay);

        float cosTime = Math.min((float) Math.cos(radianTime) + 0.3f, 1f);

        float red = 0.398f * cosTime;
        float green = 0.800f *  cosTime;
        float blue = 0.900f *  cosTime;


        return new Color(red,green,blue, 0.3f);
    }

}
