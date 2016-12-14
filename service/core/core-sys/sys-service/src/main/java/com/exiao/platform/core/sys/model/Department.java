package com.exiao.platform.core.sys.model;

public class Department implements java.io.Serializable{

    private Long id;
    /**
     * 机构名称
     */
	private String deptName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
 
	
}
