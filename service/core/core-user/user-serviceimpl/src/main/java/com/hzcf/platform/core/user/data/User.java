package com.hzcf.platform.core.user.data;

import java.math.BigDecimal;
import java.util.Date;

import com.hzcf.platform.common.model.AbstractEntity;

public class User  extends AbstractEntity{
	
	private String password;
    private String mobile;

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
        return mobile;
    }

    public void setName(String mobile) {
        this.mobile = mobile;
    }
}
