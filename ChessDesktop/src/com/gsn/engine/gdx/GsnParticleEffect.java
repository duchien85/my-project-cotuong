package com.gsn.engine.gdx;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GsnParticleEffect extends ParticleEffect {
	private SpriteBatch batcher = new SpriteBatch();
	private OrthographicCamera camera;

	boolean started = false;

	float x, y;

	public GsnParticleEffect() {
		super();
	}

	public GsnParticleEffect(Camera camera) {
		super();
		this.camera = (OrthographicCamera) camera;
	}

	public void drawNow() {
		if (started) {
			if (camera != null) {
				camera.update();
				batcher.setProjectionMatrix(camera.combined);
			}
			batcher.begin();
			this.draw(batcher);
			batcher.end();
		}
	}

	public void drawNow(float delta) {
		update(delta);
		drawNow();
	}

	public Camera getCamera() {
		return camera;
	}

	public boolean isStarted() {
		return started;
	}

	public void setCamera(Camera camera) {
		this.camera = (OrthographicCamera) camera;
	}

	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		super.setPosition(x, y);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		started = true;
	}

	public void startNow(Camera camera, float x, float y) {
		setPosition(x, y);
		setCamera(camera);
		start();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (super.isComplete())
			started = false;
		super.update(delta);
	}
}
