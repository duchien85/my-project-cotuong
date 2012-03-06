package com.gsn.chess.loading;

import com.gsn.chess.game.ChessGame;
import com.gsn.engine.myplay.GsnScreen;

public class LoadingScreen extends GsnScreen {
	public LoadingLayer loadingLayer;
	public LoadingScreen(ChessGame game, float width, float height) {
		super(width, height);
		loadingLayer = new LoadingLayer(game, width, height);
		addLayer(loadingLayer);
	}

	@Override
	public void setInputListener() {

	}	
}
