package com.ethome.iws.utils.cache.redis;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;


public class CacheManager<T extends Object> extends AbstractCacheManager {

	private Collection<? extends RedisCache> caches;

	public void setCaches(Collection<? extends RedisCache> caches) {
		this.caches = caches;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}
}
