package com.gsn.chess.play;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.lobby.GsnDialogYesNoLayer;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EDialogType;
import com.gsn.chess.lobby.LobbyLayer;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EButtonType;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.IDialogYesNoListener;
import com.gsn.engine.myplay.GsnScreen;

public class PlayScreen extends GsnScreen implements IDialogYesNoListener {
	static final String tag = "Play Screen";

	static final String DLG_QUIT = "quit";
	static final String DLG_OTHER_QUIT = "other quit";

	BoardLayer boardLayer;
	LobbyLayer lobbyLayer;
	GsnDialogYesNoLayer dialogLayer;

	public PlayScreen(float width, float height) {
		super(width, height);
		boardLayer = new BoardLayer(this, width, height);
		// lobbyLayer = new LobbyLayer(this,width, height);
		dialogLayer = new DialogYesNoChessLayer();
		dialogLayer.setListener(this);

		addLayer(boardLayer, true);
		addLayer(dialogLayer, false);
	}

	@Override
	public void setInputListener() {
		setInputLayer(boardLayer);
	}

	@Override
	public void clickButton(String nameDlg, EButtonType btn) {
		Gdx.app.log(tag, "click yes btn, DLG : " + nameDlg);
		if (btn == EButtonType.YES) {
			if (nameDlg.equals(DLG_QUIT)) {
				Gdx.app.exit();
			} else if (nameDlg.equals(DLG_OTHER_QUIT)){
				Gdx.app.log(tag, "OTHER QUIT");
			}
		}
	}

	public void showQuitDialog() {
		dialogLayer.createDialog(EDialogType.YES_NO, DLG_QUIT, "Bạn có muốn thoát không?");
		dialogLayer.show();
	}

	public void showQuitOtherDlg() {
		dialogLayer.createDialog(EDialogType.YES, DLG_OTHER_QUIT, "Doi thu cua ban da thoat khoi phong!!");
		dialogLayer.show();

	}

}
