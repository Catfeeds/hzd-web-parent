package com.hzcf.platform.core;

/**
 * 
 * @description:token 长亮
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class ConstantsToken {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 111;
    /**
     * 短信验证码有效时间（一分钟）
     */
    public static final int SMS_EXPIRES_MIN = 60;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    
    public static final String USER_CACHE_KEY = "USER_CACHE_";
    
    //注册 验证码
    public static final String SMS_CACHE_REG_KEY = "SMS_CACHE_REG_";
    //修改密码 验证码
    public static final String SMS_CACHE_UPDATEPWD_KEY = "SMS_CACHE_UPDATEPWD_";
    //找回密码 验证码
    public static final String SMS_CACHE_FINDPWD_KEY = "SMS_CACHE_FINDPWD_";
    
    public static final int TOKENLOSESTATUS = 110;
}
