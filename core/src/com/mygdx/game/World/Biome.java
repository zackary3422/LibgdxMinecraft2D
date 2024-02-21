package com.mygdx.game.World;

import com.mygdx.game.GameEngine.Movement;
import com.mygdx.game.World.Chunks.Chunk;

import java.util.ArrayList;
import java.util.Random;

public abstract class Biome {

    /** The types of biomes in the world*/
    public enum BiomeTypes {FOREST, MOUNTAIN, DESSERT}


    //SPAWN RATE PROPERTIES

    public int topLayer;

    /** The minimum number of chunks a biome needs before switching to a new one*/
    public static final int minBiomeChunkSize = 6;




    //MAYBE USE A PARALLEL ARRAY FOR ITEMS AND SPAWN RATES


    //TERRAIN PROPERTIES


    public float biomeSpawnChance;


    public BiomeTypes getRandomBiome(){
        return null;
    }

    /**
     * Generates a random biome and return it.
     * @return the random biome
     */
    public static Biome.BiomeTypes randomBiome(){

        Random rand = new Random();
        float chance;


        return Biome.BiomeTypes.FOREST;
    }


    /**
     * Generates a biome type based on previous biome type in chunks list.
     * @param chunks the list of chunks used to see previous biome types
     * @param direction the direction of where the biome should be placed
     */
    public static Biome.BiomeTypes generateBiome(ArrayList<Chunk> chunks, Movement.Direction direction){

        int currentBiomeSize = 0;

        //Determine the previous biome
        Biome.BiomeTypes currentBiome = direction == Movement.Direction.LEFT ? chunks.get(0).getBiome() : chunks.get(chunks.size()-1).getBiome();

        //Get current biome size
        for(int i = chunks.size()-1; i >= 0; i--)
            if(chunks.get(i).getBiome() == currentBiome)
                currentBiomeSize++;


        //Generate new biome if minimum biome chunk size has been met or return previous biome if not
        if(currentBiomeSize > minBiomeChunkSize)
            return randomBiome();
        else
            return currentBiome;

    }


}
