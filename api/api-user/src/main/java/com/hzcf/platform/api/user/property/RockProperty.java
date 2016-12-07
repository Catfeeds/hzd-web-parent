package com.hzcf.platform.api.user.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("rockProperty")
public class RockProperty {

    @Value("#{setting[KEY]}")
    private String key;
    @Value("#{setting[WXSUBMIT]}")
    private String wxSubmit;
    @Value("#{setting[WXQUERY]}")
    private String wxQuery;
    @Value("#{setting[SMS]}")
    private String sms;

    @Value("#{setting[SMSNUM]}")
    private String smsNum;
    @Value("#{setting[SMSNUMSWITCH]}")
    private String smsNumSwitch;
    
    @Value("#{setting[CONNECTTIMEOUT]}")
    private String connectTimeOut;
    
    @Value("#{setting[SMSNUMFAILUREDATE]}")
    private String smsNumFailureDate;
    @Value("#{setting[CONTENTTEXT]}")
    private String contentText;
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWxSubmit() {
		return wxSubmit;
	}
	public void setWxSubmit(String wxSubmit) {
		this.wxSubmit = wxSubmit;
	}
	public String getWxQuery() {
		return wxQuery;
	}
	public void setWxQuery(String wxQuery) {
		this.wxQuery = wxQuery;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getSmsNum() {
		return smsNum;
	}
	public void setSmsNum(String smsNum) {
		this.smsNum = smsNum;
	}
	public String getSmsNumSwitch() {
		return smsNumSwitch;
	}
	public void setSmsNumSwitch(String smsNumSwitch) {
		this.smsNumSwitch = smsNumSwitch;
	}
	public String getConnectTimeOut() {
		return connectTimeOut;
	}
	public void setConnectTimeOut(String connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}
	public String getSmsNumFailureDate() {
		return smsNumFailureDate;
	}
	public void setSmsNumFailureDate(String smsNumFailureDate) {
		this.smsNumFailureDate = smsNumFailureDate;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
    


    
    
}
