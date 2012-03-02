package com.gsn.engine.template;

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
}
