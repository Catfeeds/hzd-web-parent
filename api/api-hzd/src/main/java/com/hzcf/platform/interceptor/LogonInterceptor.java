package com.hzcf.platform.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.config.BaseConfig;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.CusAccessObjectUtil;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.RequestAgent;
import com.hzcf.platform.core.user.model.UserVO;

public class LogonInterceptor extends HandlerInterceptorAdapter {
	private static Log logger = Log.getLogger(LogonInterceptor.class);
	
	
	/*
	 * @Autowired private TokenManager manager;
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 如果不是映射到方法直接通过
		if (handler instanceof HandlerMethod) {

			String agent = request.getHeader("user-agent");
			
			if("".equals(agent)){
				return false;
			}
			
			String[] split = agent.split("#");
			RequestAgent ra = new RequestAgent();
			// 安卓 /IOS 规范
			if (split.length == 2) {
				ra.setTerminal(split[0]);
				ra.setVersion(split[1]);
			} else {
				// 微信规范
				ra.setTerminal(agent);
				ra.setVersion("-1");
			}
			ra.setIp(CusAccessObjectUtil.getIpAddress(request));
			request.setAttribute(BaseConfig.REQUEST_AGENT, ra);
		}
		return true;

	
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav)
			throws Exception {
	}




}
