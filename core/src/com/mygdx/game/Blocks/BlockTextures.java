package com.mygdx.game.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Components.Block;

public class BlockTextures {


    public static Texture grassBlock;
    public static Texture stoneBlock;
    public static Texture dirtBlock;




    public static void initBlockTextures(){

        grassBlock = new Texture("GrassBlock.jpg");
        stoneBlock = new Texture("StoneBlock.png");
        dirtBlock = new Texture("DirtBlock.jpg");




    }

    public static Texture getGrassBlock(){
        return grassBlock;
    }

    public static Texture getStoneBlock(){
        return stoneBlock;
    }

    public static Texture getDirtBlock(){
        return dirtBlock;
    }



}
