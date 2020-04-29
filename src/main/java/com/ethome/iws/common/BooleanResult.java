package com.ethome.iws.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ethome.iws.utils.StringUtils;

/**
 * 返回值父类
 */
public class BooleanResult {
	
	static Logger logger = LoggerFactory.getLogger(BooleanResult.class);
	
	public static final String resultKey = "content";
	
	public static final BooleanResult SUCCESS = new BooleanResult(true, ReturnCode.SUCCESS); 
	
    /**
     * 处理是否成功
     */
    private Boolean processResult;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 处理结果上下文
     *
     * @return
     */
    private Map<String, Object> resultMap = new HashMap<String, Object>();
    
    public BooleanResult(){
    }
    
    public BooleanResult(Boolean processResult, String errorMsg) {
        this.processResult = processResult;
        this.errorMsg = errorMsg;
    }
    
    public BooleanResult(Boolean processResult, String errorMsg, Object object) {
    	this(processResult, errorMsg);
    	if(object != null)
    		this.resultMap.put(resultKey, object);
    }

    public Boolean getProcessResult() {
        return processResult;
    }

    public void setProcessResult(Boolean processResult) {
        this.processResult = processResult;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public BooleanResult(Boolean processResult) {
        this.processResult = processResult;
    }
    
    public void put(Object object){
    	resultMap.put(resultKey, object);
    }

	public String toJsonString(){
		return JSON.toJSONString(this);
	}
	
	public String writeValueAsString(){
		
		return "";
	}
	
	public static BooleanResult success(){
		return new BooleanResult(true, ReturnCode.SUCCESS);
	}
	
	public static BooleanResult success(Object result){
		return new BooleanResult(true, ReturnCode.SUCCESS, result);
	}
	
	public static BooleanResult error(String errorCode){
		if(StringUtils.isEmpty(errorCode))
			errorCode = ReturnCode.SYSTEM_ERROR;
		return new BooleanResult(false, errorCode);
	}
	
	public static BooleanResult error(String errorCode, Object result){
		BooleanResult blRst = error(errorCode);
		if(result != null)
			blRst.put(result);
		return blRst;
	}
	
	public Object resultMap(){
		return this.resultMap.get(resultKey);
	}
	
	public static void main(String[] args){
		System.out.println(BooleanResult.success(10).writeValueAsString());
	}
}
