package com.hzcf.platform.api.baseEnum;

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

	HZD_CODE_0000(0, "成功"), //

	//==============用户信息10开头
	HZD_CODE_1010(1010, "用户已经注册"),
	HZD_CODE_1011(1011, "用户未注册"),
	HZD_CODE_1012(1012, "用户未登录"),
	HZD_CODE_1022(1022, "账号或密码错误"),
	HZD_CODE_1045(1045, "未查询到用户信息"),

	HZD_CODE_1030(1030, "用户未实名认证"),
	HZD_CODE_1088(1088, "已经提交实名认证信息"),
	HZD_CODE_1031(1031, "用户的真实姓名不符合要求"),
	HZD_CODE_1032(1032, "用户的身份证号码不符合要求"),
	HZD_CODE_1033(1033, "用户的真实姓名已存在"),
	HZD_CODE_1034(1034, "用户的身份证号已存在"),
	HZD_CODE_1035(1035, "保存用户实名认证信息失败"),

	HZD_CODE_1111(1111, "token已失效"),
	HZD_CODE_1099(1099, "用户被禁用,禁止登录"),
	HZD_CODE_1818(1818, "传入参数有非法字符"),


	//======进件接口
	HZD_CODE_2100(2100, "查询失败"),
	HZD_CODE_2111(2111, "查询出现异常"),
	HZD_CODE_2200(2200, "提交失败"),
	HZD_CODE_2211(2211, "提交出现异常"),
	HZD_CODE_2400(2400, "无效的借款编号"),
	HZD_CODE_2333(2333, "提交失败,已经一条借款在申请中"),
	//=====短信接口
	HZD_CODE_3010(3010, "手机号码为空"),
	HZD_CODE_3000(3000, "验证码有误"),
	HZD_CODE_3101(3101, "3分钟内不能重复获取验证码"),
	//====图片信息
	HZD_CODE_4100(4100, "图片上传失败请重新上传"),

	HZD_CODE_5100(5100, "未查询到信息"),
	//线上和调度的对接接口信息
	HZD_CODE_6100(6100,"进件失败"),
	HZD_CODE_6101(6101,"查询借款状态失败"),
	HZD_CODE_6102(6102,"用户手机号不存在"),
	HZD_CODE_6103(6103,"未查询到用户信息"),

	//APP推送
	HZD_CODE_7100(7100,"APP推送失败"),

	HZD_CODE_9000(9000, "传入参数有误"),
	HZD_CODE_9999(9999, "系统异常"),
	HZD_CODE_0001(0001, "失败");
	
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