package com.ethome.iws.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 0xxx 成功 1xxx 参数{10xx:http参数,11xx:安全校验,12xx{0x:文件上传,1x:客户端,2x:版本},13xx:数据格式}
 * 2xxx GW{20xx:{20[0|1|2|3|4]x:平台与gw,20[5|6|7|8|9]x:
 * 平台wifi},21xx:内容错误,22xx:设备错误,23xx:配置错误} 4xxx
 * 业务功能{40xx:用户,41xx:设备,42xx:情景联动,43xx:房间分享,44xx:统计分析,45xx:{[0|1]x:定时,2x:历史数据},
 * 46xx:商户,49xx:公共} 9xxx
 * 系统{90xx:GW异常,91xx:数据库异常,92xx:网络异常,93xx{0x:海康},99xx:系统异常}
 * 
 * abc
 * @date 2016年9月13日
 */
public class ReturnCode {
	public static final String SUCCESS = "0000";

	public static final String REQUEST_PARAMS_CHECK_ERROR = "1000";

	public static final String REQUEST_RANDOM_ERROR = "1001";

	public static final String REQUEST_NOT_AUTH = "1002";

	public static final String REQUEST_PARAMS_TYPE_ERROR = "1003";
	/**
	 * 参数格式错误
	 */
	public static final String REQUEST_PARAMS_FORMAT_ERROR = "1004";
	/**
	 * 参数错误
	 */
	public static final String REQUEST_PARAMS_ERROR = "1005";

	public static final String ACCESS_TOKEN_OVERDUE = "1100";
	public static final String JSON_FORMAT_ERROR = "1101";
	/**
	 * 文件上传错误
	 */
	public static final String FILE_UPLOAD_ERROR = "1200";
	/**
	 * 文件大小错误
	 */
	public static final String FILE_SIZE_ERROR = "1201";
	/**
	 * 文件类型错误
	 */
	public static final String FILE_TYPE_ERROR = "1202";
	/**
	 * 文件上传版本错误
	 */
	public static final String FILE_UPLOAD_VERSION_ERROR = "1203";
	/**
	 * 客户端错误
	 */
	public static final String CLIENT_ERROR = "1210";
	/**
	 * android版本不存在
	 */
	public static final String ANDROID_VERSION_NOT_EXIST = "1220";
	/**
	 * 已是最新版
	 */
	public static final String VERSION_IS_NEW = "1221";
	/**
	 * 升级超时
	 */
	public static final String UPGRADE_TIMEOUT = "1222";
	/**
	 * 升级中
	 */
	public static final String UPGRADEING = "1223";
	/**
	 * 可升级
	 */
	public static final String HAVE_UPGRADE_FILE = "1224";
	/**
	 * XML格式错误
	 */
	public static final String XML_FORMAT_ERR = "1300";

	/**
	 * 设备配置失败
	 */
	public static final String SETTING_FAIL = "2000";
	/**
	 * 智慧中心不在线
	 */
	public static final String GW_NOT_ONLINE = "2001";
	/**
	 * command:3 智慧中心响应超时
	 */
	public static final String GW_RESPONSE_TIMEOUT = "2002";
	/**
	 * 获取当前版本失败
	 */
	public static final String GW_GET_VERSION_FAIL = "2003";
	/**
	 * 未添加智慧中心
	 */
	public static final String GW_NOT_ADD = "2004";
	/**
	 * 智慧中心已存在
	 */
	public static final String GW_EXISTED = "2005";
	/**
	 * 智慧中心名称重复
	 */
	public static final String GW_NAME_REPEATE = "2006";
	/**
	 * 无此智慧中心
	 */
	public static final String GW_NOT_HAVE = "2007";
	/**
	 * wifi设备不在线
	 */
	public static final String WIFI_NOT_ONLINE = "2050";
	/**
	 * wifi升级错误
	 */
	public static final String WIFI_UPGRADE_ERROR = "2051";
	/**
	 * 响应超时
	 */
	public static final String WIFI_RESPONSE_TIMEOUT = "2052";

	/**
	 * NB响应超时
	 */
	public static final String NB_RESPONSE_TIMEOUT = "2352";
	/**
	 * NB设备不在线
	 */
	public static final String NB_DEVICE_NOT_ONLINE = "2353";

