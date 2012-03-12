package com.gsn.chess.play;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;

public class BubbleChat extends Group {
	GsnLabel label;
	
	final float padHeight = 5;
	final float padWidth  = 8;
	private String tag = "Bublle Chat";
	public BubbleChat(List<AtlasRegion> bg, float width, BitmapFont font, String text) {
		Image img = new Image(new NinePatch(bg.get(0), bg.get(1), bg.get(2), bg.get(3), bg.get(4), bg.get(5),bg.get(6), bg.get(7), bg.get(8)));
		label = new GsnLabel(text, font, new Color(0f, 0f, 0f, 1f));
		label.width = width - 2 * padWidth;
		label.setAlignment(Align.CENTER);
		label.setWrap(true);		
		
		Gdx.app.log(tag , "label : " + label.width + " * " + label.height);
		Gdx.app.log(tag , "label BOUND: " + label.getTextBounds().width + " * " + label.getTextBounds().height);
		img.width = width;
		img.height = label.getTextBounds().height + padHeight * 2;		
		
//		img.width = 100;
//		img.height = 200;
		
		this.width = img.width;
		this.height = img.height;
		
		ActorUtility.setCenter(label, width / 2, height / 2 + padHeight / 2);
		
		addActor(img);
		addActor(label);
	}
}
