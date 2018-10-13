/**
 * 
 */
package com.leolian.udp.broadcast;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 屏幕广播教师端
 * @author lianliang
 * @date 2018年8月28日 下午9:48:25
 */
public class Teacher {
	private static final String RECEIVER_IP = "127.0.0.1";
	private static final int RECEIVER_PORT = 9999;

	// 每个包60K
	private static final int SPLIT_SIZE = 60 * 1024;

	// 发送者
	private ScreenSender sender = new ScreenSender(RECEIVER_IP, RECEIVER_PORT);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Teacher teacher = new Teacher();
		try {
			while (true) {
				teacher.sendOneScreenData();
				TimeUnit.SECONDS.sleep(5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void sendOneScreenData() {
		try {
			// 截屏
			byte[] frame = ScreenUtil.captureScreen();
			// 压缩数据
			byte[] zipData = ScreenUtil.zipData(frame);
			// 切屏
			List<FrameUnit> frameUnits = splitFrameUnit(zipData);
			// 发送
			sender.send(frameUnits);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 切分
	 * 
	 * @param frame
	 * @return
	 */
	public static List<FrameUnit> splitFrameUnit(byte[] frame) {
		long frameId = System.currentTimeMillis();
		List<FrameUnit> list = new ArrayList<>();
		ByteBuffer byteBuffer = ByteBuffer.wrap(frame);
		int units = (frame.length % SPLIT_SIZE == 0) ? (frame.length / SPLIT_SIZE) : (frame.length / SPLIT_SIZE + 1);
		byte i = 1;
		int count = units;
		FrameUnit frameUnit = null;
		byte[] bytes = null;
		do {
			frameUnit = new FrameUnit();
			frameUnit.setFrameId(frameId);
			frameUnit.setUnitCount((byte) units);
			frameUnit.setUnitNum(i);
			if (byteBuffer.remaining() > SPLIT_SIZE) {
				bytes = new byte[SPLIT_SIZE];
				byteBuffer.get(bytes);
			} else {
				bytes = new byte[byteBuffer.remaining()];
				byteBuffer.get(bytes);
			}
			frameUnit.setDataLen(bytes.length);
			frameUnit.setData(bytes);
			list.add(frameUnit);
			i++;
		} while (--count > 0);
		return list;
	}
}
