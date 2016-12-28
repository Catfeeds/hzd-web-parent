package com.hzcf.platform.core.user.data;

import java.math.BigDecimal;
import java.util.Date;

public class userInfo {
    private String userInfoId;

    private String userId;

    private String applyId;

    private String idcardValidity;

    private String education;

    private String domicileProvince;

    private String domicileCity;

    private String domicileAddress;

    private String marriageStatus;

    private String childrenStatus;

    private String houseStatus;

    private String residentProvince;

    private String residentCity;

    private String residentAddress;

    private String residentTelCode;

    private String email;

    private BigDecimal annualIncome;

    private BigDecimal creditCardLimit;

    private String liveTogether;

    private String staffNo;

    private Date createTime;

    private String orgName;

    private String orgType;

    private String orgProvince;

    private String orgCity;

    private String orgAddress;

    private String orgTelCode;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getIdcardValidity() {
        return idcardValidity;
    }

    public void setIdcardValidity(String idcardValidity) {
        this.idcardValidity = idcardValidity == null ? null : idcardValidity.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getDomicileProvince() {
        return domicileProvince;
    }

    public void setDomicileProvince(String domicileProvince) {
        this.domicileProvince = domicileProvince == null ? null : domicileProvince.trim();
    }

    public String getDomicileCity() {
        return domicileCity;
    }

    public void setDomicileCity(String domicileCity) {
        this.domicileCity = domicileCity == null ? null : domicileCity.trim();
    }

    public String getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress == null ? null : domicileAddress.trim();
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus == null ? null : marriageStatus.trim();
    }

    public String getChildrenStatus() {
        return childrenStatus;
    }

    public void setChildrenStatus(String childrenStatus) {
        this.childrenStatus = childrenStatus == null ? null : childrenStatus.trim();
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus == null ? null : houseStatus.trim();
    }

    public String getResidentProvince() {
        return residentProvince;
    }

    public void setResidentProvince(String residentProvince) {
        this.residentProvince = residentProvince == null ? null : residentProvince.trim();
    }

    public String getResidentCity() {
        return residentCity;
    }

    public void setResidentCity(String residentCity) {
        this.residentCity = residentCity == null ? null : residentCity.trim();
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress == null ? null : residentAddress.trim();
    }

    public String getResidentTelCode() {
        return residentTelCode;
    }

    public void setResidentTelCode(String residentTelCode) {
        this.residentTelCode = residentTelCode == null ? null : residentTelCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        this.liveTogether = liveTogether == null ? null : liveTogether.trim();
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo == null ? null : staffNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgProvince() {
        return orgProvince;
    }

    public void setOrgProvince(String orgProvince) {
        this.orgProvince = orgProvince == null ? null : orgProvince.trim();
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity == null ? null : orgCity.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getOrgTelCode() {
        return orgTelCode;
    }

    public void setOrgTelCode(String orgTelCode) {
        this.orgTelCode = orgTelCode == null ? null : orgTelCode.trim();
    }
}