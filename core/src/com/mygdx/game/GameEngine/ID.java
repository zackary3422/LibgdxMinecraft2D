package com.mygdx.game.GameEngine;

public class ID {

    private long sequentialID;

    private static long currentSequentialID = 0;


    public ID() {
        assignSequentialID();
    };

    public void assignSequentialID(){
        sequentialID = currentSequentialID;
        currentSequentialID++;
    }

    public void copy(ID id){
        sequentialID = id.sequentialID;
    }

    public boolean equals(ID id){
        return sequentialID == id.sequentialID;
    }
}
