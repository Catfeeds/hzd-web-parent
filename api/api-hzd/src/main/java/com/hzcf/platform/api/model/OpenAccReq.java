package com.hzcf.platform.api.model;


import java.io.Serializable;
import java.util.List;

/**
 * 
 * @description:请求参数
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
public class OpenAccReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bizId;//	String	30	流水号
	private String signature;//	String		签名信息
	private String systemSourceId;//	String	30	来源系统
	private String sendType;//	String;//	2	发送类型(实时：1 ，批量：2)
	private List<SendBillList> sendBillList;
	
	
	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}


	public String getSystemSourceId() {
		return systemSourceId;
	}


	public void setSystemSourceId(String systemSourceId) {
		this.systemSourceId = systemSourceId;
	}


	public String getSendType() {
		return sendType;
	}


	public void setSendType(String sendType) {
		this.sendType = sendType;
	}


	public List<SendBillList> getSendBillList() {
		return sendBillList;
	}


	public void setSendBillList(List<SendBillList> sendBillList) {
		this.sendBillList = sendBillList;
	}

	
	public static class  SendBillList{
		private String bizId;//	String	30	流水号
		private String addr;//	String	50	"发送地址，如手机号
		private String content;//	String	500	发送内容
		public String getBizId() {
			return bizId;
		}
		public void setBizId(String bizId) {
			this.bizId = bizId;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
			

	}
}
