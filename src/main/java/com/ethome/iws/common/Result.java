package com.ethome.iws.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ethome.iws.utils.StringUtils;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Result implements Serializable{
	
	static Logger logger = LoggerFactory.getLogger(Result.class);
	
	private static final long serialVersionUID = -4516395871059949665L;
	private String code;
	private String msg;
	private Object data;

	public Result() {
	}
	
	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result(String code, String msg, Object data) {
		this(code, msg);
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static Result success(){
		return new Result(ReturnCode.SUCCESS, ReturnCode.returnMap.get(ReturnCode.SUCCESS));
	}
	
	public static Result success(Object result){
		return new Result(ReturnCode.SUCCESS,ReturnCode.returnMap.get(ReturnCode.SUCCESS), result);
	}
	
	public static Result error(String errorCode){
		if(StringUtils.isEmpty(errorCode))
			errorCode = ReturnCode.SYSTEM_ERROR;
		return new Result(errorCode, ReturnCode.returnMap.get(errorCode));
	}
	
	public static Result error(String errorCode, Object result){
		return new Result(errorCode, ReturnCode.returnMap.get(errorCode), result);
	}
	
	public boolean suc(){
		return ReturnCode.SUCCESS.equals(this.code);
	}
	
	public String toJsonString(){
		return JSON.toJSONString(this);
	}
	
	public String writeValueAsString(){
		
		return "";
	}
	
	public String writeValueAsString(SimpleDateFormat dateFmt){
		
		return "";
	}
	
	public static void main(String[] args){
		System.out.println(JSON.toJSONString(Result.success()));
	}
}
