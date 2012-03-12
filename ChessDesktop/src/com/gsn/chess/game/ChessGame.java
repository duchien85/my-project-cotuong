package com.gsn.chess.game;

import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.asset.ChessSound;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.DataProvider;
import com.gsn.chess.loading.LoadingAsset;
import com.gsn.chess.loading.LoadingScreen;
import com.gsn.chess.lobby.LobbyScreen;
import com.gsn.chess.lobby.PlayerInfo;
import com.gsn.chess.packet.CmdDefine;
import com.gsn.chess.packet.PacketFactory;
import com.gsn.chess.play.PlayScreen;
import com.gsn.engine.mercurry.MercuryClient.IMercuryListener;
import com.gsn.engine.myplay.GsnGame;

public class ChessGame extends GsnGame implements IMercuryListener {
	public LobbyScreen lobbyScreen;
	public PlayScreen playScreen;
	public LoadingScreen loadingScreen;
	float width;
	float height;
	static String tag = "Chess game";
	String session;

	public ChessGame(String session) {
		this.session = session;
		ChessTexture.create();
	}

	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		ChessTexture.create();
		ChessTexture.loadTexture();
		
		ChessSound.load();

		LoadingAsset.create();
		loadingScreen = new LoadingScreen(this, width, height);
		setScreen(loadingScreen);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {

	}

	public void otherJoin() {
		playScreen.boardLayer.otherJoin();
	}

	public void otherReady() {
		playScreen.boardLayer.otherReady();
	}

	public void startGame(int first) {
		Gdx.app.log(tag, "Start Game. first : + " + first);
		playScreen.boardLayer.startGame(first);
		nextTurn(first);
	}

	public void otherQuit() {
		playScreen.showQuitOtherDlg();
	}

	@Override
	public void onConnected() {
		session = "1F01898824E661A8CE722EB1";
		// session = "1F018988DE18E1";
		MyChess.client.send(PacketFactory.createLogin(session));
	}

	@Override
	public void onDisconnected() {
		Gdx.app.log(tag, "mercury disconnect");
		if (currentScreen == lobbyScreen) {
			lobbyScreen.showCantConnect();
		} else if (currentScreen == playScreen) {
			setLobbyScreen();
			lobbyScreen.showCantConnect();
		}

	}

	@Override
	public void onReceived(String s) {
		// TODO Auto-generated method stub

	}

	public boolean reserve;

