package com.hzcf.platform.api.model;
/**
 * 
 * @description:贷款申请模型
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class OnlineLoanInfo {
	
	private String mobile;
	private String name;
	private String idCard; 
	private String area;
	private String openId;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "OnlineLoanInfo [mobile=" + mobile + ", name=" + name + ", idCard=" + idCard + ", area=" + area
				+ ", openId=" + openId + "]";
	}
	
	
}