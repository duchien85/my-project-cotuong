package com.gsn.chess.lobby;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnLabel;

public class LobbyLayer extends GsnLayer {

	public LobbyLayer(float width, float height) {
		super(width, height);
		BitmapFont font = new BitmapFont();
		int x = 100;
		int y = 100;
		Label goc = new Label("goc", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		goc.x = x;
		goc.y = y;
		
		Group group = new Group();
		group.x = 50;
		group.y = 50;
	
		GsnLabel my = new GsnLabel("myLabel", new GsnLabel.LabelStyle(font, new Color(1, 1, 1, 1)));
		//TrungLabel my = new TrungLabel();
		my.x = 50;
		my.y = 50;
		
		group.addActor(my);
		addActor(group);
		
		
		addActor(goc);
		addActor(group);
	}

}
