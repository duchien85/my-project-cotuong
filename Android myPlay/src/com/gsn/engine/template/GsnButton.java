package com.gsn.engine.template;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class GsnButton extends GsnNormalButton {
	ClickListener clickListener;
	ImageButtonStyle disableState;
	Boolean enable;
	ImageButtonStyle enableState;
	public int id;
	public String nameButton;
	public GsnButton(int id, String name, TextureRegion normal, TextureRegion down, TextureRegion disable) {
		super(name, normal, down, normal);
		
		enableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, normal, down, normal);

		if (disable != null) {
			disableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, disable, disable, disable);
		}
		this.id = id;
	}

	public GsnButton(int id, String name,TextureRegion normal, TextureRegion down, TextureRegion up, TextureRegion disable) {
		super(name, normal, down, up);
		enableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, normal, down, up);

		if (disable != null) {
			disableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, disable, disable, disable);
		}
		this.id = id;
	}

	public void setAndSaveClickListener(ClickListener listener) {
		super.setClickListener(listener);
		clickListener = listener;
	}
	
	public void setEnable(boolean _enable) {
		enable = _enable;
		if (enable) {
			super.setStyle(enableState);
			setClickListener(clickListener);
		} else {
			super.setStyle(disableState);
			setClickListener(null);
		}
	}
}
