package com.gx0c.topdownshooter.core.game.systems.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.BulletComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class BulletSystem extends IteratingSystem {

	public BulletSystem() {
		super(Family.all(BulletComponent.class, SpriteComponent.class, BodyComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BulletComponent bulletComponent = BulletComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

		bodyComponent.body.setLinearVelocity(bulletComponent.direction);
		spriteComponent.sprite.setPosition(bodyComponent.body.getPosition().x - spriteComponent.sprite.getWidth() / 2, bodyComponent.body.getPosition().y - spriteComponent.sprite.getHeight() / 2);
	}
}
