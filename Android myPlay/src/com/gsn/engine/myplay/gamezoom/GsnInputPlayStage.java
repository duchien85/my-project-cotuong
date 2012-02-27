package com.gsn.engine.myplay.gamezoom;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.gsn.engine.GdxUtility;

public class GsnInputPlayStage implements InputProcessor {
	public GsnBoardLayer boardLayer;
	public GsnMenuLayer menuLayer;
	public GsnPlayScreen playScreen;
	public boolean touchBoard = true;

	Vector2 vector = new Vector2();

	public GsnInputPlayStage(GsnPlayScreen stage) {
		this.playScreen = stage;
		this.boardLayer = stage.boardLayer;
		this.menuLayer = stage.menuLayer;
	}

	private void checkCanTouchBoard(int x, int y) {
		menuLayer.toStageCoordinates(x, y, vector);
		if (GdxUtility.inRectangle(vector.x, vector.y, menuLayer.getRectangleBound())) {
			boardLayer.resetPinchToZoom();
			touchBoard = false;
		}
	}

	public void dontTouchBoard() {
		touchBoard = false;
		boardLayer.resetPinchToZoom();
	}

	@Override
	public boolean keyDown(int keycode) {	
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		boardLayer.keyTyped(character);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		boardLayer.keyUp(keycode);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return true;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		menuLayer.touchDown(x, y, pointer, button);
		checkCanTouchBoard(x, y);
		if (touchBoard)
			boardLayer.touchDown(x, y, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		menuLayer.touchDragged(x, y, pointer);
		checkCanTouchBoard(x, y);
		if (touchBoard)
			boardLayer.touchDragged(x, y, pointer);
		return true;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		menuLayer.touchUp(x, y, pointer, button);
		checkCanTouchBoard(x, y);
		if (touchBoard)
			boardLayer.touchUp(x, y, pointer, button);
		touchBoard = true;
		return true;
	}
}
