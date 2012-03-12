package com.gsn.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DownloaderAndroid extends IDowloader {
	private String tag = "Download android";
	@Override
	public void saveBitmapToFileAsync(final String id, final String link, final int width, final int height, final String localPath, final IImageDownloadListener listener) {
		final int quality = 80;
		Thread t = new Thread(new Runnable() {			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				URL url;
				URLConnection uCon;
				Bitmap bMap;
				Bitmap des;
				try {
					File fileOut = new File(cacheDir, localPath);
					if (fileOut.exists()){
						Log.i(tag, "Da ton tai anh!! Ko phai down lai");
						listener.onFinishLoading(id, fileOut);
						return;
					} 
					
					url = new URL(link);
					uCon = url.openConnection();
					bMap = BitmapFactory.decodeStream(uCon.getInputStream());
					des = Bitmap.createScaledBitmap(bMap, width, height, true);					
					OutputStream out = new FileOutputStream(fileOut);

					des.compress(Bitmap.CompressFormat.PNG, quality, out);
					out.flush();
					out.close();
					listener.onFinishLoading(id, fileOut);
					Log.i(tag, "Down anh vao : " + fileOut.getAbsolutePath());
				} catch (Exception e) {
					Log.e("Gallery", "loi gi nay", e);
					listener.onError(e);
				}
			}
		});
		t.start();
	}
}