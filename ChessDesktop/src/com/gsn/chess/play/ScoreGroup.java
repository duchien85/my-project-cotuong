package com.gsn.chess.play;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;

public class ScoreGroup extends Group {
	List<AtlasRegion> nums;
	Group oneGroup;
	Vector2 centerOne = new Vector2();
	
	Group twoGroup;
	Vector2 centerTwo = new Vector2();
	
	public ScoreGroup(TextureRegion background, List<AtlasRegion> nums ) {
		this.nums = nums;
		Image bg = new Image(background);
		addActor(bg);
		this.height = bg.height;
		this.width = bg.width;		
		init();
		centerOne.x = oneGroup.x + oneGroup.width / 2;
		centerOne.y = oneGroup.y + oneGroup.height / 2;
		
		centerTwo.x = twoGroup.x + twoGroup.width / 2;
		centerTwo.y = twoGroup.y + twoGroup.height / 2;
		
		setScore(2, 4);
	}
	
	private void init(){
		int one = 88;
		int two = 88;
		if (oneGroup != null)
			oneGroup.remove();
		if (twoGroup != null)
			twoGroup.remove();
		oneGroup = convertNum(one);
		twoGroup = convertNum(two);
		ActorUtility.setRatio(oneGroup, -0.1f, 1.1f, 0, height);
		ActorUtility.setRatio(twoGroup, 1.1f, -0.1f, width, 0);
		addActor(oneGroup);
		addActor(twoGroup);
	}
	
	public void setScore(int one, int two){
		if (oneGroup != null)
			oneGroup.remove();
		if (twoGroup != null)
			twoGroup.remove();
		oneGroup = convertNum(one);
		twoGroup = convertNum(two);
		ActorUtility.setCenter(oneGroup, centerOne.x, centerOne.y);
		ActorUtility.setCenter(twoGroup, centerTwo.x, centerTwo.y);
		addActor(oneGroup);
		addActor(twoGroup);
	}
	
	private Group convertNum(Integer time){
		Group group = new Group();
		group.height = nums.get(0).getRegionHeight();
		String str = time.toString();		
//		if (time < 10)
//			str = '0' + str;
		for (int i = 0; i < str.length(); i++){			
			Image tmp = new Image((TextureRegion) (nums.get(str.charAt(i) - '0')));
		//	tmp.color.set(1, 0, 0, 1);
			tmp.x = group.width;
			group.width += tmp.width;
			group.addActor(tmp);
		}
		return group;
	}	
}
