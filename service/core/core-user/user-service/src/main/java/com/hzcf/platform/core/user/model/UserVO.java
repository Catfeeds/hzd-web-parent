package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.BaseVO;
import com.hzcf.platform.common.util.json.parser.JsonUtil;

public class UserVO extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9188703063339311809L;
	private String token;
	private String mobile;
	private String password;
	//缓存验证码 类型
	private String smsCacheType;
	
	public UserVO(){
		
	}
	
	public UserVO(String mobile,String password){
		this.mobile=mobile;
		this.password=password;
	}
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getSmsCacheType() {
		return smsCacheType;
	}

	public void setSmsCacheType(String smsCacheType) {
		this.smsCacheType = smsCacheType;
	}

	@Override
	public String toString() {
		return JsonUtil.json2String(this);
	}
	
}


