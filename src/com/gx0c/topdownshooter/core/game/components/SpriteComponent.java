package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gx0c.topdownshooter.core.game.components.SpriteComponent;

public class SpriteComponent implements Component {
	public static ComponentMapper<SpriteComponent> mapper = ComponentMapper.getFor(SpriteComponent.class);
	public Sprite sprite = new Sprite();
}
