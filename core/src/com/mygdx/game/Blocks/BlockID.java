package com.mygdx.game.Blocks;

public class BlockID {


    /* ----- BLOCK ID'S ----- */

    public static final int EMPTYBLOCK = 0;

    public static final int GRASSBLOCK = 1;

    public static final int DIRTBLOCK = 2;

    public static final int STONEBLOCK = 3;


    public static boolean isTopLayer(int ID){
        return (ID == GRASSBLOCK);
    }


}


