package com.hzcf.platform.mgr.sys.common.pageModel;
/**
 * 实名认证用户详情信息
 * @author chunhe.hao
 *
 */
public class SmsUserInfo {

	private String mobile;
	private String name;
	private String idCard;
	private String checkStatus;
	private String createTime;
	private String statusInfo;
	private String butt;
	public String getButt() {
		return butt;
	}
	public void setButt(String butt) {
		this.butt = butt;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	private String artWork;
	private String small;
	private String imageType;
	private String type;
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
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getArtWork() {
		return artWork;
	}
	public void setArtWork(String artWork) {
		this.artWork = artWork;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
