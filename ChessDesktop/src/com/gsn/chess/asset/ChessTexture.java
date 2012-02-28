package com.gsn.chess.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
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
		blueElephant = atlas.findRegion("xanh");
		blueRook = atlas.findRegion("xanh tuongs");
		blueGeneral = atlas.findRegion("xanh xe");
		effectSelect = atlas.findRegion("okBtn");
		effectJustMove = atlas.findRegion("okBtn");
		effectSuggest = atlas.findRegion("okBtn");

	}

	static public void loadTexture() {
		manager.load("chess/content/pack", TextureAtlas.class);
		//manager.load("common/font/medium.fnt", BitmapFont.class);		
	}

	static public boolean update() {
		return manager.update();
	}
}
