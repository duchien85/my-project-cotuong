package com.gsn.engine.mercurry;

public interface IMercuryCommunicator {
	void connect();

	void disconnect();

	void killService();

	void sendPacket(String s);

	void setSession(String s);
}
