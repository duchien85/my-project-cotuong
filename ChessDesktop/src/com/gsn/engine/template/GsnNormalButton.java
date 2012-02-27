package com.gsn.engine.template;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GsnNormalButton extends ImageButton {

	public String nameButton;
	public GsnNormalButton(String name, TextureRegion regionUp, TextureRegion regionDown) {
		super(regionUp, regionDown);
		this.nameButton = name;
	}
	
	public GsnNormalButton(String name, TextureRegion regionUp, TextureRegion regionDown, TextureRegion regionSelect) {
		super(regionUp, regionDown, regionSelect);
		this.nameButton = name;
	}
}
