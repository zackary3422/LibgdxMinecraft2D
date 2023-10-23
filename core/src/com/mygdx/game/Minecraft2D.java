package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
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
		ScreenUtils.clear(0, 0, 0, 1);

		window.draw(batch);

		window.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
