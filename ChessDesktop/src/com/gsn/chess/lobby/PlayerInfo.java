package com.gsn.chess.lobby;


import org.json.JSONException;
import org.json.JSONObject;

public class PlayerInfo {
	public int id;
	public String username;	
	public String avatar;
	public int exp;
	public int win;
	public int lose;
	public int draw;	
	public int gold;
	
	public PlayerInfo() {	
	}
	
	public PlayerInfo(JSONObject json){
		try {
			id = json.getInt("id");
			if (json.has("username"))
				username = json.getString("username");
			else
				username = json.getString("name");
			avatar = json.getString("avatar");
			gold = json.getInt("gold");
			exp = json.getInt("exp");
			win = json.getInt("win");
			lose = json.getInt("lose");
			draw = json.getInt("draw");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
