package com.hzcf.platform.api.config;

import com.hzcf.platform.common.util.json.parser.JsonUtil;

/**
 * Created by Jacobow on 2016/6/25. 请求信息
 */
public class RequestAgent implements java.io.Serializable {

	private static final long serialVersionUID = -8160823805450039949L;
	/**
	 * 终端类型
	 */
	private String terminal;

	/**
	 * 终端版本
	 */
	private String version;
	
	
	private String ip;
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public RequestAgent(String terminal, String version,String ip) {
		setTerminal(terminal);
		setVersion(version);
		setIp(ip);
	}

	public RequestAgent() {
		//
	}

	public RequestAgent(String terminal) {
		this.terminal = terminal;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return JsonUtil.json2String(this);
	}
}
