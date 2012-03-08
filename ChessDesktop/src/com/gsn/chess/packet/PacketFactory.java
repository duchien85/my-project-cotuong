package com.gsn.chess.packet;

import org.json.JSONException;
import org.json.JSONObject;

public class PacketFactory {
	private static JSONObject create() {
		JSONObject json = new JSONObject();
		try {
			json.put("ext", "cc");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public static JSONObject createGUI() {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.CMD_GET_INFO);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}	

	public static JSONObject createQuickPlay(int betLv) {
		// 100, 500, 5000
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.CMD_INSTANT_PLAY);
			JSONObject params = new JSONObject().put("active", true);			
			params.put("bet", betLv);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createLogin(String session) {
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.CMD_CUSTOM_LOGIN);
			JSONObject params = new JSONObject().put("sessionKey", session);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createReady() {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.CMD_READY);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject createMove(int fromRow, int fromCol, int toRow, int toCol) {	
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.CMD_CHESS_MOVE);
			params.put("fromRow", fromRow);
			params.put("fromCol", fromCol);
			params.put("toRow", toRow);
			params.put("toCol", toCol);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject createOutRoom() {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.CMD_OUT_ROOM);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return json;
	}
	
	public static JSONObject createAskDrawn() {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.ASK_DRAW);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return json;
	}
	
	public static JSONObject createAskLose() {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.ASK_LOSE);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject createAnswerDrawn(boolean agree) {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {			
			json.put(CmdDefine.CMD, CmdDefine.ANSWER_DRAW);
			params.put("agree", agree);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return json;
	}
}
