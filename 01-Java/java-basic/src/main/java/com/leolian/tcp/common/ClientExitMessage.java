package com.leolian.tcp.common;

/**
 * 客户端退出消息
 */
public class ClientExitMessage extends Message {

	public int getMessageType() {
		return CLIENT_TO_SERVER_EXIT;
	}

}
