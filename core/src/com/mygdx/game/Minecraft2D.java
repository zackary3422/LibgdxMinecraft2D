package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Blocks.BlockTextures;
import com.mygdx.game.Components.Time;
import com.mygdx.game.Components.DaylightCycle;
import com.mygdx.game.World.Window;

public class Minecraft2D extends ApplicationAdapter {

	SpriteBatch batch;
	Window window;
	int WIDTH;
	int HEIGHT;

	public Minecraft2D(int width, int height){
		WIDTH = width;
		HEIGHT = height;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		window = new Window(WIDTH, HEIGHT);
	}

	@Override
	public void render () {
		ScreenUtils.clear(DaylightCycle.currentDayColor());

		Time.incrementTime();

		window.draw(batch);
		window.update();
		//System.out.println(Gdx.graphics.getFramesPerSecond());

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
