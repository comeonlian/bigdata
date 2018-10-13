/**
 * 
 */
package com.leolian.udp.broadcast;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月31日 下午8:44:28
 */
public class ScreenUtilTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		OutputStream outputStream = new FileOutputStream(new File("F:\\Java-Dev\\taobao-it18\\01-Java\\03-Java\\image.jpg"));
		Robot robot = new Robot();
		BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(0, 0, 1366, 768));
		ImageIO.write(bufferedImage, "jpg", outputStream);
	}

}
