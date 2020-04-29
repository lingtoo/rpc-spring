package com.ethome.iws.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 设备上报的历史数据
 * 
 * @author zxy
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class HisDevData extends BaseEntity {
	private static final long serialVersionUID = -111529353720684484L;
	private String deviceId;
	private String deviceType;
	private String upData;
	private Date updatedAt;

	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String p5;
	private String p6;
	private String p7;
	private String p8;
	private String p9;
	private String p10;

	private String p11;
	private String p12;
	private String p13;
	private String p14;
	private String p15;

	private String p16;
	private String p17;
	private String p18;
	private String p19;
	private String p20;

	public HisDevData() {
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getUpData() {
		return upData;
	}

	public void setUpData(String upData) {
		this.upData = upData;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getP11() {
		return p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}

	public String getP12() {
		return p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}

	public String getP13() {
		return p13;
	}

	public void setP13(String p13) {
		this.p13 = p13;
	}

	public String getP14() {
		return p14;
	}

	public void setP14(String p14) {
		this.p14 = p14;
	}

	public String getP15() {
		return p15;
	}

	public void setP15(String p15) {
		this.p15 = p15;
	}

	public String getP16() {
		return p16;
	}

	public void setP16(String p16) {
		this.p16 = p16;
	}

	public String getP17() {
		return p17;
	}

	public void setP17(String p17) {
		this.p17 = p17;
	}

	public String getP18() {
		return p18;
	}

	public void setP18(String p18) {
		this.p18 = p18;
	}

	public String getP19() {
		return p19;
	}

	public void setP19(String p19) {
		this.p19 = p19;
	}

	public String getP20() {
		return p20;
	}

	public void setP20(String p20) {
		this.p20 = p20;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}

	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}

	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}
}
