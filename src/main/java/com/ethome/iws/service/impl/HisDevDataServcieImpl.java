package com.ethome.iws.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ethome.iws.app.SpringContext;
import com.ethome.iws.dao.HisDevDataDao;
import com.ethome.iws.domain.HisDevData;
import com.ethome.iws.service.HisDevDataService;

@Service
public class HisDevDataServcieImpl implements HisDevDataService {
	static final Logger logger = LoggerFactory.getLogger(HisDevDataServcieImpl.class);

	@Autowired
	private HisDevDataDao hisDevDataDao;

	@Override
	public int createHistoryData(HisDevData hisDevData) {
		hisDevData.setUpdatedAt(new Date());
		String deviceId = hisDevData.getDeviceId();
		if (null != deviceId && deviceId.length() == 12) {
			hisDevData.setDeviceType(deviceId.substring(2, 6));
		}
		logger.info(JSONObject.toJSONString(hisDevData));
		return hisDevDataDao.insert(hisDevData);
	}

	@Override
	public HisDevData getHistoryDataById(int id) {
		return hisDevDataDao.getById(id);
	}

	@Override
	public List<HisDevData> findHistoryData(HisDevData hisDevData) {
		return hisDevDataDao.findHistoryData(hisDevData);
	}

	public static void main(String[] args) {
		String snString = "020340111222";
		String type = snString.substring(2, 6);
		System.out.println(type);
	}
	
	public HisDevDataDao getDao() {
		if (null != hisDevDataDao) {
			return hisDevDataDao;
		}
		hisDevDataDao = (HisDevDataDao) SpringContext.getApplicationContext().getBean("hisDevDataDaoImpl");
		return hisDevDataDao;
	}
}
