package com.gx0c.topdownshooter.core.game.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationUtil {

	public static Animation<TextureRegion> get(TextureRegion[][] animationRegions, int frameCountX, int frameCountY, int x, int y, float frameDuration) {
		TextureRegion[] region = new TextureRegion[frameCountX * frameCountY];

		int index = 0;
		for (int i = 0; i < frameCountY; i++) {
			for (int j = 0; j < frameCountX; j++) {
				region[index++] = animationRegions[i + y][j + x];
			}
		}
		return new Animation<TextureRegion>(frameDuration, region);
	}
}
