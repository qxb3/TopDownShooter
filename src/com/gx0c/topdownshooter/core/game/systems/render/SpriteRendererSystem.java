package com.gx0c.topdownshooter.core.game.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class SpriteRendererSystem extends IteratingSystem {
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public SpriteRendererSystem(SpriteBatch batch, OrthographicCamera camera) {
		super(Family.all(SpriteComponent.class).get());

		this.batch = batch;
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		batch.setProjectionMatrix(camera.combined);
		batch.enableBlending();
		batch.begin();

		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		spriteComponent.sprite.draw(batch);

		batch.end();
	}
}
