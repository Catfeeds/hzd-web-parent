//package com.hzcf.platform.core.user.webService.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
///**
// * Description: 汇众贷进件申请二期VO
// * @author Administrator
// * @version 1.0
// * <pre>
// * Modification History: 
// * Date         Author      Version     Description 
//------------------------------------------------------------------
// * 2017年1月3日    Administrator       1.0        1.0 Version 
// * </pre>
// */
//public class HuiZhongApplyVo {
//	private String systemId;//进件标识
//	private String employeeId;//员工编号
//	private String operatorId;//操作人ID
//	private String name;//姓名
//	private String usedName;//曾用名
//	private String education;//学历
//	private String domicileProvince;//户籍所在省
//	private String domicileCity;//户籍所在市
//	private String domicileDistrict;//户籍所在区
//	private String domicileAddress;//户籍详细地址
//	private String domicilePostCode;//户籍邮政编码
//	private String idType;//证件类型
//	private String idNum;//身份证号
//	private Date idValidityDate;//证件有效期
//	private Date birthday;//出生日期
//	private String productId;//贷款类型
//	private String period;//期数(月)
//	private String gender;//性别
//	private String marriageStatus;//婚姻状况
//	private String childrenStatus;//有无子女
//	private String houseStatus;//房产情况
//	private String residentProvince;//家庭所在省
//	private String residentCity;//家庭所在市
//	private String residentDistrict;//家庭所在区
//	private String residentAddress;//家庭详细地址
//	private String residentTelAreaCode;//家庭电话区号
//	private String residentTelCode;//家庭电话号码
//	private String residentPostCode;//家庭邮政编码
//	private String residentTelExt;
//	private String mobile1;//手机号码
//	private String email;//邮箱
//	private BigDecimal annualIncome;//个人年收入(元)
//	private BigDecimal creditCardLimit;//信用卡最高额度(元)
//	private String liveTogether;//共同居住者
//	private String loanPurposeOne;//借款用途
//	private String loanPurposeTwo;//借款用途详情
//	private BigDecimal minApplyAmount;//申请最低额度(元)
//	private BigDecimal maxApplyAmount;//申请最高额度(元)
//	private BigDecimal maxMonthlyPayment;//月还款最高额度(元)
//	private String orgName;//单位名称
//	private String orgEntryDate;//入职时间
//	private String orgDepartment;//所在部门
//	private String orgTitle;//担任职称
//	private String orgType;//单位性质
//	private String orgProvince;//单位所在省
//	private String orgCity;//单位所在市
//	private String orgDistrict;//单位所在区
//	private String orgAddress;//单位详细地址
//	private String orgPostCode;//单位邮政编码
//	private String orgTelAreaCode;//单位电话区号
//	private String orgTelCode;//单位电话号码
//	private String orgTelExt;//单位电话分机号
////	private String bankId;//开户行行别
////	private String bankProvinceId;//开户行所在省
////	private String bankCityId;//开户行所在市
////	private String bankName;//开户行支行名称
////	private String bankAccountName;//开户行户名
////	private String bankAccountNo;//开户行帐号
//	private String isExpress;//是否加急
//	private String learnSource;//从何获知
//	private String learnSourceDesc;//从何获知（其他）
//	private String receiverLoginName;//受理人
//	private String isInside;//网内/网外
//	private String orgTeamId;//所属团队
//	private String borrowType;//借款类型
//	private String saleAuditLoginName;//审核人
//	private String remark;//备注
//	private List<BorrowRelationVo> borrowRelationist;//借款人关系List
//	private List<ImageVO> imageList;//图片List
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getUsedName() {
//		return usedName;
//	}
//	public void setUsedName(String usedName) {
//		this.usedName = usedName;
//	}
//	public String getEducation() {
//		return education;
//	}
//	public void setEducation(String education) {
//		this.education = education;
//	}
//	public String getDomicileProvince() {
//		return domicileProvince;
//	}
//	public void setDomicileProvince(String domicileProvince) {
//		this.domicileProvince = domicileProvince;
//	}
//	public String getDomicileCity() {
//		return domicileCity;
//	}
//	public void setDomicileCity(String domicileCity) {
//		this.domicileCity = domicileCity;
//	}
//	public String getDomicileDistrict() {
//		return domicileDistrict;
//	}
//	public void setDomicileDistrict(String domicileDistrict) {
//		this.domicileDistrict = domicileDistrict;
//	}
//	public String getDomicileAddress() {
//		return domicileAddress;
//	}
//	public void setDomicileAddress(String domicileAddress) {
//		this.domicileAddress = domicileAddress;
//	}
//	public String getDomicilePostCode() {
//		return domicilePostCode;
//	}
//	public void setDomicilePostCode(String domicilePostCode) {
//		this.domicilePostCode = domicilePostCode;
//	}
//	public String getIdType() {
//		return idType;
//	}
//	public void setIdType(String idType) {
//		this.idType = idType;
//	}
//	public String getIdNum() {
//		return idNum;
//	}
//	public void setIdNum(String idNum) {
//		this.idNum = idNum;
//	}
//	public Date getIdValidityDate() {
//		return idValidityDate;
//	}
//	public void setIdValidityDate(Date idValidityDate) {
//		this.idValidityDate = idValidityDate;
//	}
//	public String getProductId() {
//		return productId;
//	}
//	public void setProductId(String productId) {
//		this.productId = productId;
//	}
//	public String getPeriod() {
//		return period;
//	}
//	public void setPeriod(String period) {
//		this.period = period;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getMarriageStatus() {
//		return marriageStatus;
//	}
//	public void setMarriageStatus(String marriageStatus) {
//		this.marriageStatus = marriageStatus;
//	}
//	public String getChildrenStatus() {
//		return childrenStatus;
//	}
//	public void setChildrenStatus(String childrenStatus) {
//		this.childrenStatus = childrenStatus;
//	}
//	public String getHouseStatus() {
//		return houseStatus;
//	}
//	public void setHouseStatus(String houseStatus) {
//		this.houseStatus = houseStatus;
//	}
//	public String getResidentProvince() {
//		return residentProvince;
//	}
//	public void setResidentProvince(String residentProvince) {
//		this.residentProvince = residentProvince;
//	}
//	public String getResidentCity() {
//		return residentCity;
//	}
//	public void setResidentCity(String residentCity) {
//		this.residentCity = residentCity;
//	}
//	public String getResidentDistrict() {
//		return residentDistrict;
//	}
//	public void setResidentDistrict(String residentDistrict) {
//		this.residentDistrict = residentDistrict;
//	}
//	public String getResidentAddress() {
//		return residentAddress;
//	}
//	public void setResidentAddress(String residentAddress) {
//		this.residentAddress = residentAddress;
//	}
//	public String getResidentTelAreaCode() {
//		return residentTelAreaCode;
//	}
//	public void setResidentTelAreaCode(String residentTelAreaCode) {
//		this.residentTelAreaCode = residentTelAreaCode;
//	}
//	public String getResidentTelCode() {
//		return residentTelCode;
//	}
//	public void setResidentTelCode(String residentTelCode) {
//		this.residentTelCode = residentTelCode;
//	}
//	public String getResidentPostCode() {
//		return residentPostCode;
//	}
//	public void setResidentPostCode(String residentPostCode) {
//		this.residentPostCode = residentPostCode;
//	}
//	public String getMobile1() {
//		return mobile1;
//	}
//	public void setMobile1(String mobile1) {
//		this.mobile1 = mobile1;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public BigDecimal getAnnualIncome() {
//		return annualIncome;
//	}
//	public void setAnnualIncome(BigDecimal annualIncome) {
//		this.annualIncome = annualIncome;
//	}
//	public BigDecimal getCreditCardLimit() {
//		return creditCardLimit;
//	}
//	public void setCreditCardLimit(BigDecimal creditCardLimit) {
//		this.creditCardLimit = creditCardLimit;
//	}
//	public String getLiveTogether() {
//		return liveTogether;
//	}
//	public void setLiveTogether(String liveTogether) {
//		this.liveTogether = liveTogether;
//	}
//	public String getLoanPurposeOne() {
//		return loanPurposeOne;
//	}
//	public void setLoanPurposeOne(String loanPurposeOne) {
//		this.loanPurposeOne = loanPurposeOne;
//	}
//	public String getLoanPurposeTwo() {
//		return loanPurposeTwo;
//	}
//	public void setLoanPurposeTwo(String loanPurposeTwo) {
//		this.loanPurposeTwo = loanPurposeTwo;
//	}
//	public BigDecimal getMinApplyAmount() {
//		return minApplyAmount;
//	}
//	public void setMinApplyAmount(BigDecimal minApplyAmount) {
//		this.minApplyAmount = minApplyAmount;
//	}
//	public BigDecimal getMaxApplyAmount() {
//		return maxApplyAmount;
//	}
//	public void setMaxApplyAmount(BigDecimal maxApplyAmount) {
//		this.maxApplyAmount = maxApplyAmount;
//	}
//	public BigDecimal getMaxMonthlyPayment() {
//		return maxMonthlyPayment;
//	}
//	public void setMaxMonthlyPayment(BigDecimal maxMonthlyPayment) {
//		this.maxMonthlyPayment = maxMonthlyPayment;
//	}
//	public String getOrgName() {
//		return orgName;
//	}
//	public void setOrgName(String orgName) {
//		this.orgName = orgName;
//	}
//	public String getOrgEntryDate() {
//		return orgEntryDate;
//	}
//	public void setOrgEntryDate(String orgEntryDate) {
//		this.orgEntryDate = orgEntryDate;
//	}
//	public String getOrgDepartment() {
//		return orgDepartment;
//	}
//	public void setOrgDepartment(String orgDepartment) {
//		this.orgDepartment = orgDepartment;
//	}
//	public String getOrgTitle() {
//		return orgTitle;
//	}
//	public void setOrgTitle(String orgTitle) {
//		this.orgTitle = orgTitle;
//	}
//	public String getOrgType() {
//		return orgType;
//	}
//	public void setOrgType(String orgType) {
//		this.orgType = orgType;
//	}
//	public String getOrgProvince() {
//		return orgProvince;
//	}
//	public void setOrgProvince(String orgProvince) {
//		this.orgProvince = orgProvince;
//	}
//	public String getOrgCity() {
//		return orgCity;
//	}
//	public void setOrgCity(String orgCity) {
//		this.orgCity = orgCity;
//	}
//	public String getOrgDistrict() {
//		return orgDistrict;
//	}
//	public void setOrgDistrict(String orgDistrict) {
//		this.orgDistrict = orgDistrict;
//	}
//	public String getOrgAddress() {
//		return orgAddress;
//	}
//	public void setOrgAddress(String orgAddress) {
//		this.orgAddress = orgAddress;
//	}
//	public String getOrgPostCode() {
//		return orgPostCode;
//	}
//	public void setOrgPostCode(String orgPostCode) {
//		this.orgPostCode = orgPostCode;
//	}
//	public String getOrgTelAreaCode() {
//		return orgTelAreaCode;
//	}
//	public void setOrgTelAreaCode(String orgTelAreaCode) {
//		this.orgTelAreaCode = orgTelAreaCode;
//	}
//	public String getOrgTelCode() {
//		return orgTelCode;
//	}
//	public void setOrgTelCode(String orgTelCode) {
//		this.orgTelCode = orgTelCode;
//	}
//	public String getOrgTelExt() {
//		return orgTelExt;
//	}
//	public void setOrgTelExt(String orgTelExt) {
//		this.orgTelExt = orgTelExt;
//	}
////	public String getBankId() {
////		return bankId;
////	}
////	public void setBankId(String bankId) {
////		this.bankId = bankId;
////	}
////	public String getBankProvinceId() {
////		return bankProvinceId;
////	}
////	public void setBankProvinceId(String bankProvinceId) {
////		this.bankProvinceId = bankProvinceId;
////	}
////	public String getBankCityId() {
////		return bankCityId;
////	}
////	public void setBankCityId(String bankCityId) {
////		this.bankCityId = bankCityId;
////	}
////	public String getBankName() {
////		return bankName;
////	}
////	public void setBankName(String bankName) {
////		this.bankName = bankName;
////	}
////	public String getBankAccountName() {
////		return bankAccountName;
////	}
////	public void setBankAccountName(String bankAccountName) {
////		this.bankAccountName = bankAccountName;
////	}
////	public String getBankAccountNo() {
////		return bankAccountNo;
////	}
////	public void setBankAccountNo(String bankAccountNo) {
////		this.bankAccountNo = bankAccountNo;
////	}
//	public String getIsExpress() {
//		return isExpress;
//	}
//	public void setIsExpress(String isExpress) {
//		this.isExpress = isExpress;
//	}
//	public String getLearnSource() {
//		return learnSource;
//	}
//	public void setLearnSource(String learnSource) {
//		this.learnSource = learnSource;
//	}
//	public String getLearnSourceDesc() {
//		return learnSourceDesc;
//	}
//	public void setLearnSourceDesc(String learnSourceDesc) {
//		this.learnSourceDesc = learnSourceDesc;
//	}
//	public String getReceiverLoginName() {
//		return receiverLoginName;
//	}
//	public void setReceiverLoginName(String receiverLoginName) {
//		this.receiverLoginName = receiverLoginName;
//	}
//	public String getIsInside() {
//		return isInside;
//	}
//	public void setIsInside(String isInside) {
//		this.isInside = isInside;
//	}
//	public String getOrgTeamId() {
//		return orgTeamId;
//	}
//	public void setOrgTeamId(String orgTeamId) {
//		this.orgTeamId = orgTeamId;
//	}
//	public String getBorrowType() {
//		return borrowType;
//	}
//	public void setBorrowType(String borrowType) {
//		this.borrowType = borrowType;
//	}
//	public String getSaleAuditLoginName() {
//		return saleAuditLoginName;
//	}
//	public void setSaleAuditLoginName(String saleAuditLoginName) {
//		this.saleAuditLoginName = saleAuditLoginName;
//	}
//	public List<BorrowRelationVo> getBorrowRelationist() {
//		return borrowRelationist;
//	}
//	public void setBorrowRelationist(List<BorrowRelationVo> borrowRelationist) {
//		this.borrowRelationist = borrowRelationist;
//	}
//	public String getSystemId() {
//		return systemId;
//	}
//	public void setSystemId(String systemId) {
//		this.systemId = systemId;
//	}
//	public String getEmployeeId() {
//		return employeeId;
//	}
//	public void setEmployeeId(String employeeId) {
//		this.employeeId = employeeId;
//	}
//	public String getReamrk() {
//		return remark;
//	}
//	public void setReamrk(String remark) {
//		this.remark = remark;
//	}
//	public String getResidentTelExt() {
//		return residentTelExt;
//	}
//	public void setResidentTelExt(String residentTelExt) {
//		this.residentTelExt = residentTelExt;
//	}
//	public Date getBirthday() {
//		return birthday;
//	}
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}
//	public List<ImageVO> getImageList() {
//		return imageList;
//	}
//	public void setImageList(List<ImageVO> imageList) {
//		this.imageList = imageList;
//	}
//	public String getOperatorId() {
//		return operatorId;
//	}
//	public void setOperatorId(String operatorId) {
//		this.operatorId = operatorId;
//	}
//	public String getRemark() {
//		return remark;
//	}
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//}
