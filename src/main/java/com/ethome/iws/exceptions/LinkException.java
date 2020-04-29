package com.ethome.iws.exceptions;

public class LinkException extends BizException {

	private static final long serialVersionUID = -4480112748355342050L;
	
	private Object obj;

	public LinkException() {
	}

	public LinkException(String code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public LinkException(String code, String msg) {
		super(code, msg);
	}
	
	public LinkException(String code, Object obj){
		super(code);
		super.code = code;
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}
	
}
