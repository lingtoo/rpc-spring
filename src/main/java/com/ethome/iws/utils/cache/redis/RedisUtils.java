package com.ethome.iws.utils.cache.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ethome.iws.common.ApplicationParams;
import com.ethome.iws.common.ServerConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtils {

	private static Logger logger = Logger.getLogger(RedisUtils.class);

	/** 缓存1小时 */
	private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;
	
	/** 缓存2小时 */
	public static final int CACHE_2_HOUR = 60 * 60 * 2;

	/**
	 * 缓存30分钟
	 */
	private static final int CACHE_30_MINUTE = 60 * 30 * 1;

	/**
	 * 缓存7天
	 */
	private static final int CACHE_SEVEN_DAY = DEFAULT_CACHE_SECONDS * 24 * 7;

	/** 连接池 **/
	private static JedisPool jedisPool = null;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1000;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 500;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 1000 * 30 * 1;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	// 超时时间
	private static int TIMEOUT = 1000 * 60 * 2;

	static {
		try {
			if (jedisPool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(MAX_ACTIVE);
				config.setMaxIdle(MAX_IDLE);
				config.setMaxWaitMillis(MAX_WAIT);
				config.setTestOnBorrow(TEST_ON_BORROW);
				jedisPool = new JedisPool(config, ServerConfig.REDIS_HOST, ServerConfig.REDIS_PORT, TIMEOUT);
			}
			
		} catch (Exception e) {
			logger.error("First create JedisPool error : " + e);
		}
	}

	/**
	 * 释放redis资源
	 * 
	 * @param jedis
	 */
	private static void releaseResource(Jedis jedis) {
		if (jedis != null) {
			try {
				jedisPool.returnResource(jedis);
			} catch (Exception e) {
				logger.error("Cache release Resource fail：" + e);
			}
		}
	}

	/**
	 * 删除Redis中的所有key
	 * 
	 * @throws Exception
	 */
	public static void flushAll() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.flushAll();
		} catch (Exception e) {
			logger.error("Cache清空失败：" + e);
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 缓存7天
	 * 
	 * @param key
	 * @param object
	 */
	public static Boolean saveSevenDay(Object key, Object object) {
		return save(key, object, CACHE_SEVEN_DAY);
	}

	/**
	 * 缓存30分钟
	 * 
	 * @param key
	 * @param object
	 */
	public static Boolean saveThirtyMinute(Object key, Object object) {
		return save(key, object, CACHE_30_MINUTE);
	}

	/**
	 * 保存一个对象到Redis中 . <br/>
	 * 
	 * @param key
	 *            键 . <br/>
	 * @param object
	 *            缓存对象 . <br/>
	 * @return true or false . <br/>
	 * @throws Exception
	 */
	public static Boolean save(Object key, Object object) {
		return save(key, object, 0);
	}

	/**
	 * 保存一个对象到redis中并指定过期时间
	 * 
	 * @param key
	 *            键 . <br/>
	 * @param object
	 *            缓存对象 . <br/>
	 * @param seconds
	 *            过期时间（单位为秒）.<br/>
	 * @return true or false .
	 */
	public static Boolean save(Object key, Object object, int seconds) {
		if(object == null){
			logger.error("redis save object is null, key is "+key);
			return false;
		}
		if(key == null){
			logger.error("redis save key is null");
			return false;
		}
			
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
			if (seconds > 0)
				jedis.expire(SerializeUtils.serialize(key), seconds);
			return true;
		} catch (Exception e) {
			logger.error("Cache保存失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 根据缓存键获取Redis缓存中的值.<br/>
	 * 
	 * @param key
	 *            键.<br/>
	 * @return Object .<br/>
	 * @throws Exception
	 */
	public static Object get(Object key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] obj = jedis.get(SerializeUtils.serialize(key));
			return obj == null ? null : SerializeUtils.unSerialize(obj);
		} catch (Exception e) {
			logger.error("Cache获取失败：" + e);
			return null;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 根据缓存键清除Redis缓存中的值.<br/>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Boolean del(Object key) {
		Jedis jedis = null;
		try {
			// System.out.println(key);
			jedis = jedisPool.getResource();
			jedis.del(SerializeUtils.serialize(key));
			return true;
		} catch (Exception e) {
			logger.error("Cache删除失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 根据缓存键清除Redis缓存中的值.<br/>
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static Boolean del(Object... keys) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(SerializeUtils.serialize(keys));
			return true;
		} catch (Exception e) {
			logger.error("Cache删除失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 
	 * @param key
	 * @param seconds
	 *            超时时间（单位为秒）
	 * @return
	 */
	public static Boolean expire(Object key, int seconds) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(SerializeUtils.serialize(key), seconds);
			return true;
		} catch (Exception e) {
			logger.error("Cache设置超时时间失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 添加一个内容到指定key的hash中
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static Boolean addHash(String key, Object field, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(SerializeUtils.serialize(key), SerializeUtils.serialize(field), SerializeUtils.serialize(value));
			return true;
		} catch (Exception e) {
			logger.error("Cache保存失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 从指定hash中拿一个对象
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static Object getHash(Object key, Object field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] obj = jedis.hget(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
			return SerializeUtils.unSerialize(obj);
		} catch (Exception e) {
			logger.error("Cache读取失败：" + e);
			return null;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 从hash中删除指定filed的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static Boolean delHash(Object key, Object field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
			return result == 1 ? true : false;
		} catch (Exception e) {
			logger.error("Cache删除失败：" + e);
			return null;
		} finally {
			releaseResource(jedis);
		}
	}
	
	/**
	 * 新增value到key的set中
	 * @param key
	 * @param value
	 * @return
	 */
	public static Boolean addSet(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long sRst = jedis.sadd(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
			return sRst == 1;
		} catch (Exception e) {
			logger.error("Cache保存失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}
	
	public static Set<Object> getAllSet(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<byte[]> bytes = jedis.smembers(SerializeUtils.serialize(key));
			Set<Object> sets = new HashSet<>();
			for(byte[] b : bytes){
				sets.add(SerializeUtils.unSerialize(b));
			}
			return sets;
		} catch (Exception e) {
			logger.error("Cache fail：" + e);
			return null;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * vaule是否存在于key的set中
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean isExistSet(String key, Object value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sismember(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
		} catch (Exception e) {
			logger.error("Cache fail：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}
	
	/**
	 * 从key的 set中移除value
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean delSet(String key, Object value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long rst = jedis.srem(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
			return 1==rst;
		} catch (Exception e) {
			logger.error("Cache fail：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 拿到缓存中所有符合pattern的key
	 * 
	 * @param pattern
	 * @return
	 */
	public static Set<byte[]> keys(String pattern) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<byte[]> allKey = jedis.keys(("*" + pattern + "*").getBytes());
			return allKey;
		} catch (Exception e) {
			logger.error("Cache获取失败：" + e);
			return new HashSet<byte[]>();
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 获得hash中的所有key value
	 * 
	 * @param key
	 * @return
	 */
	public static Map<byte[], byte[]> getAllHash(Object key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Map<byte[], byte[]> map = jedis.hgetAll(SerializeUtils.serialize(key));
			return map;
		} catch (Exception e) {
			logger.error("Cache获取失败：" + e);
			return null;
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 判断一个key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static Boolean exists(Object key) {
		Jedis jedis = null;
		Boolean result = false;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(SerializeUtils.serialize(key));
			return result;
		} catch (Exception e) {
			logger.error("Cache获取失败：" + e);
			return false;
		} finally {
			releaseResource(jedis);
		}
	}

	public static String getAccessTokenKey(int loginClient, int userId) {
		return ApplicationParams.ACCESS_TOKEN + loginClient + "_" + userId;
	}

	public static int getNumActive() {
		Jedis jedis = null;
		try {
			return jedisPool.getNumActive();
		} catch (Exception e) {
			logger.error("Cache获取失败：" + e);
			return -1;
		} finally {
			releaseResource(jedis);
		}
	}
	
	public static String getRedisCacheValue(){
		System.out.println("--->REDIS HOST::"+ServerConfig.REDIS_HOST+",port::"+ServerConfig.REDIS_PORT);
	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deviceId", "0203a0fff800");
		
		RedisUtils.save("test_device", map);
		
		Object obj2 = RedisUtils.get("SCAN_DEVICE");
		System.out.println("----------------SCAN_DEVICE::"+obj2);
		Object obj = RedisUtils.get("WATCHED_DEVICE020370fff800");
		logger.info("---->RedisUtils get::"+obj+",abc::"+RedisUtils.get("test_device"));
		if(null != obj){
			JSONObject json = JSONObject.parseObject(JSON.toJSONString(obj));
			logger.info("redis cache value::"+json);
			return (String)json.get("deviceId");
		}
		return "0203a0fff800";
	}

	public static void main(String[] args) {
		
	}

}
