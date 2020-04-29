package com.ethome.iws.exceptions;

public class DeviceException extends BizException {

	private static final long serialVersionUID = 5760999717643774149L;
	
	public DeviceException() {
	}

	public DeviceException(String code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public DeviceException(String code, String msg) {
		super(code, msg);
	}
}
