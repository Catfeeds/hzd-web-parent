package com.hzcf.platform.core.user.data;


import com.hzcf.platform.common.model.AbstractEntity;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

public class UserInfo extends AbstractEntity {
    private String userInfoId;//用户详情ID
    private String userId;//用户ID
    private String applyId;//用户申请ID

    /**身份证信息*/
    private String idType;//证件类型,默认值为“01”  (新增字段)
    private String idcardValidity; //证件有效期
    private Date birthday;//出生日期,从身份证号中截取   (新增字段)
    private String gender;//性别,根据身份证号倒数第二位设置值      (新增字段)
    
    
    /**户籍信息*/
    private String education;//最高学历（10-研究生；20-大学本科（简称“大学”）；30-大学专科和专科学校（简称“大专”）；40-中等专业学校或中等技术学校；50-技术学校；60-高中；70-初中；80-小学；90-文盲或半文盲；99-未知。）
    private String domicileProvince;//户籍所在省
    private String domicileCity;//户籍所在市
    private String domicileAddress;//户籍详细地址
    private String domicilePostCode;//户籍邮政编码    (新增字段)
    
    
    /**家庭收入信息*/
    private String marriageStatus;//婚姻状况（10-未婚；20-已婚；21-初婚；22-再婚；23-复婚；30-丧偶；40-离婚；90-未说明的婚姻状况。）
    private String childrenStatus;//有无子女 1有  0无
    private String houseStatus;//房产情况
    private String residentProvince;//家庭所在省
    private String residentCity;//家庭所在市
    private String residentAddress;//家庭详细地址
    private String residentTelCode;//家庭电话号码
    private String residentTelAreaCode;//家庭电话区号  (新增字段)
    private String residentPostCode;//家庭邮政编码  (新增字段)
    private String email;//电子邮件地址
    private BigDecimal annualIncome;//个人年收入
    private BigDecimal creditCardLimit;//信用卡最高额度
    private String liveTogether;//共同居住者
    private String staffNo;//员工编号，对应线下的employeeId（员工编号）


    /**单位信息*/
    private String orgName;//单位名称
    private String orgType;//单位性质
    private String orgProvince;//单位所在省
    private String orgCity;//单位所在市
    private String orgAddress;//单位详细地址
    private String orgPostCode;//单位邮政编码  (新增字段)
    private String orgTelAreaCode;//单位电话区号  (新增字段)
    private String orgTelCode;//单位电话号码
	private String department; //所在部门
	private String positions; //担任职务
	private Date entryDate; //入职时间
	private String remark;//备注
    
    /**借款信息*/
    private String isExpress;//是否加急,默认为0，就是默认为否  (新增字段)
    private String productId;//贷款类型,默认为“精英贷1.89”,对应的productId的值为“01”  (新增字段)
    private String receiverLoginName;//受理人，默认为“线上进件”  (新增字段)
    private String isInside;//网内/网外  (新增字段)
    private String orgTeamId;//所属团队，咨询师  (新增字段)
    private String borrowType;//借款类型  (新增字段)