	/**
	 * 智慧中心密码验证失败
	 */
	public static final String GW_PWD_CHECK_FAIL = "2100";
	/**
	 * 无效指令
	 */
	public static final String INVALID_CMD = "2101";
	/**
	 * 设备不存在
	 */
	public static final String DEVICE_ID_NOT_EXIST = "2201";
	/**
	 * 设备匹配码不匹配
	 */
	public static final String MATCH_CODE_ERROR = "2202";
	/**
	 * 匹配失败
	 */
	public static final String MATCH_FAIL = "2203";

	/**
	 * 设备类型不匹配
	 */
	public static final String GW_DEVICE_TYPE_ERROR = "2204";

	/**
	 * 设备未匹配
	 */
	public static final String GW_DEVICE_NOT_MATCH = "2205";

	/**
	 * 查询设备超时
	 */
	public static final String DEVICE_QUERY_TIMEOUT = "2206";

	/**
	 * 设置设备超时
	 */
	public static final String DEVICE_SET_TIMEOUT = "2207";

	/**
	 * 设置组信息失败
	 */
	public static final String SET_GROUP_INFO_FAIL = "2208";

	/**
	 * 设置中央空调风速失败（非制热制冷模式下）
	 */
	public static final String SET_FAN_SPEED_FAIL = "2209";

	/**
	 * 设备离线
	 */
	public static final String GW_DEVICE_OFFLINE = "2210";

	/**
	 * 绑定智慧中心旧密码不正确
	 */
	public static final String BIND_GW_OLD_PWD_ERROR = "2301";

	/**
	 * 修改智慧总中心密码失败
	 */
	public static final String MODIFY_GW_PWD_FAIL = "2302";

	/**
	 * 升级系统文件不正确
	 */
	public static final String UP_GW_FILE_ERROR = "2303";

	/**
	 * 升级系统失败
	 */
	public static final String UP_GW_FAIL = "2304";

	/**
	 * 访客模式创建失败
	 */
	public static final String CREATE_VISITOR_MODE_FAIL = "2305";

	/**
	 * 获取网络模式失败
	 */
	public static final String GET_NETWORK_MODE_FAIL = "2306";

	/**
	 * 修改网络模式失败
	 */
	public static final String MODIFY_NETWORK_MODE_FAIL = "2307";

	/**
	 * 修改SSID密码失败
	 */
	public static final String MODIFY_SSID_PWD_FAIL = "2308";

	/**
	 * 获取SSID密码失败
	 */
	public static final String GET_SSID_PWD_FAIL = "2309";

	/**
	 * 获取IP地址失败
	 */
	public static final String GET_IP_FAIL = "2310";

	/**
	 * 设置ip地址失败
	 */
	public static final String SET_IP_FAIL = "2311";

	/**
	 * 设置音乐参数无效
	 */
	public static final String SET_MUSIC_PARAM_INVALID = "2312";
	/**
	 * 获取版本信息失败
	 */
	public static final String GET_GW_VERSION_FAIL = "2313";

	/**
	 * 未登录
	 */
	public static final String NOT_LOGIN = "4000";
	/**
	 * 用户名格式错误
	 */
	public static final String USERNAME_FORMAT_ERROR = "4001";
	/**
	 * 密码格式错误
	 */
	public static final String USER_PWD_FORMAT_ERROR = "4002";
	/**
	 * 旧密码格式错误
	 */
	public static final String USER_OLD_PWD_FORMAT_ERROR = "4003";
	/**
	 * 旧密码错误
	 */
	public static final String USER_OLD_PWD_ERROR = "4004";
	/**
	 * 新旧密码相同
	 */
	public static final String NEW_OLD_PWD_SAME = "4005";
	/**
	 * 手机号为空
	 */
	public static final String MOBILE_PHONE_IS_EMPTY = "4006";
	/**
	 * 已注册
	 */
	public static final String USER_REGISTED = "4007";
	/**
	 * 未注册
	 */
	public static final String USER_NOT_REGIST = "4008";
	/**
	 * 获取验证码类型错误
	 */
	public static final String SEND_CHECK_CODE_TYPE_ERROR = "4009";
	/**
	 * 验证码为空
	 */
	public static final String SEND_CHECK_CODE_IS_EMPTY = "4010";
	/**
	 * 验证码错误
	 */
	public static final String SEND_CHECK_CODE_ERROR = "4011";
	/**
	 * 用户名密码错误
	 */
	public static final String USER_NAME_PWD_ERROR = "4012";
	/**
	 * 用户密码错误
	 */
	public static final String USER_PWD_ERROR = "4013";
	/**
	 * 手机号错误
	 */
	public static final String MOBILE_PHONE_ERROR = "4014";
	/**
	 * 用户不存在
	 */
	public static final String USER_NOT_EXIST = "4015";
	/**
	 * 无此消息
	 */
	public static final String MESSAGE_NOT_EXIST = "4020";
	/**
	 * 短信发送失败
	 */
	public static final String SMS_SEND_FAIL = "4021";
	/**
	 * 消息已处理
	 */
	public static final String MESSAGE_HANDLED = "4022";

