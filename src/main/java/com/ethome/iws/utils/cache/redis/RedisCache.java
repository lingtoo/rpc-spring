package com.ethome.iws.utils.cache.redis;

import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;




@Component("redisCache")
public class RedisCache implements Cache {
	/**缓存的命名属性**/
	private String name;

	public RedisUtils cache = new RedisUtils();
	
	/**
	 * 清空所有的缓存 
	 */
	public void clear() {
		RedisUtils.flushAll();
	}

	@Override
	public void evict(Object key) {
		RedisUtils.del(key);
	}

	/**
	 * 根据Key值获得缓存数据
	 */
	public ValueWrapper get(Object key) {
		ValueWrapper result = null;
		Object thevalue = RedisUtils.get(key);
		if (thevalue != null) {
			result = new SimpleValueWrapper(thevalue);
		}
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return cache;
	}
	
	/**添加缓存*/
	public void put(Object arg0, Object arg1) {
		RedisUtils.save(arg0, arg1,20000);
	}

	public RedisCache() {
	}

	public RedisCache(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

}
