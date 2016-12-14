/**
 *
 */
package com.hzcf.platform.mgr.sys.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author zj
 * @version 2013-11-19
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	/**
	 * 按照JAVA_请求客户端ip_当前时间的long型毫秒数_3位随机数规则生成版本号
	 * @return
	 */
	public static String getVersionNumber(HttpServletRequest request){
		String newVersion = "JAVA_" + getRemoteAddr(request).replace("\\.", "-") + "_" + System.currentTimeMillis() + "_" + RandomStringUtils.random(3, false, true);
		return newVersion;
	}
	
	/**
	 * 按照JAVA_system_cancel_当前时间的long型毫秒数_3位随机数规则生成版本号
	 * @return
	 */
	public static String getVersionNumber(){
		String newVersion = "JAVA_SYSTEM_CANCEL_" + System.currentTimeMillis() + "_" + RandomStringUtils.random(3, false, true);
		return newVersion;
	}
	
	/**
	 * 判断该字符串，是不是数字
	 * @param str
	 * @return
	 */
	public static boolean validateInt(String str){
		if(StringUtils.isNotEmpty(str)){
			for(int n=0;n<str.length();n++){
				int chr=str.charAt(n);
				if(chr<48 || chr>57)
			         return false;
			}
			return true;
		}else{
			return false;
		}
	}
	
}
