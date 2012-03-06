package com.gsn.chess.lobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnLayer;
import com.gsn.engine.template.GsnCustomDialog;

public class GsnDialogYesNoLayer extends GsnLayer implements ClickListener{
	public String name;
	ImageButton yesBtn;
	ImageButton noBtn;
	IDialogYesNoListener listener;
	Image dlgBG;
	Image greyBG;
	
	public enum EDialogType{
		YES, YES_NO
	}
	
	public enum EButtonType{
		YES, NO
	}
	
	public interface IDialogYesNoListener{
		void clickButton(String nameDlg, EButtonType btn);
	}
	
	public GsnDialogYesNoLayer(TextureRegion greyBG, TextureRegion dlgBG, TextureRegion yesBtn, TextureRegion yesBtnDown, TextureRegion noBtn, TextureRegion noBtnDown) {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.greyBG = new Image(greyBG);
		this.dlgBG = new Image(dlgBG);
		this.yesBtn = new ImageButton(yesBtn, yesBtnDown);
		this.yesBtn.setClickListener(this);
		this.noBtn = new ImageButton(noBtn, noBtnDown);
		this.noBtn.setClickListener(this);
	}
	
	public void createDialog(EDialogType type, String nameDlg, String title){
		this.clear();
		this.name = nameDlg;
		GsnCustomDialog dialog = null;
		switch (type) {
		case YES_NO:
			dialog = new GsnCustomDialog(title, dlgBG, yesBtn, noBtn);
			break;
		case YES:
			dialog = new GsnCustomDialog(title, dlgBG, yesBtn);
			break;
		default:
			break;
		}	
		
		
		greyBG.width = width;
		greyBG.height = height;		
		
		ActorUtility.setCenter(dialog, width / 2, height / 2);
		
		addActor(greyBG);
		addActor(dialog);
	}
	
	public void setListener(IDialogYesNoListener listener){
		this.listener = listener;
	}

	@Override
	public void click(Actor actor, float x, float y) {
		hide();
		if (listener != null){
			if (actor == yesBtn)
				listener.clickButton(name, EButtonType.YES);
			else if (actor == noBtn)
				listener.clickButton(name, EButtonType.NO);
		} else 
			Gdx.app.log("Dialog ", "chua set Listener Dialog");		
	}
	
	public InputProcessor oldInput;
	
	public void show(){
		oldInput = Gdx.input.getInputProcessor();
		visible = true;
		setInputListener();		
	}
	
	public void hide(){
		Gdx.input.setInputProcessor(oldInput);
		oldInput = null;
		visible = false;
	}
}
