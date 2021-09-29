package com.gx0c.topdownshooter.core.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.components.AnimationComponent;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.EnemyComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.components.StateComponent;
import com.gx0c.topdownshooter.core.game.utils.AnimationUtil;

public class EnemyEntity extends Entity {

	public EnemyEntity(World world, Vector2 position) {
		SpriteComponent spriteComponent = new SpriteComponent();
		spriteComponent.sprite.setRegion(Game.assets.enemyStrip);
		spriteComponent.sprite.setBounds(0, 0, 0.20f, 0.20f);
		spriteComponent.sprite.setOriginCenter();

		AnimationComponent animationComponent = new AnimationComponent();
		TextureRegion[][] runRegion = TextureRegion.split(Game.assets.enemyStrip, Game.assets.enemyStrip.getWidth() / 7, Game.assets.enemyStrip.getHeight() / 1);
		TextureRegion[][] deadRegion = TextureRegion.split(Game.assets.enemyDead, Game.assets.enemyDead.getWidth() / 1, Game.assets.enemyDead.getHeight() / 1);
		animationComponent.animations.put("RUN", AnimationUtil.get(runRegion, 7, 1, 0, 0, 0.1f));
		animationComponent.animations.put("DEAD", AnimationUtil.get(deadRegion, 1, 1, 0, 0, 0.1f));

		StateComponent stateComponent = new StateComponent();
		stateComponent.state = "RUN";
		stateComponent.isLooping = true;

		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = createBody(world, position);

		this.add(new EnemyComponent());
		this.add(spriteComponent);
		this.add(animationComponent);
		this.add(stateComponent);
		this.add(bodyComponent);
	}

	private Body createBody(World world, Vector2 position) {
		CircleShape shape = new CircleShape();
		shape.setRadius(0.08f);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(position);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = Game.ENEMY_BIT;
		fixtureDef.filter.maskBits = Game.COLLIDABLE_BIT | Game.PLAYER_BIT | Game.BULLET_BIT | Game.ENEMY_BIT;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		shape.dispose();
		return body;
	}
}
