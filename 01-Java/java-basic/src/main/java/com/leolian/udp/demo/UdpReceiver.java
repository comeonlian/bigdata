/**
 * 
 */
package com.leolian.udp.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月24日 下午10:40:42
 */
public class UdpReceiver {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(8888);
		byte[] bytes = new byte[1024];
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
		while(true) {
			socket.receive(packet);
			int len = packet.getLength();
			if(len > 0) {
				String string = new String(bytes, 0, len);
				System.out.println("Receiver: " + string);
			}
			
		}
		
	}
	
}
