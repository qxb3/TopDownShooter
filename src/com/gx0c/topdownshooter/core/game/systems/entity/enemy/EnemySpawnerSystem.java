package com.gx0c.topdownshooter.core.game.systems.entity.enemy;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.gx0c.topdownshooter.core.game.entities.EnemyEntity;
import com.gx0c.topdownshooter.core.game.systems.render.TiledMapRendererSystem;
import java.util.ArrayList;

public class EnemySpawnerSystem extends EntitySystem {
	private World world;
	private long spawnTimer;
	private ArrayList<Vector2> enemySpawns;
	
	public EnemySpawnerSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void update(float deltaTime) {
		if (TimeUtils.millis() - spawnTimer > 5000) {
			this.getEngine().addEntity(new EnemyEntity(world, enemySpawns.get(MathUtils.random(0, enemySpawns.size()))));
			spawnTimer = TimeUtils.millis();
		}
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		enemySpawns = engine.getSystem(TiledMapRendererSystem.class).getEnemySpawns();
	}
}
