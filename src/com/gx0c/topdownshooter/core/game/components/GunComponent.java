package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.gx0c.topdownshooter.core.game.components.GunComponent;

public class GunComponent implements Component {
	public static ComponentMapper<GunComponent> mapper = ComponentMapper.getFor(GunComponent.class);
}
