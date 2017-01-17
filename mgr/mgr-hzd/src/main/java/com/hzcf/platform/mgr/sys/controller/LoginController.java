package com.hzcf.platform.mgr.sys.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.mgr.sys.service.ISysUsersService;
/**
 * 
 * @author zhangmx
 * 
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	ISysUsersService sysUsersService;
	
	@ResponseBody
	@RequestMapping(value = "/sys/login", produces = { "application/json" })
	public LoginJsonResult login(String irand,String username,String password,HttpServletRequest request) {
		/*User userDB = getDBUser(username, password);
		if (userDB != null) {
			if(userDB.getStatus() == 0){//用户状态为“已注销”
				msg = "{msg:\'error\',success:true,resultContents:\'" + "用户已注销"
						+ "\'}";
			}else{
				request.getSession().setAttribute(
						ConstantUtil.LOGIN_NAME.getName(), userDB);
			 
				result = "登录成功!";
				msg = "{msg:\'ok\',success:true,interactiveUrl:\'" + "main.jsp"
						+ "\',sysType:\'" + "se" + "\',resultContents:\'" + result
						+ "\'}";
			}
		} else {
			result = "用户名或密码错误!";
			msg = "{msg:\'error\',success:true,resultContents:\'" + result
					+ "\'}";
		}*/
		String result = "";
		LoginJsonResult loginJsonResult = new LoginJsonResult();
		String rand = (String) request.getSession().getAttribute("rand");
		boolean login = sysUsersService.CheckLogin(username,password);
		if (!irand.equalsIgnoreCase(rand)) {
			result = "验证码错误!";
			loginJsonResult.setMsg("error");
			loginJsonResult.setSuccess(true);
			loginJsonResult.setResultContents(result);
		} else  if (login == true) {
			result = "登录成功!";
			loginJsonResult.setMsg("ok");
			loginJsonResult.setSuccess(true);
			loginJsonResult.setInteractiveUrl("home/main.jsp");
			loginJsonResult.setSysType("redpack");
			loginJsonResult.setResultContents(result);
		} else {
			result = "用户名或密码错误!";
			loginJsonResult.setMsg("error");
			loginJsonResult.setSuccess(true);
			loginJsonResult.setResultContents(result);
		}
		
		//保存登陆信息到session中
		SysUsersVO user = new SysUsersVO();
		user.setUserName(username);
		user.setPassword(password);
		request.getSession().setAttribute("user", user);
		
		//request.getSession().setMaxInactiveInterval(60);
		
		return loginJsonResult;
	}

	@RequestMapping(value = "/sys/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<?> enums = session.getAttributeNames();
		while (enums.hasMoreElements()) {
			session.removeAttribute(enums.nextElement().toString());
		}
		return "login/login";
	}
	
	@RequestMapping(value = "/sys/tologin")
	public String tologin(HttpServletRequest request) {
		
		return "login/login";
	}
	
	@RequestMapping(value = "/sys/main")
	public String main(HttpServletRequest request) {
		
		//判断有无session信息，有直接到首页，无返回登陆
/*		if(request.getSession().getAttribute("user")!=null)
		{
			return "home/main";
		}
		return "login/login";*/
		
		return "home/main";
	}
	

	/*private User getDBUser(String userName, String password) {
		User loginUser = new User();
		loginUser.setLoginName(userName);
		loginUser.setLoginPass(password);
		return userService.findByLogin(loginUser);
	}*/

	private class LoginJsonResult implements java.io.Serializable {

		private static final long serialVersionUID = 2349487124465234825L;

		private boolean success = false;

		private String msg = "";

		private Object obj = null;

		private String interactiveUrl = "";
		private String resultContents = "";
		private String sysType = "";

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}

		public String getInteractiveUrl() {
			return interactiveUrl;
		}

		public void setInteractiveUrl(String interactiveUrl) {
			this.interactiveUrl = interactiveUrl;
		}

		public String getResultContents() {
			return resultContents;
		}

		public void setResultContents(String resultContents) {
			this.resultContents = resultContents;
		}

		public String getSysType() {
			return sysType;
		}

		public void setSysType(String sysType) {
			this.sysType = sysType;
		}
	}
}
