package com.vng.chess;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gsn.chess.game.ChessGame;
import com.gsn.chess.game.MyChess;
import com.gsn.engine.mercurry.MercuryClient;
import com.gsn.engine.myplay.GsnGame;

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
		MyChess.game = game;
		MyChess.client = new MercuryClient("120.138.65.118", 443, game);
		MyChess.client.connect();
	}
	
	static ChessGame game;
	
	public static void createGame(int width, int height) {
		game  = new ChessGame("sdafa");		
		new LwjglApplication(game, "My Caro", width, height, false);
		binder();
	}

	public static void main(String[] args) {
		createGame(1);
	}
}
