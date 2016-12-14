package com.hzcf.platform.core.sys.data;

import com.hzcf.platform.common.model.DataEntity;

public class User extends DataEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录名
	 */
	private String loginName;
    /**
     * 密码
     */
	private String password;

	/**
	 *  确认密码
	 */
	private String confirmPassword;

	/**
	 * 姓名
	 */
	private String userName;
    /**
     * 归属部门
     */
	private Integer departmentId;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 电话
     */
	private String phone;
    /**
     * 手机
     */
	private String mobile;
    /**
     * 用户类型
     */
	private Integer userType;
    /**
     * 角色编号
     */
	private Integer  roleID;
	
	/**
	 * 最后登陆IP
	 */
	private String loginIp;
    /**
     * 最后登陆时间
     */
	private java.util.Date loginDate;
    /**
     * 创建者
     */
	private Integer createBy;
	/**
	 * 更新者
	 * 
	 */
	private Integer updateBy;
	/**
	 * 盐
	 */
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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


	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
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


}
