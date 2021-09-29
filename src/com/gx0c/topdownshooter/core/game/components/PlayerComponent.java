package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.gx0c.topdownshooter.core.game.components.PlayerComponent;
import com.gx0c.topdownshooter.core.game.ui.Touchpad;

public class PlayerComponent implements Component {
	public static ComponentMapper<PlayerComponent> mapper = ComponentMapper.getFor(PlayerComponent.class);
	public Touchpad movementTouchpad;
	public Touchpad shootTouchpad;
	public long shootTimer = 0;
	public boolean isRunningRight = true;
}
