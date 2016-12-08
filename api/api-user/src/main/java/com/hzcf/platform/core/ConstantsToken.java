package com.hzcf.platform.core;

/**
 * 常量
 * @author lei
 * @date 2016/12/06
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
    
   // public static final String BASE = "base_";
}
