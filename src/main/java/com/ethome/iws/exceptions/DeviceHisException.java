package com.ethome.iws.exceptions;

public class DeviceHisException extends BizException {

	private static final long serialVersionUID = -5531126346924330651L;

	public DeviceHisException() {
	}

	public DeviceHisException(String code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public DeviceHisException(String code, String msg) {
		super(code, msg);
	}
}
