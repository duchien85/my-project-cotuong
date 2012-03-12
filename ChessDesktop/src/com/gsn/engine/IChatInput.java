package com.gsn.engine;

public interface IChatInput {
	public interface IChatListener{
		void onFinish(String input);
	}
	void chatInput(IChatListener listener);
}
