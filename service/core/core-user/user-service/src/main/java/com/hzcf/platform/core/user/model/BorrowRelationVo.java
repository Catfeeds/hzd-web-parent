package com.hzcf.platform.core.user.model;


/**
  * @Description:
  * 	BorrowRelationVo：表示借款人关系，用于线上和线下对接转换
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2017年1月3日 下午4:56:51 
  * @version 1.0 
  * @since  JDK1.7
  */
public class BorrowRelationVo {
	private	String	name;//姓名
	private	String	relationType;//和本人关系
	private	String	telCode;//电话/手机号
	private	String	type;//关系类型
	public BorrowRelationVo() {
		super();
	}
	public BorrowRelationVo(String name, String relationType, String telCode, String type) {
		super();
		this.name = name;
		this.relationType = relationType;
		this.telCode = telCode;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getTelCode() {
		return telCode;
	}
	public void setTelCode(String telCode) {
		this.telCode = telCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BorrowRelationVo [name=" + name + ", relationType=" + relationType + ", telCode=" + telCode + ", type="
				+ type + "]";
	}
}