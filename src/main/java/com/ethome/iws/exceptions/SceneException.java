package com.ethome.iws.exceptions;

public class SceneException extends BizException {

	private static final long serialVersionUID = -6565839902000593656L;

	public SceneException() {
	}

	public SceneException(String code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public SceneException(String code, String msg) {
		super(code, msg);
	}

}
