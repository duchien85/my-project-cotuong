package com.gsn.engine;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorUtility {

	public static void setCenter(Actor actor, float x, float y) {
		actor.x = x - actor.width / 2;
		actor.y = y - actor.height / 2;
	}

	public static void setRatio(Actor actor, float ratioWidth, float ratioHeight, float x, float y) {
		actor.x = x - actor.width * ratioWidth;
		actor.y = y - actor.height * ratioHeight;
	}

	public static String getShortName(String name) {
		if (name.length() >= 9)
			return (name.substring(0, 6) + "...");
		else
			return name;
	}
}
