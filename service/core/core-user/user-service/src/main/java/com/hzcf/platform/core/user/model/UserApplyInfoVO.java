package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

public class UserApplyInfoVO extends BaseVO {
    private String applyId;//申请编号

    private String userId;//用户ID

    private String loanPurposeOne; //借款用途大类

    private String loanPurposeTwo;//借款用途小类

    private BigDecimal minApplyAmount; //申请最低额度

    private BigDecimal maxApplyAmount;//申请最高额度

    private BigDecimal maxMonthlyPayment; //可接受最高月还款额

    private String status;//1=有效   0=无效

    private Date applySubmitTime;//进件时间

    //ext，对应后台页面中的“用户列表”中的查询条件
	private String mobile;//用户手机号
	private String name;//用户姓名
	private String idCard;//身份证号
    private String startDate;//注册日期-开始时间
    private String endDate;//注册日期-结束时间

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    
    
}