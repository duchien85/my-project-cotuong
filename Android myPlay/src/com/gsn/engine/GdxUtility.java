package com.gsn.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gsn.engine.layout.GsnRectangle;

public class GdxUtility {
	public static boolean canHit(float x, float y, float width, float height) {
		return (x >= 0 && x <= width && y >= 0 && y <= height);
	}

	public static TextureRegion[] convertListRegionToArray(List<AtlasRegion> list) {
		TextureRegion[] tmp = new TextureRegion[list.size()];
		int count = 0;
		for (TextureRegion region : list) {
			tmp[count] = region;
			count++;
		}
		return tmp;
	}

	public static void drawCenter(SpriteBatch batcher, Sprite in, Sprite out) {
		moveToCenter(in, out);
		in.draw(batcher);
	}

	public static void drawNumberFromTexture(List<com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion> numbers, SpriteBatch sb, int num, float x, float y) {
		String s = String.valueOf(num);
		com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion tex;
		float dx = x;
		for (int i = 0; i < s.length(); i++) {
			tex = numbers.get(s.charAt(i) - 48);
			sb.draw(tex, dx, y);
			dx += tex.getRegionWidth();
		}
	}

	public static void drawText(SpriteBatch sb, BitmapFont font, String text, float width, float height, float x, float y) {
		font.draw(sb, text, x, y);
	}

	public static int findPower(int x) {
		int mu = (int) (Math.ceil(Math.log(x) / Math.log(2)));
		return (int) Math.pow(2, mu);
	}

	public static String formatGold(int gold) {
		if (gold >= 1000000000)
			return String.valueOf(gold / 1000000000) + "B";
		else if (gold >= 1000000)
			return String.valueOf(gold / 1000000) + "M";
		else if (gold >= 1000) {
			int x = gold % 1000;
			return String.valueOf(gold / 1000) + "." + String.valueOf(x / 100) + "K";
		} else
			return String.valueOf(gold);
	}

	public static String formatNumber(int value) {
		if (value >= 1000) {
			String s = String.valueOf(value % 1000);
			while (s.length() < 3)
				s = "0" + s;
			return String.valueOf(value / 1000) + "." + s;
		} else
			return String.valueOf(value);
	}

	public static Vector2 getCenter(Sprite sprite) {
		return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}

	public static Vector2 getCenter(TextureRegion tex, Sprite out) {
		Vector2 cenOut = getCenter(out);
		return new Vector2(cenOut.x - tex.getRegionWidth() / 2, cenOut.y - tex.getRegionHeight() / 2);
	}

	public static String getShortName(String name) {
		if (name.length() >= 9)
			return (name.substring(0, 6) + "...");
		else
			return name;
	}

	public static boolean inRange(float x, float a, float b) {
		return (a <= x) && (x <= b);
	}

	public static boolean inRectangle(float x, float y, GsnRectangle actor) {
		return inRange(x, actor.x, actor.x + actor.width) && inRange(y, actor.y, actor.y + actor.height);
	}

	public static void moveToCenter(Sprite in, Sprite out) {
		Vector2 cenOut = getCenter(out);
		setCenter(in, cenOut.x, cenOut.y);
	}

	public static boolean pointInRectangle(float rx, float ry, float rw, float rh, float x, float y) {
		return rx <= x && rx + rw >= x && ry <= y && ry + rh >= y;
	}

	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	};

	public static void setCenter(Sprite sprite, float x, float y) {
		sprite.setPosition(0, 0);
		Vector2 cen = getCenter(sprite);
		sprite.setPosition(x - cen.x, y - cen.y);
	}
}
