package com.gsn.engine.myplay;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

abstract public class GsnGame implements ApplicationListener {
	public GsnScreen currentScreen;
	
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (currentScreen != null) {
			currentScreen.act(Gdx.graphics.getDeltaTime());
			currentScreen.draw();
		}
	}

	public void setScreen(GsnScreen screen) {
		if  (currentScreen != null)
			currentScreen.onHideScreen();
		currentScreen = screen;
		currentScreen.onShowScreen();
		screen.setInputListener();
	};

}
