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
	
	private String ip;
	
	/**
	 * 终端类型
	 */
	private String terminal;

	/**
	 * 终端版本
	 */
	private String version;
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

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


