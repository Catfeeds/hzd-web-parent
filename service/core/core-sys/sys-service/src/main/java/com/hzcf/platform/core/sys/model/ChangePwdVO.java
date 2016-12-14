package com.hzcf.platform.core.sys.model;

import com.hzcf.platform.common.model.Entity;
/**
 * 用户修改密码
 * @author xiaojun
 *
 */
public class ChangePwdVO extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String oriPsd;
	
	private String newPsd;
	
	private String confirmPsw;

	public String getOriPsd() {
		return oriPsd;
	}

	public void setOriPsd(String oriPsd) {
		this.oriPsd = oriPsd;
	}

	public String getNewPsd() {
		return newPsd;
	}

	public void setNewPsd(String newPsd) {
		this.newPsd = newPsd;
	}

	public String getConfirmPsw() {
		return confirmPsw;
	}

	public void setConfirmPsw(String confirmPsw) {
		this.confirmPsw = confirmPsw;
	}
	

}
