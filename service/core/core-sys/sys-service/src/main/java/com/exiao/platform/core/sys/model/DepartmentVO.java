package com.exiao.platform.core.sys.model;

import com.exiao.platform.common.model.Entity;

public class DepartmentVO extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 父级编号
     */
	private Integer parentId;
    /**
     * 所有父级编号
     */
	private String parentIds;
    /**
     * 区域编码
     */
	private String code;
    /**
     * 机构名称
     */
	private String deptName;
    /**
     * 联系地址
     */
	private String address;
    /**
     * 邮政编码
     */
	private String zip_code;
    /**
     * 负责人
     */
	private String master;
    /**
     * 电话
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 创建者
     */
	private Integer createBy;
    /**
     * 更新者
     */
	private Integer updateBy;
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
