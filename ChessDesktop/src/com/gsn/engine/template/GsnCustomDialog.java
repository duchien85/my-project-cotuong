package com.gsn.engine.template;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.engine.ActorUtility;

public class GsnCustomDialog extends Group {
	ImageButton[] buttons;
	private Image dialogBG;
	private GsnLabel contentLabel;

	public GsnCustomDialog(String title, Image background, ImageButton... buttons) {
		this.dialogBG = background;
		dialogBG.setClickListener(new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
			}
		});
		this.buttons = buttons;
		addActor(dialogBG);
		width = dialogBG.width;
		height = dialogBG.height;
		
		for (int i = 0; i < buttons.length; i++)
			addActor(buttons[i]);

		if (buttons.length == 2) {
			ActorUtility.setRatio(buttons[0], 0.5f, 0f, width * 0.25f, 0f);
			ActorUtility.setRatio(buttons[1], 0.5f, 0f, width * 0.75f, 0f);
		} else {
			float tmp = width / (buttons.length + 1);
			for (int i = 0; i < buttons.length; i++)
				ActorUtility.setRatio(buttons[i], 0.5f, 0, tmp * (i + 1), 0);
		}

		contentLabel = new GsnLabel(title, new GsnLabel.LabelStyle(ChessTexture.fontLarge, new Color(1, 1, 1, 1)));
		contentLabel.setAlignment(Align.CENTER);
		addActor(contentLabel);
		contentLabel.width = 0.8f * width;
		contentLabel.setWrap(true);	
		
		ActorUtility.setCenter(contentLabel, width / 2, height / 1.7f);
				
	}
	
	public static void main(String[] args) throws JSONException {
		String s = "{\"DATA\":[[1,1,\"United States\",\"Alabama\",1],[1,2,\"United States\",\"Alaska\",1],[1,3,\"United States\",\"Arizona\",1]],\"COLUMNS\":[\"COUNTRY_ID\",\"STATE_ID\",\"COUNTRY_NAME\",\"STATE_NAME\",\"COUNTRY_DEFAULT_COUNTRY_ID\"]}";
		System.out.println(s);
		JSONObject json = new JSONObject(s);
		JSONArray data = json.getJSONArray("DATA");
		System.out.println("-----DATA: ");
		for (int i = 0; i < data.length(); i++){
			System.out.print(" phan tu thu " + i + ": [");
			JSONArray arr = data.getJSONArray(i);
			for (int j = 0; j < data.length(); j++){
				System.out.print( arr.get(j)  + ", " );
			}
			System.out.println("]");
		}
		System.out.println("-----COLUMN: ");
		JSONArray column = json.getJSONArray("COLUMNS");
		for (int i = 0; i < column.length(); i++){
			System.out.print( column.get(i)  + ", " );
		}
	}
}
