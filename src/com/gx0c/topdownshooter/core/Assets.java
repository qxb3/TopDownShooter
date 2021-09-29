package com.gx0c.topdownshooter.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
	public Texture playerIdleStrip;
	public Texture playerRunStrip;
	public Texture gunTexture;
	public Texture bulletTexture;
	public Texture enemyStrip;
	public Texture enemyDead;

	public Sound bulletSound;
	public Sound deathSound;

	public Skin skin;

	public Assets() {
		playerIdleStrip = new Texture("sprites/player/player_idle_strip.png");
		playerRunStrip = new Texture("sprites/player/player_run_strip.png");
		gunTexture = new Texture("sprites/player/gun.png");
		bulletTexture = new Texture("sprites/player/bullet.png");
		enemyStrip = new Texture("sprites/enemy/enemy_strip.png");
		enemyDead = new Texture("sprites/enemy/enemy_dead.png");

		bulletSound = Gdx.audio.newSound(Gdx.files.internal("sfx/bulletSound.wav"));
		deathSound = Gdx.audio.newSound(Gdx.files.internal("sfx/deathSound.wav"));

		skin = new Skin();
		skin.add("touchpad_background", new Texture("scenes/hud/touchpad_background.png"));
		skin.add("touchpad_knob", new Texture("scenes/hud/touchpad_knob.png"));
		skin.add("button_shoot", new Texture("scenes/hud/button_shoot.png"));
		skin.add("button_shoot_pressed", new Texture("scenes/hud/button_shoot_pressed.png"));
	}

	@Override
	public void dispose() {
		playerIdleStrip.dispose();
		playerRunStrip.dispose();
		gunTexture.dispose();
		bulletTexture.dispose();
		enemyStrip.dispose();
		enemyDead.dispose();

		bulletSound.dispose();
		deathSound.dispose();

		skin.dispose();
	}
}
