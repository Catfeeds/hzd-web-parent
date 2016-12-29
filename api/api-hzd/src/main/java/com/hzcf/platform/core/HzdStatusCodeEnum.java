package com.hzcf.platform.core;

import com.hzcf.platform.common.util.json.parser.JsonUtil;

/**
 * 
 * @description:枚举状态码
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public enum HzdStatusCodeEnum {

	MEF_CODE_0000(0, "成功"), //
	
	//==============用户信息10开头
	MEF_CODE_1010(1010, "用户已经注册"),
	MEF_CODE_1011(1011, "用户未注册"),
	MEF_CODE_1012(1012, "用户未登录"),
	MEF_CODE_1022(1022, "账号或密码错误"),
	MEF_CODE_1111(1111, "token已失效"),
	MEF_CODE_1099(1099, "用户被禁用,禁止登录"),


	//======进件接口
	MEF_CODE_2100(2100, "查询失败"),
	MEF_CODE_2111(2111, "查询出现异常"),
	MEF_CODE_2200(2200, "提交失败"),
	MEF_CODE_2211(2211, "提交出现异常"),
	MEF_CODE_2400(2400, "无效的借款编号"),
	//=====短信接口 
	MEF_CODE_3010(3010, "手机号码为空"),
	MEF_CODE_3000(3000, "验证码有误"),
	
	MEF_CODE_9000(9000, "传入参数有误"),
	MEF_CODE_9999(9999, "系统异常"),
	MEF_CODE_0001(0001, "失败");
	
	private int code;
	private String msg;
	private String showMsg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}


	HzdStatusCodeEnum(int code, String msg, String... showMsg) {
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