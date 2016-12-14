package com.hzcf.platform.mgr.sys.shiro.redis;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import com.hzcf.platform.mgr.sys.util.CookieConstants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exiao.platform.common.cache.ICache;


@SuppressWarnings("rawtypes")
public class RedisCache<K,V> implements Cache{

	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** 
     * The wrapped Jedis instance. 
     */  
	private ICache cache;
	
     
    public ICache getCache() {
		return cache;
	}

	public void setCache(ICache cache) {
		this.cache = cache;
	}

	/** 
     * The Redis key prefix for caches  
     */  
    private String keyPrefix = CookieConstants.USER_ID;
    /** 
     * Returns the Redis session keys 
     * prefix. 
     * @return The prefix 
     */  
    public String getKeyPrefix() {  
        return keyPrefix;  
    }  
  
    /** 
     * Sets the Redis sessions key  
     * prefix. 
     * @param keyPrefix The prefix 
     */  
    public void setKeyPrefix(String keyPrefix) {  
        this.keyPrefix = keyPrefix;  
    }  
      
    /** 
     * 通过一个JedisManager实例构造RedisCache 
     */  
    public RedisCache(ICache cache){  
         if (cache == null) {  
             throw new IllegalArgumentException("Cache argument cannot be null.");  
         }  
         this.cache = cache;  
    }  
      
    /** 
     * Constructs a cache instance with the specified 
     * Redis manager and using a custom key prefix. 
     * @param cache The cache manager instance 
     * @param prefix The Redis key prefix 
     */  
    public RedisCache(ICache cache,   
                String prefix){  
           
        this( cache );  
          
        // set the prefix  
        this.keyPrefix = prefix;  
    }  
      
    /** 
     * 获得byte[]型的key 
     * @param key 
     * @return 
     */  
    private String getByteKey(K key){  
        if(key instanceof String){  
            String preKey = this.keyPrefix + key.toString();  
            return preKey;  
        }else{  
        	return key.toString();
            //return SerializeUtils.serialize(key);  
        }
		
    }  
  
    @Override  
    public void clear() throws CacheException {  
        logger.debug("从redis中删除所有元素");  
        try {  
        	//cache.;  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @Override  
    public int size() {  
        try {  
        	Long longSize =null;
            //Long longSize = new Long();  
            return longSize.intValue();  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public Set<K> keys() {  
        try {  
        	 Set<K> newKeys = new HashSet<K>(); 
            //Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");  
            //if (CollectionUtils.isEmpty(keys)) {  
            //    return Collections.emptySet();  
            //}else{  
            //    Set<K> newKeys = new HashSet<K>();  
            //    for(byte[] key:keys){  
            //        newKeys.add((K)key);  
            //    }  
            //    return newKeys;
        	
           // }  
        	return newKeys;
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @Override  
    public Collection<V> values() {  
        try {  
            /*Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");  
            if (!CollectionUtils.isEmpty(keys)) {  
                List<V> values = new ArrayList<V>(keys.size());  
                for (byte[] key : keys) {  
                    V value = (V) get((K)key);  
                    if (value != null) {  
                        values.add(value);  
                    }  
                }  
                return Collections.unmodifiableList(values);  
            } else {  
                return Collections.emptyList();  
            }  */
        	return Collections.emptyList();
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }

	@Override
	public Object get(Object key) throws CacheException {
		// TODO Auto-generated method stub
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");  
        try {  
            if (key == null) {  
                return null;  
            }else{  
                //byte[] bytekey=getByteKey((K)key);
            	String  perkey=getByteKey((K)key.toString());
            	V value = cache.load(perkey);  
                return value;  
            }  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
	}

	@Override
	public Object put(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub
		  logger.debug("根据key从存储 key [" + key + "]");  
	         try {  
	        	  
	        	 String  perkey=getByteKey((K)key.toString());
	        	 cache.save(perkey, value);
	        	 //redisManager.set(getByteKey(key), SerializeUtils.serialize(value));  
	             return value;  
	            } catch (Throwable t) {  
	                throw new CacheException(t);  
	            }  
	}

	@Override
	public Object remove(Object key) throws CacheException {
		// TODO Auto-generated method stub
		  logger.debug("从redis中删除 key [" + key + "]");  
	        try {  
	            V previous = (V) get(key);  
	        	String  perkey=getByteKey((K)key.toString());
	            cache.delete(perkey);
	            return previous;  
	        } catch (Throwable t) {  
	            throw new CacheException(t);  
	        }  
	}

	
}
