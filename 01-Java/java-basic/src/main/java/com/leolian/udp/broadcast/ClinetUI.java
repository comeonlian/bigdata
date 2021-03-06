/**
 * 
 */
package com.leolian.udp.broadcast;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @description:
 * @author lianliang
 * @date 2018年9月1日 下午9:21:38
 */
public class ClinetUI extends JFrame {

	/**
	 */
	private static final long serialVersionUID = 6011855551029889717L;
	
	private JLabel lblIcon;

	public ClinetUI(){
		init();
	}

	private void init() {
		this.setTitle("学生端");
		this.setBounds(0, 0, 1366, 768);
		this.setLayout(null);

		// 标签空间
		lblIcon = new JLabel();
		lblIcon.setBounds(0, 0, 1366, 768);

		// 图标
		ImageIcon icon = new ImageIcon("F:\\Java-Dev\\taobao-it18\\01-Java\\03-Java\\006.jpg");
		lblIcon.setIcon(icon);
		this.add(lblIcon);
		this.setVisible(true);
	}

	/**
	 * 更新图标
	 */
	public void updateIcon(byte[] dataBytes) {
		ImageIcon icon = new ImageIcon(dataBytes);
		lblIcon.setIcon(icon);
	}

}
