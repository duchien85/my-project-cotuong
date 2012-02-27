package com.gsn.caro.asset;

import java.util.List;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gsn.engine.gdx.GsnParticleEffect;

public class CaroTexture {
	final static String tag = "Texture Asset";
	
	static public AtlasRegion avatar;

	static public AtlasRegion avatarLobby;

	static public AtlasRegion backDeactiveBtn;
	static public AtlasRegion backgroundLobby;
	// lobby
	static public TextureRegion bet100_1;
	static public TextureRegion bet100_2;
	static public TextureRegion bet100_3;
	static public TextureRegion bet100_4;
	static public TextureRegion bet10G_1;
	static public TextureRegion bet10G_2;
	static public TextureRegion bet10G_3;
	static public TextureRegion bet10G_4;
	static public TextureRegion bet1G_1;
	static public TextureRegion bet1G_2;
	static public TextureRegion bet1G_3;
	static public TextureRegion bet1G_4;
	static public TextureRegion bet500_1;
	static public TextureRegion bet500_2;
	static public TextureRegion bet500_3;
	static public TextureRegion bet500_4;
	static public TextureRegion bet5000_1;
	static public TextureRegion bet5000_2;
	static public TextureRegion bet5000_3;

	static public TextureRegion bet5000_4;
	static public AtlasRegion betBG;

	static public AtlasRegion board;
	static public List<AtlasRegion> board9Path;

	static public AtlasRegion boardBG;

	static public AtlasRegion boardBorder;
	static public List<AtlasRegion> bubleChatMe9Path;
	static public List<AtlasRegion> bubleChatOther9Path;
	static public AtlasRegion cancelBtn;

	static public AtlasRegion cancelBtnDown;
	static public AtlasRegion chatBtn;
	static public AtlasRegion chatBtnDown;

	static public GsnParticleEffect clickEffect = new GsnParticleEffect();
	static public AtlasRegion clockBG;
	static public AtlasRegion dialogBg;

	static public List<AtlasRegion> drawEffect;
	static public TextureRegion enterRoom;

	static public TextureRegion enterRoomClick;
	static public TextureRegion enterRoomDisable;
	static public TextureRegion exitClick;
	static public TextureRegion exitNormal;
	static public BitmapFont mediumFont = new BitmapFont();
	static public BitmapFont largeFont = new BitmapFont();
	static public AtlasRegion greyBG;
	static public AtlasRegion hideInfoBtn;
	static public AtlasRegion hideInfoBtnDown;

	static public AtlasRegion iconGold;
	static public AtlasRegion iconMe;
	static public AtlasRegion iconOther;
	static public AtlasRegion iconXu;

	static public AtlasRegion infoBg;
	static public List<AtlasRegion> infoUserBG9Path;
	static public List<AtlasRegion> listOEffect;
	static public List<AtlasRegion> listOWinEffect;

	static public List<AtlasRegion> listXEffect;
	static public List<AtlasRegion> listXWinEffect;
	static public TextureRegion login;
	static public TextureRegion loginClick;

	static public List<AtlasRegion> loseEffect;
	static public AssetManager manager;
	static public AtlasRegion menuBg;
	static  public AtlasRegion menuBtn;
	static  public AtlasRegion menuBtnDown;
	static  public TextureRegion musicClick;
	static  public TextureRegion musicOff;
	static public TextureRegion musicOn;
	static public AtlasRegion num100Gold;
	static public AtlasRegion num10G;
	static public AtlasRegion num1G;

	static public AtlasRegion num5000Gold;
	static public AtlasRegion num500Gold;
	static public List<AtlasRegion> numberPointBlue;
	static public List<AtlasRegion> numberPointRed;
	static public TextureRegion oEffect;
	static public AtlasRegion okBtn;
	static public AtlasRegion okBtnDown;
	static public AtlasRegion pieceO;
	static public AtlasRegion pieceX;
	static public AtlasRegion quickChat;

	static public TextureRegion readyButton;

	static public TextureRegion readyButtonClick;

	static public AtlasRegion readyItem;

	static public AtlasRegion scoreBG;

