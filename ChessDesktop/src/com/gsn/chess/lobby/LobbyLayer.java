package com.gsn.chess.lobby;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnEnableButton;

public class LobbyLayer extends GsnLayer {

	public LobbyLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(CommonTexture.backgroundLobby);
		bg.width = width;
		bg.height = height;
		addActor(bg);
		
		GsnEnableButton bet1000 = new GsnEnableButton(1, "quick play", CommonTexture.bet100_1, CommonTexture.bet100_2, CommonTexture.bet100_3, CommonTexture.bet100_4);
		GsnEnableButton bet5000 = new GsnEnableButton(1, "quick play", CommonTexture.bet500_1, CommonTexture.bet500_2, CommonTexture.bet500_3, CommonTexture.bet500_4);
		
		GsnEnableButton quickPlayBtn = new GsnEnableButton(1, "", CommonTexture.enterRoom, CommonTexture.enterRoomClick, CommonTexture.enterRoomDisable);
		addActor(quickPlayBtn);
		quickPlayBtn.setEnable(false);
	}
}
