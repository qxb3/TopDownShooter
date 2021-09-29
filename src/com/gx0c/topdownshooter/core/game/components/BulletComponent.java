package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.gx0c.topdownshooter.core.game.components.BulletComponent;

public class BulletComponent implements Component {
	public static ComponentMapper<BulletComponent> mapper = ComponentMapper.getFor(BulletComponent.class);
	public Vector2 direction = new Vector2();
}
