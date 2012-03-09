package com.gsn.chess.lobby;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.chess.asset.ChessTexture;
import com.gsn.chess.game.MyChess;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.IDowloader.IImageDownloadListener;
import com.gsn.engine.template.GsnLabel;

public class UserInfoGroup extends Group implements IImageDownloadListener{
	Image avatarDefault;
	PlayerInfo info;
	boolean loadAvatar = false;
	File avatarFile = null;

	public UserInfoGroup(PlayerInfo info) {
		avatarDefault = new Image(ChessTexture.avatarBG);
		setInfo(info);
	}

	public void setInfo(PlayerInfo info) {
		this.info = info;
		clear();
		Image bg = new Image(ChessTexture.userinfoBG);
		addActor(bg);
		this.width = bg.width;
		this.height = bg.height;

		if (info == null) {
			visible = false;
			return;
		}

		visible = true;

		GsnLabel name = new GsnLabel(ActorUtility.getShortName(info.username), ChessTexture.fontMedium, new Color(1f, 1f, 1f, 1f));
		ActorUtility.setRatio(name, 0.5f, 1, width / 2, height);

		ActorUtility.setRatio(avatarDefault, 0.5f, 1, width / 2, name.y);

		GsnLabel exp = new GsnLabel("Điểm : " + info.exp, ChessTexture.fontMedium, new Color(1f, 1f, 1f, 1f));
		ActorUtility.setRatio(exp, 0.5f, 0.75f, width / 2, avatarDefault.y);

		GsnLabel win = new GsnLabel("Thắng\n" + info.win, ChessTexture.fontMedium, new Color(1f, 1f, 1f, 1f));
		win.setAlignment(Align.CENTER);
		ActorUtility.setRatio(win, 0.5f, 0.25f, width / 4, height / 4);

		GsnLabel lose = new GsnLabel("Thua\n" + info.lose, ChessTexture.fontMedium, new Color(1f, 1f, 1f, 1f));
		lose.setAlignment(Align.CENTER);
		ActorUtility.setRatio(lose, 0.5f, 0.25f, 3 * width / 4, height / 4);

		GsnLabel gold = new GsnLabel(String.valueOf(info.gold), ChessTexture.fontLarge, new Color(1f, 1f, 0.5f, 1f));
		ActorUtility.setRatio(gold, 0f, 0.5f, width * 33 / 103, height * 17 / 160);

		addActor(gold);
		addActor(lose);
		addActor(win);
		addActor(exp);
		addActor(name);
		addActor(avatarDefault);
		
		MyChess.client.downloader.saveBitmapToFileAsync("me", info.avatar, 64, 64, info.id + ".png", this);
	}

	public void setAvatar(TextureRegion region) {
		if (!loadAvatar) {
			Gdx.app.log("User Info Group", " load avatar : " + avatarFile.getAbsolutePath());
			loadAvatar = true;
			Image tmp;
			tmp = new Image(region);
			tmp.width = avatarDefault.width;
			tmp.height = avatarDefault.height;

			avatarDefault = tmp;
			setInfo(info);
		}
	}

	@Override
	public void onError(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinishLoading(String id, File outFile) {
		avatarFile = outFile;		
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if (avatarFile != null){		
			Texture texture = new Texture(new FileHandle(avatarFile));
			TextureRegion region = new TextureRegion(texture);
			setAvatar(region);			
			avatarFile = null;			
		}		

	}
}
