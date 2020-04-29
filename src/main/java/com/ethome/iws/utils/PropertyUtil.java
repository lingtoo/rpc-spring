package com.ethome.iws.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取，properties
 * 
 * @author zxy
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PropertyUtil {

	static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
	private static final String ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	public static Properties loadPropFile(String filePath) {
		Properties props = new Properties();
		String trustcapath = ROOT_PATH + filePath;
		InputStream in = null;
		props = new Properties();
		try {
			in = new FileInputStream(trustcapath);
			props.load(in);
		} catch (IOException e) {
			logger.error("读取出错配置文件出错", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					logger.error("关闭IO异常", ex);
				}
			}
		}
		return props;
	}

	public static String getValue(String key, Properties props) {
		return props.getProperty(key);
	}

	public static void main(String[] args) {
		Properties properties = loadPropFile("server_config.properties");
		String value = getValue("DO_MAIN", properties);
		System.out.println("DO_MAIN value::" + value);
	}
}
