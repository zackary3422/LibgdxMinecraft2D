package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameEngine.DaylightCycle;
import com.mygdx.game.GameEngine.Engine;
import com.mygdx.game.GameEngine.Player;
import com.mygdx.game.World.World;

public class Minecraft2D extends ApplicationAdapter {


	Engine engine;
	World world;

	public Minecraft2D(int width, int height){

	}
	
	@Override
	public void create () {

		engine = new Engine(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		world = new World();

		Engine.players.add(new Player(new Sprite(new Texture("Steve.png")), world.getSpawnPoint()));
	}

	@Override
	public void render () {
		ScreenUtils.clear(DaylightCycle.currentDayColor());

		engine.update();
		world.worldExpansion(Engine.players.get(0));

	}
	
	@Override
	public void dispose () {

		engine.dispose();
	}

}