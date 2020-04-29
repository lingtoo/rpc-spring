package com.ethome.iws.domain.vo;

import java.io.Serializable;

public class CmdVO implements Serializable {
	private static final long serialVersionUID = 1653976734860654693L;

	private String deviceId;
	private String thdDeviceId;
	private String content;
	private int expireTime;

	public CmdVO() {
	}

	public CmdVO(String deviceId, String thdDeviceId, String content, int expireTime) {
		this.deviceId = deviceId;
		this.content = content;
		this.thdDeviceId = thdDeviceId;
		this.expireTime = expireTime;
	}

	public CmdVO(String thdDeviceId, String content) {
		this.content = content;
		this.thdDeviceId = thdDeviceId;
	}

	public CmdVO(String thdDeviceId, String content, int expireTime) {
		this.content = content;
		this.thdDeviceId = thdDeviceId;
		this.expireTime = expireTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getThdDeviceId() {
		return thdDeviceId;
	}

	public void setThdDeviceId(String thdDeviceId) {
		this.thdDeviceId = thdDeviceId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

}
