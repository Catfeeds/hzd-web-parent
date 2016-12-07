package com.hzcf.platform.webService;


import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.hzcf.platform.api.model.OpenAccReq;
import com.hzcf.platform.api.model.OpenAccReq.SendBillList;
import com.hzcf.platform.common.util.http.HttpRequest;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.utils.AESUtil;
import com.hzcf.platform.common.util.utils.Md5Util;
import com.hzcf.platform.common.util.utils.Serialnumber;
import com.hzcf.platform.config.ConstantsDictionary;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * @description: 获取短信Service
 * @author leijiaming
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年11月18日            leijiaming      1.0       1.0 Version 
 * </pre>
 */
public class SmsObtainService {
	private static Logger log = Logger.getLogger(SmsObtainService.class);
	public static String smsObtain(String six, String phoneNum){
		String dataInfo="";
		//生成流水号
		String getnum = Serialnumber.Getnum();
		//生成短信验证码
		List<SendBillList> sendBillList = new ArrayList<SendBillList>();
		OpenAccReq payOpenAccReq =new OpenAccReq();
		OpenAccReq.SendBillList sendBill = new SendBillList();
		sendBill.setBizId(getnum);
		
		String  contentText =ConstantsDictionary.CONTENTTEXT;
		StringBuffer stringBuffer = new StringBuffer(contentText);
		int indexOf = stringBuffer.indexOf("【");
		StringBuffer insert = stringBuffer.insert(indexOf+1,six);
		log.info("发送的短信内容---:"+insert);
		
		//sendBill.setContent("{'content':'验证码：【"+six+"】，一分钟内输入有效，请勿将验证码告知他人并确认该申请是您本人！'}");
		sendBill.setContent("{'content':'"+insert.toString()+"'}");

		sendBill.setAddr(phoneNum);
		
		sendBillList.add(sendBill);
		payOpenAccReq.setBizId(getnum);
		payOpenAccReq.setSystemSourceId(ConstantsDictionary.APP);
		payOpenAccReq.setSendType("1");
		payOpenAccReq.setSendBillList(sendBillList);
		
		try {
			
			String single = Md5Util.getMD5String(StringUtils.join(new String[]{payOpenAccReq.getSystemSourceId(),payOpenAccReq.getBizId()}, ","), ConstantsDictionary.KEY);  				
			payOpenAccReq.setSignature(single);
			String payOpenAccReqJson =JsonUtil.json2String(payOpenAccReq);
			log.info("请求的JSON数据："+payOpenAccReqJson);
			String billParms = AESUtil.enCrypt(payOpenAccReqJson, ConstantsDictionary.KEY);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("billParms", billParms));
			String Data = URLEncodedUtils.format(params, "UTF-8");

			dataInfo= HttpRequest.sendGet(ConstantsDictionary.SMS, Data);
			//sendRsp = AESUtil.deCrypt(dataInfo, ConstantsDictionary.KEY);
			log.info("返回JSON数据："+dataInfo);
		} catch (Exception e) {
			log.error("请求短信服务出现异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	
    //主方法测试  
   public static void main(String[] args) {  
		 long startTime=System.currentTimeMillis();
		 System.out.println("请求时间：------------------"+startTime);
	   String smsObtain = smsObtain("123456","13911890913"); 
		 System.out.println("响应耗时-------------------------"+(float)(System.currentTimeMillis()-startTime)/1000);

	   JSONObject  json = JSONObject.fromObject(smsObtain.toString());
	   JSONArray jsonArray = json.getJSONArray("sendBillList");
	   JSONObject jsonObject = jsonArray.getJSONObject(0);
	   String string = jsonObject.getString("retCode");
	   System.out.println(string.toString());
	   System.out.println(json);
	   
	   /*String id = StringUUID.getId();
	   int length = id.length();
	   System.out.println(id);
	   System.out.println(length);*/
	   
   }
}
