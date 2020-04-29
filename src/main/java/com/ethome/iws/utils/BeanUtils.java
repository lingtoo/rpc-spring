package com.ethome.iws.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BeanUtils extends org.springframework.beans.BeanUtils {

	/**
	 * 将对象转换成VO对象
	 * 
	 * @param orig
	 *            源数据
	 * @param dest
	 *            目标类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertVO(Object orig, Class<T> dest) {
		T t = null;
		try {
			t = dest.newInstance();
			PropertyUtils.copyProperties(t, orig);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * 将对象转换成ListVO对象
	 * 
	 * @param list
	 *            源数据
	 * @param dest
	 *            目标类型
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> convertListVO(List<?> list, Class<T> dest) {
		List<T> t = new ArrayList<T>();
		for (Object orig : list) {
			t.add(convertVO(orig, dest));
		}
		return t;
	}

	/**
	 * Copy the property values of the given source bean into the given target
	 * bean.
	 * <p>
	 * Note: The source and target classes do not have to match or even be
	 * derived from each other, as long as the properties match. Any bean
	 * properties that the source bean exposes but the target bean does not will
	 * silently be ignored.
	 * 
	 * @param source
	 *            the source bean
	 * @param target
	 *            the target bean
	 * @param editable
	 *            the class (or interface) to restrict property setting to
	 * @param ignoreProperties
	 *            array of property names to ignore
	 * @throws BeansException
	 *             if the copying failed
	 * @see BeanWrapper
	 */
	public static void copyProperties(Object source, Object target) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);

		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						if (value != null) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}
}
