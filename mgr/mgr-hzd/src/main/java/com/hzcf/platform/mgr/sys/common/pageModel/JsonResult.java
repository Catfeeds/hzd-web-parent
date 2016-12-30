package com.hzcf.platform.mgr.sys.common.pageModel;


/**
 * 
 * @author miaoxiong
 * 
 *
 *         Description: .
 * 
 *         JSON模型
 * 
 *         用户后台向前台返回的JSON对象
 * 
 *         CreateTime: 2014年5月5日 上午9:47:11
 *
 *         Change History:
 *
 *         Date CR Number Name Description of change
 *
 *
 */
public class JsonResult implements java.io.Serializable {

	private static final long serialVersionUID = 2349487124465234825L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;

	public JsonResult(boolean success, String msg, Object obj) {
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

	public JsonResult(boolean success) {
		this.success = success;
	}

	public JsonResult() {

	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
