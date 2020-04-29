package com.ethome.iws.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ethome.iws.domain.HisDevData;

public interface HisDevDataService {
	static final Logger logger = LoggerFactory.getLogger(HisDevDataService.class);

	public int createHistoryData(HisDevData historyData);

	public HisDevData getHistoryDataById(int id);

	public List<HisDevData> findHistoryData(HisDevData historyData);

}
