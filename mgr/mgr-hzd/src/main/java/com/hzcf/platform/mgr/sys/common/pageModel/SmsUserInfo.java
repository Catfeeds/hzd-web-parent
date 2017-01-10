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
	private String artWorkA;
	private String artWorkB;
	private String artWorkC;
	private String smallA;
	private String smallB;
	private String smallC;
	private String imageType;
	private String type;
	private String imgIdA;
	private String imgIdB;
	private String imgIdC;
	
	public String getImgIdA() {
		return imgIdA;
	}
	public void setImgIdA(String imgIdA) {
		this.imgIdA = imgIdA;
	}
	public String getImgIdB() {
		return imgIdB;
	}
	public void setImgIdB(String imgIdB) {
		this.imgIdB = imgIdB;
	}
	public String getImgIdC() {
		return imgIdC;
	}
	public void setImgIdC(String imgIdC) {
		this.imgIdC = imgIdC;
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
	
	public String getArtWorkA() {
		return artWorkA;
	}
	public void setArtWorkA(String artWorkA) {
		this.artWorkA = artWorkA== null? "":artWorkA.trim();
	}
	public String getArtWorkB() {
		return artWorkB;
	}
	public void setArtWorkB(String artWorkB) {
		this.artWorkB = artWorkB== null? "":artWorkB.trim();
	}
	public String getArtWorkC() {
		return artWorkC;
	}
	public void setArtWorkC(String artWorkC) {
		this.artWorkC = artWorkC== null? "":artWorkC.trim();
	}
	public String getSmallA() {
		return smallA;
	}
	public void setSmallA(String smallA) {
		this.smallA = smallA== null? "":smallA.trim();
	}
	public String getSmallB() {
		return smallB;
	}
	public void setSmallB(String smallB) {
		this.smallB = smallB== null? "":smallB.trim();
	}
	public String getSmallC() {
		return smallC;
	}
	public void setSmallC(String smallC) {
		this.smallC = smallC== null? "":smallC.trim();
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
