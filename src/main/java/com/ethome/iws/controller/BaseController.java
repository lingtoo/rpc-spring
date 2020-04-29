package com.ethome.iws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * Controller基类
 * abc
 * @date 2016年7月18日
 */
public class BaseController {
	
	public static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	


	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	

	/**
	 * 将对象转换成VO对象
	 * @param orig 源数据
	 * @param dest 目标类型
	 * @return
	 * @throws Exception
	 */
	protected <T> T convertVO(Object orig, Class<T> dest) throws Exception {
		T t = dest.newInstance();
		PropertyUtils.copyProperties(t, orig);
		return t;
	}
	
	/**
	 * 将对象转换成ListVO对象
	 * @param list 源数据
	 * @param dest 目标类型
	 * @return
	 * @throws Exception
	 */
	protected <T> List<T>  convertListVO(List<?> list, Class<T> dest) {
		List<T> t = new ArrayList<T>();
		try{
			for (Object orig : list) {
				t.add(convertVO(orig, dest));
			}
		}catch(Exception e){
			logger.error("将对象转换成ListVO对象 ", e);
		}
		return t;
	}
}