	@Override
	public void onReceivedJson(JSONObject json) {
		Gdx.app.log(tag, "receive json: " + json);
		try {
			if (json.has("loginOK")) {
				if (json.getInt("loginOK") == 0) {
					MyChess.client.send(PacketFactory.createGUI());
				} else {
					Gdx.app.log(tag, "loginOK failure");
					lobbyScreen.showLoginFailure();
				}
				return;
			}
			int cmd = json.getInt(CmdDefine.CMD);
			switch (cmd) {
			case CmdDefine.CMD_GET_INFO:
				JSONObject pinfo = json.getJSONObject("pInfo");
				PlayerInfo info = new PlayerInfo(pinfo);
				DataProvider.myInfo = info;
				lobbyScreen.lobbyLayer.setUserInfo();
				break;
			case CmdDefine.CMD_JOIN_ROOM:
				JSONArray users = json.getJSONArray("users");
				for (int i = 0; i < users.length(); i++) {
					PlayerInfo tmp = new PlayerInfo(users.getJSONObject(i));
					if (tmp.id == DataProvider.myInfo.id) {
						DataProvider.myInfo = tmp;
					} else {
						DataProvider.otherInfo = tmp;
						playScreen.infoLayer.updateInfo();
					}
					otherJoin();
				}
				break;
			case CmdDefine.CMD_READY:
				otherReady();
				break;
			case CmdDefine.CMD_START:
				ChessSound.playSoundStart();
				int next = json.getInt("next");
				// int below = json.getInt("below");				
				int above = json.getInt("above");
				reserve = (above == DataProvider.myInfo.id);
				if (next == DataProvider.myInfo.id)
					startGame(0);
				else
					startGame(1);
				break;
			case CmdDefine.CMD_CHESS_MOVE:
				ChessSound.playSoundHit();
				int id = json.getInt("playerId");
				int fromRow = json.getInt("fromRow");
				int fromCol = json.getInt("fromCol");
				int toRow = json.getInt("toRow");
				int toCol = json.getInt("toCol");
				moveChess(id, fromRow, fromCol, toRow, toCol);
				break;
			case CmdDefine.CMD_OUT_ROOM:
				playScreen.showQuitOtherDlg();
				break;
			case CmdDefine.CMD_STOP:
				int canContinue = json.getInt("canContinue");
				if (json.has("winner")) {
					int winner = json.getInt("winner");
					if (winner == DataProvider.myInfo.id)
						win(canContinue);
					else
						lose(canContinue);
				} else
					draw(canContinue);
				break;
			case CmdDefine.ANSWER_DRAW:
				boolean agree = json.getBoolean("agree");
				playScreen.boardLayer.hideWaitThinking();
				if (!agree) {
					playScreen.showKoDongYHoa();
				}
				break;
			case CmdDefine.ASK_DRAW:
				playScreen.showCauHoa();
				break;
			case CmdDefine.CMD_UPDATE_STATUS:
				int pID = json.getInt("id");
				if (pID == DataProvider.myInfo.id) {
					PlayerInfo myInfo = DataProvider.myInfo;
					JSONArray arr = json.getJSONArray("properties");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject e = arr.getJSONObject(i);
						String name = e.getString("name");
						int value = e.getInt("value");
						if (name.equals("lose"))
							myInfo.lose = value;
						else if (name.equals("win"))
							myInfo.win = value;
						else if (name.equals("exp"))
							myInfo.exp = value;
						else if (name.equals("gold"))
							myInfo.gold = value;
						playScreen.infoLayer.updateInfo();
					}
				}

				if (DataProvider.otherInfo != null && DataProvider.otherInfo.id == pID) {
					PlayerInfo otherInfo = DataProvider.otherInfo;
					JSONArray arr = json.getJSONArray("properties");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject e = arr.getJSONObject(i);
						String name = e.getString("name");
						int value = e.getInt("value");
						if (name.equals("lose"))
							otherInfo.lose = value;
						else if (name.equals("win"))
							otherInfo.win = value;
						else if (name.equals("exp"))
							otherInfo.exp = value;
						else if (name.equals("gold"))
							otherInfo.gold = value;
						playScreen.infoLayer.updateInfo();
					}
				}
				break;
			case CmdDefine.CMD_CHAT_ROOM:
				String chat = json.getString("m");
				playScreen.boardLayer.chatOther(chat);
				break;
			case CmdDefine.CMD_EARN_MONEY:
				int type = json.getInt("type");
				int gold = json.getInt("gold");
				int data = json.getInt("data");
				if (type == 0)
					lobbyScreen.showDailyLogin(data, gold);
				break;
			}
		} catch (Exception e) {
			Gdx.app.error(tag, "Loi onReceive", e);
		}
	}

	public void setPlayScreen() {
		setScreen(playScreen);
	}

	public void moveChess(int id, int fromRow, int fromCol, int toRow, int toCol) {
		if (reserve) {
			fromRow = 9 - fromRow;
			fromCol = 8 - fromCol;
			toRow = 9 - toRow;
			toCol = 8 - toCol;
		}
		int turn = id == DataProvider.myInfo.id ? 0 : 1;
		if (turn == 1)
			playScreen.boardLayer.move(turn, fromRow, fromCol, toRow, toCol);
	}

	public void setLobbyScreen() {
		setScreen(lobbyScreen);
	}

	public void win(int canContinue) {
		Gdx.app.log(tag, "WIN");
		ChessSound.playSoundWin();
		playScreen.boardLayer.youWin(canContinue);
		playScreen.infoLayer.win();
	}

	public void lose(int canContinue) {
		Gdx.app.log(tag, "LOSE");
		ChessSound.playSoundLose();
		playScreen.boardLayer.youLose(canContinue);
		playScreen.infoLayer.lose();
	}

	public void draw(int canContinue) {
		ChessSound.playSoundDraw();
		Gdx.app.log(tag, "DRAW");
		playScreen.boardLayer.youDraw(canContinue);

	}

	public void nextTurn(int turn) {
		Gdx.app.log(tag, "*********next turn : " + turn);
		playScreen.boardLayer.nextTurn(turn);

	}

	public void onFinishLoading() {
		LoadingAsset.unload();

		lobbyScreen = new LobbyScreen(width, height);
		playScreen = new PlayScreen(width, height);

		setLobbyScreen();
		MyChess.client.connect();
	}

	@Override
	public void onException(Exception ex) {
		Gdx.app.log(tag, "loi mercury : ", ex);
	}
}
