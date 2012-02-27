package com.gsn.caro.lobby;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TrungLabel extends Actor {	
	BitmapFont font = new BitmapFont();
	String text = "aaaaa";
	public void setText(String text){
		this.text = text;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, "aaa aaa", x, y);
		
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
