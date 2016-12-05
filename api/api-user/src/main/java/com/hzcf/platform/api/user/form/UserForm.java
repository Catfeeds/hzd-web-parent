package com.hzcf.platform.api.user.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserForm implements Serializable{

	private static final long serialVersionUID = 1999518194615108530L;
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
