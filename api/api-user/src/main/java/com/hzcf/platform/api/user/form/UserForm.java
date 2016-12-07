package com.hzcf.platform.api.user.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserForm implements Serializable{

	private static final long serialVersionUID = 1999518194615108530L;
	private transient String token;    // 用户登录token

    private String userId;   // 用户唯一标识

    private String username; // 用户登录名

    private String password; // 用户密码

	private String nickName; // 用户昵称

    private boolean enabled; // 是否可用

	private String status;	 // 用户状态

	private String confirmPassword;
	private String oldPassword;

	private boolean accountExpired;     // 账号过期
	private boolean accountLocked;      // 账号锁定
	private boolean credentialsExpired; // 证书过期

	private String tempPageUrl;
	

}
