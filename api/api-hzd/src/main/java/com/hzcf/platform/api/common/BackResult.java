package com.hzcf.platform.api.common;

import com.hzcf.platform.common.util.json.parser.JsonUtil;
/**
 * 
 * @description:响应码
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
public class BackResult {
	  private static final long serialVersionUID = 2177938600160116668L;

	    private int state;// 状态码

	    private String message;// 信息

	    private String showMessage;// 显示信息

	    private Object data;// 数据

	    public BackResult(int state, String message, String showMessage, Object data) {
	        setState(state);
	        setMessage(message);
	        setShowMessage(showMessage);
	        setData(data);
	    }

	    public BackResult(int state, String message, Object data) {
	        super();
	        this.state = state;
	        this.message = message;
	        this.data = data;
	    }

	    //lvpeng insert
	    public BackResult(int state, String message) {
	        super();
	        this.state = state;
	        this.message = message;
	    }

	    public BackResult(int state, String message, String showMessage) {
	        setState(state);
	        setMessage(message);
	        setShowMessage(showMessage);
	    }

	    public BackResult() {
	        // nothing...
	    }

	    public int getState() {
	        return state;
	    }

	    public void setState(int state) {
	        this.state = state;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getShowMessage() {
	        return showMessage;
	    }

	    public void setShowMessage(String showMessage) {
	        this.showMessage = showMessage;
	    }

	    public <T> T getData() {
	        @SuppressWarnings("unchecked")
	        T _data = (T) data;
	        return _data;
	    }

	    public <T> void setData(T data) {
	        this.data = data;
	    }

	    public String toString() {
	        return JsonUtil.json2String(this);
	    }
}
