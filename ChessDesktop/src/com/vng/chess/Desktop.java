package com.vng.chess;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gsn.chess.game.ChessGame;
import com.gsn.engine.myplay.GsnGame;
import com.gsn.engine.myplay.GsnMyPlay;

public class Desktop {

	public static void createGame(int mode) {
		switch (mode) {
		case 1:
			createGame(240, 320);
			break;
		case 2:
			createGame(320, 480);
			break;
		case 3:
			createGame(540, 960);
			break;
		case 4:
			createGame(480, 800);
			break;
		}
	}
	
	public static void binder(){
		GsnMyPlay a;
	}
	
	public static void createGame(int width, int height) {
		GsnGame game = new ChessGame();		
		new LwjglApplication(game, "My Caro", width, height, false);		
	}

	public static void main(String[] args) {
		createGame(1);
	}
}
