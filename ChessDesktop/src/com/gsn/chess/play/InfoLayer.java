package com.gsn.chess.play;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.asset.DataProvider;
import com.gsn.chess.lobby.UserInfoGroup;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;

public class InfoLayer extends GsnLayer {
	private UserInfoGroup infoMe;
	private UserInfoGroup infoOther;
	ScoreGroup scoreGroup;
	int winOne = 0;
	int winTwo = 0;
	
	public InfoLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(ChessTexture.greyBG);
		bg.width = width;
		bg.height = height;
		addActor(bg);
		
		infoMe = new UserInfoGroup(DataProvider.myInfo);
		infoOther = new UserInfoGroup(DataProvider.otherInfo);
		
		ActorUtility.setCenter(infoMe, width / 4, height / 2);
		ActorUtility.setCenter(infoOther, 3 * width / 4, height / 2);
		
		scoreGroup = new ScoreGroup(ChessTexture.scoreBoard, ChessTexture.numClock1);
		scoreGroup.visible = infoOther.visible;
				
		ActorUtility.setRatio(scoreGroup, 0.5f, 1f, width / 2, height);
		
		addActor(scoreGroup);
		
		addActor(infoMe);
		addActor(infoOther);
				
	}
	
	public void win() {		
		winOne++;
		scoreGroup.setScore(winOne, winTwo);
	}
	
	public void lose() {		
		winTwo++;
		scoreGroup.setScore(winOne, winTwo);
	}
	
	public void updateInfo(){
		infoMe.setInfo(DataProvider.myInfo);
		infoOther.setInfo(DataProvider.otherInfo);
		scoreGroup.visible = infoOther.visible;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		((PlayScreen)parent).hideInfoLayer();
		return super.touchUp(x, y, pointer, button);
	}
}
