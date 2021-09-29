package com.gx0c.topdownshooter.core.game.systems.entity.player;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.GunComponent;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.entities.BulletEntity;
import com.gx0c.topdownshooter.core.game.systems.physics.PhysicsSystem;

public class PlayerShootingSystem extends EntitySystem {
	private ImmutableArray<Entity> playerEntities;
	private ImmutableArray<Entity> gunEntities;
	private long shootTimer;

	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < playerEntities.size(); i++) {
			for (int j = 0; j < gunEntities.size(); j++) {
				Entity player = playerEntities.get(i);
				Entity gun = gunEntities.get(j);

				PlayerComponent playerComponent = PlayerComponent.mapper.get(player);
				BodyComponent playerBodyComponent = BodyComponent.mapper.get(player);
				SpriteComponent gunSpriteComponent = SpriteComponent.mapper.get(gun);

				if (playerComponent.shootTouchpad.isTouched() && TimeUtils.millis() - shootTimer > playerComponent.shootTimer) {
					float angleX = MathUtils.cos(gunSpriteComponent.sprite.getRotation() * MathUtils.degreesToRadians);
					float angleY = MathUtils.sin(gunSpriteComponent.sprite.getRotation() * MathUtils.degreesToRadians);
					BulletEntity bullet = new BulletEntity(this.getEngine().getSystem(PhysicsSystem.class).getWorld(),
														   new Vector2(playerBodyComponent.body.getPosition().x - gunSpriteComponent.sprite.getWidth() / 2 + 0.11f + angleX / 7, playerBodyComponent.body.getPosition().y - gunSpriteComponent.sprite.getHeight() / 2 + angleY / 7),
														   new Vector2(MathUtils.random(angleX - 0.16f, angleX + 0.16f) * 6, MathUtils.random(angleY - 0.16f, angleY + 0.16f) * 6));
					this.getEngine().addEntity(bullet);
					Game.assets.bulletSound.play();
					shootTimer = TimeUtils.millis();
				}
			}
		}
	}

	@Override
	public void addedToEngine(Engine engine) {
		playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, BodyComponent.class).get());
		gunEntities = engine.getEntitiesFor(Family.all(GunComponent.class, SpriteComponent.class).get());
	}
}
