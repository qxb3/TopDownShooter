package com.gx0c.topdownshooter.core.game.systems.entity.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.components.StateComponent;

public class PlayerMovementSystem extends IteratingSystem {
	
	public PlayerMovementSystem() {
		super(Family.all(PlayerComponent.class, SpriteComponent.class, StateComponent.class, BodyComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		StateComponent stateComponent = StateComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

		if ((playerComponent.movementTouchpad.getKnobPercentX() < 0 && !spriteComponent.sprite.isFlipX())) {
			spriteComponent.sprite.flip(true, false);
		} else if ((playerComponent.movementTouchpad.getKnobPercentX() > 0 && spriteComponent.sprite.isFlipX())) {
			spriteComponent.sprite.flip(true, false);
		}

		if (playerComponent.movementTouchpad.getKnobPercentX() < 0 || playerComponent.movementTouchpad.getKnobPercentX() > 0)
			stateComponent.state = "RUN";
		else
			stateComponent.state = "IDLE";

		bodyComponent.body.setLinearVelocity(playerComponent.movementTouchpad.getKnobPercentX(), playerComponent.movementTouchpad.getKnobPercentY());
		spriteComponent.sprite.setPosition(bodyComponent.body.getPosition().x - spriteComponent.sprite.getWidth() / 2, bodyComponent.body.getPosition().y - spriteComponent.sprite.getHeight() / 2);
	}
}
