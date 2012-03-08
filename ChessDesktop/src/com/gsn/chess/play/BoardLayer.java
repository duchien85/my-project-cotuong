package com.gsn.chess.play;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.game.MyChess;
import com.gsn.chess.packet.PacketFactory;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnEnableButton;

public class BoardLayer extends GsnLayer implements ClickListener {
	public static final String tag = "Board Layer";
	
	public static final int MIN_XIN_HOA = 1;
	BoardGroup boardGroup;
	float time = 0;
	float timeEffect = 1.5f;
	ClockPlayer clockOne;
	ClockPlayer clockTwo;
	ImageButton settingBtn;
	ImageButton chatBtn;
	ImageButton featureBtn;

	ImageButton readyBtn;
	Image startEffect;
	Image winEffect;
	Image loseEffect;
	Image drawEffect;
	
	Image iconReadyMe;
	Image iconReadyOther;
	
	Group featureGroup;
	GsnEnableButton xinhoaBtn;
	GsnEnableButton xinthuaBtn;
	
	Image greyBG;
	Image waitThinkingNocite;
	
	int turnXinHoa = -1;
	int numXinHoa = 0;
	public BoardLayer(float width, float height) {
		super(width, height);
		init();
//		 Image test = new Image(ChessTexture.numScore.get(0));
//		 addActor(test);
	}
	
