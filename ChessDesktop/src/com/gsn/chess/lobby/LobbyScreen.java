package com.gsn.chess.lobby;

import com.gsn.engine.myplay.GsnScreen;

public class LobbyScreen extends GsnScreen {
	public LobbyLayer lobbyLayer;
	public LobbyScreen(float width, float height) {
		super(width, height);	
		lobbyLayer = new LobbyLayer(width, height);
		addLayer(lobbyLayer, 1, true);
	}

	@Override
	public void setInputListener() {
		setInputLayer(lobbyLayer);		
	}	

}
