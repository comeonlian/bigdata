/**
 * 
 */
package com.leolian.udp.broadcast;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月28日 下午9:48:53
 */
public class Student {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClinetUI clinetUI = new ClinetUI();
		new Thread(new Receiver(clinetUI)).start();
	}

}
