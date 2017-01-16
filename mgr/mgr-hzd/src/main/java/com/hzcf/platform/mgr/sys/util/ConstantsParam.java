package com.hzcf.platform.mgr.sys.util;

public class ConstantsParam {

	public final static String MSG_STATUS_YES = "0"; // 有效
	public final static String MSG_STATUS_WX = "1";// 无效
	public final static String MSG_STATUS_TG = "2";//审核通过
	public final static String MSG_STATUS_BTG = "3";//审核不通过
	
	public final static String MSG_TYPE = "0"; // 
	public final static String MSG_IS_READ_YES = "0"; // 可读
	public final static String MSG_IS_READ_NO = "1"; // 未读
	
	public final static String IMG_TYPE_RZ = "4"; // 认证图片
	
	public final static String USER_STATUS_Y = "0"; // 用户状态 可用
	public final static String USER_STATUS_N = "1"; // 用户状态 不可用
	public final static String USER_CKECKSTATUS_Y = "0"; // 审核状态通过
	public final static String USER_CKECKSTATUS_N = "1"; // 审核状态不通过
	public final static String USER_CKECKSTATUS = "2"; // 审核状态待审核
	public final static String USE_APPLY_STASUE_N = "1";//进件状态 为未进件
	
	public final static String USER_APPLYINFO_STATU_WJ="0";// user_apply_info 表中的进件状态 未进件
	public final static String USER_APPLYINFO_STATU_JJ="1";//已进件
	public final static String USER_APPLYINFO_STATU_DSH="2";//待审核
	
	
	public final static int APPLY_STATUS_SUCCESS = 1;//调用线下系统成功
	public final static int APPLY_STATUS_FAIL = 2;//调用线下系统失败
	
}
