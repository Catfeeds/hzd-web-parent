package com.hzcf.platform.core;

import com.hzcf.platform.common.util.json.parser.JsonUtil;

/**
 * MyfStatusCodeEnum callback code
 * 
 * @author kayson Yang
 * @date 2016/6/19 0017
 */
public enum MyfStatusCodeEnum {

	MEF_CODE_0000(0, "成功"), //
	
	//==============用户信息10开头
	MEF_CODE_1010(1010, "用户已经注册"),
	MEF_CODE_1011(1011, "用户未注册"),
	MEF_CODE_1022(1022, "账号或密码错误"),
	//======进件接口
	MEF_CODE_2100(2100, "查询失败"),
	MEF_CODE_2111(2111, "查询出现异常"),
	MEF_CODE_2200(2200, "提交失败"),
	MEF_CODE_2211(2211, "提交出现异常"),
	//=====短信接口
	MEF_CODE_3010(3010, "111"),
	
	
	
	MEF_CODE_9999(9999, "系统异常");
	
	
	private int code;
	private String msg;
	private String showMsg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}


	MyfStatusCodeEnum(int code, String msg, String... showMsg) {
		this.code = code;
		this.msg = msg;
		if (showMsg.length > 0)
			this.showMsg = showMsg[0];
	}


	/**
	 * 重载toString方法，打印自定义字段
	 */
	@Override
	public String toString() {
		return super.toString() + ">>" + JsonUtil.json2String(this);
	}
}
