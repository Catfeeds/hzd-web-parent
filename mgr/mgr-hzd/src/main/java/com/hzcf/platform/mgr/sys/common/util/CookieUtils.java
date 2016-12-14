/**
 *
 */
package com.hzcf.platform.mgr.sys.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * 
 * @author
 * @version
 */
public class CookieUtils {

	/**
	 * 设置 Cookie（生成时间为1天）
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, 60 * 60 * 24);
	}

	/**
	 * 设置 Cookie
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param maxAge
	 *            生存时间（单位秒）
	 * @param uri
	 *            路径
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}

	/**
	 * 获得指定Cookie的值
	 * 
	 * @param name
	 *            名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, null, name, false);
	}

	/**
	 * 获得指定Cookie的值，并删除。
	 * 
	 * @param name
	 *            名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		return getCookie(request, response, name, true);
	}

	/**
	 * 获得指定Cookie的值
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @param name
	 *            名字
	 * @param isRemove
	 *            是否移除
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name,
			boolean isRemove) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (isRemove) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getName(HttpServletRequest req, String name) {
		Cookie cookie = get(req, name);
		String cookieVal = (null == cookie) ? null : cookie.getValue();
		return cookieVal;
	}

	public static Cookie get(HttpServletRequest req, String name) {
		Map<String, Cookie> cookieMap = _readCookieMap(req);
		if (cookieMap.containsKey(name)) {
			return (Cookie) cookieMap.get(name);
		} else {
			return null;
		}
	}

	/**
	 * 清除cookie
	 * 
	 * @param req
	 * @param res
	 * @param name
	 */
	public static void remove(HttpServletRequest req, HttpServletResponse res, String name) {
		String cookieName = getName(req, name);
		if (null != cookieName) {
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
	}

	/**
	 * 清除所有cookie
	 * 
	 * @param req
	 * @param res
	 */
	public static void clear(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		for (int i = 0, len = cookies.length; i < len; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
	}

	private static Map<String, Cookie> _readCookieMap(HttpServletRequest req) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
