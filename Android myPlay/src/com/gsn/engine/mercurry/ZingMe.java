package com.gsn.engine.mercurry;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

public class ZingMe {
	public static String getSessionkeyUser(String userName, String passWord) {
		HttpClient httpclient = new DefaultHttpClient();
		String session = null;
		try {
			// create a local instance of cookies store
			CookieStore cookiesStore = new BasicCookieStore();
			HttpContext context = new BasicHttpContext();
			context.setAttribute(ClientContext.COOKIE_STORE, cookiesStore);

			// Connect
			HttpPost httpPost = new HttpPost();

			httpPost.setURI(new URI("https://sso2.zing.vn/index.php?method=login"));

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("pid", "25"));
			nameValuePairs.add(new BasicNameValuePair("u1", "http://login.me.zing.vn/login/success"));
			nameValuePairs.add(new BasicNameValuePair("pt", "http://login.me.zing.vn/login/fail"));
			nameValuePairs.add(new BasicNameValuePair("u", userName));
			nameValuePairs.add(new BasicNameValuePair("p", passWord));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse respones = httpclient.execute(httpPost, context);
			HttpEntity entity = respones.getEntity();
			if (entity != null) {
				System.out.print("Response content Length" + entity.getContentLength());
			}
			System.out.print("----------------------------");
			List<Cookie> cookies = cookiesStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				Log.i("Cookie : ", cookies.get(i).toString());

				if ("ZAUTH".equals(cookies.get(i).getName())) {
					session = cookies.get(i).getValue();
					Log.i("Cookie Session Value : ", cookies.get(i).getValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return session;

	}

	public static boolean pingConnection() {

		boolean fConnect = false;
		HttpClient client = new DefaultHttpClient();
		;
		try {
			HttpPost post = new HttpPost();
			post.setURI(new URI("https://loginm.zing.vn/app"));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null)
				fConnect = true;
			else
				fConnect = false;

		} catch (Exception e) {

		} finally {
			client.getConnectionManager().shutdown();
		}

		return fConnect;
	}

}
