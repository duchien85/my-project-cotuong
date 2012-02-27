package com.gsn.engine.layout;

import com.badlogic.gdx.math.Vector2;

public class GsnPoint {
	public float x, y;

	public GsnPoint(float x, float y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + " " + y + ")";
	}

	public Vector2 toVector2() {
		return new Vector2(x, y);
	}
}