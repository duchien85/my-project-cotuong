package com.vng.mycaro;

import com.gsn.chess.game.MyChess;
import com.gsn.engine.mercurry.MercuryClient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ChessService extends Service {
	public class ChessBinder extends Binder {
		public MercuryClient client;
	}

	ChessBinder binder;
	MercuryClient client;

	@Override
	public IBinder onBind(Intent arg0) {
		binder.client = client;
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		binder = new ChessBinder();
		client = new MercuryClient(MyChess.server, 443, MyChess.game);
	}
}
