package com.hzcf.platform.core.user.model;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */

import com.hzcf.platform.common.model.BaseVO;

import java.math.BigDecimal;
import java.util.Date;


public class UserInfoVO extends BaseVO {
    private String userInfoId;

    private String userId;

    private String applyId;

    private String idcardValidity; //证件有效期

    private String education;//最高学历（10-研究生；20-大学本科（简称“大学”）；30-大学专科和专科学校（简称“大专”）；40-中等专业学校或中等技术学校；50-技术学校；60-高中；70-初中；80-小学；90-文盲或半文盲；99-未知。）

    private String domicileProvince;//户籍所在省

    private String domicileCity;//户籍所在市

    private String domicileAddress;//户籍详细地址

    private String marriageStatus;//婚姻状况（10-未婚；20-已婚；21-初婚；22-再婚；23-复婚；30-丧偶；40-离婚；90-未说明的婚姻状况。）

    private String childrenStatus;//有无子女 1有  0无

    private String houseStatus;//房产情况

    private String residentProvince;//家庭所在省

    private String residentCity;//家庭所在市

    private String residentAddress;//家庭详细地址

    private String residentTelCode;//家庭电话号码

    private String email;//电子邮件地址

    private BigDecimal annualIncome;//个人年收入

    private BigDecimal creditCardLimit;//信用卡最高额度

    private String liveTogether;//共同居住者

    private String staffNo;//员工编号



    private String orgName;//单位名称

    private String orgType;//单位性质

    private String orgProvince;//单位所在省

    private String orgCity;//单位所在市

    private String orgAddress;//单位详细地址

    private String orgTelCode;//单位电话号码


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
