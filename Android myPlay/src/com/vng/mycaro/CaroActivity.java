package com.vng.mycaro;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gsn.chess.game.ChessGame;

public class CaroActivity extends AndroidApplication {
    /** Called when the activity is first created. */
	ChessGame game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		createWakeLock(config);
		game = new ChessGame();
		//this.bindService(new Intent(this, CaroService.class), conn, Context.BIND_AUTO_CREATE);
		initialize(game, config);
      //  setContentView(R.layout.main);
    }
}