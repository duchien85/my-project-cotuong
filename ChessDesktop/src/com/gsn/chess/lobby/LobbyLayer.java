package com.gsn.chess.lobby;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.CommonTexture;
import com.gsn.engine.myplay.GsnLayer;

public class LobbyLayer extends GsnLayer {

	public LobbyLayer(float width, float height) {
		super(width, height);
		Image bg = new Image(CommonTexture.backgroundLobby);
		addActor(bg);
	}

}
