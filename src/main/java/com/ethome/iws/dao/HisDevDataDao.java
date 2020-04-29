package com.ethome.iws.dao;

import java.util.List;

import com.ethome.iws.domain.HisDevData;

public interface HisDevDataDao extends BaseDao<HisDevData> {
	public List<HisDevData> findHistoryData(HisDevData hisDevData);
}
