package com.hzcf.platform.api.model;


import java.io.Serializable;
/**
 * 
 * @description:返回响应信息
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class ServiceStatus implements Serializable{
	
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
