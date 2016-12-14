package com.hzcf.platform.core.sys.model;

public class Element implements java.io.Serializable { 
	
	private String contentCn;

	/**
	 * 数据内容 英文
	 */
	private String contentEn;
	/**
	 * 索引
	 */
	private Integer position;
	
	public String getContentCn() {
		return contentCn;
	}
	public void setContentCn(String contentCn) {
		this.contentCn = contentCn;
	}
	public String getContentEn() {
		return contentEn;
	}
	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}

}
