package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.BaseVO;
import com.hzcf.platform.common.util.json.parser.JsonUtil;

import java.util.Date;

public class UserVO extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9188703063339311809L;




	private String mobile;

	private String name;

	private String userName;

	private String password;

	private String idCard;

	private String status;

	private String checkStatus;

	private String nopassCause;
	private String submitTime;
	
	private String token;

	//缓存验证码 类型
	private String smsCacheType;
	
	private String ip;
	
    //ext
    private String regTime;
    private String startDate;
    private String endDate;
    private String subStartDate;
    private String subEndDate;
    
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getNopassCause() {
		return nopassCause;
	}

	public void setNopassCause(String nopassCause) {
		this.nopassCause = nopassCause;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getSubStartDate() {
		return subStartDate;
	}

	public void setSubStartDate(String subStartDate) {
		this.subStartDate = subStartDate;
	}

	public String getSubEndDate() {
		return subEndDate;
	}

	public void setSubEndDate(String subEndDate) {
		this.subEndDate = subEndDate;
	}
	
}


