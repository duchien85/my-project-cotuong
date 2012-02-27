package com.gsn.caro.game;

import com.badlogic.gdx.Gdx;
import com.gsn.caro.asset.CaroTexture;
import com.gsn.caro.lobby.LobbyScreen;
import com.gsn.engine.myplay.GsnGame;

public class CaroGame extends GsnGame {
	LobbyScreen lobbyScreen;
	float width;
	float height;
	
	public CaroGame() {		
	}

	@Override
	public void create() {
//		CaroTexture.create();
//		CaroTexture.loadAll();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		lobbyScreen = new LobbyScreen(width, height);
		setScreen(lobbyScreen);		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
