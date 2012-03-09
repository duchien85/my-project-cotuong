package com.gsn.engine;

import java.io.File;

public abstract class IDowloader {
	public static interface IImageDownloadListener {
		void onError(Exception e);

		void onFinishLoading(String id, File outFile);
	}
	public File cacheDir;
	
	public abstract void saveBitmapToFileAsync(String id, final String link, final int width, final int height, final String localPath, final IImageDownloadListener listener);	
}
