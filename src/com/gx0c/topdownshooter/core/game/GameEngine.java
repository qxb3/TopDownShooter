package com.gx0c.topdownshooter.core.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.entities.GunEntity;
import com.gx0c.topdownshooter.core.game.entities.PlayerEntity;
import com.gx0c.topdownshooter.core.game.systems.OrthographicCameraSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.BulletSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.GunSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.enemy.EnemyMovementSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.enemy.EnemySpawnerSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.player.PlayerMovementSystem;
import com.gx0c.topdownshooter.core.game.systems.entity.player.PlayerShootingSystem;
import com.gx0c.topdownshooter.core.game.systems.physics.CollisionSystem;
import com.gx0c.topdownshooter.core.game.systems.physics.PhysicsSystem;
import com.gx0c.topdownshooter.core.game.systems.render.AnimationSystem;
import com.gx0c.topdownshooter.core.game.systems.render.HudSystem;
import com.gx0c.topdownshooter.core.game.systems.render.SpriteRendererSystem;
import com.gx0c.topdownshooter.core.game.systems.render.TiledMapRendererSystem;
import com.gx0c.topdownshooter.core.game.systems.utils.ScreenDebuggerSystem;
import com.gx0c.topdownshooter.core.game.utils.Resizable;

public class GameEngine extends PooledEngine implements Resizable, Disposable {
	private Game game;

	private SpriteBatch batch;
	private World world;

	private OrthographicCamera camera;
	private Viewport viewport;

	public GameEngine(Game game) {
		this.game = game;

		batch = new SpriteBatch();
		world = new World(new Vector2(0, 0), false);

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Game.WIDTH, Game.HEIGHT, camera);

		//Entities
		this.addEntity(new PlayerEntity(world, new Vector2(0, 0)));
		this.addEntity(new GunEntity());

		//Systems
		this.addSystem(new TiledMapRendererSystem(camera));
		this.addSystem(new SpriteRendererSystem(batch, camera));
		this.addSystem(new AnimationSystem());
		this.addSystem(new HudSystem());
		this.addSystem(new PhysicsSystem(world, camera));
		this.addSystem(new CollisionSystem(world));
		this.addSystem(new OrthographicCameraSystem(camera));
		this.addSystem(new PlayerMovementSystem());
		this.addSystem(new PlayerShootingSystem());
		this.addSystem(new GunSystem());
		this.addSystem(new BulletSystem());
		this.addSystem(new EnemySpawnerSystem(world));
		this.addSystem(new EnemyMovementSystem());
		this.addSystem(new ScreenDebuggerSystem());

		this.getSystem(TiledMapRendererSystem.class).getCollidable(world);
		this.getSystem(TiledMapRendererSystem.class).getBulletCollidable(world);
	}

	@Override
	public void resize(int screenWidth, int screenHeight) {
		viewport.update(screenWidth, screenHeight);
		this.getSystem(HudSystem.class).resize(screenWidth, screenHeight);
		this.getSystem(ScreenDebuggerSystem.class).resize(screenWidth, screenHeight);
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
		this.getSystem(TiledMapRendererSystem.class).dispose();
		this.getSystem(PhysicsSystem.class).dispose();
		this.getSystem(HudSystem.class).dispose();
		this.getSystem(ScreenDebuggerSystem.class).dispose();
	}
}
