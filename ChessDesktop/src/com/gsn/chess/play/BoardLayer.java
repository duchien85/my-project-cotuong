package com.gsn.chess.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.chess.game.MyChess;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;

public class BoardLayer extends GsnLayer implements ClickListener {
	public static final String tag = "Board Layer";
	BoardGroup boardGroup;
	float time = 0;
	ClockPlayer clockOne;
	private ClockPlayer clockTwo;
	PlayScreen parent;
	ImageButton quitBtn;
	ImageButton chatBtn;

	ImageButton readyBtn;
	Image startEffect;

	public BoardLayer(PlayScreen parent, float width, float height) {
		super(width, height);
		this.parent = parent;
		Image betIcon = new Image(ChessTexture.betIcon1000);
		ActorUtility.setRatio(betIcon, 0, 1, 0, height);

		quitBtn = new ImageButton(ChessTexture.quitBtn, ChessTexture.quitBtnDown);
		ActorUtility.setRatio(quitBtn, 1, 1, width, height);
		quitBtn.setClickListener(this);

		chatBtn = new ImageButton(ChessTexture.chatBtn, ChessTexture.chatBtnDown);
		ActorUtility.setRatio(chatBtn, 1, 0, quitBtn.x, quitBtn.y);
		chatBtn.setClickListener(this);

		ClockTurn clockTurn = new ClockTurn(ChessTexture.clock1green, ChessTexture.numClock1);
		ClockGame clockGame = new ClockGame(ChessTexture.clock2green, ChessTexture.numClock2, ChessTexture.haicham);
		clockOne = new ClockPlayer(0, clockTurn, clockGame, 120, 1800);

		clockTurn = new ClockTurn(ChessTexture.clock1red, ChessTexture.numClock1);
		clockGame = new ClockGame(ChessTexture.clock2red, ChessTexture.numClock2, ChessTexture.haicham);
		clockTwo = new ClockPlayer(1, clockTurn, clockGame, 120, 1800);

		ScoreGroup scoreGroup = new ScoreGroup(ChessTexture.scoreBG, ChessTexture.numScore);
		scoreGroup.x = 100;
		scoreGroup.y = 0;

		float heightTop = betIcon.height;
		float heightBottom = Math.max(Math.max(scoreGroup.height, clockOne.height), clockTwo.height);
		ActorUtility.setCenter(scoreGroup, width / 2, heightBottom / 2);
		ActorUtility.setRatio(clockOne, 1.05f, 0.5f, scoreGroup.x, heightBottom / 2);
		ActorUtility.setRatio(clockTwo, -0.05f, 0.5f, scoreGroup.x + scoreGroup.width, heightBottom / 2);

		float pad = 5;
		boardGroup = new BoardGroup(width, height - heightBottom - heightTop - 2 * pad);
		boardGroup.y = heightBottom + pad;

		readyBtn = new ImageButton(CommonTexture.readyButton, CommonTexture.readyButtonClick);
		ActorUtility.setCenter(readyBtn, width / 2, height / 2);
		readyBtn.visible = false;
		readyBtn.setClickListener(this);
		
		startEffect = new Image(CommonTexture.startEffect.get(0));
		ActorUtility.setCenter(startEffect, width / 2, height / 2);		

		addActor(boardGroup);
		addActor(quitBtn);
		addActor(betIcon);
		addActor(chatBtn);
		addActor(clockOne);
		addActor(clockTwo);
		addActor(scoreGroup);

		addActor(readyBtn);		
		// Image test = new Image(ChessTexture.numScore.get(0));
		// addActor(test);
	}

	@Override
	public void act(float delta) {
		time += delta;
		super.act(delta);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.F1:
			MyChess.game.otherJoin();
			break;
		case Keys.F2:
			MyChess.game.startGame(0);
			break;
		case Keys.F3:
			MyChess.game.otherQuit();
		}
		return super.keyDown(keycode);
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == quitBtn)
			parent.showQuitDialog();
		else if (actor == chatBtn)
			parent.showQuitOtherDlg();
		else if (actor == readyBtn){
			Gdx.app.log(tag, "click Ready");
			readyBtn.visible = false;		
		}
	}

	public void otherJoin() {
		readyBtn.visible = true;
		boardGroup.initBoardPiece();
	}

	public void startGame(int firstTurn) {
		Gdx.app.log(tag, "start game");
		boardGroup.startGame(firstTurn);
		addActor(startEffect);
		startEffect.color.a = 1;
		startEffect.action(Sequence.$(FadeOut.$(1.5f), Remove.$()));
		
	}	
}
