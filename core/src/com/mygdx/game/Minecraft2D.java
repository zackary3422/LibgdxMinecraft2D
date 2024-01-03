package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.DaylightCycle;
import com.mygdx.game.GameEngine.Engine;

public class Minecraft2D extends ApplicationAdapter {


	Engine engine;

	public Minecraft2D(int width, int height){

		World world;

		engine.addPlayer(input(), sprite);

	}
	
	@Override
	public void create () {

		engine = new Engine(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	}

	@Override
	public void render () {
		ScreenUtils.clear(DaylightCycle.currentDayColor());

		engine.update();

	}
	
	@Override
	public void dispose () {

		engine.window.batch.dispose();

	}


}
