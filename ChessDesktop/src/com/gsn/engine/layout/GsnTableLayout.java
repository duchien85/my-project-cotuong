package com.gsn.engine.layout;

import java.util.ArrayList;
import java.util.List;

public class GsnTableLayout {
	public List<GsnRectangle> list;
	private float tmpHeight, tmpWidth, tmpX, tmpY, oldHeight;
	public float x, y, width, height;

	public GsnTableLayout(float x, float y, float width, float height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tmpX = x;
		this.tmpY = y;
		this.oldHeight = 0;
		list = new ArrayList<GsnRectangle>();
	}

	public GsnTableLayout(GsnRectangle table) {
		// TODO Auto-generated constructor stub
		this(table.x, table.y, table.width, table.height);
	}

	public GsnRectangle add(float rWidth) {
		tmpWidth = width * rWidth;
		GsnRectangle tmp = new GsnRectangle(tmpX, tmpY, tmpWidth, tmpHeight);
		list.add(tmp);
		tmpX += tmpWidth;
		return tmp;
	}

	public void addList(float... rWidths) {
		for (int i = 0; i < rWidths.length; i++)
			add(rWidths[i]);
	}

	public GsnRectangle getBoundingRectangle() {
		return new GsnRectangle(x, y, width, height);
	}

	public GsnRectangle newRow(boolean isRatio, float rHeight) {
		this.tmpX = x;
		this.tmpY = this.tmpY + oldHeight;
		if (isRatio)
			oldHeight = tmpHeight = rHeight * height;
		else
			oldHeight = tmpHeight = rHeight;
		return new GsnRectangle(this.tmpX, this.tmpY, this.width, this.tmpHeight);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + ", " + y + ", " + width + ", " + height + ")";
	}
}
