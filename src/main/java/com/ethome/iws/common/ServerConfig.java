package com.ethome.iws.common;

import java.util.Properties;

import com.ethome.iws.utils.PropertyUtil;

public class ServerConfig {

	private static String SERVER_CONFIG_FILE_PATH = "server_config.properties";
	private static Properties props = PropertyUtil.loadPropFile(SERVER_CONFIG_FILE_PATH);

	private static String getValue(String key) {
		return PropertyUtil.getValue(key, props);
	}

	/************* 生产 **************/
	// public final static String SERVICE_URL = "http://116.62.166.200:8181";
	// public static final String REDIS_HOST = "172.16.212.5";
	// public static final int REDIS_PORT = 6300;
	// public static final String UDP_PROXY_HOST = "http://172.16.212.4:8585";
	// public static final String SYS_DATA_APPKEY =
	// "DCE16E160AAC2154BDC5F60D39FE2F59";
	// public static final String SYS_DATA_SECRET =
	// "jos7zdnd3yyb1ws9av4ntetb6q78glbu";
	// public static final String FILE_SERVER = "http://116.62.166.200:8085";
	// public static final String PUSH_HOST = "http://172.16.212.5:8383";
	// public static final String QS_HOST = "http://home.pstone.cc:8080";
	// public static final String NBIOT_HOST = "https://180.101.147.89:8743";
	//
	// public final static String GETUI_APPID = "h115L9rgQH6OnlPQo3b9U3";
	// public final static String GETUI_APPKEY = "jQYVPJ3mwNA9sDVgn5UCh6";
	// public final static String GETUI_MASTER_SECRET =
	// "6jFgmTc3kL9UJ9GQfDbrk8";

	/************* 本地 **************/
	// public final static String SERVICE_URL = "https://yun.ethome.com";
	// public static final String REDIS_HOST = "127.0.0.1";
	// public static final int REDIS_PORT = 6379;
	// public static final String UDP_PROXY_HOST = "http://udp.ethome.com:8585";
	// public static final String SYS_DATA_APPKEY =
	// "2CB602D60C06AE2C1CE503A6B31CF1AC";
	// public static final String SYS_DATA_SECRET =
	// "m4w0bfzj02368jf3oqpfvy4hmfw4wyy8";
	// public static final String FILE_SERVER = "http://";
	// public static final String PUSH_HOST = "http://push.ethome.com:8084";
	// public static final String QS_HOST =
	// "https://test.pstone.cc/PstoneServerByJFinal2.0";
	// public static final String NBIOT_HOST = "https://180.101.147.89:8743";
	// public final static String GETUI_APPID = "jY7WgRnltq5rkpX0zUkQG5";
	// public final static String GETUI_APPKEY = "akNKJ4ItZ37OttbFPoOkV5";
	// public final static String GETUI_MASTER_SECRET =
	// "IkL2KJF85P8v1qZWHf5Gd3";

	/************* 测试 ************/
	// public static final String SERVICE_URL = "http://116.62.166.200:8181";
	// public static final String REDIS_HOST = "172.16.212.5";
	// public static final int REDIS_PORT = 6300;
	// public static final String UDP_PROXY_HOST = "http://172.16.212.4:8585";
	// public static final String SYS_DATA_APPKEY =
	// "DCE16E160AAC2154BDC5F60D39FE2F59";
	// public static final String SYS_DATA_SECRET =
	// "jos7zdnd3yyb1ws9av4ntetb6q78glbu";
	// public static final String FILE_SERVER = "http://116.62.166.200:8085";
	// public static final String PUSH_HOST = "http://172.16.212.5:8383";
	// public static final String QS_HOST = "http://home.pstone.cc:8080";
	// public static final String NBIOT_HOST = "https://180.101.147.89:8743";
	// public final static String GETUI_APPID = "jY7WgRnltq5rkpX0zUkQG5";
	// public final static String GETUI_APPKEY = "akNKJ4ItZ37OttbFPoOkV5";
	// public final static String GETUI_MASTER_SECRET =
	// "IkL2KJF85P8v1qZWHf5Gd3";

	public final static String SERVICE_URL = ServerConfig.getValue("SERVICE_URL");
	public static final String REDIS_HOST = ServerConfig.getValue("REDIS_HOST");
	public static final int REDIS_PORT = Integer.parseInt(ServerConfig.getValue("REDIS_PORT"));
	public static final String UDP_PROXY_HOST = ServerConfig.getValue("UDP_PROXY_HOST");
	public static final String SYS_DATA_APPKEY = ServerConfig.getValue("SYS_DATA_APPKEY");
	public static final String SYS_DATA_SECRET = ServerConfig.getValue("SYS_DATA_SECRET");
	public static final String FILE_SERVER = ServerConfig.getValue("FILE_SERVER");
	public static final String PUSH_HOST = ServerConfig.getValue("PUSH_HOST");
	public static final String QS_HOST = ServerConfig.getValue("QS_HOST");
	public static final String NBIOT_HOST = ServerConfig.getValue("NBIOT_HOST");
	public final static String GETUI_APPID = ServerConfig.getValue("GETUI_APPID");
	public final static String GETUI_APPKEY = ServerConfig.getValue("GETUI_APPKEY");
	public final static String GETUI_MASTER_SECRET = ServerConfig.getValue("GETUI_MASTER_SECRET");

