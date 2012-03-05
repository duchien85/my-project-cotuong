package com.vng.mycaro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gsn.chess.game.ChessGame;
import com.gsn.chess.game.MyChess;
import com.vng.mycaro.ChessService.ChessBinder;

public class ChessActivity extends AndroidApplication {    
	String tag = "Chess Activity";
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(tag, "on disconnect service");			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(tag, "on Connect Service");			
			ChessBinder binder = (ChessBinder) service;
			MyChess.client = binder.client;			
			MyChess.client.connect();
		}
	};
	
	ChessGame game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "on create");
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		createWakeLock(config);
        game = new ChessGame("123DSDA");
		MyChess.game = game;		
		initialize(game, config);
        
        this.bindService(new Intent(this, ChessService.class), conn, Context.BIND_AUTO_CREATE);
        
    	
		
       // setContentView(R.layout.main);
    }
}