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
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;
import com.gx0c.topdownshooter.core.game.components.StateComponent;
import com.gx0c.topdownshooter.core.game.ui.Touchpad;
import com.gx0c.topdownshooter.core.game.utils.AnimationUtil;

public class PlayerEntity extends Entity {

	public PlayerEntity(World world, Vector2 position) {
		PlayerComponent playerComponent = new PlayerComponent();
		playerComponent.movementTouchpad = new Touchpad(0, Game.assets.skin.getDrawable("touchpad_background"), Game.assets.skin.getDrawable("touchpad_knob"));
		playerComponent.shootTouchpad = new Touchpad(0, Game.assets.skin.getDrawable("touchpad_background"), Game.assets.skin.getDrawable("touchpad_knob"));
		playerComponent.isRunningRight = true;

		SpriteComponent spriteComponent = new SpriteComponent();
		spriteComponent.sprite.setRegion(Game.assets.playerIdleStrip);
		spriteComponent.sprite.setBounds(0, 0, 0.24f, 0.24f);
		spriteComponent.sprite.setOriginCenter();

		AnimationComponent animationComponent = new AnimationComponent();
		TextureRegion[][] idleRegion = TextureRegion.split(Game.assets.playerIdleStrip, Game.assets.playerIdleStrip.getWidth() / 4, Game.assets.playerIdleStrip.getHeight() / 1);
		TextureRegion[][] runRegion = TextureRegion.split(Game.assets.playerRunStrip, Game.assets.playerRunStrip.getWidth() / 7, Game.assets.playerRunStrip.getHeight() / 1);
		animationComponent.animations.put("IDLE", AnimationUtil.get(idleRegion, 4, 1, 0, 0, 0.1f));
		animationComponent.animations.put("RUN", AnimationUtil.get(runRegion, 7, 1, 0, 0, 0.1f));

		StateComponent stateComponent = new StateComponent();
		stateComponent.state = "IDLE";
		stateComponent.isLooping = true;

		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = createBody(world, position);

		this.add(playerComponent);
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
		fixtureDef.filter.categoryBits = Game.PLAYER_BIT;
		fixtureDef.filter.maskBits = Game.COLLIDABLE_BIT | Game.ENEMY_BIT;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		shape.dispose();
		return body;
	}
}
