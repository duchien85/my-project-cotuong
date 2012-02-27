package com.gsn.engine.myplay.gamezoom;

import com.gsn.engine.GsnPinchToZoom;
import com.gsn.engine.GsnPinchToZoom.ITouchUpWithoutZoomListener;
import com.gsn.engine.myplay.GsnLayer;

abstract public class GsnBoardLayer extends GsnLayer implements ITouchUpWithoutZoomListener{
	public GsnPinchToZoom pinch;
	public GsnBoardLayer(float width, float height) {
		super(width, height);
		pinch = new GsnPinchToZoom(this);
	}

	public void resetPinchToZoom() {
		pinch.reset();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		pinch.keyDown(keycode);
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		pinch.keyUp(keycode);
		return super.keyUp(keycode);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		pinch.touchDown(x, y, pointer, button);
		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		pinch.touchDragged(x, y, pointer);
		return super.touchDragged(x, y, pointer);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		pinch.touchUp(x, y, pointer, button);
		return super.touchUp(x, y, pointer, button);
	}
}
