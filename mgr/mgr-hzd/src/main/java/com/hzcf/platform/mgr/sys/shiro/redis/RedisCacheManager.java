package com.hzcf.platform.mgr.sys.shiro.redis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzcf.platform.common.cache.ICache;

public class RedisCacheManager  implements CacheManager{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	private ICache cache;

	public ConcurrentMap<String, Cache> getCaches() {
		return caches;
	}

		public <K, V> Cache<K, V> getCache(String name) throws CacheException {
			logger.debug("获取名称为: " + name + " 的RedisCache实例");
			Cache c = caches.get(name);
			if (c == null) {
				c = new RedisCache<K,V>(cache);
				caches.put(name, c);
			} 
			return c;
		}
	        //setter和getter方法省略

		public ICache getCache() {
			return cache;
		}

		public void setCache(ICache cache) {
			this.cache = cache;
		}

		
}
