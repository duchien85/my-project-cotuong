package com.gsn.chess.lobby;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;

public class Notice extends Group {
	public Notice(String text) {
		Image bg = new Image(ChessTexture.waitingBG);
		this.width = bg.width;
		this.height = bg.height;
		addActor(bg);
		
		GsnLabel label = new GsnLabel(text, ChessTexture.fontMedium, new Color(0, 0, 0, 1));
		label.width = 0.8f * width;
		label.setWrap(true);
		label.setAlignment(Align.CENTER);
		
		ActorUtility.setCenter(label, width / 2, height / 4);
		addActor(label);
	}
}
