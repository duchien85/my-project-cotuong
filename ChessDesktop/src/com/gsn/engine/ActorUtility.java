package com.gsn.engine;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorUtility {
	public static void setBottomLeft(Actor actor, float x, float y) {
		actor.x = x;
		actor.y = y;
	}

	public static void setBottomRight(Actor actor, float x, float y) {
		actor.x = x - actor.width;
		actor.y = y;
	}

	public static void setCenter(Actor actor, float x, float y) {
		actor.x = x - actor.width / 2;
		actor.y = y - actor.height / 2;
	}

	public static void setCenterBottom(Actor actor, float x, int y) {
		actor.x = x - actor.width / 2;
		actor.y = y;
	}

	public static void setCenterHeight(Actor actor, float x, float y) {
		actor.x = x;
		actor.y = y - actor.height / 2;
	}

	public static void setCenterTop(Actor actor, float x, int y) {
		actor.x = x;
		actor.y = y - actor.height / 2;
	}

	public static void setCenterWidth(Actor actor, float x, float y) {
		actor.x = x - actor.width / 2;
		actor.y = y;
	}

	public static void setLeftCenter(Actor actor, float x, float y) {
		actor.x = x;
		actor.y = y - actor.height / 2;
	}

	public static void setRatio(Actor actor, float ratioWidth, float ratioHeight, float x, float y) {
		actor.x = x - actor.width * ratioWidth;
		actor.y = y - actor.height * ratioHeight;
	}

	public static void setTopLeft(Actor actor, float x, float y) {
		actor.x = x;
		actor.y = y - actor.height;
	}

	public static void setTopRight(Actor actor, float x, float y) {
		actor.x = x - actor.width;
		actor.y = y - actor.height;
	}
}
