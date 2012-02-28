package com.gsn.chess.play;

import com.gsn.engine.myplay.GsnLayer;

public class BoardLayer extends GsnLayer{
	public static final String tag = "Board Layer";
	public BoardLayer(float width, float height) {
		super(width, height);		
		
		BoardGroup board = new BoardGroup(400, 400);
		board.x = 50;
		board.y = 100;
		addActor(board);
	}
}
