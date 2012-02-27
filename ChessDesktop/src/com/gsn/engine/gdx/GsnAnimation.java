package com.gsn.engine.gdx;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GsnAnimation extends Image {
	public interface IFinishEffectListener {
		public String typeEffect = "";

		public void onFinish();
	}
	public float frameDuration;
	final TextureRegion[] keyFrames;
	public IFinishEffectListener listener;

	Boolean loop = false;
	public boolean running = false;
	float period = 0;

	public GsnAnimation(float frameDuration, List<AtlasRegion> a) {
		super(a.get(0));
		this.frameDuration = frameDuration;
		this.keyFrames = new TextureRegion[a.size()];
		for (int i = 0, n = a.size(); i < n; i++) {
			this.keyFrames[i] = a.get(i);
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if(running)
		{
			period += delta;
			TextureRegion frame = getKeyFrame(period, false);
			if (frame != null)
				setRegion(frame);
			else {
				if (loop) {
					period = 0;
				} else {
					if (listener != null)
						listener.onFinish();
					stop();
				}
			}
		}
		// System.out.println("act animation... " + delta );
	}

	public TextureRegion getKeyFrame(float stateTime, boolean looping) {
		int frameNumber = (int) (stateTime / frameDuration);
		if (!looping) {
			if (frameNumber >= keyFrames.length) {
				return null;
			}
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);

		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}

	public TextureRegion getStaticKeyFrame(int index) {
		return keyFrames[index];
	}

	public void resetTime() {
		period = 0;
	}

	public void setListener(IFinishEffectListener finish) {
		listener = finish;
	}

	public void setLoop(Boolean _loop) {
		loop = _loop;
	}
	
	public void stop()
	{
		running = false;
		resetTime();
		if(parent!=null)
			remove();
	}
	public void start()
	{
		running = true;
	}
}