	public String getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdcardValidity() {
		return idcardValidity;
	}
	public void setIdcardValidity(String idcardValidity) {
		this.idcardValidity = idcardValidity;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDomicileProvince() {
		return domicileProvince;
	}
	public void setDomicileProvince(String domicileProvince) {
		this.domicileProvince = domicileProvince;
	}
	public String getDomicileCity() {
		return domicileCity;
	}
	public void setDomicileCity(String domicileCity) {
		this.domicileCity = domicileCity;
	}
	public String getDomicileAddress() {
		return domicileAddress;
	}
	public void setDomicileAddress(String domicileAddress) {
		this.domicileAddress = domicileAddress;
	}
	public String getDomicilePostCode() {
		return domicilePostCode;
	}
	public void setDomicilePostCode(String domicilePostCode) {
		this.domicilePostCode = domicilePostCode;
	}
	public String getMarriageStatus() {
		return marriageStatus;
	}
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}
	public String getChildrenStatus() {
		return childrenStatus;
	}
	public void setChildrenStatus(String childrenStatus) {
		this.childrenStatus = childrenStatus;
	}
	public String getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}
	public String getResidentProvince() {
		return residentProvince;
	}
	public void setResidentProvince(String residentProvince) {
		this.residentProvince = residentProvince;
	}
	public String getResidentCity() {
		return residentCity;
	}
	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}
	public String getResidentAddress() {
		return residentAddress;
	}
	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}
	public String getResidentTelCode() {
		return residentTelCode;
	}
	public void setResidentTelCode(String residentTelCode) {
		this.residentTelCode = residentTelCode;
	}
	public String getResidentTelAreaCode() {
		return residentTelAreaCode;
	}
	public void setResidentTelAreaCode(String residentTelAreaCode) {
		this.residentTelAreaCode = residentTelAreaCode;
	}
	public String getResidentPostCode() {
		return residentPostCode;
	}
	public void setResidentPostCode(String residentPostCode) {
		this.residentPostCode = residentPostCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	public BigDecimal getCreditCardLimit() {
		return creditCardLimit;
	}
	public void setCreditCardLimit(BigDecimal creditCardLimit) {
		this.creditCardLimit = creditCardLimit;
	}
	public String getLiveTogether() {
		return liveTogether;
	}
	public void setLiveTogether(String liveTogether) {
		this.liveTogether = liveTogether;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgProvince() {
		return orgProvince;
	}
	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgPostCode() {
		return orgPostCode;
	}
	public void setOrgPostCode(String orgPostCode) {
		this.orgPostCode = orgPostCode;
	}
	public String getOrgTelAreaCode() {
		return orgTelAreaCode;
	}
	public void setOrgTelAreaCode(String orgTelAreaCode) {
		this.orgTelAreaCode = orgTelAreaCode;
	}
	public String getOrgTelCode() {
		return orgTelCode;
	}
	public void setOrgTelCode(String orgTelCode) {
		this.orgTelCode = orgTelCode;
	}
	public String getIsExpress() {
		return isExpress;
	}
	public void setIsExpress(String isExpress) {
		this.isExpress = isExpress;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getReceiverLoginName() {
		return receiverLoginName;
	}
	public void setReceiverLoginName(String receiverLoginName) {
		this.receiverLoginName = receiverLoginName;
	}
	public String getIsInside() {
		return isInside;
	}
	public void setIsInside(String isInside) {
		this.isInside = isInside;
	}
	public String getOrgTeamId() {
		return orgTeamId;
	}
	public void setOrgTeamId(String orgTeamId) {
		this.orgTeamId = orgTeamId;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userInfoId='" + userInfoId + '\'' +
				", userId='" + userId + '\'' +
				", applyId='" + applyId + '\'' +
				", idType='" + idType + '\'' +
				", idcardValidity='" + idcardValidity + '\'' +
				", birthday=" + birthday +
				", gender='" + gender + '\'' +
				", education='" + education + '\'' +
				", domicileProvince='" + domicileProvince + '\'' +
				", domicileCity='" + domicileCity + '\'' +
				", domicileAddress='" + domicileAddress + '\'' +
				", domicilePostCode='" + domicilePostCode + '\'' +
				", marriageStatus='" + marriageStatus + '\'' +
				", childrenStatus='" + childrenStatus + '\'' +
				", houseStatus='" + houseStatus + '\'' +
				", residentProvince='" + residentProvince + '\'' +
				", residentCity='" + residentCity + '\'' +
				", residentAddress='" + residentAddress + '\'' +
				", residentTelCode='" + residentTelCode + '\'' +
				", residentTelAreaCode='" + residentTelAreaCode + '\'' +
				", residentPostCode='" + residentPostCode + '\'' +
				", email='" + email + '\'' +
				", annualIncome=" + annualIncome +
				", creditCardLimit=" + creditCardLimit +
				", liveTogether='" + liveTogether + '\'' +
				", staffNo='" + staffNo + '\'' +
				", orgName='" + orgName + '\'' +
				", orgType='" + orgType + '\'' +
				", orgProvince='" + orgProvince + '\'' +
				", orgCity='" + orgCity + '\'' +
				", orgAddress='" + orgAddress + '\'' +
				", orgPostCode='" + orgPostCode + '\'' +
				", orgTelAreaCode='" + orgTelAreaCode + '\'' +
				", orgTelCode='" + orgTelCode + '\'' +
				", department='" + department + '\'' +
				", positions='" + positions + '\'' +
				", entryDate=" + entryDate +
				", remark='" + remark + '\'' +
				", isExpress='" + isExpress + '\'' +
				", productId='" + productId + '\'' +
				", receiverLoginName='" + receiverLoginName + '\'' +
				", isInside='" + isInside + '\'' +
				", orgTeamId='" + orgTeamId + '\'' +
				", borrowType='" + borrowType + '\'' +
				'}';
	}
}