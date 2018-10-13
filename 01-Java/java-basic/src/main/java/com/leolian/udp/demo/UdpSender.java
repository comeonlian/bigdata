/**
 * 
 */
package com.leolian.udp.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月24日 下午10:40:32
 */
public class UdpSender {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(9999);
		
		int i = 0;
		for(;;) {
			byte[] bytes = ("hello world " + i++).getBytes();
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
			packet.setSocketAddress(new InetSocketAddress("127.0.0.1", 8888));
			socket.send(packet);
			Thread.sleep(500);
		}
		
	}
	
}