	static public TextureRegion sendButton;

	static public TextureRegion sendButtonClick;

	static public AtlasRegion showInfoBtn;

	static public AtlasRegion showInfoBtnDown;

	static public TextureRegion soundClick;

	static public TextureRegion soundOff;

	static public TextureRegion soundOn;

	static public List<AtlasRegion> startEffect;
	static public List<AtlasRegion> timeNumbers;
	static public AtlasRegion waitOpponent;
	static public AtlasRegion waitOpponentReady;

	static public AtlasRegion waitOther;
	static public List<AtlasRegion> winEffect;
	static public TextureRegion xEffect;
	
	static public void assignContent() {
		clickEffect.load(Gdx.files.internal("data/particle/click.p"), Gdx.files.internal("data/particle"));
		TextureAtlas atlas = manager.get("data/content/pack", TextureAtlas.class);

		okBtn = atlas.findRegion("okBtn");
		cancelBtn = atlas.findRegion("cancelBtn");
		cancelBtnDown = atlas.findRegion("cancelBtnDown");
		okBtnDown = atlas.findRegion("okBtnDown");
		dialogBg = atlas.findRegion("dialogBg");
		clockBG = atlas.findRegion("khung dong ho");
		betBG = atlas.findRegion("nen muc cuoc");
		scoreBG = atlas.findRegion("bang ti so");
		backDeactiveBtn = atlas.findRegion("nut back an");
		showInfoBtn = atlas.findRegion("nut so");
		showInfoBtnDown = atlas.findRegion("nut so an");
		board = atlas.findRegion("ban choi");
		pieceO = atlas.findRegion("dau o");
		pieceX = atlas.findRegion("dau x");
		boardBorder = atlas.findRegion("khung ban choi");

		board9Path = atlas.findRegions("boarder");
		boardBG = atlas.findRegion("BG ban choi");

		waitOpponent = atlas.findRegion("waitOpponent");
		menuBtn = atlas.findRegion("nut menu");
		menuBtnDown = atlas.findRegion("nut menu an");

		iconGold = atlas.findRegion("icon $ muc cuoc");

		iconMe = atlas.findRegion("icon nguoi choi");
		iconOther = atlas.findRegion("icon doi thu");
		bubleChatOther9Path = atlas.findRegions("bubble-chat-2");
		bubleChatMe9Path = atlas.findRegions("bubble-chat-1");
		infoUserBG9Path = atlas.findRegions("nen-thong-tin");

		hideInfoBtn = atlas.findRegion("nut dong");
		hideInfoBtnDown = atlas.findRegion("nut dong an");

		chatBtn = atlas.findRegion("nut chat");
		chatBtnDown = atlas.findRegion("nut chat an");
		avatar = atlas.findRegion("khung avatar");
		greyBG = atlas.findRegion("nen-xam");

		musicClick = atlas.findRegion("nut nhac an");
		musicOn = atlas.findRegion("nut nhac");
		musicOff = atlas.findRegion("nut nhac tat");

		soundClick = atlas.findRegion("nut loa an");
		soundOn = atlas.findRegion("nut loa");
		soundOff = atlas.findRegion("nut loa tat");

		exitNormal = atlas.findRegion("nut thoat");
		exitClick = atlas.findRegion("nut thoat an");

		bet100_1 = atlas.findRegion("muc cuoc 100$");
		bet100_2 = atlas.findRegion("muc cuoc 100$ an");
		bet100_3 = atlas.findRegion("muc cuoc 100$ chon");
		bet100_4 = atlas.findRegion("muc cuoc 100$ disable");

		bet500_1 = atlas.findRegion("muc cuoc 500$");
		bet500_2 = atlas.findRegion("muc cuoc 500$ an");
		bet500_3 = atlas.findRegion("muc cuoc 500$ chon");
		bet500_4 = atlas.findRegion("muc cuoc 500$ disable");

		bet5000_1 = atlas.findRegion("muc cuoc 5k$");
		bet5000_2 = atlas.findRegion("muc cuoc 5k$ an");
		bet5000_3 = atlas.findRegion("muc cuoc 5k$ chon");
		bet5000_4 = atlas.findRegion("muc cuoc 5k$ disable");

		bet1G_1 = atlas.findRegion("muc cuoc 1G");
		bet1G_2 = atlas.findRegion("muc cuoc 1G an");
		bet1G_3 = atlas.findRegion("muc cuoc 1G chon");
		bet1G_4 = atlas.findRegion("muc cuoc 1G disable");

		bet10G_1 = atlas.findRegion("muc cuoc 10G");
		bet10G_2 = atlas.findRegion("muc cuoc 10G an");
		bet10G_3 = atlas.findRegion("muc cuoc 10G chon");
		bet10G_4 = atlas.findRegion("muc cuoc 10G disable");

		enterRoom = atlas.findRegion("nut choi ngay");
		enterRoomClick = atlas.findRegion("nut choi ngay an");
		enterRoomDisable = atlas.findRegion("nut choi ngay disable");
		backgroundLobby = atlas.findRegion("BG lobby");
		avatarLobby = atlas.findRegion("khung avatar lobby");

		num100Gold = atlas.findRegion("cuoc 100$");
		num500Gold = atlas.findRegion("cuoc 500$");
		num5000Gold = atlas.findRegion("cuoc 5k$");
		num1G = atlas.findRegion("cuoc 1G");
		num10G = atlas.findRegion("cuoc 10G");
		iconXu = atlas.findRegion("icon G muc cuoc");
		numberPointRed = atlas.findRegions("ti so do");
		numberPointBlue = atlas.findRegions("ti so xanh");
		quickChat = atlas.findRegion("khung chat");
		sendButton = atlas.findRegion("nut gui");
		sendButtonClick = atlas.findRegion("nut gui an");
		waitOther = atlas.findRegion("vui long cho doi thu");
		login = atlas.findRegion("nut dang nhap");
		loginClick = atlas.findRegion("nut dang nhap an");
		readyItem = atlas.findRegion("tem san sang");
		readyButton = atlas.findRegion("nut san sang");
		readyButtonClick = atlas.findRegion("nut san sang an");
		menuBg = atlas.findRegion("nen BG");

		winEffect = atlas.findRegions("Thang");
		loseEffect = atlas.findRegions("Thua");
		drawEffect = atlas.findRegions("Hoa");
		startEffect = atlas.findRegions("Batdau");
		timeNumbers = atlas.findRegions("time vang");
		oEffect = atlas.findRegion("dauOeffect");
		xEffect = atlas.findRegion("dauXeffect");
		listOEffect = atlas.findRegions("4O");
		listXEffect = atlas.findRegions("4X");

		listOWinEffect = atlas.findRegions("5O");
		listXWinEffect = atlas.findRegions("5X");
		infoBg = atlas.findRegion("bang thong tin");
		waitOpponentReady = atlas.findRegion("waitOpponentReady");

		mediumFont = manager.get("data/font/medium.fnt", BitmapFont.class);
		largeFont = manager.get("data/font/large.fnt", BitmapFont.class);
		// font = new BitmapFont();
	}

	static public void create() {
		Resolution[] resolutions = { new Resolution(240, 320, "240320"), new Resolution(480, 800, "480800") };
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
		manager.setErrorListener(new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable throwable) {
				// TODO Auto-generated method stub
				Gdx.app.error(tag, "couldn't load asset '" + fileName + "'", (Exception) throwable);
			}
		});
		Texture.setAssetManager(manager);
	}

	static public void finishLoading() {
		long begin = System.currentTimeMillis();
		manager.finishLoading();
		long now = System.currentTimeMillis();
		Log.i("asset", "loading content :" + (now - begin));
	}

	static public void loadAll() {
		loadTexture();
		finishLoading();
		assignContent();
	}

	static public void loadTexture() {
		manager.load("data/content/pack", TextureAtlas.class);
		manager.load("data/font/medium.fnt", BitmapFont.class);
		manager.load("data/font/large.fnt", BitmapFont.class);
	}		

	static public boolean update() {
		return manager.update();
	}
}
