package com.hzcf.platform.mgr.sys.from;



import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;


public class PolicyManageInfoFrom extends PageHelper{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6502643395010907726L;
	private String memberId;
	private String insuranceName;
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String startTime;
	//@DateTimeFormat(iso=ISO.DATE)
	private String endTime;
	private String policyNumber;
	private String recognizeeName;
	private String idNumber;
	private String email;
	private String telephone;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getRecognizeeName() {
		return recognizeeName;
	}
	public void setRecognizeeName(String recognizeeName) {
		this.recognizeeName = recognizeeName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
