package com.hzcf.platform.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConstantsDictionary {
	
	static {

		Properties props = new Properties();
		InputStreamReader in = null;
		try {
			System.out.println("------------------------------------------------------------------");
			in = new InputStreamReader(ConstantsDictionary.class
					.getClassLoader()
					.getResourceAsStream("/rock.properties"), "UTF-8");
			props.load(in);

			KEY = props.getProperty("KEY");
			WXSUBMIT = props.getProperty("WXSUBMIT");
			WXQUERY = props.getProperty("WXQUERY");
			SMS = props.getProperty("SMS");
			SMSNUM = props.getProperty("SMSNUM");
			SMSNUMSWITCH = props.getProperty("SMSNUMSWITCH");
			CONNECTTIMEOUT = Integer.valueOf(props.getProperty("CONNECTTIMEOUT"));
			SNSNUMFAILUREDATE = Integer.valueOf(props.getProperty("SNSNUMFAILUREDATE"));//短信失效时间
			CONTENTTEXT = props.getProperty("CONTENTTEXT");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}	  
	public static String KEY ;
	public static String WXSUBMIT;
	public static String WXQUERY;
	public static String SMS;
	public static String SMSNUM; //超级验证码
	public static String APP="APP";//系统标示
	public static String SMSNUMSWITCH;//超级验证码开关
	public static String SMSCONTENT ="汇中融资咨询手机号绑定验证码：【】。请勿将验证码告知他人并确认该申请是您本人操作！";
	public static int CONNECTTIMEOUT;
	public static int SNSNUMFAILUREDATE;
	public final static String SMSSSTATUS1="1";
	public final static String SMSSSTATUS0="0";//失效
	public static String CONTENTTEXT;
}
