package com.gsn.engine;

import java.io.File;

import com.badlogic.gdx.Gdx;

public class DownloadDesktop extends IDowloader{
	public File cacheDir;
	public void saveBitmapToFileAsync(String id, final String link, final int width, final int height, final String localPath, final IImageDownloadListener listener) {
		File outFile = Gdx.files.internal("tmp/avatar.png").file();
		listener.onFinishLoading(id, outFile);
	}

//	public static void saveBitmapToFile(String link, int width, int height, int quality, OutputStream out) {
//		URL url;
//		URLConnection uCon;
//		Bitmap bMap;
//		Bitmap des;
//		try {
//			url = new URL(link);
//			uCon = url.openConnection();
//			bMap = BitmapFactory.decodeStream(uCon.getInputStream());
//			des = Bitmap.createScaledBitmap(bMap, width, height, true);
//			des.compress(Bitmap.CompressFormat.PNG, quality, out);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			Log.e("Gallery", "loi gi nay", e);
//		}
//	}
//	
//	public static void saveBitmapToFileAsync(final String link, final int width, final int height, final File cacheDir, final String localPath, final IImageFactoryListener listener) {
//		final int quality = 80;
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				URL url;
//				URLConnection uCon;
//				Bitmap bMap;
//				Bitmap des;
//				try {
//					url = new URL(link);
//					uCon = url.openConnection();
//					bMap = BitmapFactory.decodeStream(uCon.getInputStream());
//					des = Bitmap.createScaledBitmap(bMap, width, height, true);
//					File fileOut = new File(cacheDir, localPath);
//					OutputStream out = new FileOutputStream(fileOut);
//
//					des.compress(Bitmap.CompressFormat.PNG, quality, out);
//					out.flush();
//					out.close();
//					listener.onFinishLoading(fileOut);
//				} catch (Exception e) {
//					Log.e("Gallery", "loi gi nay", e);
//					listener.onError(e);
//				}
//			}
//		});
//		t.start();
//	}
//
//	public String getFileName(String path) {
//		int slashIndex = path.lastIndexOf('/');
//		String fileName = path.substring(slashIndex + 1);
//		return fileName;
//	}
}