	/**
	 * 设备类型错误
	 */
	public static final String DEVICE_TYPE_ERROR = "4100";
	/**
	 * 设备不存在
	 */
	public static final String DEVICE_NOT_EXIST = "4101";
	/**
	 * 设备名称已存在
	 */
	public static final String DEVICE_NAME_EXIST = "4102";
	/**
	 * 设备未配对
	 */
	public static final String DEVICE_NOT_MATCH = "4103";
	/**
	 * 父设备不存在
	 */
	public static final String PARENT_DEVICE_NOT_EXIST = "4104";
	/**
	 * 父设备未配对
	 */
	public static final String PARENT_DEVICE_NOT_MATCH = "4105";
	/**
	 * 设备详情格式错误
	 */
	public static final String DEVICE_DETAIL_FORMAT_ERROR = "4106";
	/**
	 * 配对状态错误
	 */
	public static final String DEVICE_MATCHSTATUS_ERROR = "4107";
	/**
	 * 无此设备
	 */
	public static final String NOT_HAVE_DEVICE = "4108";
	/**
	 * 设备已匹配
	 */
	public static final String DEVICE_MATCHED = "4109";
	/**
	 * 配对设备失败
	 */
	public static final String DEVICE_MATCH_FAIL = "4110";
	/**
	 * 设备状态错误
	 */
	public static final String DEVICE_STATUS_ERROR = "4111";
	/**
	 * 设备参数不完整
	 */
	public static final String DEVICE_PARAMS_MISSING = "4112";
	/**
	 * 设备密码已设置
	 */
	public static final String DEVICE_PWD_SETED = "4113";
	/**
	 * 设备密码错误
	 */
	public static final String DEVICE_PWD_ERROR = "4114";
	/**
	 * 设备组gw不一致
	 */
	public static final String DEVICE_GW_INCONFM = "4115";
	/**
	 * 开关存在于情景中
	 */
	public static final String SWITCH_IN_SCENE = "4116";
	/**
	 * 开关加入多联中
	 */
	public static final String SWITCH_IN_GROUP = "4117";
	/**
	 * 开关存在于联动中
	 */
	public static final String SWITCH_IN_LINK = "4118";
	/**
	 * 设备未授权
	 */
	public static final String DEVICE_NOT_AUTH = "4119";
	/**
	 * 设备已添加
	 */
	public static final String DEVICE_ADDED = "4120";

	/**
	 * 无此功能
	 */
	public static final String NOT_HIS_FUNCTION = "4190";

	/**
	 * 设备命令发送失败
	 */
	public static final String DEVICE_SEND_FAIL = "4199";

