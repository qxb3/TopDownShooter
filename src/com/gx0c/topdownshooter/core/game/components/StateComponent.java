package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.gx0c.topdownshooter.core.game.components.StateComponent;

public class StateComponent implements Component {
	public static ComponentMapper<StateComponent> mapper = ComponentMapper.getFor(StateComponent.class);
	public String state = "NONE";
	public float time = 0f;
	public boolean isLooping = false;
}
