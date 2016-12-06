package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.BaseVO;

public class UserVO extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9188703063339311809L;

	private String name;
	private String password;
	
	public UserVO(){
		
	}
	public UserVO(String name,String password){
		this.name=name;
		this.password=password;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


