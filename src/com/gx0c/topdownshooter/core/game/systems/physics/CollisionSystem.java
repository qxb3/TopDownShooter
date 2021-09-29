package com.gx0c.topdownshooter.core.game.systems.physics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.gx0c.topdownshooter.core.Game;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;
import com.gx0c.topdownshooter.core.game.components.BulletComponent;
import com.gx0c.topdownshooter.core.game.components.EnemyComponent;

public class CollisionSystem extends EntitySystem implements ContactListener {
	private World world;

	public CollisionSystem(World world) {
		this.world = world;
		this.world.setContactListener(this);
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		for (Entity bullet : this.getEngine().getEntitiesFor(Family.all(BulletComponent.class, BodyComponent.class).get())) {
			if (bullet != null) {
				BodyComponent bodyComponent = BodyComponent.mapper.get(bullet);
				
				if (!this.getEngine().getSystem(PhysicsSystem.class).getBodiesToDestroy().contains(bodyComponent.body, true)) {
					if (fixtureA.getBody() == bodyComponent.body && fixtureB.getFilterData().categoryBits == Game.BULLET_COLLIDABLE_BIT ||
						fixtureA.getFilterData().categoryBits == Game.BULLET_COLLIDABLE_BIT && fixtureB.getBody() == bodyComponent.body) {
						this.getEngine().getSystem(PhysicsSystem.class).getBodiesToDestroy().add(bodyComponent.body);
						this.getEngine().removeEntity(bullet);
					}
					
					if (fixtureA.getBody() == bodyComponent.body && fixtureB.getFilterData().categoryBits == Game.ENEMY_BIT ||
						fixtureA.getFilterData().categoryBits == Game.ENEMY_BIT && fixtureB.getBody() == bodyComponent.body) {
						this.getEngine().getSystem(PhysicsSystem.class).getBodiesToDestroy().add(bodyComponent.body);
						this.getEngine().removeEntity(bullet);
					}
				}
			}
		}

		for (Entity enemy : this.getEngine().getEntitiesFor(Family.all(EnemyComponent.class, BodyComponent.class).get())) {
			if (enemy != null) {
				BodyComponent bodyComponent = BodyComponent.mapper.get(enemy);

				if (!this.getEngine().getSystem(PhysicsSystem.class).getBodiesToDestroy().contains(bodyComponent.body, true)) {
					if (fixtureA.getFilterData().categoryBits == Game.BULLET_BIT && fixtureB.getBody() == bodyComponent.body ||
						fixtureA.getBody() == bodyComponent.body && fixtureB.getFilterData().categoryBits == Game.BULLET_BIT) {
						this.getEngine().getSystem(PhysicsSystem.class).getBodiesToDestroy().add(bodyComponent.body);
						this.getEngine().removeEntity(enemy);
						Game.assets.deathSound.play();
					}
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold manifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	@Override
	public void removedFromEngine(Engine engine) {
		world.setContactListener(null);
	}
}
