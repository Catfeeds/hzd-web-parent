package com.hzcf.platform.core.user.webService.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
  * @Description:
  * 	MainBorrowerVO：对应线下的“主借款人数据集合”，用于线上和线下的对接转换
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2017年1月3日 下午3:46:32 
  * @version 1.0 
  * @since  JDK1.7
  */
public class MainBorrowerVO {
	private	String	name;//姓名
	private	String	education;//学历
	private	String	domicileProvince;//户籍所在省
	private	String	domicileCity;//户籍所在市
	private	String	domicilePostCode;//户籍邮政编码
	private	String  idType;//证件类型
	private	String	idNum;//证件号码
	private	Date	idValidityDate;//证件有效期
	private	Date	birthday;//出生日期
	private	String	gender;//性别
	private	String	marriageStatus;//婚姻状况
	private	String	childrenStatus;//有无子女
	private	String	houseStatus;//房产情况
	private	String	residentProvince;//家庭所在省
	private	String	residentCity;//家庭所在市
	private	String	residentAddress;//家庭详细地址
	private	String	residentTelAreaCode;//家庭电话区号
	private	String	residentTelCode	;//家庭电话号码
	private	String	residentPostCode;//家庭邮政编码
	private	String	mobile1	;//申请人手机号码
	private	String	email;//电子邮件地址
	private	BigDecimal	annualIncome;//个人年收入(元)
	private	BigDecimal	creditCardLimit;//信用卡最高额度(元)
	private	String	liveTogether;//共同居住者
	private	String	loanPurposeOne;//借款用途
	private	String	loanPurposeTwo;//借款用途详情
	private	BigDecimal	minApplyAmount;//申请最低额度(元)
	private	BigDecimal	maxApplyAmount;//申请最高额度(元)
	private	BigDecimal	maxMonthlyPayment;//月还款最高额度(元)
	private	String	orgName;//单位名称
	private	String	orgType;//单位性质
	private	String	orgProvince;//单位所在省
	private	String	orgCity;//单位所在市
	private	String	orgAddress;//单位详细地址
	private	String	orgPostCode;//单位邮政编码
	private	String	orgTelAreaCode;//单位电话区号
	private	String	orgTelCode;//单位电话号码
	private	String	bankId;//开户行行别
	private	String	bankProvinceId;//开户行所在省
	private	String	bankCityId;//开户行所在市
	private	String	bankName;//开户行支行名称
	private	String	bankAccountName;//开户行户名
	private	String	bankAccountNo;//开户行帐号
	private	String	isExpress;//是否加急
	private	String	productId;//贷款类型
	private	String	period;//期数(月)
	private	String	receiverLoginName;//受理人
	private	String	isInside;//网内/网外
	private	String	orgTeamId;//所属团队
	private	String	borrowType;//借款类型
	
	private List<BorrowRelationVo> borrowRelationVoList;//借款人关系
	
	
	public MainBorrowerVO() {
		super();
	}


	public MainBorrowerVO(String name, String education, String domicileProvince, String domicileCity,
			String domicilePostCode, String idType, String idNum, Date idValidityDate, Date birthday, String gender,
			String marriageStatus, String childrenStatus, String houseStatus, String residentProvince,
			String residentCity, String residentAddress, String residentTelAreaCode, String residentTelCode,
			String residentPostCode, String mobile1, String email, BigDecimal annualIncome, BigDecimal creditCardLimit,
			String liveTogether, String loanPurposeOne, String loanPurposeTwo, BigDecimal minApplyAmount,
			BigDecimal maxApplyAmount, BigDecimal maxMonthlyPayment, String orgName, String orgType, String orgProvince,
			String orgCity, String orgAddress, String orgPostCode, String orgTelAreaCode, String orgTelCode,
			String bankId, String bankProvinceId, String bankCityId, String bankName, String bankAccountName,
			String bankAccountNo, String isExpress, String productId, String period, String receiverLoginName,
			String isInside, String orgTeamId, String borrowType, List<BorrowRelationVo> borrowRelationVoList) {
		super();
		this.name = name;
		this.education = education;
		this.domicileProvince = domicileProvince;
		this.domicileCity = domicileCity;
		this.domicilePostCode = domicilePostCode;
		this.idType = idType;
		this.idNum = idNum;
		this.idValidityDate = idValidityDate;
		this.birthday = birthday;
		this.gender = gender;
		this.marriageStatus = marriageStatus;
		this.childrenStatus = childrenStatus;
		this.houseStatus = houseStatus;
		this.residentProvince = residentProvince;
		this.residentCity = residentCity;
		this.residentAddress = residentAddress;
		this.residentTelAreaCode = residentTelAreaCode;
		this.residentTelCode = residentTelCode;
		this.residentPostCode = residentPostCode;
		this.mobile1 = mobile1;
		this.email = email;
		this.annualIncome = annualIncome;
		this.creditCardLimit = creditCardLimit;
		this.liveTogether = liveTogether;
		this.loanPurposeOne = loanPurposeOne;
		this.loanPurposeTwo = loanPurposeTwo;
		this.minApplyAmount = minApplyAmount;
		this.maxApplyAmount = maxApplyAmount;
		this.maxMonthlyPayment = maxMonthlyPayment;
		this.orgName = orgName;
		this.orgType = orgType;
		this.orgProvince = orgProvince;
		this.orgCity = orgCity;
		this.orgAddress = orgAddress;
		this.orgPostCode = orgPostCode;
		this.orgTelAreaCode = orgTelAreaCode;
		this.orgTelCode = orgTelCode;
		this.bankId = bankId;
		this.bankProvinceId = bankProvinceId;
		this.bankCityId = bankCityId;
		this.bankName = bankName;
		this.bankAccountName = bankAccountName;
		this.bankAccountNo = bankAccountNo;
		this.isExpress = isExpress;
		this.productId = productId;
		this.period = period;
		this.receiverLoginName = receiverLoginName;
		this.isInside = isInside;
		this.orgTeamId = orgTeamId;
		this.borrowType = borrowType;
		this.borrowRelationVoList = borrowRelationVoList;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getDomicilePostCode() {
		return domicilePostCode;
	}


	public void setDomicilePostCode(String domicilePostCode) {
		this.domicilePostCode = domicilePostCode;
	}


	public String getIdType() {
		return idType;
	}


	public void setIdType(String idType) {
		this.idType = idType;
	}


	public String getIdNum() {
		return idNum;
	}


	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}


