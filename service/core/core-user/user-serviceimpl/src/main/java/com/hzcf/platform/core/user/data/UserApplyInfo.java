package com.hzcf.platform.core.user.data;

import com.hzcf.platform.common.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class UserApplyInfo extends AbstractEntity {
    private String applyId;//申请编号
    private String userId;//用户ID

    /**借款信息*/
    private String loanPurposeOne; //借款用途大类
    private String loanPurposeTwo;//借款用途小类
    private BigDecimal minApplyAmount; //申请最低额度
    private BigDecimal maxApplyAmount;//申请最高额度
    private BigDecimal maxMonthlyPayment; //可接受最高月还款额
    private String status;//1=有效   0=无效
    private Date applySubmitTime;//进件时间
    private String period;//期数(月)   (新增字段)
	public UserApplyInfo() {
		super();
	}
	public UserApplyInfo(String applyId, String userId, String loanPurposeOne, String loanPurposeTwo,
			BigDecimal minApplyAmount, BigDecimal maxApplyAmount, BigDecimal maxMonthlyPayment, String status,
			Date applySubmitTime, String period) {
		super();
		this.applyId = applyId;
		this.userId = userId;
		this.loanPurposeOne = loanPurposeOne;
		this.loanPurposeTwo = loanPurposeTwo;
		this.minApplyAmount = minApplyAmount;
		this.maxApplyAmount = maxApplyAmount;
		this.maxMonthlyPayment = maxMonthlyPayment;
		this.status = status;
		this.applySubmitTime = applySubmitTime;
		this.period = period;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplySubmitTime() {
		return applySubmitTime;
	}
	public void setApplySubmitTime(Date applySubmitTime) {
		this.applySubmitTime = applySubmitTime;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "UserApplyInfo [applyId=" + applyId + ", userId=" + userId + ", loanPurposeOne=" + loanPurposeOne
				+ ", loanPurposeTwo=" + loanPurposeTwo + ", minApplyAmount=" + minApplyAmount + ", maxApplyAmount="
				+ maxApplyAmount + ", maxMonthlyPayment=" + maxMonthlyPayment + ", status=" + status
				+ ", applySubmitTime=" + applySubmitTime + ", period=" + period + "]";
	}
}