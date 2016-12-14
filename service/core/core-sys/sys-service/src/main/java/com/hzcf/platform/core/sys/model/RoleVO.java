package com.hzcf.platform.core.sys.model;

import com.hzcf.platform.common.model.BaseVO;


public class RoleVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String roleName;
	/**
	 * 
	 */
	private String enName;
	/**
	 * 
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	private Integer userType;
	/**
	 * 
	 */
	private Integer roleType;
	/**
	 * 
	 */
	private Integer dataScope;
	/**
	 * 
	 */
	private Integer createBy;
	
	private String createName;//操作员名称
	
	

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * 
	 */
	private Integer updateBy;
	
	/**
	 * 角色人数
	 */
	private Integer personNum;
	/**
	 * 
	 */
	private String remark;

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getDataScope() {
		return dataScope;
	}

	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}

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

	@Override
	public String toString() {
		return "Role {roleName=" + roleName + ", personNum=" + personNum
				+ ", remark=" + remark+ "}";
	}
}
