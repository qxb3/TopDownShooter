package com.gx0c.topdownshooter.core.game.entities;

import com.badlogic.ashley.core.Entity;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.components.GunComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class GunEntity extends Entity {

	public GunEntity() {
		SpriteComponent spriteComponent = new SpriteComponent();
		spriteComponent.sprite.setRegion(Game.assets.gunTexture);
		spriteComponent.sprite.setBounds(0, 0, 0.12f, 0.08f);
		spriteComponent.sprite.setOriginCenter();

		this.add(new GunComponent());
		this.add(spriteComponent);
	}
}
