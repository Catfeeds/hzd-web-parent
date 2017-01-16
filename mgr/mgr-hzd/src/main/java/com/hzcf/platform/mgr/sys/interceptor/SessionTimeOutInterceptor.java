package com.hzcf.platform.mgr.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.sys.model.SysUsersVO;


public class SessionTimeOutInterceptor extends HandlerInterceptorAdapter {//此处一般继承HandlerInterceptorAdapter适配器即可  
	
	//日志
	//private static Log logger = Log.getLogger(SessionTimeOutInterceptor.class);

//	@Override  
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
//        if (request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) 
//        	response.setHeader("sessionstatus", "timeout");//在响应头设置session状态    
//        	//response.getWriter().print("timeout"); //打印一个返回值
//        return true;  
//    }
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {

		HttpSession session = request.getSession();
		//从session 里面获取用户名的信息
		SysUsersVO user = (SysUsersVO)session.getAttribute("user");
		//判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
		if(user == null || "".equals(user)){
			//response.sendRedirect("/mgr-hzd/sys/tologin");//未登录，跳转到登陆页面
			request.getRequestDispatcher("/WEB-INF/pages/login/login.jsp").forward(request, response);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception exc) throws Exception {
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
	}  
}  