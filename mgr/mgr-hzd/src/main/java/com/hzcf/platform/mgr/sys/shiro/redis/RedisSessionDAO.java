package com.hzcf.platform.mgr.sys.shiro.redis;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzcf.platform.common.cache.ICache;

import com.hzcf.platform.mgr.sys.util.CookieConstants;



public class RedisSessionDAO extends AbstractSessionDAO implements Serializable {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	/**
	 * shiro-redis的session对象前缀
	 */
	//private final String SHIRO_REDIS_SESSION_PRE = "shiro_redis_session:";
	
	/**
	 * 存放uid的对象前缀
	 */
	//private final String SHIRO_SHESSIONID_PRE = "shiro_sessionid:";
	
	/**
	 * 存放uid 当前状态的前缀
	 */
	//private final String UID_PRE = "uid:";
	
	/**
	 * 存放用户权限的前缀
	 */
	private final String PERMISSION_PRE ="permission:";

    //private JedisPoolManager cache;
    
	private ICache cache;

	public ICache getCache() {
		return cache;
	}

	public void setCache(ICache cache) {
		this.cache = cache;
	}

	private long expire;//毫秒

	 public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	/** 
     * The Redis key prefix for the sessions  
     */  
    private String keyPrefix = CookieConstants.COOKIE_ID;  
      
	
	@Override  
    public void update(Session session) throws UnknownSessionException {  
        this.saveSession(session);  
    }  
      
    /** 
     * save session 
     * @param session 
     * @throws UnknownSessionException 
     */  
    private void saveSession(Session session) throws UnknownSessionException{  
        if(session == null || session.getId() == null){  
            logger.error("session or session id is null");  
            return;  
        }  
        
          
        //byte[] key = getByteKey(session.getId());  
        //byte[] value = cache.serialize(session); 
        //this.cache.set(key, value);
        //session.setTimeout(cache.getExpire()*1000);
	    String key=getByteKey(session.getId());

        //expire=1800000;
        //session.setTimeout(expire);
	    //Long redisExpire = expire/1000;
	    //int timeout = redisExpire.intValue();
	    //this.cache.setex(key, session,redisExpire.intValue());
        this.cache.save(key, session);  
    }  
    
   
  
    @Override  
    public void delete(Session session) {  
        if(session == null || session.getId() == null){  
            logger.error("session or session id is null");  
            return;  
        }  
        cache.delete(this.getByteKey(session.getId()));  
  
    }  
  
    //用来统计当前活动的session  
    @Override  
    public Collection<Session> getActiveSessions() {  
        Set<Session> sessions = new HashSet<Session>();  
          
        //Set<byte[]> keys = cache.keys(this.keyPrefix + "*");  
        //if(keys != null && keys.size()>0){  
        //    for(byte[] key:keys){  
        //        Session s = (Session)SerializeUtils.deserialize(cache.get(key));  
        //        sessions.add(s);  
        //    }  
        //}  
          
        return sessions;  
    }  
  
    @Override  
    protected Serializable doCreate(Session session) {  
        Serializable sessionId = this.generateSessionId(session);    
        this.assignSessionId(session, sessionId);  
        this.saveSession(session);  
        return sessionId;  
    }  
  
    @Override  
    protected Session doReadSession(Serializable sessionId) {  
        if(sessionId == null){  
            logger.error("session id is null");  
            return null;  
        }  
        String key=this.getByteKey(sessionId);
        Session s = (Session)cache.load(key);  
        return s;  
    }  
    
   
   /* public PermissionContext getPermissions(String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setRoles(authorizationService.findRoles(appKey, username));
        permissionContext.setPermissions(authorizationService.findPermissions(appKey, username));
        return permissionContext;
    }*/
      
    /** 
     * 获得byte[]型的key 
     * @param key 
     * @return 
     */  
    private String getByteKey(Serializable sessionId){  
        String preKey = this.keyPrefix + sessionId;  
        return preKey;  
    }  
  
  
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
	
}
