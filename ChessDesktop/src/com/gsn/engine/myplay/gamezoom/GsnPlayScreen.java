package com.gsn.engine.myplay.gamezoom;

import com.badlogic.gdx.Gdx;
import com.gsn.engine.myplay.GsnScreen;

abstract public class GsnPlayScreen extends GsnScreen{
	public GsnBoardLayer boardLayer;
	public GsnMenuLayer menuLayer;
	public GsnInputPlayStage input;
	public GsnPlayScreen(float width, float height) {
		super(width, height);		
		init();
		input = new GsnInputPlayStage(this);
	}
	
	abstract public void init();
		

	@Override
	public void setInputListener() {		
		Gdx.input.setInputProcessor(input);
	}

}
