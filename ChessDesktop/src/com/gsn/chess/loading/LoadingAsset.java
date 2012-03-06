package com.gsn.chess.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class LoadingAsset {
	public static AtlasRegion dotText;
	public static AtlasRegion loadingBG;
	public static AtlasRegion loadingText;
	static AssetManager manager;

	public static void create() {
		Resolution[] resolutions = { new Resolution(240, 320, "240320"), new Resolution(480, 800, "480800") };
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
		manager.setErrorListener(new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable throwable) {
				// TODO Auto-generated method stub
				Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", throwable);
			}
		});
		Texture.setAssetManager(manager);
		manager.load("chess/loading/pack", TextureAtlas.class);
		manager.finishLoading();
		TextureAtlas atlas = manager.get("chess/loading/pack", TextureAtlas.class);
		loadingBG = atlas.findRegion("loading");
		loadingText = atlas.findRegion("chu loading");
		dotText = atlas.findRegion("dau cham");
	}

	public static void unload() {
		manager.unload("chess/loading/pack");
	}
}
