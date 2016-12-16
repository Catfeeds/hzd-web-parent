package com.hzcf.platform.core.sys.data;

import com.hzcf.platform.common.model.AbstractEntity;

public class SysLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志类型，1--系统管理操作，2--业务日志
	 */
	private Integer type;
	/**
	 * 请求URL
	 * 
	 */ 
	private String request_uri;
	/**
	 * 请求的方法
	 */
	private String method;
	/**
	 * 请求的参数
	 */
	private String params;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 操作类型，1--新增，2--删除，3--修改，4--查看
	 */
	private Integer operate_type;
	/**
	 * 用户岗位/角色ID
	 */
	private Integer role_id;
	/**
	 * 用户所在部门ID
	 */
	private Integer department_id;
	/**
	 * 日志异常信息
	 */
	private String exception;
	/**
	 * 用户ID
	 */
	private Integer user_id;
	/**
	 * 用户代理
	 */
	private String user_agent;
	/**
	 * 客户端访问地址IP
	 */
	private String remote_addr;
	/**
	 * 创建者
	 */
	private Integer creator;
	
	/**
	 * 操作员
	 */
	private String operator;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRequest_uri() {
		return request_uri;
	}

	public void setRequest_uri(String request_uri) {
		this.request_uri = request_uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(Integer operate_type) {
		this.operate_type = operate_type;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public String getRemote_addr() {
		return remote_addr;
	}

	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

}
