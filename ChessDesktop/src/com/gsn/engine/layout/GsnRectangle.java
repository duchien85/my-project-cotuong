package com.gsn.engine.layout;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gsn.engine.ActorUtility;

public class GsnRectangle {
	public static String toString(Sprite sprite) {
		Rectangle rect = sprite.getBoundingRectangle();
		return "(" + (int) rect.getX() + ", " + (int) rect.getY() + ", " + (int) rect.getWidth() + ", " + (int) rect.getHeight() + ")";
	}

	public float x, y, width, height;

	public GsnRectangle(float x, float y, float width, float height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public GsnPoint getCenter() {
		GsnPoint tmp = new GsnPoint(x + width / 2, y + height / 2);
		return tmp;
	}

	public GsnRectangle putBottomCenter(Actor actor) {
		actor.x = x + width / 2 - actor.width / 2;
		actor.y = y;
		return this;
	}

	public GsnRectangle putCenter(Actor actor) {
		ActorUtility.setCenter(actor, this.x + this.width / 2, this.y + this.height / 2);
		return this;
	}

	public GsnRectangle putTopCenter(Actor actor) {
		actor.x = x + width / 2 - actor.width / 2;
		actor.y = y + height - actor.height;
		return this;
	}

	public GsnRectangle setMargin(boolean isRatio, float rMarginX, float rMarginY) {
		// TODO Auto-generated method stub
		float marginX;
		float marginY;
		if (isRatio) {
			marginX = rMarginX * width;
			marginY = rMarginY * height;
		} else {
			marginX = rMarginX;
			marginY = rMarginY;
		}

		float x = this.x + marginX;
		float y = this.y + marginY;
		float width = this.width - 2 * marginX;
		float height = this.height - 2 * marginY;
		return new GsnRectangle(x, y, width, height);
	}


	public Rectangle toRectangle() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + (int) x + ", " + (int) y + ", " + (int) width + ", " + (int) height + ")";
	}
}