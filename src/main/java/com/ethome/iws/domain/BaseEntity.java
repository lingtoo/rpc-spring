package com.ethome.iws.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(value = { "version" })
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5400721825988305118L;

	private Integer id; // 主键

	private Integer version = null; // 暂时保留 实现乐观锁

	private Date createdAt;

	public BaseEntity() {
		this.createdAt = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String queryCondition(Object obj) {
		Class<?> object = obj.getClass();
		Field[] self = object.getDeclaredFields();
		StringBuilder condition = new StringBuilder("?");
		try {
			buildCondition(obj, self, condition);
			Field[] parent = object.getSuperclass().getDeclaredFields();
			if (parent != null)
				buildCondition(obj, parent, condition);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		if (condition.equals("?"))
			return "";
		condition.setLength(condition.length() - 1);
		return condition.toString();
	}

	private void buildCondition(Object obj, Field[] self, StringBuilder condition)
			throws IllegalArgumentException, IllegalAccessException {
		for (int j = 0; j < self.length; j++) {
			if ("serialVersionUID".equals(self[j].getName()) || "pageParam".equals(self[j].getName()))
				continue;

			Field f = self[j];
			f.setAccessible(true); // 设置些属性是可以访问的
			Object val = f.get(obj);// 得到此属性的值
			if (val != null && !"".equals(val)) {
				condition.append(self[j].getName());
				condition.append("=");
				condition.append(val);
				condition.append("&");
			}

			// 权限修饰符
			// int mo = self[j].getModifiers();
			// String priv = Modifier.toString(mo);
			// 属性类型
			// Class<?> type = self[j].getType();
		}
	}
}
