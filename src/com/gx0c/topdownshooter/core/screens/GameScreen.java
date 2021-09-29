package com.gx0c.topdownshooter.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.GameEngine;

public class GameScreen extends ScreenAdapter {
	private GameEngine engine;

	public GameScreen(Game game) {
		engine = new GameEngine(game);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0f, 0f, 1f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(deltaTime);
	}

	@Override
	public void resize(int screenWidth, int screenHeight) {
		engine.resize(screenWidth, screenHeight);
	}

	@Override
	public void dispose() {
		engine.dispose();
	}
}
