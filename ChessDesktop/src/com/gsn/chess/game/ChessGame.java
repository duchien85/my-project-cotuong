package com.gsn.chess.game;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.chess.lobby.LobbyScreen;
import com.gsn.chess.play.PlayScreen;
import com.gsn.engine.myplay.GsnGame;

public class ChessGame extends GsnGame {
	LobbyScreen lobbyScreen;
	PlayScreen playScreen;
	float width;
	float height;
	
	public ChessGame() {		
	}

	@Override
	public void create() {				
		ChessTexture.create();
		ChessTexture.loadAll();
		
		CommonTexture.create();
		CommonTexture.loadAll();				
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		lobbyScreen = new LobbyScreen(width, height);		
		playScreen = new PlayScreen(width, height);
		
		setScreen(playScreen);		
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
