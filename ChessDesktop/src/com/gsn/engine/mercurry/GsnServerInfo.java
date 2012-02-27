package com.gsn.engine.mercurry;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GsnServerInfo {
	public static List<GsnServerInfo> getServerInfo(String page) throws Exception {
		// TODO Auto-generated constructor stub
		// "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo"
		page = "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo";
		List<GsnServerInfo> list = new ArrayList<GsnServerInfo>();
		URL url;
		url = new URL(page);
		URLConnection ucon = url.openConnection();
		InputStream is = ucon.getInputStream();
		byte[] b = toByteArray(is);
		String s = new String(b, "UTF-8");
		s = s.substring(1);

		JSONObject obj = new JSONObject(s);
		JSONArray arr = obj.getJSONArray("servers");
		for (int i = 0; i < arr.length(); i++) {
			JSONObject server = arr.getJSONObject(i);
			GsnServerInfo e = new GsnServerInfo();
			e.port = server.getInt("port");
			e.maxUsers = server.getInt("maxUsers");
			e.name = server.getString("name");
			e.numUsers = server.getInt("numUsers");
			e.ip = server.getString("ip");
			list.add(e);
		}
		return list;
	}
	private static byte[] toByteArray(InputStream in) {
		try {
			List<Byte> list = new ArrayList<Byte>();
			long startTime = System.currentTimeMillis();
			InputStream is = in;
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] b = new byte[1024];
			int current = 0;
			while ((current = bis.read(b)) != -1) {
				for (int i = 0; i < current; i++) {
					list.add(b[i]);
				}
			}
			byte[] result = new byte[list.size()];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String ip;

	private int maxUsers;

	private String name;

	private int numUsers;

	private int port;
	private GsnServerInfo() {
		// TODO Auto-generated constructor stub
	}
	public String getIp() {
		return ip;
	}

	public int getMaxUsers() {
		return maxUsers;
	}

	public String getName() {
		return name;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public int getPort() {
		return port;
	}
}
