package com.ethome.iws.common;

/**
 * 全局参数
 */
public class ApplicationParams {

    public final static int NORMAL_SCENE = 1;
    public final static int LINK_SCENE = 2;

    /* 多联开关数限制 五联 */
    public final static int GROUP_MAX_SWITCH = 5;

    /* 平台类型定义 */
    public final static int ANDROID_PLATFORM = 3;
    public final static int IOS_PLATFORM = 4;
    public final static int IPAD_PLATFORM = 5;
    public final static int ANDPAD_PLATFORM = 6;
    /**
     * 包括android和IOS
     */
    public final static int MOBILEPHONE_PLATFORM = 2;
    /**
     * 包括android和IOS
     */
    public final static int PAD_PLATFORM = 7;

    //开关
    public final static String SWITCH_TYPE_ID = "0010";
    //插座
    public final static String SOCKET_TYPE_ID = "0020";
    //风机空调
    public final static String FAN_AIR_CON_TYPE_ID = "0030";
    //地暖
    public final static String WARM_AIR_CON_TYPE_ID = "0050";
    //主机空调A款
    public final static String HOST_AIR_CON_A_TYPE_ID = "0061";
    //主机空调B款
    public final static String HOST_AIR_CON_B_TYPE_ID = "0060";
    //移动感应器
    public final static String MOVE_INDUCTOR_TYPE_ID = "0070";
    //智能门铃
    public final static String DOOR_BELL_TYPE_ID = "0080";
    //呼叫铃
    public final static String BELL_CALL_TYPE_ID = "0081";
    //智能门磁
    public final static String DOOR_SENSOR_TYPE_ID = "0090";
    //环境感应器
    public final static String ENVIRONMENT_INDUCTOR_TYPE_ID = "00a0";
    //模拟设备
    public final static String SIMULATION_DEVICE_TYPE_ID = "00b0";
    //网络摄像机
    public final static String IP_CAMERA_TYPE_ID = "00c0";
    //空调伴侣
    public final static String AIR_REMOTE_CON_TYPE_ID = "00d0";
    //智能门锁
    public final static String SMART_LOCK_TYPE_ID = "00e0";
    //告警单元
    public final static String INFORM_EQUIPMENT_TYPE_ID = "00f0";

    //其他消息
    public final static String OTHER_MESSAGE = "01";
    //报警消息
    public final static String WARN_MESSAGE = "02";
    //情景消息(普通+联动)
    public final static String SCENE_MESSAGE = "03";
    //定时消息
    public final static String TIMING_MESSAGE = "04";
    //访客模式消息
    public final static String VISITOR_MESSAGE = "05";

    /* 推送消息状态 */
    public final static String PUSH_MESSAGE_SUCESS = "0";
    public final static String PUSH_MESSAGE_RESCIND_SUCESS = "1";
    public final static String PUSH_MESSAGE_RESCIND_FAILED = "2";
    public final static String PUSH_MESSAGE_FAILED = "3";

    /* 感应延时时间 单位秒  */
    public final static int INFRARSENSE_DELAY = 5;

    // android wq
    public final static String Baidu_Push_API_KEY_ANDROID = "0owjV1zEIyuLDLkh0YQYwa9i";
    public final static String Baidu_Push_SECRET_KEY_ANDROID = "TcUqHTZ1wlcbDKjfVMGGB7ENxlcKDjL1";

    // ios xzp
    public final static String Baidu_Push_API_KEY_IOS = "xjche1b4umpj1UGWZUDGZ6HT";
    public final static String Baidu_Push_SECRET_KEY_IOS = "pGWX0MSM221FjGyI1ewjrt3wkhGgyy35";

    // android zss
//      public final static String Baidu_Push_API_KEY_ANDROID = "yfCX1ExuyAiHIOPBhFLMf77d";
//      public final static String Baidu_Push_SECRET_KEY_ANDROID = "jkUBxFyeRyS48mA1qG3vbmp6jWN0OUXP";


    /* 海康摄像机相关 */
    public final static String HK_APPKEY = "6c1020a926944f6cb531e67631b19cca";
    public final static String HK_APPSECRET = "eafc241555d8325c55f058131497ad2e";
    public final static String HK_PUSHSECRET = "4e14d7e2-4e05-4da3-888d-856e43a0165f";

    public final static String APP_LOAD_URL = "http://www.ethome.com/appcode.htm";
    
    public final static String CHARACTER_SET = "UTF-8";
    
    public final static String LOGIN_TOKEN = "LOGIN_TOKEN_";
    public final static String ACCESS_TOKEN = "ACCESS_TOKEN_";
    public final static String PARTNER_KEY = "PARTNER_APPKEY_";
    
    public final static String LOGIN_ADMIN = "LOGIN_ADMIN";
    
	/**
	 * 返回类型
	 */
	public static final String CONTENTTYPE_APP_JSON = "application/json";
	
	public static final String DEVICE_PORT = "DEVICE_PORT";
	
	/**
	 * 告警模式
	 */
	public static final String INFRAR_ALARM= "infrarAlarm";
	/**
	 * 联动模式
	 */
	public static final String INFRAR_SENSE= "infrarSense";
	/**
	 * 反向联动
	 */
	public static final String INFRAR_CLEAN = "infrarClean";
	
	/**
	 * pad首页设备快捷标识
	 */
	public static final String SHORTCUT = "shortcut";
	public static final String Y = "Y";
	public static final String N = "N";
	
	/**
	 * 启用
	 */
	public static final String SCENE_PWD_STATUS_ON = "on";
	/**
	 * 停用
	 */
	public static final String SCENE_PWD_STATUS_STOP = "stop";
	
	public static final String GW_TYPE_C91 = "C91";
	
	public static final int ROOT = 203;
	public static final int PARTNER = 204;
	
	public static final int MONTH_12 = 12;
}
