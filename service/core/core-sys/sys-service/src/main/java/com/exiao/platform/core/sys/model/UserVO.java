package com.exiao.platform.core.sys.model;

import com.exiao.platform.common.model.Entity;

public class UserVO extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;

	private String password;
    
	private String confirmPassword;

	private String userName;

	private Integer departmentId;

	private String deptName;

	private Integer roleID;

	private String roleName;

	private String loginIp;

	private java.util.Date loginDate;

	private String email;

	private String phone;

	private String mobile;

	private Integer userType;

	private Integer createBy;

	private Integer updateBy;

	private String salt;
	
	/**
	 * 是否禁用，锁定
	 */
	private Integer locked;
	
	

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	// 角色

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public java.util.Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(java.util.Date loginDate) {
		this.loginDate = loginDate;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public boolean isAdmin() {
		return isAdmin(this.getId());
	}

	public static boolean isAdmin(Long id) {
		return id != null && id.equals(1L);
	}
}
