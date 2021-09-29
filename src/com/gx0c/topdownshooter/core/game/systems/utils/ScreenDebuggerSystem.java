package com.gx0c.topdownshooter.core.game.systems.utils;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gx0c.topdownshooter.core.game.utils.Resizable;

public class ScreenDebuggerSystem extends EntitySystem implements Resizable, Disposable {
	private static final int WIDTH = Gdx.graphics.getWidth() / 2;
	private static final int HEIGHT = Gdx.graphics.getHeight() / 2;

	private OrthographicCamera camera;
	private Viewport viewport;

	private SpriteBatch batch;
	private BitmapFont font;

	public ScreenDebuggerSystem() {
		camera = new OrthographicCamera();
		camera.position.set(camera.position.x + WIDTH * 0.5f, camera.position.y + HEIGHT * 0.5f, 0f);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0f, 1f, 0f, 1f);
	}

	@Override
	public void update(float deltaTime) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
				  "\n",
				  10, HEIGHT - 10);

		batch.end();
	}

	@Override
	public void resize(int screenWidth, int screenHeight) {
		viewport.update(screenWidth, screenHeight);
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
