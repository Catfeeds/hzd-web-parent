package com.exiao.platform.core.sys.model;

import com.exiao.platform.common.model.Entity;

public class ScheduleJobVO extends Entity {

	/** 任务id */
	private String jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务分组 */
	private String jobGroup;
	/** 任务状态 0禁用 1启用 2删除 */
	private Integer jobStatus;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String remark;

	/**
	 * 创建者
	 */
	private Integer createBy;
	/**
	 * 更新者
	 */
	private Integer updateBy;

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