	public Date getIdValidityDate() {
		return idValidityDate;
	}


	public void setIdValidityDate(Date idValidityDate) {
		this.idValidityDate = idValidityDate;
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


	public String getResidentTelAreaCode() {
		return residentTelAreaCode;
	}


	public void setResidentTelAreaCode(String residentTelAreaCode) {
		this.residentTelAreaCode = residentTelAreaCode;
	}


	public String getResidentTelCode() {
		return residentTelCode;
	}


	public void setResidentTelCode(String residentTelCode) {
		this.residentTelCode = residentTelCode;
	}


	public String getResidentPostCode() {
		return residentPostCode;
	}


	public void setResidentPostCode(String residentPostCode) {
		this.residentPostCode = residentPostCode;
	}


	public String getMobile1() {
		return mobile1;
	}


	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
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


	public String getLoanPurposeOne() {
		return loanPurposeOne;
	}


	public void setLoanPurposeOne(String loanPurposeOne) {
		this.loanPurposeOne = loanPurposeOne;
	}


	public String getLoanPurposeTwo() {
		return loanPurposeTwo;
	}


	public void setLoanPurposeTwo(String loanPurposeTwo) {
		this.loanPurposeTwo = loanPurposeTwo;
	}


	public BigDecimal getMinApplyAmount() {
		return minApplyAmount;
	}


	public void setMinApplyAmount(BigDecimal minApplyAmount) {
		this.minApplyAmount = minApplyAmount;
	}


	public BigDecimal getMaxApplyAmount() {
		return maxApplyAmount;
	}


	public void setMaxApplyAmount(BigDecimal maxApplyAmount) {
		this.maxApplyAmount = maxApplyAmount;
	}


	public BigDecimal getMaxMonthlyPayment() {
		return maxMonthlyPayment;
	}


	public void setMaxMonthlyPayment(BigDecimal maxMonthlyPayment) {
		this.maxMonthlyPayment = maxMonthlyPayment;
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


	public String getBankId() {
		return bankId;
	}


	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public String getBankProvinceId() {
		return bankProvinceId;
	}


	public void setBankProvinceId(String bankProvinceId) {
		this.bankProvinceId = bankProvinceId;
	}


	public String getBankCityId() {
		return bankCityId;
	}


	public void setBankCityId(String bankCityId) {
		this.bankCityId = bankCityId;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankAccountName() {
		return bankAccountName;
	}


	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}


	public String getBankAccountNo() {
		return bankAccountNo;
	}


	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
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


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
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


	public List<BorrowRelationVo> getBorrowRelationVoList() {
		return borrowRelationVoList;
	}


	public void setBorrowRelationVoList(List<BorrowRelationVo> borrowRelationVoList) {
		this.borrowRelationVoList = borrowRelationVoList;
	}


	@Override
	public String toString() {
		return "MainBorrowerVO [name=" + name + ", education=" + education + ", domicileProvince=" + domicileProvince
				+ ", domicileCity=" + domicileCity + ", domicilePostCode=" + domicilePostCode + ", idType=" + idType
				+ ", idNum=" + idNum + ", idValidityDate=" + idValidityDate + ", birthday=" + birthday + ", gender="
				+ gender + ", marriageStatus=" + marriageStatus + ", childrenStatus=" + childrenStatus
				+ ", houseStatus=" + houseStatus + ", residentProvince=" + residentProvince + ", residentCity="
				+ residentCity + ", residentAddress=" + residentAddress + ", residentTelAreaCode=" + residentTelAreaCode
				+ ", residentTelCode=" + residentTelCode + ", residentPostCode=" + residentPostCode + ", mobile1="
				+ mobile1 + ", email=" + email + ", annualIncome=" + annualIncome + ", creditCardLimit="
				+ creditCardLimit + ", liveTogether=" + liveTogether + ", loanPurposeOne=" + loanPurposeOne
				+ ", loanPurposeTwo=" + loanPurposeTwo + ", minApplyAmount=" + minApplyAmount + ", maxApplyAmount="
				+ maxApplyAmount + ", maxMonthlyPayment=" + maxMonthlyPayment + ", orgName=" + orgName + ", orgType="
				+ orgType + ", orgProvince=" + orgProvince + ", orgCity=" + orgCity + ", orgAddress=" + orgAddress
				+ ", orgPostCode=" + orgPostCode + ", orgTelAreaCode=" + orgTelAreaCode + ", orgTelCode=" + orgTelCode
				+ ", bankId=" + bankId + ", bankProvinceId=" + bankProvinceId + ", bankCityId=" + bankCityId
				+ ", bankName=" + bankName + ", bankAccountName=" + bankAccountName + ", bankAccountNo=" + bankAccountNo
				+ ", isExpress=" + isExpress + ", productId=" + productId + ", period=" + period
				+ ", receiverLoginName=" + receiverLoginName + ", isInside=" + isInside + ", orgTeamId=" + orgTeamId
				+ ", borrowType=" + borrowType + ", borrowRelationVoList=" + borrowRelationVoList + "]";
	}

}