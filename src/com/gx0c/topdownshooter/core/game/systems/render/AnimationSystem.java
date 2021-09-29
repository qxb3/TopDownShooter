package com.gx0c.topdownshooter.core.game.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gx0c.topdownshooter.core.game.components.AnimationComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.components.StateComponent;

public class AnimationSystem extends IteratingSystem {

	public AnimationSystem() {
		super(Family.all(SpriteComponent.class, AnimationComponent.class, StateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		StateComponent stateComponent = StateComponent.mapper.get(entity);

		if (animationComponent.animations.containsKey(stateComponent.state))
			spriteComponent.sprite.setRegion(animationComponent.animations.get(stateComponent.state).getKeyFrame(stateComponent.time, stateComponent.isLooping));

		stateComponent.time += deltaTime;
	}
}