	/**
	 * 情景下无设备
	 */
	public static final String SCENE_NOT_DEVICE = "4200";
	/**
	 * 联动已存在
	 */
	public static final String LINK_NAME_IS_EXIST = "4201";
	/**
	 * 联动设备重复
	 */
	public static final String LINK_DEVICE_REPEATE = "4202";
	/**
	 * 没有联动条件
	 */
	public static final String LINK_NOT_CONDITION = "4203";
	/**
	 * 只能有一个联动条件
	 */
	public static final String LINK_CONDITION_ONLY_ONE = "4204";
	/**
	 * 没有联动行为
	 */
	public static final String LINK_NOT_ACTION = "4205";
	/**
	 * 设备已存在于其他联动中
	 */
	public static final String DEVICE_EXIST_OTHER_LINK = "4206";
	/**
	 * 无此联动
	 */
	public static final String NOT_LINK = "4207";
	/**
	 * 无此情景
	 */
	public static final String NOT_SCENE = "4230";
	/**
	 * 已存在于其他情景中
	 */
	public static final String DEVICE_EXIST_OTHER_SCENE = "4231";
	/**
	 * 情景已存在
	 */
	public static final String SCENE_EXISTED = "4232";
	/**
	 * 情景不存在
	 */
	public static final String SCENE_NOT_EXIST = "4233";
	/**
	 * 情景设备重复
	 */
	public static final String SCENE_DEVICE_REPEATE = "4234";
	/**
	 * 情景顺序错误
	 */
	public static final String SCENE_ORDER_ERROR = "4235";
	/**
	 * 开启情景失败
	 */
	public static final String SCENE_START_FAIL = "4236";
	/**
	 * 情景开关已存在
	 */
	public static final String SCENE_SWITCH_EXIST = "4237";
	/**
	 * 情景开关不存在
	 */
	public static final String SCENE_SWITCH_NOT_EXIST = "4238";
	/**
	 * 默认情景不能删除
	 */
	public static final String SCENE_NOT_DEL = "4239";
	/**
	 * 情景密码错误
	 */
	public static final String SCENE_PWD_ERR = "4240";
	/**
	 * 情景密码已设置
	 */
	public static final String SCENE_PWD_SETED = "4241";

	/**
	 * 分享邀请码错误
	 */
	public static final String INVITE_CODE_ERROR = "4300";
	/**
	 * 已获取此分享
	 */
	public static final String INVITE_CODE_USED = "4301";
	/**
	 * 超出分享数量
	 */
	public static final String SHARE_OUT_OF_RANG = "4302";
	/**
	 * 获取分享失败
	 */
	public static final String SHARE_GET_FAIL = "4303";
	/**
	 * 未分享给此用户
	 */
	public static final String NOT_SHARED_TO_USER = "4304";
	/**
	 * 分享设备为空
	 */
	public static final String SHARED_DEVICE_EMPTY = "4305";

	/**
	 * 无此房间
	 */
	public static final String NOT_HAVE_ROOM = "4350";
	/**
	 * 房间信息格式错误
	 */
	public static final String ROOM_DETAIL_FORMAT_ERROR = "4351";
	/**
	 * 房间已存在
	 */
	public static final String ROOM_EXISTED = "4352";
	/**
	 * 房间名称重复
	 */
	public static final String ROOM_NAME_REPEATE = "4353";
	/**
	 * 房间顺序错误
	 */
	public static final String ROOM_ORDER_ERROR = "4354";

	/**
	 * 定时不存在
	 */
	public static final String TIMING_NOT_EXIST = "4500";
	/**
	 * 开始结束时间为空
	 */
	public static final String TIMING_TIME_MISSING = "4501";
	/**
	 * 定时时间错误
	 */
	public static final String TIMING_TIME_ERROR = "4502";
	/**
	 * 定时重复
	 */
	public static final String TIMING_REPEATE = "4503";
	/**
	 * 定时冲突
	 */
	public static final String TIMING_CLASH = "4504";

	/**
	 * 商户不存在
	 */
	public static final String PARTNER_NOT_EXIST = "4600";
	/**
	 * 商户状态错误
	 */
	public static final String PARTNER_STATUS_ERROR = "4601";

	/**
	 * 超出限制
	 */
	public static final String OUT_OF_RANG = "4900";

	/**
	 * command:3 响应处理异常
	 */
	public static final String GW_RES_HANDLE_EXCP = "9000";
	/**
	 * command:3 响应处理异常
	 */
	public static final String WIFI_RES_HANDLE_EXCP = "9001";
	/**
	 * 服务器忙
	 */
	public static final String WIFI_UPGRADE_BUSY = "9002";
	/**
	 * 暂无数据
	 */
	public static final String NOT_DATA = "9003";
	/**
	 * 权石响应错误
	 */
	public static final String THIRD_RESP_ERR = "9004";

	/**
	 * 数据库操作,insert返回0
	 */
	public static final String DB_INSERT_RESULT_0 = "9110";
	/**
	 * 数据库操作,update返回0
	 */
	public static final String DB_UPDATE_RESULT_0 = "9120";
	/**
	 * 获取序列出错
	 */
	public static final String DB_GET_SEQ_NEXT_VALUE_ERROR = "9130";

	/**
	 * 获取访问海康设备token失败
	 */
	public static final String HK_GET_ACCESS_TOKEN_FAIL = "9300";

