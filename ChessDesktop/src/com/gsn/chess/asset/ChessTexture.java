package com.gsn.chess.asset;

import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class ChessTexture {
	static public final String tag = "Chess Texture";
	static public AssetManager manager;

	public static AtlasRegion board;
	public static AtlasRegion boardBG;
	public static AtlasRegion redCavarly;
	public static AtlasRegion redCatapult;
	public static AtlasRegion redGuard;
	public static AtlasRegion redSoldier;
	public static AtlasRegion redElephant;
	public static AtlasRegion redRook;
	public static AtlasRegion redGeneral;

	public static AtlasRegion blueCavarly;
	public static AtlasRegion blueCatapult;
	public static AtlasRegion blueGuard;
	public static AtlasRegion blueSoldier;
	public static AtlasRegion blueElephant;
	public static AtlasRegion blueRook;
	public static AtlasRegion blueGeneral;

	public static AtlasRegion effectSelect;
	public static AtlasRegion effectJustMove;
	public static AtlasRegion effectSuggest;
	
	public static AtlasRegion betIcon1000;
	public static AtlasRegion betIcon5000;
	public static AtlasRegion chatBtn;
	public static AtlasRegion chatBtnDown;
	public static AtlasRegion clock1green;
	public static AtlasRegion clock2green;
	public static AtlasRegion clock1red;
	public static AtlasRegion clock2red;
	public static AtlasRegion signX;
	public static AtlasRegion effectAnQuan;
	public static AtlasRegion effectChieuTuong;
	public static AtlasRegion settingBtn;
	public static AtlasRegion settingBtnDown;
	public static AtlasRegion scoreBG;
	public static List<AtlasRegion> numScore;
	public static List<AtlasRegion> numClock1;
	public static List<AtlasRegion> numClock2;
	public static AtlasRegion haicham;
	
	public static BitmapFont fontLarge;
	public static BitmapFont fontMedium;
	public static AtlasRegion background;
	public static AtlasRegion bet1000_1;
	public static AtlasRegion bet1000_2;
	public static AtlasRegion bet1000_3;
	public static AtlasRegion bet1000_4;
	
	public static AtlasRegion bet5000_1;
	public static AtlasRegion bet5000_2;
	public static AtlasRegion bet5000_3;
	public static AtlasRegion bet5000_4;
	public static AtlasRegion quickPlayBtn;
	public static AtlasRegion quickPlayBtnDown;
	public static AtlasRegion quickPlayBtnDisable;
	public static AtlasRegion readyButton;
	public static AtlasRegion readyButtonDown;
	public static AtlasRegion effectBatDau;
	public static AtlasRegion effectThang;
	public static AtlasRegion effectThua;
	public static AtlasRegion effectHoa;
	
	public static AtlasRegion greyBG;
	public static AtlasRegion yesBtn;
	public static AtlasRegion yesBtnDown;
	public static AtlasRegion cancelBtnDown;
	public static AtlasRegion cancelBtn;
	public static AtlasRegion dialogBg;
	public static AtlasRegion waitingBG;
	public static AtlasRegion exitBtn;
	public static AtlasRegion exitBtnDown;
	public static AtlasRegion soundBtn;
	public static AtlasRegion soundBtnDown;
	public static AtlasRegion soundBtnOff;
	public static AtlasRegion effectChieuMe;
	public static AtlasRegion effectChieuOther;	
	public static AtlasRegion iconSanSang;
	public static AtlasRegion clock1redBg;
	public static AtlasRegion clock1greenBg;
	public static AtlasRegion featureBtn;
	public static AtlasRegion featureBtnDown;
	
	public static AtlasRegion featureBG;
	public static AtlasRegion xinhoaBtn;
	public static AtlasRegion xinhoaBtnDown;
	public static AtlasRegion xinhoaBtnInvi;
	
	public static AtlasRegion xinthuaBtn;
	public static AtlasRegion xinthuaBtnDown;
	public static AtlasRegion xinthuaBtnInvi;
	public static AtlasRegion userinfoBG;
	public static AtlasRegion avatarBG;
	public static AtlasRegion scoreBoard;
	public static AtlasRegion infoLobby;
	public static AtlasRegion avatarLobby;
	
	public static List<AtlasRegion> chatMe;
	public static List<AtlasRegion> chatOther;

	static public void create() {
		Resolution[] resolutions = { new Resolution(240, 320, "240320")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
//		manager.setErrorListener(new AssetErrorListener() {
//			@Override
//			public void error(String fileName, Class type, Throwable throwable) {
//				// TODO Auto-generated method stub
//				Gdx.app.error(tag, "couldn't load asset '" + fileName + "'", (Exception) throwable);
//			}
//		});
		Texture.setAssetManager(manager);
	}

	static public void finishLoading() {		
		manager.finishLoading();
	}

	static public void loadAll() {
		loadTexture();
		finishLoading();
		assignContent();
	}

	public static void assignContent() {
		fontMedium = manager.get("chess/font/medium.fnt", BitmapFont.class);				
		fontLarge = manager.get("chess/font/large.fnt", BitmapFont.class);
		
		TextureAtlas atlas = manager.get("chess/content/pack", TextureAtlas.class);
	
		yesBtn = atlas.findRegion("dongyBtn");
		yesBtnDown = atlas.findRegion("dongyBtnDown");
		cancelBtn = atlas.findRegion("huyboBnt");
		cancelBtnDown = atlas.findRegion("huyboBntDown");
		dialogBg = atlas.findRegion("notice board");	
		greyBG = atlas.findRegion("BgAlpha");
		background = atlas.findRegion("BG240x320new"); 
		board = atlas.findRegion("board");
		boardBG = atlas.findRegion("boardBG");
		redCavarly = atlas.findRegion("do ma");
		redCatapult = atlas.findRegion("do phao");
		redGuard = atlas.findRegion("do sy");
		redSoldier = atlas.findRegion("do tot");
		redElephant = atlas.findRegion("do tuongj");
		redRook = atlas.findRegion("do xe");
		redGeneral = atlas.findRegion("do tuongs");

		blueCavarly = atlas.findRegion("xanh ma");		
		blueCatapult = atlas.findRegion("xanh phao");
		blueGuard = atlas.findRegion("xanh sy");
		blueSoldier = atlas.findRegion("xanh tot");
		blueElephant = atlas.findRegion("xanh tuongj");
		blueRook = atlas.findRegion("xanh xe");
		blueGeneral = atlas.findRegion("xanh tuongs");
		
		effectSelect = atlas.findRegion("effect chon");
		effectJustMove = atlas.findRegion("effect vua di");		
		effectSuggest = atlas.findRegion("goi y");
		effectAnQuan = atlas.findRegion("effect an");
		effectBatDau = atlas.findRegion("effect bat dau");
		effectThang = atlas.findRegion("effect thang");
		effectHoa = atlas.findRegion("effect hoa");
		effectThua = atlas.findRegion("effect thua");
		effectChieuTuong = atlas.findRegion("effect an");
		signX = atlas.findRegion("dau x");
		
		betIcon1000 = atlas.findRegion("beticon1000");
		betIcon5000 = atlas.findRegion("beticon5000");
		chatBtn = atlas.findRegion("chatBtn");
		chatBtnDown = atlas.findRegion("chatBtnDown");
		
		
		settingBtn = atlas.findRegion("settingBtn");
		settingBtnDown = atlas.findRegion("settingBtnDown");
		clock1green = atlas.findRegion("clock1green");
		clock2green = atlas.findRegion("clock2green");
		clock1red = atlas.findRegion("clock1red");
		clock2red = atlas.findRegion("clock2red");
		scoreBG = atlas.findRegion("scoreBG");		
		haicham = atlas.findRegion("hai cham");
		
		numClock1 = atlas.findRegions("numberClock1");
		numClock2 = atlas.findRegions("numberClock2");
		numScore = atlas.findRegions("numScore");
		
		bet1000_1 = atlas.findRegion("muc cuoc 1k");
		bet1000_2 = atlas.findRegion("muc cuoc 1k an");
		bet1000_3 = atlas.findRegion("muc cuoc 1k chon");
		bet1000_4 = atlas.findRegion("muc cuoc 1k disable");
		
		bet5000_1 = atlas.findRegion("muc cuoc 5k");
		bet5000_2 = atlas.findRegion("muc cuoc 5k an");
		bet5000_3 = atlas.findRegion("muc cuoc 5k chon");
		bet5000_4 = atlas.findRegion("muc cuoc 5k disable");
		
		quickPlayBtn = atlas.findRegion("choingayBtn");
		quickPlayBtnDown = atlas.findRegion("choingayBtnDown");
		quickPlayBtnDisable = atlas.findRegion("choingayBtnOff");
		
		readyButton = atlas.findRegion("sansangBtn");
		readyButtonDown = atlas.findRegion("sansangBtnDown");
		
		effectBatDau = atlas.findRegion("effect bat dau");
		
		waitingBG = atlas.findRegion("noticeBG");		
		
		exitBtn = atlas.findRegion("ExitBtn");
		exitBtnDown = atlas.findRegion("ExitBtnDown");
		soundBtn = atlas.findRegion("soundBtn");
		soundBtnDown = atlas.findRegion("soundBtnDown");
		soundBtnOff = atlas.findRegion("soundBtnOff");
		
		effectChieuMe = atlas.findRegion("effect chieu tuong to");
		effectChieuOther = atlas.findRegion("effect chieu tuong to nguoc");
		iconSanSang = atlas.findRegion("icon san sang");
		
		clock1redBg = atlas.findRegion("clock1redBg");
		clock1greenBg = atlas.findRegion("clock1greenBg");
		
		featureBtn = atlas.findRegion("button");
		featureBtnDown = atlas.findRegion("buttonDown");
		
		featureBG = atlas.findRegion("BG btn cauhoa-xin thua");
		xinhoaBtn = atlas.findRegion("cauhoaBtn");
		xinhoaBtnDown = atlas.findRegion("cauhoaBtnDown");
		xinhoaBtnInvi = atlas.findRegion("cauhoaBtn invi");
		
		xinthuaBtn = atlas.findRegion("xinthuaBtn");
		xinthuaBtnDown = atlas.findRegion("xinthuaBtnDown");
		xinthuaBtnInvi = atlas.findRegion("xinthuaBtn invi");
		
		userinfoBG = atlas.findRegion("userInfo");
		avatarBG = atlas.findRegion("avatar user");	
		scoreBoard = atlas.findRegion("scoreBoard");
		
		chatMe = atlas.findRegions("bubbleChatPhai");
		chatOther = atlas.findRegions("bubbleChatTrai");
		
		infoLobby = atlas.findRegion("Lobby userInfoBG");
		avatarLobby = atlas.findRegion("Lobby userInfoAvatar");
	}

	static public void loadTexture() {
		manager.load("chess/content/pack", TextureAtlas.class);
		manager.load("chess/font/medium.fnt", BitmapFont.class);		
		manager.load("chess/font/large.fnt", BitmapFont.class);
	}

	static public boolean update() {
		return manager.update();
	}
}
