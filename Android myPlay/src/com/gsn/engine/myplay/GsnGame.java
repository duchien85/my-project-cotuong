package com.gsn.engine.myplay;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

abstract public class GsnGame implements ApplicationListener {
	public GsnScreen currentStage;


	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (currentStage != null) {
			currentStage.act(Gdx.graphics.getDeltaTime());
			currentStage.draw();
		}
	}

	public void setScreen(GsnScreen stage) {
		this.currentStage = stage;
		stage.setInputListener();
	};

}
