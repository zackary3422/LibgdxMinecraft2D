package com.mygdx.game.Components;

public class GameMath {

    public static float getOverlap(float min1, float max1, float min2, float max2){

        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
    }

}
