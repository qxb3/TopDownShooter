package com.gx0c.topdownshooter.core.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;
import com.gx0c.topdownshooter.core.game.components.AnimationComponent;

public class AnimationComponent implements Component {
	public static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);
	public ArrayMap<String, Animation<TextureRegion>> animations = new ArrayMap<String, Animation<TextureRegion>>();
}