	/**
	 * 第三方响应错误
	 */
	public static final String THD_RESP_ERR = "9399";

	public static final String SYSTEM_ERROR = "9999";

	public static Map<String, String> returnMap = new HashMap<>();

	static {
		returnMap.put(SUCCESS, "操作成功");
		returnMap.put(REQUEST_PARAMS_CHECK_ERROR, "请求参数校验不通过");
		returnMap.put(REQUEST_RANDOM_ERROR, "登录随机数错误");
		returnMap.put(REQUEST_NOT_AUTH, "您无此权限，请联系管理员");
		returnMap.put(REQUEST_PARAMS_TYPE_ERROR, "请求参数类型错误");
		returnMap.put(REQUEST_PARAMS_FORMAT_ERROR, "参数格式错误");
		returnMap.put(REQUEST_PARAMS_ERROR, "参数错误");
		returnMap.put(ACCESS_TOKEN_OVERDUE, "访问token过期");
		returnMap.put(JSON_FORMAT_ERROR, "json格式错误");
		returnMap.put(FILE_UPLOAD_ERROR, "文件上传错误");
		returnMap.put(FILE_SIZE_ERROR, "文件大小错误");
		returnMap.put(FILE_TYPE_ERROR, "文件类型错误");
		returnMap.put(FILE_UPLOAD_VERSION_ERROR, "上传文件版本错误");
		returnMap.put(CLIENT_ERROR, "android:3;ios:4;ipad:5");
		returnMap.put(ANDROID_VERSION_NOT_EXIST, "android版本不存在");
		returnMap.put(VERSION_IS_NEW, "已是最新版");
		returnMap.put(UPGRADE_TIMEOUT, "升级超时");
		returnMap.put(UPGRADEING, "升级中");
		returnMap.put(HAVE_UPGRADE_FILE, "有升级版本");
		returnMap.put(XML_FORMAT_ERR, "XML格式错误");

		returnMap.put(SETTING_FAIL, "配置错误");
		returnMap.put(GW_NOT_ONLINE, "智慧中心不在线");
		returnMap.put(GW_RESPONSE_TIMEOUT, "智慧中心响应超时");
		returnMap.put(GW_GET_VERSION_FAIL, "获取当前版本失败");
		returnMap.put(GW_NOT_ADD, "未添加智慧中心");
		returnMap.put(GW_EXISTED, "智慧中心已存在");
		returnMap.put(GW_NAME_REPEATE, "智慧中心名称重复");
		returnMap.put(GW_NOT_HAVE, "无此智慧中心");

		returnMap.put(WIFI_NOT_ONLINE, "wifi设备不在线");
		returnMap.put(WIFI_UPGRADE_ERROR, "升级错误");
		returnMap.put(WIFI_RESPONSE_TIMEOUT, "wifi响应超时");

		returnMap.put(GW_PWD_CHECK_FAIL, "智慧中心密码验证失败");
		returnMap.put(INVALID_CMD, "无效指令");
		returnMap.put(DEVICE_ID_NOT_EXIST, "设备id不存在");
		returnMap.put(MATCH_CODE_ERROR, "匹配码不匹配");
		returnMap.put(MATCH_FAIL, "匹配失败");
		returnMap.put(GW_DEVICE_TYPE_ERROR, "设备类型错误");
		returnMap.put(GW_DEVICE_NOT_MATCH, "设备未匹配");
		returnMap.put(DEVICE_QUERY_TIMEOUT, "设备查询超时");
		returnMap.put(DEVICE_SET_TIMEOUT, "设备设置超时");
		returnMap.put(SET_GROUP_INFO_FAIL, "设置组信息错误");
		returnMap.put(SET_FAN_SPEED_FAIL, "设置中央空调风速失败");
		returnMap.put(GW_DEVICE_OFFLINE, "设备离线");
		returnMap.put(BIND_GW_OLD_PWD_ERROR, "绑定智慧中心旧密码不正确");
		returnMap.put(MODIFY_GW_PWD_FAIL, "修改智慧总中心密码失败");
		returnMap.put(UP_GW_FILE_ERROR, "升级系统文件不正确");
		returnMap.put(UP_GW_FAIL, "升级系统失败");
		returnMap.put(CREATE_VISITOR_MODE_FAIL, "访客模式创建失败");
		returnMap.put(GET_NETWORK_MODE_FAIL, "获取网络模式失败");
		returnMap.put(MODIFY_NETWORK_MODE_FAIL, "修改网络模式失败");
		returnMap.put(MODIFY_SSID_PWD_FAIL, "修改SSID密码失败");
		returnMap.put(GET_SSID_PWD_FAIL, "获取SSID密码失败");
		returnMap.put(GET_IP_FAIL, "获取IP地址失败");
		returnMap.put(SET_IP_FAIL, "设置ip地址失败");
		returnMap.put(SET_MUSIC_PARAM_INVALID, "设置音乐参数无效");
		returnMap.put(GET_GW_VERSION_FAIL, "获取版本信息失败");

		returnMap.put(NOT_LOGIN, "未登录");
		returnMap.put(USERNAME_FORMAT_ERROR, "登录帐号格式错误");
		returnMap.put(USER_PWD_FORMAT_ERROR, "密码格式错误");
		returnMap.put(USER_OLD_PWD_FORMAT_ERROR, "旧密码格式错误");
		returnMap.put(USER_OLD_PWD_ERROR, "旧密码错误");
		returnMap.put(NEW_OLD_PWD_SAME, "新旧密码相同");
		returnMap.put(MOBILE_PHONE_IS_EMPTY, "手机号为空");
		returnMap.put(USER_REGISTED, "已注册");
		returnMap.put(USER_NOT_REGIST, "未注册");
		returnMap.put(SEND_CHECK_CODE_TYPE_ERROR, "获取校验码类型错误");
		returnMap.put(SEND_CHECK_CODE_IS_EMPTY, "校验码为空");
		returnMap.put(SEND_CHECK_CODE_ERROR, "校验码错误");
		returnMap.put(USER_NAME_PWD_ERROR, "用户名或密码错误");
		returnMap.put(USER_PWD_ERROR, "密码错误");
		returnMap.put(MOBILE_PHONE_ERROR, "手机号错误");
		returnMap.put(USER_NOT_EXIST, "用户不存在");
		returnMap.put(MESSAGE_NOT_EXIST, "无此消息");
		returnMap.put(SMS_SEND_FAIL, "发送短信失败");
		returnMap.put(MESSAGE_HANDLED, "消息已处理");

		returnMap.put(DEVICE_TYPE_ERROR, "设备类型错误");
		returnMap.put(DEVICE_NOT_EXIST, "设备不存在");
		returnMap.put(DEVICE_NAME_EXIST, "设备名称已存在");
		returnMap.put(DEVICE_NOT_MATCH, "未配对");
		returnMap.put(PARENT_DEVICE_NOT_EXIST, "父设备不存在");
		returnMap.put(PARENT_DEVICE_NOT_MATCH, "父设备未配对");
		returnMap.put(DEVICE_DETAIL_FORMAT_ERROR, "设备详情格式错误");
		returnMap.put(DEVICE_MATCHSTATUS_ERROR, "配对状态错误");
		returnMap.put(NOT_HAVE_DEVICE, "无此设备");
		returnMap.put(DEVICE_MATCHED, "已配对");
		returnMap.put(DEVICE_MATCH_FAIL, "配对失败");
		returnMap.put(DEVICE_STATUS_ERROR, "设备状态错误");
		returnMap.put(DEVICE_PARAMS_MISSING, "设备参数不完整");
		returnMap.put(DEVICE_PWD_SETED, "密码已设置");
		returnMap.put(DEVICE_PWD_ERROR, "设备密码错误");
		returnMap.put(DEVICE_GW_INCONFM, "设备组gw不一致");
		returnMap.put(SWITCH_IN_SCENE, "开关存在于情景中");
		returnMap.put(SWITCH_IN_GROUP, "开关已加入多联");
		returnMap.put(SWITCH_IN_LINK, "开关存在于联动中");
		returnMap.put(DEVICE_NOT_AUTH, "设备未授权");
		returnMap.put(DEVICE_ADDED, "设备已添加");
		returnMap.put(NOT_HIS_FUNCTION, "无此功能");
		returnMap.put(DEVICE_SEND_FAIL, "设备命令发送失败");
		returnMap.put(NB_RESPONSE_TIMEOUT, "设备不在线");

		returnMap.put(SCENE_NOT_DEVICE, "无情景设备");
		returnMap.put(LINK_NAME_IS_EXIST, "联动已存在");
		returnMap.put(LINK_DEVICE_REPEATE, "联动设备重复");
		returnMap.put(LINK_NOT_CONDITION, "无联动条件");
		returnMap.put(LINK_CONDITION_ONLY_ONE, "联动条件唯一");
		returnMap.put(LINK_NOT_ACTION, "无联动行为");
		returnMap.put(DEVICE_EXIST_OTHER_LINK, "已存在与其他联动中");
		returnMap.put(NOT_LINK, "无此联动");
		returnMap.put(NOT_SCENE, "无此情景");
		returnMap.put(DEVICE_EXIST_OTHER_SCENE, "存在其他情景中");
		returnMap.put(SCENE_EXISTED, "情景已存在");
		returnMap.put(SCENE_NOT_EXIST, "情景不存在");
		returnMap.put(SCENE_DEVICE_REPEATE, "情景设备重复");
		returnMap.put(SCENE_ORDER_ERROR, "情景顺序错误");
		returnMap.put(SCENE_START_FAIL, "开启情景失败");
		returnMap.put(SCENE_SWITCH_EXIST, "情景开关已存在");
		returnMap.put(SCENE_SWITCH_NOT_EXIST, "情景开关不存在");
		returnMap.put(SCENE_NOT_DEL, "默认情景不能删除");
		returnMap.put(SCENE_PWD_ERR, "情景密码错误");
		returnMap.put(SCENE_PWD_SETED, "情景密码已设置");

		returnMap.put(INVITE_CODE_ERROR, "邀请码错误");
		returnMap.put(INVITE_CODE_USED, "分享已获取");
		returnMap.put(SHARE_OUT_OF_RANG, "超出分享数量");
		returnMap.put(SHARE_GET_FAIL, "获取分享失败");
		returnMap.put(NOT_SHARED_TO_USER, "未分享给此用户");
		returnMap.put(SHARED_DEVICE_EMPTY, "分享设备为空");
		returnMap.put(NOT_HAVE_ROOM, "无此房间");
		returnMap.put(ROOM_DETAIL_FORMAT_ERROR, "房间信息错误");
		returnMap.put(ROOM_EXISTED, "房间已存在");
		returnMap.put(ROOM_NAME_REPEATE, "房间名重复");
		returnMap.put(ROOM_ORDER_ERROR, "房间顺序错误");

		returnMap.put(TIMING_NOT_EXIST, "定时不存在");
		returnMap.put(TIMING_TIME_MISSING, "定时时间为空");
		returnMap.put(TIMING_TIME_ERROR, "定时时间设置错误");
		returnMap.put(TIMING_REPEATE, "定时重复");
		returnMap.put(TIMING_CLASH, "定时冲突");

		returnMap.put(PARTNER_NOT_EXIST, "商户不存在");
		returnMap.put(PARTNER_STATUS_ERROR, "状态错误");

		returnMap.put(OUT_OF_RANG, "超出范围");

		returnMap.put(GW_RES_HANDLE_EXCP, "command:3 响应处理异常");
		returnMap.put(WIFI_RES_HANDLE_EXCP, "wifi响应处理异常");
		returnMap.put(WIFI_UPGRADE_BUSY, "服务器忙");
		returnMap.put(NOT_DATA, "暂无数据");
		returnMap.put(THIRD_RESP_ERR, "失败");

		returnMap.put(DB_INSERT_RESULT_0, "insert result 0");
		returnMap.put(DB_UPDATE_RESULT_0, "update result 0");
		returnMap.put(DB_GET_SEQ_NEXT_VALUE_ERROR, "get seq next error");

		returnMap.put(HK_GET_ACCESS_TOKEN_FAIL, "获取访问海康设备token失败");
		returnMap.put(THD_RESP_ERR, "第三方响应异常");
		returnMap.put(SYSTEM_ERROR, "系统异常");
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Class<?> object = ReturnCode.class;
		Field[] self = object.getDeclaredFields();
		for (int j = 0; j < self.length; j++) {
			System.out.println("case " + self[j].getName() + ":{");
			System.out.println("errorMsg = @\"" + ReturnCode.returnMap.get(self[j].get(object)) + "\";");
			System.out.println("}");
			System.out.println("break;");
		}
	}

}
