package com.hzcf.platform.core.user.data;


import com.hzcf.platform.common.model.AbstractEntity;

public class User  extends AbstractEntity{
	
	private String mobile;
	private String password;

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
}
