package com.gx0c.topdownshooter.core.game.systems.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.GunComponent;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class GunSystem extends EntitySystem {
	private ImmutableArray<Entity> playerEntities;
	private ImmutableArray<Entity> gunEntities;

	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < playerEntities.size(); i++) {
			for (int j = 0; j < gunEntities.size(); j++) {
				Entity player = playerEntities.get(i);
				Entity gun = gunEntities.get(j);

				PlayerComponent playerComponent = PlayerComponent.mapper.get(player);
				BodyComponent playerBodyComponent = BodyComponent.mapper.get(player);

				SpriteComponent gunSpriteComponent = SpriteComponent.mapper.get(gun);

				float angle = MathUtils.atan2(playerComponent.shootTouchpad.getKnobPercentY(), playerComponent.shootTouchpad.getKnobPercentX());
				gunSpriteComponent.sprite.setRotation(angle * MathUtils.radiansToDegrees);
				gunSpriteComponent.sprite.setPosition(playerBodyComponent.body.getPosition().x - gunSpriteComponent.sprite.getWidth() / 2 + 0.06f, playerBodyComponent.body.getPosition().y - gunSpriteComponent.sprite.getHeight() / 2 - 0.04f);
			}
		}
	}

	@Override
	public void addedToEngine(Engine engine) {
		playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, BodyComponent.class).get());
		gunEntities = engine.getEntitiesFor(Family.all(GunComponent.class, SpriteComponent.class).get());
	}
}
