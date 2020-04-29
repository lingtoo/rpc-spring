package com.ethome.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ethome.api.HelloServant;
import com.ethome.iws.domain.HisDevData;
import com.ethome.iws.service.HisDevDataService;
import com.ethome.iws.utils.cache.redis.RedisUtils;
import com.gexin.fastjson.JSON;

@Component
public class HelloServantImpl implements HelloServant {
	static final Logger logger = LoggerFactory.getLogger(HelloServantImpl.class);
	

	@Autowired
	private HisDevDataService service;

	public String hello(int no, String name) {
		HisDevData data = new HisDevData();
		String deviceId = getRedisCacheValue();
		data.setDeviceId(deviceId);
		List<HisDevData> ret =  service.findHistoryData(data); //服务层调用DB
		if(ret.size() > 0){
			HisDevData  his = ret.get(0);
			name += ",deviceId::"+his.getDeviceId()+",type::"+his.getDeviceType();
		}
		logger.info("param::"+name+",autowire::"+service);
		return String.format("hello no=%s, name=%s, time=%s", no, name, System.currentTimeMillis());
	}
	
	private String getRedisCacheValue(){
		Object obj = RedisUtils.get("test_device"); // redis交互
		if(null != obj){
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(obj));
			logger.info("redis cache value::"+json);
			return (String)json.get("deviceId");
		}else{
			logger.info("default deviceid.....");
		}
		return "0203a0fff800";
	}

}
