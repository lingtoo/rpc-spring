package com.ethome.iws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ethome.iws.dao.HisDevDataDao;
import com.ethome.iws.domain.HisDevData;

@Repository("hisDevDataDaoImpl")
public class HisDevDataDaoImpl extends BaseDaoImpl<HisDevData> implements HisDevDataDao {

	public List<HisDevData> findHistoryData(HisDevData historyData) {
		return super.getSessionTemplate().selectList("findHistoryData", historyData);
	}
}
