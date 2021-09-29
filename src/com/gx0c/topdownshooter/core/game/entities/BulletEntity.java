package com.gx0c.topdownshooter.core.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.BulletComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class BulletEntity extends Entity {

	public BulletEntity(World world, Vector2 position, Vector2 direction) {
		BulletComponent bulletComponent = new BulletComponent();
		bulletComponent.direction.set(direction);

		SpriteComponent spriteComponent = new SpriteComponent();
		spriteComponent.sprite.setRegion(Game.assets.bulletTexture);
		spriteComponent.sprite.setBounds(0, 0, 0.06f, 0.06f);
		spriteComponent.sprite.setOriginCenter();

		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = createBody(world, position);

		this.add(bulletComponent);
		this.add(spriteComponent);
		this.add(bodyComponent);
	}

	private Body createBody(World world, Vector2 position) {
		CircleShape shape = new CircleShape();
		shape.setRadius(0.03f);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(position);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = Game.BULLET_BIT;
		fixtureDef.filter.maskBits = Game.BULLET_COLLIDABLE_BIT | Game.ENEMY_BIT;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		shape.dispose();
		return body;
	}
}
