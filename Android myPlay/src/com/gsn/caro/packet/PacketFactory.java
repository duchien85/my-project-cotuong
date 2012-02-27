package com.gsn.caro.packet;

import org.json.JSONException;
import org.json.JSONObject;

public class PacketFactory {
	private static JSONObject create() {
		JSONObject json = new JSONObject();
		try {
			json.put("ext", "caro");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public static JSONObject createChat(String s) {
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.CHAT);
			JSONObject params = new JSONObject().put("message", s);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createLogin(String session) {
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.LOGIN);
			JSONObject params = new JSONObject().put("username", session);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static JSONObject createMobileLog(String device, String system, String place, String name) {
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.INFO);
			JSONObject params = new JSONObject().put("device", device);
			params.put("system", system);
			params.put("place", place);
			params.put("name", name);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createMove(int turn, int cell) {
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {
			params.put("whoseTurn", turn);
			params.put("cell", cell);
			json.put(CmdDefine.CMD, CmdDefine.CHESS_MOVE);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject createOutRoom() {
		try {
			return create().put(CmdDefine.CMD, CmdDefine.OUT_ROOM);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createQuickPlay(int betLv) {
		// 100, 500, 5000
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.INSTANCE_PLAY);
			JSONObject params = new JSONObject().put("active", true);
			params.put("type", "gold");
			params.put("betLv", betLv);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createQuickPlay(int betLv, int betType) {
		// 100, 500, 5000
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.INSTANCE_PLAY);
			JSONObject params = new JSONObject().put("active", true);
			if (betType == Constant.BET_GOLD)
				params.put("type", "gold");
			else if (betType == Constant.BET_COIN)
				params.put("type", "xu");
			params.put("betLv", betLv);
			// params.put("betType", betType);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject createReady() {
		try {
			return create().put(CmdDefine.CMD, CmdDefine.GAME_READY);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
