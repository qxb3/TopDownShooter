package com.gx0c.topdownshooter.core;

import com.badlogic.gdx.Game;
import com.gx0c.topdownshooter.core.screens.GameScreen;

public class Game extends Game {
	public static final float WIDTH = 1.32f;
	public static final float HEIGHT = 1.32f;
	public static final float PPM = 100f;

	public static final short COLLIDABLE_BIT = 1;
	public static final short BULLET_COLLIDABLE_BIT = 2;
	public static final short PLAYER_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short BULLET_BIT = 16;

	public static Assets assets;

	@Override
	public void create() {
		assets = new Assets();

		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		assets.dispose();
	}
}
