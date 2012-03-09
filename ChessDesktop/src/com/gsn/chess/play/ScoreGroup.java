package com.gsn.chess.play;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.template.GsnLabel;

public class ScoreGroup extends Group {
	List<AtlasRegion> nums;
	GsnLabel label;
	Vector2 centerOne = new Vector2();
	
	Group twoGroup;
	Vector2 centerTwo = new Vector2();
	
	public ScoreGroup(TextureRegion background, List<AtlasRegion> nums ) {
		this.nums = nums;
		Image bg = new Image(background);
		addActor(bg);
		this.height = bg.height;
		this.width = bg.width;
		label = new GsnLabel("aaaaa", ChessTexture.fontLarge, new Color(0f, 0f, 0f, 1f));
		addActor(label);
		setScore(0, 0);
		
	}
	
	
	public void setScore(int one, int two){
		label.setText(one + " : " + two);
		ActorUtility.setRatio(label, 0.5f, 0, width / 2, height * 0.01f);
	}

}
