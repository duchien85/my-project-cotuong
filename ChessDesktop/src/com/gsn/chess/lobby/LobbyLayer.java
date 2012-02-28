package com.gsn.chess.lobby;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnEnableButton;

public class LobbyLayer extends GsnLayer {

	public LobbyLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(CommonTexture.backgroundLobby);
		addActor(bg);
		
		GsnEnableButton bet100 = new GsnEnableButton(1, "quick play", CommonTexture.bet100_1, CommonTexture.bet100_2, CommonTexture.bet100_3, CommonTexture.bet100_4);
		
		addActor(bet100);
		bet100.setEnable(false);
	}
}
