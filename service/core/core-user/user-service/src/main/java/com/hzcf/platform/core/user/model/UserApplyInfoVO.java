package com.hzcf.platform.core.user.model;

import com.hzcf.platform.common.model.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

public class UserApplyInfoVO extends BaseVO {
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

    /**ext，extends：扩展字段，对应后台页面中的“用户列表”中的查询条件*/
	private String mobile;//用户手机号
	private String name;//用户姓名
	private String idCard;//身份证号
    private String startDate;//注册日期-开始时间
    private String endDate;//注册日期-结束时间


	private String borrowerApplyId;	//线下系统申请ID
	private Date additionalSubmitTime;//补充提交时间
	private String additionalStatus;//补充状态
	private String additionalContent;//待补充内容

	private String addStartDate;//补充提交时间-开始时间
	private String addEndDate;//补充提交时间-结束时间

	private String checkStatus;//审核状态  0:通过(已认证)  1:不通过(未认证)  2:待审核(审核中)

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getAddStartDate() {
		return addStartDate;
	}

	public void setAddStartDate(String addStartDate) {
		this.addStartDate = addStartDate;
	}

	public String getAddEndDate() {
		return addEndDate;
	}

	public void setAddEndDate(String addEndDate) {
		this.addEndDate = addEndDate;
	}

	public String getBorrowerApplyId() {
		return borrowerApplyId;
	}

	public void setBorrowerApplyId(String borrowerApplyId) {
		this.borrowerApplyId = borrowerApplyId;
	}

	public Date getAdditionalSubmitTime() {
		return additionalSubmitTime;
	}

	public void setAdditionalSubmitTime(Date additionalSubmitTime) {
		this.additionalSubmitTime = additionalSubmitTime;
	}

	public String getAdditionalStatus() {
		return additionalStatus;
	}

	public void setAdditionalStatus(String additionalStatus) {
		this.additionalStatus = additionalStatus;
	}

	public String getAdditionalContent() {
		return additionalContent;
	}

	public void setAdditionalContent(String additionalContent) {
		this.additionalContent = additionalContent;
	}

	public UserApplyInfoVO() {
		super();
	}
	public UserApplyInfoVO(String applyId, String userId, String loanPurposeOne, String loanPurposeTwo,
			BigDecimal minApplyAmount, BigDecimal maxApplyAmount, BigDecimal maxMonthlyPayment, String status,
			Date applySubmitTime, String period, String mobile, String name, String idCard, String startDate,
			String endDate) {
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
		this.mobile = mobile;
		this.name = name;
		this.idCard = idCard;
		this.startDate = startDate;
		this.endDate = endDate;
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
	@Override
	public String toString() {
		return "UserApplyInfoVO [applyId=" + applyId + ", userId=" + userId + ", loanPurposeOne=" + loanPurposeOne
				+ ", loanPurposeTwo=" + loanPurposeTwo + ", minApplyAmount=" + minApplyAmount + ", maxApplyAmount="
				+ maxApplyAmount + ", maxMonthlyPayment=" + maxMonthlyPayment + ", status=" + status
				+ ", applySubmitTime=" + applySubmitTime + ", period=" + period + ", mobile=" + mobile + ", name="
				+ name + ", idCard=" + idCard + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}