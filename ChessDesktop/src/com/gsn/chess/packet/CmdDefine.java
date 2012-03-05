package com.gsn.chess.packet;

public class CmdDefine {
	public static final String CMD = "_cmd";
	public static final String PARAMS = "params";
	
    public static final int CMD_CUSTOM_LOGIN = 0;
    public static final int CMD_GET_INFO = 1;
    public static final int CMD_UPDATE_STATUS = 2;
    public static final int CMD_GET_FRIENDS = 3;
    public static final int CMD_INVITE = 4;
    public static final int CMD_ACCEPT_INVITATION = 5;
    public static final int CMD_REFUSE_INVITATION = 6;
    public static final int CMD_CANCEL_INVITATION = 7;
    public static final int CMD_AUTO_DENY_INVITATION = 8;
    public static final int CMD_INSTANT_PLAY = 9;
    public static final int CMD_JOIN_ROOM = 10;
    public static final int CMD_OUT_ROOM = 11;
    public static final int CMD_SERVER_MESSAGE = 12;
    public static final int CMD_CHAT_ROOM = 13;
    public static final int CMD_READY = 14;
    public static final int CMD_START = 15;
    public static final int CMD_CHESS_MOVE = 16;
    public static final int CMD_STOP = 17;
    public static final int CMD_NEWS = 18;
    public static final int CMD_EARN_MONEY = 19;
    public static final int CMD_FEED = 20;
    public static final int CMD_PUSH_FEED = 21;
    public static final int ASK_DRAW = 22;
    public static final int ANSWER_DRAW = 23;
    public static final int ASK_LOSE = 24;
    public static final int ASK_ROLL_BACK = 25;
    public static final int ANSWER_ROLL_BACK = 26;   
}
