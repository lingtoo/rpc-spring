package com.ethome.iws.exceptions;

import com.ethome.iws.common.ReturnCode;

/**
 * @Description: 业务异常基类，所有业务异常都必须继承于此异常
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -6807477568410905059L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final BizException DB_INSERT_RESULT_0 = new BizException(ReturnCode.DB_INSERT_RESULT_0, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final BizException DB_UPDATE_RESULT_0 = new BizException(ReturnCode.DB_UPDATE_RESULT_0, "数据库操作,update返回0");

	/**
	 * 获取序列出错
	 */
	public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(ReturnCode.DB_GET_SEQ_NEXT_VALUE_ERROR, "获取序列出错");

	/**
	 * 文件上传出错
	 */
	public static final BizException FILE_UPLOAD_ERROR = new BizException(ReturnCode.FILE_UPLOAD_ERROR, "文件上传出错");
	
	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;

	public BizException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BizException newInstance(String msgFormat, Object... args) {
		return new BizException(this.code, msgFormat, args);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}

}
