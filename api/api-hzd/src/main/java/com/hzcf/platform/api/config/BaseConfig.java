package com.hzcf.platform.api.config;


/**
 * 
 * @description:基础配置信息
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class BaseConfig {

	/**
	 * USER
	 */
	public static final String REQUEST_USER = "REQUEST_USER_MYF";

	/**
	 * HOST
	 */
	public static final String REQUEST_HOST = "REQUEST_HOST_MYF";

	/**
	 * AGENT
	 */
	public static final String REQUEST_AGENT = "REQUEST_AGENT_MYF";

	public static final String ENCODE = "utf-8";
	/**
	 * REDIS
	 */
	public static final String REDIS_PREFIXED = "MYF_REDIS";
	
	public static final String USER_TYPE="user";
	
	public static final String USER_IOS="IOS";
	
	public static final String USER_ANDROID="Android";
	
	public static final String IP="ip";

	public static final String status_1="1"; //用户有效状态 1无效 0有效
	public static final String status_0="0";
}
