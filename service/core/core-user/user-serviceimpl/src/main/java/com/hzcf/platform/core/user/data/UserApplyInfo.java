package com.hzcf.platform.core.user.data;

import java.math.BigDecimal;
import java.util.Date;

public class UserApplyInfo {
    private String applyId;

    private String userId;

    private String loanPurposeOne;

    private String loanPurposeTwo;

    private BigDecimal minApplyAmount;

    private BigDecimal maxApplyAmount;

    private BigDecimal maxMonthlyPayment;

    private String status;

    private Date applySubmitTime;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoanPurposeOne() {
        return loanPurposeOne;
    }

    public void setLoanPurposeOne(String loanPurposeOne) {
        this.loanPurposeOne = loanPurposeOne == null ? null : loanPurposeOne.trim();
    }

    public String getLoanPurposeTwo() {
        return loanPurposeTwo;
    }

    public void setLoanPurposeTwo(String loanPurposeTwo) {
        this.loanPurposeTwo = loanPurposeTwo == null ? null : loanPurposeTwo.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public Date getApplySubmitTime() {
        return applySubmitTime;
    }

    public void setApplySubmitTime(Date applySubmitTime) {
        this.applySubmitTime = applySubmitTime;
    }
}