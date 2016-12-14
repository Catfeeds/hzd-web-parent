package com.hzcf.platform.mgr.sys.shiro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * 
 * @author xiaojun
 * 多项目登录，比如如果是从其他应用中重定向过来的，首先检查Session 中是否有
 * “authc.fallbackUrl”属性，如果有就认为它是默认的重定向地址；否则使用Server 自己的
 * successUrl作为登录成功后重定向到的地址
 *
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		String fallbackUrl = (String) getSubject(request, response).getSession().getAttribute("authc.fallbackUrl");
		if (StringUtils.isEmpty(fallbackUrl)) {
			fallbackUrl = getSuccessUrl();
		}
		logger.info(" ====ServerFormAuthenticationFilter 重定向地址:" + fallbackUrl);
		WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (isLoginRequest(request, response)) {  
            if (isLoginSubmission(request, response)) {  
                return executeLogin(request, response);  
            } else {  
                return true;  
            }  
        } else {  
        	  HttpServletResponse res = WebUtils.toHttp(response);  
      		  logger.info(" ====ServerFormAuthenticationFilter 系统session超时返回错误码:" + HttpServletResponse.SC_UNAUTHORIZED);

              res.sendError(HttpServletResponse.SC_UNAUTHORIZED); 
              //saveRequestAndRedirectToLogin(request, response);  
              return false;  
        }  
		 
	}

}
