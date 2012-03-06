package com.gsn.chess.game;

import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.CommonTexture;
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
	LobbyScreen lobbyScreen;
	PlayScreen playScreen;
	LoadingScreen loadingScreen;
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
		Gdx.app.log(tag, "other READY");
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
		// TODO Auto-generated method stub
		session = "1F0189885E5AB41EF7EE03F2";
		MyChess.client.send(PacketFactory.createLogin(session));
	}

	@Override
	public void onDisconnected() {
		Gdx.app.log(tag, "mercury disconnect");

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
				}
				return;
			}
			int cmd = json.getInt(CmdDefine.CMD);
			switch (cmd){
			case CmdDefine.CMD_GET_INFO:
				JSONObject pinfo = json.getJSONObject("pInfo");
				PlayerInfo info = new PlayerInfo(pinfo);
				DataProvider.myInfo = info;				
				System.out.println(" day ne :  " + info.id + " " + info.avatar);
				loadingScreen.loadingLayer.connected = true;
				break;	
			case CmdDefine.CMD_JOIN_ROOM:
				JSONArray users = json.getJSONArray("users");
				for (int i = 0; i < users.length(); i++){
					PlayerInfo tmp = new PlayerInfo(users.getJSONObject(i));
					if (tmp.id == DataProvider.myInfo.id){
						DataProvider.myInfo = tmp;						
					}
					else 
						DataProvider.otherInfo = tmp;
					otherJoin();
				}					
				break;
			case CmdDefine.CMD_READY:
				otherReady();
				break;
			case CmdDefine.CMD_START:				
				int next = json.getInt("next");
				//int below = json.getInt("below");
				int above = json.getInt("above");
				reserve = (above == DataProvider.myInfo.id);
				if (next == DataProvider.myInfo.id)
					startGame(0);
				else 
					startGame(1);
				break;
			case CmdDefine.CMD_CHESS_MOVE:
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
				int canContinue = json.getInt("canContinue") ; 
				if (json.has("winner")){
					int winner = json.getInt("winner");
					if (winner == DataProvider.myInfo.id)
						win(canContinue);
					else
						lose(canContinue);					
				} else
					draw(canContinue);				
				break;
			}
		} catch (Exception e) {
			Gdx.app.error(tag, "Loi onReceive", e);
		}
	}

	public void setPlayScreen() {
		setScreen(playScreen);		
	}	
	
	public void moveChess(int id, int fromRow, int fromCol, int toRow, int toCol){				
		if (reserve){
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
		playScreen.boardLayer.youWin(canContinue);		
	}

	public void lose(int canContinue) {
		Gdx.app.log(tag, "LOSE");
		playScreen.boardLayer.youLose(canContinue);		
	}

	public void draw(int canContinue) {
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

	}	
}
