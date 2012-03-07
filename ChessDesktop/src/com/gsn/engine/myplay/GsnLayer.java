package com.gsn.engine.myplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GsnLayer extends Stage implements Comparable<GsnLayer> {
	
	public GsnScreen parent;
	private Vector3 vector3 = new Vector3();
	public boolean visible = true;
	public float index = 0;

	public GsnLayer(float width, float height) {
		super(width, height, false);
	}
	@Override
	public int compareTo(GsnLayer another) {
		if (this.index < another.index)
			return -1;
		if (this.index == another.index)
			return 0;
		return 1;
	}

	public int getRatioHeight(float ratio) {
		return (int) (ratio * height);
	}

	public int getRatioWidth(float ratio) {
		return (int) (ratio * width);
	}

	public void setInputListener() {		
		Gdx.input.setInputProcessor(this);
	}

	public void toScreenCoordinates(float x, float y, Vector2 out) {
		camera.project(vector3.set(x, y, 0));
		out.x = vector3.x;
		out.y = vector3.y;
	}

}