package com.gsn.chess.play;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;

public class ClockGame extends Group {	
	List<AtlasRegion> nums;
	Image haicham;
	Group timeGroup;
	
	float standardTime;
	float remainTime;
	boolean pause = false;
	Image bg;	
	public ClockGame(TextureRegion background, List<AtlasRegion> nums, TextureRegion haicham) {
		this.nums = nums;
		this.haicham = new Image(haicham);
		bg = new Image(background);
		addActor(bg);
		this.width = bg.width;
		this.height = bg.height;							
	}
	
	private Group converNum(Integer num){
		Group group = new Group();
		group.height = nums.get(0).getRegionHeight();
		String str = num.toString();
		if (num < 10)
			str = '0' + str;
		for (int i = 0; i < str.length(); i++){			
			Image tmp = new Image((TextureRegion) (nums.get(str.charAt(i) - '0')));
			//tmp.color.set(0.7f, 0.7f, 0.2f, 1);
			tmp.x = group.width;
			group.width += tmp.width;
			group.addActor(tmp);
		}
		return group;
	}
	
	public void setClickListener(ClickListener listener){
		bg.setClickListener(listener);
	}
	
	private Group convertTime(int time){
		Group group = new Group();
		group.width = width;
		group.height = height;
		
		int m = time / 60;
		int s = time % 60;
		int pad = 2;
		
		Group minute = converNum(m);
		Group second = converNum(s);
		
		ActorUtility.setCenter(minute, width / 4, height / 2);
		ActorUtility.setCenter(haicham, group.width / 2, group.height / 2);
		ActorUtility.setCenter(second, width * 3f / 4, height / 2);
		
		group.addActor(minute);
		group.addActor(haicham);
		group.addActor(second);
				
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
	
	public void setTime(int time){
		if (timeGroup != null)
			timeGroup.remove();
		timeGroup = convertTime(time);
		ActorUtility.setCenter(timeGroup, width / 2, height / 2);
		addActor(timeGroup);		
	}
	
	public void reset(){
		remainTime = standardTime;
		setTime((int) remainTime);
	}
	
	@Override
	public void act(float delta) {			
		super.act(delta);
		if (!pause){
			remainTime -= delta;
			if (remainTime < 0)
				remainTime = 0;
			setTime((int)remainTime);
		}
	}
}