	@Deprecated
	public static final String UPGRADE_HOST = "";
	@Deprecated
	public static final String WIFI_UPGRADE_PATH = "/home/Java.SmartHome/wifi/";
	@Deprecated
	public static final String DO_MAIN = "yun.ethome.com";
	@Deprecated
	public final static String IMG_SAVE_FOLDER = "/home/Java.SmartHome/uploadImages/";
	@Deprecated
	public final static String GW_SAVE_FOLDER = "/home/Java.SmartHome/updateGwFile/";
	@Deprecated
	public final static String ANDROID_SAVE_FOLDER = "/home/Java.SmartHome/updateAndroidFile/";
	@Deprecated
	public final static String ANDROID_PAD_FOLDER = "/home/Java.SmartHome/updateAndroidFile/pad/";
	/********* 公共 *********/
	public final static String JAVA_IMAGE_URL_PREFIX = SERVICE_URL;
	public final static String GW_URL_PREFIX = FILE_SERVER + "/download/gw/";

	public static void main(String[] args) {
		// ::locale
		String SERVICE_URL = "https://yun.ethome.com";
		String REDIS_HOST = "127.0.0.1";
		int REDIS_PORT = 6379;
		String UDP_PROXY_HOST = "http://udp.ethome.com:8585";
		String SYS_DATA_APPKEY = "2CB602D60C06AE2C1CE503A6B31CF1AC";
		String SYS_DATA_SECRET = "m4w0bfzj02368jf3oqpfvy4hmfw4wyy8";
		String FILE_SERVER = "http://";
		String PUSH_HOST = "http://push.ethome.com:8084";
		String QS_HOST = "https://test.pstone.cc/PstoneServerByJFinal2.0";
		String NBIOT_HOST = "https://180.101.147.89:8743";
		String GETUI_APPID = "jY7WgRnltq5rkpX0zUkQG5";
		String GETUI_APPKEY = "akNKJ4ItZ37OttbFPoOkV5";
		String GETUI_MASTER_SECRET = "IkL2KJF85P8v1qZWHf5Gd3";

		// ::test
		// String SERVICE_URL = "http://116.62.166.200:8181";
		// String REDIS_HOST = "172.16.212.5";
		// int REDIS_PORT = 6300;
		// String UDP_PROXY_HOST = "http://172.16.212.4:8585";
		// String SYS_DATA_APPKEY = "DCE16E160AAC2154BDC5F60D39FE2F59";
		// String SYS_DATA_SECRET = "jos7zdnd3yyb1ws9av4ntetb6q78glbu";
		// String FILE_SERVER = "http://116.62.166.200:8085";
		// String PUSH_HOST = "http://172.16.212.5:8383";
		// String QS_HOST = "http://home.pstone.cc:8080";
		// String NBIOT_HOST = "https://180.101.147.89:8743";
		// String GETUI_APPID = "jY7WgRnltq5rkpX0zUkQG5";
		// String GETUI_APPKEY = "akNKJ4ItZ37OttbFPoOkV5";
		// String GETUI_MASTER_SECRET = "IkL2KJF85P8v1qZWHf5Gd3";

		// ::product
		// String SERVICE_URL = "http://116.62.166.200:8181";
		// String REDIS_HOST = "172.16.212.5";
		// int REDIS_PORT = 6300;
		// String UDP_PROXY_HOST = "http://172.16.212.4:8585";
		// String SYS_DATA_APPKEY = "DCE16E160AAC2154BDC5F60D39FE2F59";
		// String SYS_DATA_SECRET = "jos7zdnd3yyb1ws9av4ntetb6q78glbu";
		// String FILE_SERVER = "http://116.62.166.200:8085";
		// String PUSH_HOST = "http://172.16.212.5:8383";
		// String QS_HOST = "http://home.pstone.cc:8080";
		// String NBIOT_HOST = "https://180.101.147.89:8743";
		// String GETUI_APPID = "h115L9rgQH6OnlPQo3b9U3";
		// String GETUI_APPKEY = "jQYVPJ3mwNA9sDVgn5UCh6";
		// String GETUI_MASTER_SECRET = "6jFgmTc3kL9UJ9GQfDbrk8";

		System.out.println("SERVICE_URL == " + (SERVICE_URL.equals(ServerConfig.SERVICE_URL)));
		System.out.println("REDIS_HOST == " + (REDIS_HOST.equals(ServerConfig.REDIS_HOST)));
		System.out.println("REDIS_PORT == " + (REDIS_PORT == ServerConfig.REDIS_PORT));
		System.out.println("UDP_PROXY_HOST == " + (UDP_PROXY_HOST.equals(ServerConfig.UDP_PROXY_HOST)));
		System.out.println("SYS_DATA_APPKEY == " + (SYS_DATA_APPKEY.equals(ServerConfig.SYS_DATA_APPKEY)));
		System.out.println("SYS_DATA_SECRET == " + (SYS_DATA_SECRET.equals(ServerConfig.SYS_DATA_SECRET)));
		System.out.println("FILE_SERVER == " + (FILE_SERVER.equals(ServerConfig.FILE_SERVER)));
		System.out.println("PUSH_HOST == " + (PUSH_HOST.equals(ServerConfig.PUSH_HOST)));
		System.out.println("QS_HOST == " + (QS_HOST.equals(ServerConfig.QS_HOST)));
		System.out.println("NBIOT_HOST == " + (NBIOT_HOST.equals(ServerConfig.NBIOT_HOST)));
		System.out.println("GETUI_APPID == " + (GETUI_APPID.equals(ServerConfig.GETUI_APPID)));
		System.out.println("GETUI_APPKEY == " + (GETUI_APPKEY.equals(ServerConfig.GETUI_APPKEY)));
		System.out.println("GETUI_MASTER_SECRET == " + (GETUI_MASTER_SECRET.equals(ServerConfig.GETUI_MASTER_SECRET)));

	}
}
