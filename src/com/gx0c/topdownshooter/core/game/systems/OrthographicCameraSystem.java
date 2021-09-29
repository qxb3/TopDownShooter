package com.gx0c.topdownshooter.core.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.systems.render.TiledMapRendererSystem;
import java.util.ArrayList;

public class OrthographicCameraSystem extends EntitySystem {
	private OrthographicCamera camera;
	
	private Body playerBody;
	private float transformedMapWidth, transformedMapHeight;
	private ArrayList<Rectangle> cameraInfos;
	
	public OrthographicCameraSystem(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void update(float deltaTime) {
		float deltaX = camera.position.x - playerBody.getPosition().x;
		float deltaY = camera.position.y - playerBody.getPosition().y;
		float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		Vector3 lerp = camera.position.lerp(new Vector3(playerBody.getPosition().x, playerBody.getPosition().y, 0f), deltaTime + distance / 3.4f);
		camera.position.x = MathUtils.clamp(lerp.x, camera.viewportWidth / 2, transformedMapWidth - camera.viewportWidth / 2);
		camera.position.y = MathUtils.clamp(lerp.y, camera.viewportHeight / 2, transformedMapHeight - camera.viewportHeight / 2);
		camera.update();
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		Entity playerEntity = engine.getEntitiesFor(Family.all(PlayerComponent.class, SpriteComponent.class, BodyComponent.class).get()).get(0);
		playerBody = playerEntity.getComponent(BodyComponent.class).body;
		playerBody.setTransform(engine.getSystem(TiledMapRendererSystem.class).getPlayerSpawn(), 0f);
		
		transformedMapWidth = engine.getSystem(TiledMapRendererSystem.class).getTransformedMapWidth();
		transformedMapHeight = engine.getSystem(TiledMapRendererSystem.class).getTransformedMapHeight();
		cameraInfos = engine.getSystem(TiledMapRendererSystem.class).getCameraInfos();
	}
}
