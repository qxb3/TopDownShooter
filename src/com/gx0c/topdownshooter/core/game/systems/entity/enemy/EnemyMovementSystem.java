package com.gx0c.topdownshooter.core.game.systems.entity.enemy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.EnemyComponent;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class EnemyMovementSystem extends IteratingSystem {

	public EnemyMovementSystem() {
		super(Family.all(EnemyComponent.class, SpriteComponent.class, BodyComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

		for (Entity playerEntity : this.getEngine().getEntitiesFor(Family.all(PlayerComponent.class, SpriteComponent.class, BodyComponent.class).get())) {
			Body playerBody = playerEntity.getComponent(BodyComponent.class).body;

			float angleX = playerBody.getPosition().x - bodyComponent.body.getPosition().x;
			float angleY = playerBody.getPosition().y - bodyComponent.body.getPosition().y;
			float angle = MathUtils.atan2(angleY, angleX);

			float x = MathUtils.cos(angle);
			float y = MathUtils.sin(angle);
			float distance = (float) Math.sqrt((angleX * angleX) + (angleY * angleY));
			bodyComponent.body.setLinearVelocity(Math.max(x * distance / 2, x * distance / 2), Math.max(y * distance / 2, y * distance / 2));

			spriteComponent.sprite.setPosition(bodyComponent.body.getPosition().x - spriteComponent.sprite.getWidth() / 2, bodyComponent.body.getPosition().y - spriteComponent.sprite.getHeight() / 2);
		}

		if ((bodyComponent.body.getLinearVelocity().x < 0 && !spriteComponent.sprite.isFlipX())) {
			spriteComponent.sprite.flip(true, false);
		} else if ((bodyComponent.body.getLinearVelocity().x > 0 && spriteComponent.sprite.isFlipX())) {
			spriteComponent.sprite.flip(true, false);
		}
	}
}
