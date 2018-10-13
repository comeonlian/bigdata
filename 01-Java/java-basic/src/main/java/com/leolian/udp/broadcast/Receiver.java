/**
 * 
 */
package com.leolian.udp.broadcast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author lianliang
 * @date 2018年9月1日 下午9:19:40
 */
public class Receiver implements Runnable {
	private static final int CLIENT_PORT = 9999;
	
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	private byte[] bytes = new byte[64 * 1024];
	
	private ClinetUI clientUI;
	
	/**
	 */
	public Receiver(ClinetUI clinetUI) {
		try {
			this.clientUI = clinetUI;
			socket = new DatagramSocket(CLIENT_PORT);
			packet = new DatagramPacket(bytes, bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Map<Byte, FrameUnit> screen = new HashMap<>();
		long currentFrameId = 0;
		while (true) {
			try {
				socket.receive(packet);
				int len = packet.getLength();
				FrameUnit frameUnit = dataToFrameUnit(bytes, 0, len);
				// System.out.println(frameUnit);
				if(frameUnit.getFrameId() == currentFrameId) {
					screen.put(frameUnit.getUnitNum(), frameUnit);
				} else if (frameUnit.getFrameId() > currentFrameId) {
					currentFrameId = frameUnit.getFrameId();
					screen.clear();
					screen.put(frameUnit.getUnitNum(), frameUnit);
				}
				if (frameUnit.getUnitCount() == screen.size()) {
					// 组装渲染
					System.out.println("====== 渲染一屏画面 ====== ");
					render(frameUnit.getUnitCount(), screen);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param buf
	 * @param i
	 * @param len
	 */
	private FrameUnit dataToFrameUnit(byte[] buf, int i, int len) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(buf, i, len);
		FrameUnit frameUnit = new FrameUnit();
		frameUnit.setFrameId(byteBuffer.getLong());
		frameUnit.setUnitCount(byteBuffer.get());
		frameUnit.setUnitNum(byteBuffer.get());
		frameUnit.setDataLen(byteBuffer.getInt());
		byte[] remainBytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(remainBytes);
		frameUnit.setData(remainBytes);
		byteBuffer.clear();
		return frameUnit;
	}
	
	/**
	 * @param unitCount
	 * @param screen
	 * @throws IOException 
	 */
	private void render(byte unitCount, Map<Byte, FrameUnit> screen) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		for (Map.Entry<Byte, FrameUnit> entry : screen.entrySet()) {
			byteArrayOutputStream.write(entry.getValue().getData());
		}
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		byte[] rawData = ScreenUtil.unzipData(byteArray);
		clientUI.updateIcon(rawData);
	}
}
