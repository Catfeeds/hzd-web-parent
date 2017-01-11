package com.hzcf.platform.core.user.webService.model;


/**
  * @Description:TODO
  * @author 作者:裴高祥
  * @date 创建时间：2017年1月11日 下午3:03:26 
  * @version 1.0 
  * @since  JDK1.7
  */
public class BorrowRelationVo {
	private	String	name	;	//	姓名	必选
	private	String	relationType	;	//	和本人关系	必选
	private	String	telCode	;	//	电话/手机号	必选
	private	String	type	;	//	关系类型	必选(工作证明人、家庭联系人、紧急联系人)
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