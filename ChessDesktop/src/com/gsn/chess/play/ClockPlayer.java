package com.gsn.chess.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.gsn.engine.ActorUtility;

public class ClockPlayer extends Group{
	ClockTurn turnClock;
	ClockGame gameClock;
	int turnTime;
	int gameTime;
	
	public ClockPlayer(int type, ClockTurn clockTurn, ClockGame clockGame, int timeTurn, int timeGame) {
		this.turnClock = clockTurn;
		this.gameClock = clockGame;
		this.turnTime = timeTurn;
		this.gameTime = timeGame;

		this.height = clockTurn.height;
		this.width = clockGame.width * 0.9f + clockTurn.width;

		turnClock.startTime(timeTurn);
		turnClock.pause();
		gameClock.startTime(timeGame);
		turnClock.pause();
		switch (type) {		
		case 0:
			ActorUtility.setRatio(turnClock, 0f, 0f, 0, 0);
			ActorUtility.setRatio(gameClock, 1f, 0.5f, width, height / 2);
			break;
		case 1:
			ActorUtility.setRatio(turnClock, 1f, 0f, width, 0);
			ActorUtility.setRatio(gameClock, 0f, 0.5f, 0f, height / 2);
			break;
		}
		
		addActor(gameClock);
		addActor(turnClock);
	}

	public void pause() {
		gameClock.pause();
		turnClock.pause();
	}

	public void resume() {
		gameClock.resume();
		turnClock.resume();
	}

	public void otherTurn() {
		pause();
	}

	public void nextTurn() {
		float tmp = gameClock.remainTime;
		tmp = tmp - (int) tmp;
		// turnClock.reset();
		turnClock.remainTime = turnClock.standardTime + tmp;
		resume();
	}

	public void reset() {
		gameClock.reset();
		turnClock.reset();
	}
	
	public void setClickListener(ClickListener listener) {
		gameClock.setClickListener(listener);
		turnClock.setClickListener(listener);
	}
}
