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
		effectSelect = atlas.findRegion("effect Chon");
		effectJustMove = atlas.findRegion("effect vua di");
		effectSuggest = atlas.findRegion("goi y");
		effectAnQuan = atlas.findRegion("effect an");
		effectChieuTuong = atlas.findRegion("effect chieu tuong");
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
