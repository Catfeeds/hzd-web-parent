package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.Entity;

public class UserVO extends Entity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9188703063339311809L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


