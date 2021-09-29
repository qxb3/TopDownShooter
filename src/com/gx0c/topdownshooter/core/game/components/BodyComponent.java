package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.gx0c.topdownshooter.core.game.components.BodyComponent;

public class BodyComponent implements Component {
	public static ComponentMapper<BodyComponent> mapper = ComponentMapper.getFor(BodyComponent.class);
	public Body body;
}
