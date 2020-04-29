package com.ethome.iws.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.ethome.iws.common.page.PageBean;
import com.ethome.iws.common.page.PageParam;
import com.ethome.iws.dao.BaseDao;
import com.ethome.iws.dao.mybatis.interceptor.ExecutorInterceptor;
import com.ethome.iws.domain.BaseEntity;
import com.ethome.iws.exceptions.BizException;

public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置)
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Autowired
	protected DruidDataSource dataSource;

	@Override
	public int insert(T t) {
		if (t == null)
			throw new RuntimeException("T is null");

		int result = sessionTemplate.insert(getStatement(SQL_INSERT), t);

		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;

		if (t != null && t.getId() != null && result > 0)
			return t.getId();

		return result;
	}

	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);
		return sb.toString();
	}

	@Override
	public long insert(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;
		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;

		return result;
	}

	@Override
	public int update(T t) {
		if (t == null)
			throw new RuntimeException("T is null");

		int result = sessionTemplate.update(getStatement(SQL_UPDATE), t);

		if (result <= 0)
			throw BizException.DB_UPDATE_RESULT_0;

		return result;
	}

	@Override
	public long update(List<T> list) {
		if (list == null || list.size() <= 0)
			return 0;

		int result = 0;

		for (int i = 0; i < list.size(); i++) {
			this.update(list.get(i));
			result += 1;
		}

		if (result <= 0)
			throw BizException.DB_UPDATE_RESULT_0;

		return result;
	}

	@Override
	public T getById(long id) {
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	@Override
	public int deleteById(long id) {
		return sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(getStatement(SQL_LIST_PAGE), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

		// 统计总记录数
		Object countObject = (Object) getSqlSession().selectOne(getStatement(SQL_LIST_PAGE),
				new ExecutorInterceptor.CountParameter(paramMap));
		Long count = Long.valueOf(countObject.toString());

		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM),
					paramMap);
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list,
					countResultMap);
		} else {
			return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
		}
	}

	@Override
	public PageBean listPage(T queryCondition) {
		if (queryCondition == null)
			return new PageBean();
		
		PageParam pageParam = getParamPage(queryCondition);
		if(pageParam == null){
			pageParam = new PageParam(1, 10000);
		}

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(getStatement(SQL_LIST_PAGE), queryCondition,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

		// 统计总记录数
		Object countObject = (Object) getSqlSession().selectOne(getStatement(SQL_LIST_PAGE),
				new ExecutorInterceptor.CountParameter(queryCondition));
		Long count = Long.valueOf(countObject.toString());
		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
	}

	private PageParam getParamPage(Object obj){
		try{
			Class<?> object = obj.getClass();
			Field[] self = object.getDeclaredFields();
			for (int j = 0; j < self.length; j++) {
				if ("page".equals(self[j].getName())) {
					Field f = self[j];
					f.setAccessible(true); // 设置些属性是可以访问的
					return (PageParam)f.get(obj);// 得到此属性的值
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

		// 统计总记录数
		Object countObject = (Object) getSqlSession().selectOne(getStatement(sqlId),
				new ExecutorInterceptor.CountParameter(paramMap));
		Long count = Long.valueOf(countObject.toString());

		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> listBy(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getBy(Map<String, Object> paramMap) {
		return (T) this.getBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null || paramMap.isEmpty())
			return null;

		return this.getSqlSession().selectOne(getStatement(sqlId), paramMap);
	}

	@Override
	public String getSeqNextValue(String seqName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sessionTemplate.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "";
			// 数据库驱动类
			String driverClass = dataSource.getDriver().getClass().getName();
			// 不同的数据库,拼接SQL语句
			if (driverClass.equals("com.ibm.db2.jcc.DB2Driver")) {
				sql = "  VALUES " + seqName.toUpperCase() + ".NEXTVAL";
			}
			if (driverClass.equals("oracle.jdbc.OracleDriver")) {
				sql = "SELECT " + seqName.toUpperCase() + ".NEXTVAL FROM DUAL";
			}
			if (driverClass.equals("com.mysql.jdbc.Driver")) {
				sql = "SELECT  FUN_SEQ('" + seqName.toUpperCase() + "')";
			}
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				connection = sqlSessionFactory.openSession().getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return o.toString();
			}
			return null;
		} catch (Exception e) {
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR.newInstance("获取序列出现错误!序列名称:{%s}", seqName);
		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
	}

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}
}
