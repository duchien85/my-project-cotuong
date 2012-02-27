package com.gsn.chess.game;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.chess.lobby.LobbyScreen;
import com.gsn.engine.myplay.GsnGame;

public class ChessGame extends GsnGame {
	LobbyScreen lobbyScreen;
	float width;
	float height;
	
	public ChessGame() {		
	}

	@Override
	public void create() {
		CommonTexture.create();
		CommonTexture.loadAll();		
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
