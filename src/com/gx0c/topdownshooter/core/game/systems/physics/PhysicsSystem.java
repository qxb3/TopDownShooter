package com.gx0c.topdownshooter.core.game.systems.physics;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class PhysicsSystem extends EntitySystem implements Disposable {
	private static float STEP_TIME = 1 / 60f;
	private static float accumulator = 0f;

	private World world;
	private OrthographicCamera camera;

	private Box2DDebugRenderer debugRenderer;
	private Array<Body> bodiesToDestroy;
	public boolean isDebug = false;

	public PhysicsSystem(World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;

		debugRenderer = new Box2DDebugRenderer();
		bodiesToDestroy = new Array<Body>();
	}

	@Override
	public void update(float deltaTime) {
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		if (accumulator >= STEP_TIME) {
			world.step(STEP_TIME, 6, 2);
			accumulator -= STEP_TIME;

			//Deleting Bodies
			for (Body body : bodiesToDestroy) {
				for (Fixture fixture : body.getFixtureList())
					body.destroyFixture(fixture);

				world.destroyBody(body);
				bodiesToDestroy.removeValue(body, true);
			}

			if (isDebug)
				debugRenderer.render(world, camera.combined);
		}
	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		bodiesToDestroy.clear();
	}

	public World getWorld() {
		return world;
	}

	public Array<Body> getBodiesToDestroy() {
		return bodiesToDestroy;
	}
}
