package com.gsn.engine.myplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class GsnScreen{	
	private List<GsnLayer> layers = new ArrayList<GsnLayer>();
	public float width;
	public float height;
	public GsnScreen(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void onShowScreen(){
		
	}
	
	public void onHideScreen(){
		
	}
	
	public void addLayer(GsnLayer layer){
		layers.add(layer);
		sortLayer();
		layer.parent = this;
	}
	
	public void addLayer(GsnLayer layer, float index, boolean visible){
		layer.index = index;
		layer.visible = visible;
		layers.add(layer);
		sortLayer();
		layer.parent = this;
	}
	
	public void addLayer(GsnLayer layer, boolean visible){
		float max = -1000;
		for (GsnLayer tmp : layers)
			if (max < layer.index)
				max = layer.index;
		layer.index = max + 1;
		layer.visible = visible;
		layers.add(layer);
		sortLayer();
		layer.parent = this;
	}
	
	public void removeLayer(GsnLayer layer){
		layers.remove(layer);
	}
	
	public void sortLayer(){
		Collections.sort(layers);
	}	

	public int getRatioHeight(float ratio) {
		return (int) (ratio * height);
	}

	public int getRatioWidth(float ratio) {
		return (int) (ratio * width);
	}

	
	abstract public void setInputListener();

	
	public void act(float delta) {			
		for (GsnLayer tmp : layers)
			tmp.act(delta);
	}

	public void draw() {	
		for (GsnLayer tmp : layers)
			if (tmp.visible)
				tmp.draw();
	}
	
	public void setIndexLayer(GsnLayer layer, int index)
	{
		layer.index = index;
	}
	
	public void setVisibleLayer(GsnLayer layer, boolean visible)
	{
		layer.visible = visible;
	}
	
	public void setInputLayer(GsnLayer layer)
	{
		layer.setInputListener();
	}
	
	public void showAndSetListener(GsnLayer layer)
	{
		setInputLayer(layer);
		setVisibleLayer(layer, true);
	}

	public void hideChildsUnder(GsnLayer layer) {	
		for (GsnLayer tmp : layers){
			if (tmp.index > layer.index)
				tmp.visible = false;
		}		
	}
}