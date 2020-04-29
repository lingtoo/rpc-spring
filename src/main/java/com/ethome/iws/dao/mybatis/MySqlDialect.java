package com.ethome.iws.dao.mybatis;

public class MySqlDialect extends Dialect {

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			sql += " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			sql += " limit " + limitPlaceholder;
		}

		return sql;
	}

}
