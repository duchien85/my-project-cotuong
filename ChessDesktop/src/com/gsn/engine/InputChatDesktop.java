package com.gsn.engine;


public class InputChatDesktop implements IChatInput {

	@Override
	public void chatInput(IChatListener listener) {		
		listener.onFinish("Haha thua chua");
	}
}
