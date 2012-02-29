package com.gsn.chess.play;

import com.badlogic.gdx.Input.Keys;
import com.gsn.engine.myplay.GsnLayer;

public class BoardLayer extends GsnLayer{
	public static final String tag = "Board Layer";
	
	BoardGroup board;
	public BoardLayer(float width, float height) {
		super(width, height);		
		
		board = new BoardGroup(width, height);
//		board.x = 50;
//		board.y = 100;
		addActor(board);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode){
		case Keys.F1:
			board.newGame(0);
		}
		return super.keyDown(keycode);
	}
}
