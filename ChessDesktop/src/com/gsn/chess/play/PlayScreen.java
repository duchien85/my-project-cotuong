package com.gsn.chess.play;

import com.gsn.chess.lobby.LobbyLayer;
import com.gsn.engine.myplay.GsnScreen;

public class PlayScreen extends GsnScreen {
	BoardLayer boardLayer;	
	LobbyLayer lobbyLayer;
	public PlayScreen(float width, float height) {
		super(width, height);
		boardLayer = new BoardLayer(width, height);
		lobbyLayer = new LobbyLayer(width, height);
		addLayer(boardLayer,1, true);
//		addLayer(lobbyLayer,1, true);
	}

	@Override
	public void setInputListener() {
		setInputLayer(boardLayer);
	}

}
