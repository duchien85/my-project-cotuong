package com.gsn.chess.play;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.asset.ChessSound;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.DataProvider;
import com.gsn.chess.game.MyChess;
import com.gsn.chess.lobby.GsnDialogYesNoLayer;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EButtonType;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.EDialogType;
import com.gsn.chess.lobby.GsnDialogYesNoLayer.IDialogYesNoListener;
import com.gsn.chess.lobby.SettingLayer;
import com.gsn.chess.packet.PacketFactory;
import com.gsn.engine.myplay.GsnScreen;

public class PlayScreen extends GsnScreen implements IDialogYesNoListener {
	static final String tag = "Play Screen";

	static final String DLG_QUIT = "quit";
	static final String DLG_OTHER_QUIT = "other quit";

	private static final String DLG_XIN_THUA = "xin thua";

	private static final String DLG_CAU_HOA = "cau hoa";

	public BoardLayer boardLayer;
	public SettingLayer settingLayer;
	public InfoLayer infoLayer;
	public GsnDialogYesNoLayer dialogLayer;

	public PlayScreen(float width, float height) {
		super(width, height);
		init();
	}

	public void init() {
		boardLayer = new BoardLayer(width, height);
		dialogLayer = new DialogYesNoChessLayer();
		dialogLayer.setDialogListener(this);

		settingLayer = new SettingLayer(width, height);
		infoLayer = new InfoLayer(width, height);
		
		addLayer(boardLayer, true);
		
		addLayer(infoLayer, false);

		addLayer(settingLayer, false);

		addLayer(dialogLayer, false);
	}

	@Override
	public void setInputListener() {
		setInputLayer(boardLayer);
	}

	@Override
	public void clickButton(String nameDlg, EButtonType btn) {
		// Gdx.app.log(tag, "click yes btn, DLG : " + nameDlg);
		ChessSound.playSoundClick();
		if (btn == EButtonType.YES) {
			if (nameDlg.equals(DLG_QUIT)) {
				MyChess.client.send(PacketFactory.createOutRoom());
				MyChess.game.setLobbyScreen();
			} else if (nameDlg.equals(DLG_OTHER_QUIT)) {
				MyChess.client.send(PacketFactory.createOutRoom());
				MyChess.game.setLobbyScreen();
			} else if (nameDlg.equals(DLG_XIN_THUA)) {
				Gdx.app.log(tag, "BAN DA XIN THUA");
				MyChess.client.send(PacketFactory.createAskLose());
			} else if (nameDlg.equals(DLG_CAU_HOA)) {
				MyChess.client.send(PacketFactory.createAnswerDrawn(true));
				inCauHoa = false;
			}
		} else if (btn == EButtonType.NO) {
			if (nameDlg.equals(DLG_CAU_HOA)) {
				MyChess.client.send(PacketFactory.createAnswerDrawn(false));
				inCauHoa = false;
				boardLayer.hideWaitThinking();

			}
		}
	}

	public void showQuitDialog() {
		dialogLayer.createDialog(EDialogType.YES_NO, DLG_QUIT, "Bạn có muốn thoát khỏi phòng không?");
		dialogLayer.show();
	}

	public void showQuitOtherDlg() {
		dialogLayer.createDialog(EDialogType.YES, DLG_OTHER_QUIT, "Đối thủ của bạn đã thoát khỏi phòng!!");
		dialogLayer.show();
	}

	@Override
	public void onShowScreen() {
		super.onShowScreen();
		init();		
		if (DataProvider.betLvl == 5000)
			boardLayer.betIcon.setRegion(ChessTexture.betIcon5000);
		else
			boardLayer.betIcon.setRegion(ChessTexture.betIcon1000);
	}

	public void showSettingLayer() {
		setVisibleLayer(settingLayer, true);
		settingLayer.setInputListener();
		settingLayer.loadSetting();
	}
	
	@Override
	public void onHideScreen() {
		DataProvider.otherInfo = null;
		super.onHideScreen();
	}

	public void hideSettingLayer() {
		setVisibleLayer(settingLayer, false);
		boardLayer.setInputListener();
	}

	public void showXinThua() {
		dialogLayer.createDialog(EDialogType.YES_NO, DLG_XIN_THUA, "Bạn có muốn xin thua không?");
		dialogLayer.show();

	}

	public void showKoDongYHoa() {
		dialogLayer.createDialog(EDialogType.YES, "Doi Thu ko dong y hoa", "Đối thủ không đồng ý hòa?");
		dialogLayer.show();

	}

	boolean inCauHoa = false;

	public void showCauHoa() {
		dialogLayer.createDialog(EDialogType.YES_NO, DLG_CAU_HOA, "Đối thủ cầu hòa!!!\nBạn có đồng ý không?");
		dialogLayer.show();
		inCauHoa = true;
		boardLayer.clockOne.pause();
		boardLayer.clockTwo.pause();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (inCauHoa) {
					MyChess.client.send(PacketFactory.createAnswerDrawn(false));
					inCauHoa = false;
					dialogLayer.hide();
					boardLayer.hideWaitThinking();
				}
			}
		}, 4500);

	}

	public void showInfoLayer() {
		setVisibleLayer(infoLayer, true);
		infoLayer.setInputListener();		
	}
	
	public void hideInfoLayer() {
		setVisibleLayer(infoLayer, false);
		boardLayer.setInputListener();		
	}
}