	public void init(){
		clear();
		Image bg = new Image(ChessTexture.background);
		bg.width = width;
		bg.height = height;
		addActor(bg);
		
		greyBG = new Image(ChessTexture.greyBG);
		greyBG.width = width;
		greyBG.height = height;
		greyBG.setClickListener(this);
		waitThinkingNocite = new Image(ChessTexture.waitingNotice);
		ActorUtility.setCenter(waitThinkingNocite, width / 2, height / 2);
		
		Image betIcon = new Image(ChessTexture.betIcon1000);
		ActorUtility.setRatio(betIcon, 0, 1, 0, height);

		settingBtn = new ImageButton(ChessTexture.settingBtn, ChessTexture.settingBtnDown);
		ActorUtility.setRatio(settingBtn, 1, 1, width, height);
		settingBtn.setClickListener(this);

		chatBtn = new ImageButton(ChessTexture.chatBtn, ChessTexture.chatBtnDown);
		ActorUtility.setRatio(chatBtn, 1, 0, settingBtn.x, settingBtn.y);
		chatBtn.setClickListener(this);

		ClockTurn clockTurn = new ClockTurn(ChessTexture.clock1greenBg, ChessTexture.clock1green, ChessTexture.numClock1);
		ClockGame clockGame = new ClockGame(ChessTexture.clock2green, ChessTexture.numClock2, ChessTexture.haicham);
		clockOne = new ClockPlayer(0, clockTurn, clockGame, 120, 900);
		clockOne.pause();

		clockTurn = new ClockTurn(ChessTexture.clock1redBg, ChessTexture.clock1red, ChessTexture.numClock1);
		clockGame = new ClockGame(ChessTexture.clock2red, ChessTexture.numClock2, ChessTexture.haicham);
		clockTwo = new ClockPlayer(1, clockTurn, clockGame, 120, 900);
		clockTwo.pause();

		featureBtn = new ImageButton(ChessTexture.featureBtn, ChessTexture.featureBtnDown);		
		featureBtn.setClickListener(this);
		float heightTop = betIcon.height;
		float heightBottom = Math.max(Math.max(featureBtn.height, clockOne.height), clockTwo.height);
		ActorUtility.setCenter(featureBtn, width / 2, heightBottom / 2);
		ActorUtility.setRatio(clockOne, 1.1f, 0.5f, featureBtn.x, heightBottom / 2);
		ActorUtility.setRatio(clockTwo, -0.1f, 0.5f, featureBtn.x + featureBtn.width, heightBottom / 2);

		float pad = 5;
		boardGroup = new BoardGroup(this, width, height - heightBottom - heightTop - 2 * pad);
		boardGroup.y = heightBottom + pad;

		readyBtn = new ImageButton(ChessTexture.readyButton, ChessTexture.readyButtonDown);
		ActorUtility.setCenter(readyBtn, width / 2, height / 2);
		readyBtn.visible = false;
		readyBtn.setClickListener(this);
		
		startEffect = new Image(ChessTexture.effectBatDau);
		ActorUtility.setCenter(startEffect, width / 2, height / 2);		
		startEffect.color.a = 0;
		
		winEffect = new Image(ChessTexture.effectThang);
		ActorUtility.setCenter(winEffect, width / 2, height / 2);		
		winEffect.color.a = 0;
		
		loseEffect = new Image(ChessTexture.effectThua);
		ActorUtility.setCenter(loseEffect, width / 2, height / 2);		
		loseEffect.color.a = 0;
		
		drawEffect = new Image(ChessTexture.effectHoa);
		ActorUtility.setCenter(drawEffect, width / 2, height / 2);		
		drawEffect.color.a = 0;
		
		iconReadyMe = new Image(ChessTexture.iconSanSang);
		ActorUtility.setCenter(iconReadyMe, width / 2, height / 4);
		iconReadyOther = new Image(ChessTexture.iconSanSang);
		ActorUtility.setCenter(iconReadyOther, width / 2, height * 3 / 4);
		
		featureGroup = new Group();
		featureGroup.visible = false;
		Image featureBG = new Image(ChessTexture.featureBG);
		xinhoaBtn = new GsnEnableButton(1, "", ChessTexture.xinhoaBtn, ChessTexture.xinhoaBtnDown, ChessTexture.xinhoaBtnInvi);
		xinthuaBtn = new GsnEnableButton(1, "", ChessTexture.xinthuaBtn, ChessTexture.xinthuaBtnDown, ChessTexture.xinthuaBtnInvi);
		featureBG.setClickListener(this);
		xinhoaBtn.setAndSaveClickListener(this);
		xinthuaBtn.setAndSaveClickListener(this);
		
		featureGroup.width = featureBG.width;
		featureGroup.height = featureBG.height;
		ActorUtility.setCenter(xinhoaBtn, featureGroup.width / 4, featureGroup.height * 0.6f);
		ActorUtility.setCenter(xinthuaBtn, 3 * featureGroup.width / 4, featureGroup.height * 0.6f);		
		ActorUtility.setRatio(featureGroup, 0.5f, 0, width / 2, heightBottom);
		
		
		featureGroup.addActor(featureBG);
		featureGroup.addActor(xinhoaBtn);
		featureGroup.addActor(xinthuaBtn);
		
		addActor(boardGroup);
		addActor(settingBtn);
		addActor(betIcon);
		addActor(chatBtn);
		addActor(clockOne);
		addActor(clockTwo);
		addActor(featureBtn);		
		
		addActor(featureGroup);
		
		addActor(readyBtn);	
		addActor(startEffect);
		addActor(winEffect);
		addActor(loseEffect);
		addActor(drawEffect);
	}
	

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.F1:
			hideWaitThinking();
			break;
		case Keys.F2:
			MyChess.game.startGame(0);
			break;
		case Keys.F3:
			MyChess.game.otherQuit();
			break;
		case Keys.F4:
			MyChess.game.win(0);
			break;
		case Keys.F5:
			MyChess.game.lose(0);
			break;
		case Keys.F6:
			MyChess.game.draw(0);
			break;
		}
		return super.keyDown(keycode);
	}

	@Override
	public void click(Actor actor, float x, float y) {
		if (actor == settingBtn)
			((PlayScreen) parent).showSettingLayer();
		else if (actor == chatBtn)
			((PlayScreen) parent).showQuitOtherDlg();
		else if (actor == readyBtn){
			Gdx.app.log(tag, "click Ready");
			addActor(iconReadyMe);
			readyBtn.visible = false;	
			MyChess.client.send(PacketFactory.createReady());
		} else if (actor == featureBtn){
			featureGroup.visible = !featureGroup.visible;  
			setVisibleFeature();
		} else if (actor == xinthuaBtn){
			((PlayScreen) parent).showXinThua();			
		} else if (actor == xinhoaBtn){
			showWaitThinking();
			turnXinHoa = boardGroup.turn;
			numXinHoa++;
			setVisibleFeature();
			MyChess.client.send(PacketFactory.createAskDrawn());
		}
	}
	
	public void setVisibleFeature(){
		xinhoaBtn.setEnable(boardGroup.turn >= MIN_XIN_HOA && boardGroup.turn != turnXinHoa && numXinHoa < 3);
		xinthuaBtn.setEnable(boardGroup.turn >= MIN_XIN_HOA);
	}
	
	public void showWaitThinking(){
		addActor(greyBG);
		addActor(waitThinkingNocite);
		clockOne.pause();
		clockTwo.pause();
	}
	
	public void hideWaitThinking(){
		removeActor(greyBG);
		removeActor(waitThinkingNocite);
		clockOne.resume();
		clockTwo.resume();
	}

	public void otherJoin() {
		readyBtn.visible = true;
		boardGroup.initBoardPiece();
	}

	public void startGame(int firstTurn) {
		Gdx.app.log(tag, "start game");
		iconReadyMe.remove();
		iconReadyOther.remove();
		
		turnXinHoa = -1;
		numXinHoa = 0;
		setVisibleFeature();
		
		boardGroup.startGame(firstTurn);
		startEffect.color.a = 1;
		startEffect.action(Sequence.$(FadeOut.$(timeEffect)));
		clockOne.reset();
		clockTwo.reset();
	}
	
	public void delayEffect(final Image effect, final float delay, final int canContinue){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {			
				effect.color.a = 1;
				effect.action(Sequence.$(FadeOut.$(timeEffect)));
				if (canContinue == 0)
					appearReady(timeEffect);
				
			}
		}, (int)(delay * 1000));
	}
	
	public void youWin(int canContinue){	
		if (boardGroup.chieu >= 0){
			Gdx.app.log(tag, " DELAY EFFECT");
			delayEffect(winEffect, timeEffect, canContinue);
		}
		else
			delayEffect(winEffect, 0, canContinue);
		clockOne.pause();
		clockTwo.pause();				
	}
	
	public void youLose(int canContinue){
		if (boardGroup.chieu >= 0){
			Gdx.app.log(tag, " DELAY EFFECT");
			delayEffect(loseEffect, timeEffect, canContinue);
		}
		else
			delayEffect(loseEffect, 0, canContinue);
		clockOne.pause();
		clockTwo.pause();			
	}
		
	
	public void youDraw(int canContinue){
		if (boardGroup.chieu >= 0){
			Gdx.app.log(tag, " DELAY EFFECT");
			delayEffect(drawEffect, timeEffect, canContinue);
		}
		else
			delayEffect(drawEffect, 0, canContinue);
		clockOne.pause();
		clockTwo.pause();
		
	}
	
	public void appearReady(float delay){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {				
				readyBtn.visible = true;
				
			}
		}, (int)(delay * 1000));
	}
	
	public void move(int turn, int fromRow, int fromCol, int toRow, int toCol) {		
		boardGroup.moveChess(turn, fromRow, fromCol, toRow, toCol);
	}


	public void nextTurn(int turn) {
		if (turn == 0){
			clockOne.nextTurn();
			clockTwo.otherTurn();
		} else {
			clockOne.otherTurn();
			clockTwo.nextTurn();
		}
		
	}

	public void otherReady() {
		addActor(iconReadyOther);		
	}	
}
