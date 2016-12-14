package com.exiao.platform.core.sys.data;

import com.exiao.platform.common.model.DataEntity;

public class Role extends DataEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 英文名称
	 */
	private String enName;
	/**
	 * 归属部门
	 */
	private Integer departmentId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 角色类型
	 */
	private Integer roleType;
	/**
	 * 数据域
	 */
	private Integer dataScope;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 角色人数
	 */
	private Integer personNum;
    /**
     * 创建者
     */
	private Integer createBy;
    /**
     * 更新者
     */
	private Integer updateBy;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
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

}
