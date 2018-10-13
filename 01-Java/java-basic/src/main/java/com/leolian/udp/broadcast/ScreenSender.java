/**
 * 
 */
package com.leolian.udp.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月28日 下午10:41:13
 */
public class ScreenSender {
	private static final int SERVER_PORT = 8888;
	private DatagramSocket socket = null;
	private InetSocketAddress address = null;
	
	/**
	 */
	public ScreenSender(String receiverIp, int receiverPort) {
		try {
			socket = new DatagramSocket(SERVER_PORT);
			address = new InetSocketAddress(receiverIp, receiverPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param frameUnits
	 * @throws IOException 
	 */
	public void send(List<FrameUnit> frameUnits) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(64 * 1024);
		DatagramPacket packet = null;
		byte[] bytes = null;
		for (FrameUnit frameUnit : frameUnits) {
			byteBuffer.putLong(frameUnit.getFrameId());
			byteBuffer.put(frameUnit.getUnitCount());
			byteBuffer.put(frameUnit.getUnitNum());
			byteBuffer.putInt(frameUnit.getDataLen());
			byteBuffer.put(frameUnit.getData());
			bytes = new byte[byteBuffer.position()];
			byteBuffer.flip();
			byteBuffer.get(bytes);
			packet = new DatagramPacket(bytes, bytes.length);
			packet.setSocketAddress(address);
			socket.send(packet);
			byteBuffer.clear();
		}
	}
	
}
