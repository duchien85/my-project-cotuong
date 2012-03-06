package com.gsn.chess.play;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;

public class ClockTurn extends Group {
	List<AtlasRegion> nums;
	Group number;
	float standardTime;
	float remainTime;
	boolean pause = false;
	
	public ClockTurn(TextureRegion background, List<AtlasRegion> nums) {
		this.nums = nums;
		Image bg = new Image(background);
		addActor(bg);
		this.width = bg.width;
		this.height = bg.height;							
	}
	
	private Group convertNum(Integer time){
		Group group = new Group();
		group.height = nums.get(0).getRegionHeight();
		String str = time.toString();
		for (int i = 0; i < str.length(); i++){			
			Image tmp = new Image((TextureRegion) (nums.get(str.charAt(i) - '0')));
			tmp.color.set(0.7f, 0.7f, 0.2f, 1);
			tmp.x = group.width;
			group.width += tmp.width;
			group.addActor(tmp);
		}
		return group;
	}	
	
	public void pause(){
		pause = true;
	}
	
	public void resume(){
		pause = false;
	}
	
	public void startTime(int time){
		remainTime = time;
		standardTime = time;
		setTime(time);
	}
	
	public void setTime(float time){
		if (number != null)
			number.remove();
		remainTime = time;
		number = convertNum((int)time);
		ActorUtility.setCenter(number, width / 2, height / 2);
		addActor(number);		
	}
	
	public void reset(){
		remainTime = standardTime;
		setTime(remainTime);
	}
	
	@Override
	public void act(float delta) {			
		super.act(delta);
		if (!pause){
			remainTime -= delta;
			if (remainTime < 0)
				remainTime = 0;
			setTime(remainTime);
		}
	}
	
}
