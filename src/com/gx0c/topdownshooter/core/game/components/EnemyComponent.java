package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.gx0c.topdownshooter.core.game.components.EnemyComponent;

public class EnemyComponent implements Component {
	public static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);
	public int health = 15;
}
