/**
 * 
 */
package com.leolian.udp.broadcast;

/**
 * @description:
 * @author lianliang
 * @date 2018年8月28日 下午10:40:54
 */
public class FrameUnit {
	// 截屏id
	private long frameId;
	// 帧单元个数
	private byte unitCount;
	// 帧单元编号
	private byte unitNum;
	// 数据长度
	private int dataLen;
	// 数据
	private byte[] data;

	public long getFrameId() {
		return frameId;
	}

	public void setFrameId(long frameId) {
		this.frameId = frameId;
	}

	public byte getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(byte unitCount) {
		this.unitCount = unitCount;
	}

	public byte getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(byte unitNum) {
		this.unitNum = unitNum;
	}

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FrameUnit [frameId=" + frameId + ", unitCount=" + unitCount + ", unitNum=" + unitNum + ", dataLen="
				+ dataLen + "]";
	}

}
