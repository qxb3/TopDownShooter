package com.gx0c.topdownshooter.core.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Touchpad extends Touchpad {

	public Touchpad(int deadzone, Drawable background, Drawable knob) {
		super(deadzone, getStyle(background, knob));
	}

	private static Touchpad.TouchpadStyle getStyle(Drawable background, Drawable knob) {
		Touchpad.TouchpadStyle style = new TouchpadStyle();
		style.background = background;
		style.knob = knob;

		return style;
	}
}
