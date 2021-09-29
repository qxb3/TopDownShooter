package com.gx0c.topdownshooter.core.game.systems.render;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.utils.Resizable;

public class HudSystem extends EntitySystem implements Resizable, Disposable {
	private OrthographicCamera camera;
	private Viewport viewport;
	private Stage stage;

	public HudSystem() {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(264, 132, camera);
		stage = new Stage(viewport);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void update(float deltaTime) {
		stage.act(deltaTime);
		stage.draw();
	}

	@Override
	public void resize(int screenWidth, int screenHeight) {
		viewport.update(screenWidth, screenHeight);
	}

	@Override
	public void addedToEngine(Engine engine) {
		for (Entity entity : engine.getEntitiesFor(Family.all(PlayerComponent.class).get())) {
			PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);

			Table table = new Table();
			table.setFillParent(true);
			table.pad(8);

			table.add(playerComponent.movementTouchpad).size(42, 42).bottom().left().expand();
			table.add(playerComponent.shootTouchpad).size(42, 42).bottom().right().expand();

			stage.addActor(table);
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
