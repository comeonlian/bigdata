/**
 * 
 */
package com.leolian.udp.broadcast;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;

/**
 * @description: 工具类
 * @author lianliang
 * @date 2018年8月28日 下午9:49:52
 */
public class ScreenUtil {
	
	private static Robot robot;
	
	static {
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 抓取屏幕
	 */
	public static byte[] captureScreen() {
		try {
			BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(0, 0, 1366, 768));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", baos);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 压缩数据
	 * @param rawData
	 * @throws IOException 
	 */
	public static byte[] zipData(byte[] rawData) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gZIPOutputStream.write(rawData);
		gZIPOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
	
	/**
	 * 解压缩
	 * @param zipData
	 * @return
	 * @throws IOException 
	 */
	public static byte[] unzipData(byte[] zipData) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zipData);
		// 为什么使用ZipInputstream压缩存在问题 ？
		GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[64 * 1024];
		int len = 0;
		while ((len = gZIPInputStream.read(bytes)) != -1) {
			byteArrayOutputStream.write(bytes, 0, len);
		}
		gZIPInputStream.close();
		byteArrayInputStream.close();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
	
}
