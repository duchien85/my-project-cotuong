package com.gsn.chess.loading;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.game.ChessGame;
import com.gsn.engine.myplay.GsnLayer;

public class LoadingLayer extends GsnLayer {
	boolean added;
	Image[] dotText = new Image[3];
	ChessGame game;
	float time;
	public boolean connected = false;
	public boolean assignContent = false;

	public LoadingLayer(ChessGame game, float width, float height) {
		super(width, height);
		this.game = game;
		time = 0;
		Image background = new Image(LoadingAsset.loadingBG);
		this.addActor(background);

		Image loadingText = new Image(LoadingAsset.loadingText);

		loadingText.x = width - loadingText.width - getRatioWidth(0.1f);
		loadingText.y = getRatioHeight(0f);		
		this.addActor(loadingText);
		added = true;
		for (int i = 0; i < 3; i++) {
			dotText[i] = new Image(LoadingAsset.dotText);
			dotText[i].x = loadingText.x + loadingText.width + dotText[i].width * (2 * i + 1);
			dotText[i].y = loadingText.y + loadingText.height / 3;
			this.addActor(dotText[i]);
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);		
		
		if (!assignContent) {
			if (ChessTexture.update()) {
				ChessTexture.assignContent();
				assignContent = true;
			}
		} else if (connected)
			game.onFinishLoading();

		time += delta;
		if (time > 0.4f) {
			time = 0;
			added = !added;
			if (added) {
				addActor(dotText[2]);
			} else {
				removeActor(dotText[2]);
			}
		}
	}
}
