package com.gsn.chess.play;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;

public class BoardLayer extends GsnLayer{
	public static final String tag = "Board Layer";	
	BoardGroup board;
	float time = 0;
	ClockPlayer clockOne;
	public BoardLayer(float width, float height) {
		super(width, height);		
		Image betIcon = new Image(ChessTexture.betIcon1000);
		ActorUtility.setRatio(betIcon, 0, 1, 0, height);
		addActor(betIcon);		
		
		ImageButton quitBtn = new ImageButton(ChessTexture.quitBtn, ChessTexture.quitBtnDown);
		ActorUtility.setRatio(quitBtn, 1, 1, width, height);
		addActor(quitBtn);
		
		ImageButton chatBtn = new ImageButton(ChessTexture.chatBtn, ChessTexture.chatBtnDown);
		ActorUtility.setRatio(chatBtn, 1, 0, quitBtn.x, quitBtn.y);
		addActor(chatBtn);		
		
		ClockTurn clockTurn = new ClockTurn(ChessTexture.clock1green, ChessTexture.numClock1);
		clockTurn.startTime(20);
		clockTurn.x = 50;
		clockTurn.y = 50;
		//addActor(clockTurn);
		
		ClockGame clockGame = new ClockGame(ChessTexture.clock2green, ChessTexture.numClock2, ChessTexture.haicham);
		clockGame.x = 100;
		clockGame.y = 100;
		clockGame.startTime(30 * 60);
		//addActor(clockGame);
		
		clockOne = new ClockPlayer(1, clockTurn, clockGame, 120, 1800);
		addActor(clockOne);
		
//		Image test = new Image(ChessTexture.haicham);
//		addActor(test);
	}
	
	@Override
	public void act(float delta) {
		time += delta;
		super.act(delta);		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode){
		case Keys.F1:
			clockOne.otherTurn();
			break;
		case Keys.F2:
			clockOne.nextTurn();
			break;
		
		}
		return super.keyDown(keycode);
	}
}
