package com.gsn.chess.lobby;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.game.MyChess;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EButtonType;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EDialogType;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.IDialogYesNoListener;
import com.gsn.chess.play.DialogYesNoChessLayer;
import com.gsn.engine.myplay.GsnScreen;

public class LobbyScreen extends GsnScreen implements IDialogYesNoListener {
	public LobbyLayer lobbyLayer;
	public DialogYesNoChessLayer  dialogLayer;
	
	public final String DLG_CANT_CONNECT = "cant connect";
	public final String DLG_LOGIN_FAILURE = "login failure";
	
	public LobbyScreen(float width, float height) {
		super(width, height);	
		lobbyLayer = new LobbyLayer(width, height);
		addLayer(lobbyLayer, true);
		
		dialogLayer = new DialogYesNoChessLayer();		
		addLayer(dialogLayer, false);
		dialogLayer.setListener(this);
		
	}

	@Override
	public void setInputListener() {
		setInputLayer(lobbyLayer);
	}
	
	public void showCantConnect(){
		dialogLayer.createDialog(EDialogType.YES_NO, DLG_CANT_CONNECT, "Không thể kết nối đến server!!!\n Bạn có muốn thử lại không?");		
		dialogLayer.show();		
	}

	@Override
	public void clickButton(String nameDlg, EButtonType btn) {		
		if (nameDlg.equals(DLG_CANT_CONNECT)){
			switch (btn){
			case YES:
				lobbyLayer.init();
				MyChess.client.connect();
				break;
			case NO:
				Gdx.app.exit();
				break;
			}
		}
		
		if (nameDlg.equals(DLG_LOGIN_FAILURE)){
			switch (btn){
			case YES:
				Gdx.app.exit();
				break;
			}
		}
	}

	public void showLoginFailure() {
		dialogLayer.createDialog(EDialogType.YES, DLG_LOGIN_FAILURE, "Login Failure!!!");
		dialogLayer.show();		
	}	

}
