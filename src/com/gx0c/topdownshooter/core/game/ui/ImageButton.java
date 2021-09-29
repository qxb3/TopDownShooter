package com.gx0c.topdownshooter.core.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ImageButton extends ImageButton {
	public boolean isTouched;

	public ImageButton(Drawable up, Drawable down) {
		super(getStyle(up, down));
	}

	private static ImageButton.ImageButtonStyle getStyle(Drawable up, Drawable down) {
		ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
		style.up = up;
		style.down = down;

		return style;
	}
}
