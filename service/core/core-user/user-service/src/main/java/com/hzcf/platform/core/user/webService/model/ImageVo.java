package com.hzcf.platform.core.user.webService.model;

/**
  * @Description:线上和线下对接的“进件接口”，对应的线下的实体类
  * @author 作者:裴高祥
  * @date 创建时间：2017年1月11日 下午3:06:21 
  * @version 1.0 
  * @since  JDK1.7
  */
public class ImageVo {
	private	String	imageType	;	//	图片类型
	private	String	artWork	;	//	原图路径
	private	String	mid	;	//	中缩略图路径
	private	String	small	;	//	小缩略图路径
	private	String	displayName	;	//	显示名
	public ImageVo() {
		super();
	}
	public ImageVo(String imageType, String artWork, String mid, String small, String displayName) {
		super();
		this.imageType = imageType;
		this.artWork = artWork;
		this.mid = mid;
		this.small = small;
		this.displayName = displayName;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getArtWork() {
		return artWork;
	}
	public void setArtWork(String artWork) {
		this.artWork = artWork;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public String toString() {
		return "ImageVo [imageType=" + imageType + ", artWork=" + artWork + ", mid=" + mid + ", small=" + small
				+ ", displayName=" + displayName + "]";
	}
}