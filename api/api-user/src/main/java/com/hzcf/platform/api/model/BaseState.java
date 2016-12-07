package com.hzcf.platform.api.model;


import java.io.Serializable;

public class BaseState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8914921386374325083L;
	private String retCode;
	private String retInfo;
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	
	
}
