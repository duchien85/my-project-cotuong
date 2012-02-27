package com.gsn.engine.myplay.gamezoom;

import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.myplay.GsnLayer;

abstract public class GsnMenuLayer extends GsnLayer {

	public GsnMenuLayer(float width, float height) {
		super(width, height);		
	}

	abstract public GsnRectangle getRectangleBound();

}
