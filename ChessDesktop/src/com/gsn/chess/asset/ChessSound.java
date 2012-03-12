package com.gsn.chess.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.gsn.chess.game.ChessSetting;

public class ChessSound {
	public static Sound hitSound;
	public static Sound soundClick;
	public static Sound soundLose;
	public static Sound soundTime;
	public static Sound soundWin;
	public static Sound soundDraw;
	public static Sound soundStart;

	public static void load() {				
		hitSound = Gdx.audio.newSound(Gdx.files.internal("chess/sound/DrawX.mp3"));
		soundWin = Gdx.audio.newSound(Gdx.files.internal("chess/sound/Win.mp3"));
		soundLose = Gdx.audio.newSound(Gdx.files.internal("chess/sound/Lose.mp3"));
		soundLose = Gdx.audio.newSound(Gdx.files.internal("chess/sound/Lose.mp3"));
		soundClick = Gdx.audio.newSound(Gdx.files.internal("chess/sound/ButtonClick.mp3"));
		soundTime = Gdx.audio.newSound(Gdx.files.internal("chess/sound/TimeCountDown.mp3"));
		soundStart = Gdx.audio.newSound(Gdx.files.internal("chess/sound/StartGame.mp3"));
	}

	public static void playSoundHit() {

		playSound(hitSound);
	}

	public static void playSound(Sound sound) {
		if (ChessSetting.enableSound)
			sound.play();
	}

	public static void playSoundClick() {
		playSound(soundClick);
	}

	public static void playSoundLose() {

		playSound(soundLose);
	}

	public static void playSoundTime() {		
		playSound(soundTime);
	}
	public static void stopSoundTime() {
		soundTime.stop();
		
	}
	public static void playSoundWin() {
		playSound(soundWin);
	}
	
	public static void playSoundDraw() {
		playSound(soundWin);
	}
	
	public static void playSoundStart() {
		playSound(soundStart);
	}
}
