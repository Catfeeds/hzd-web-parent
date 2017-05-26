package com.hzcf.platform.mgr.sys.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.mgr.sys.service.ISysUsersService;
import com.hzcf.platform.mgr.sys.util.MD5Tools;

import net.sf.json.JSONObject;
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
			request.getSession().removeAttribute("rand");
			return loginJsonResult;
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
			request.getSession().removeAttribute("rand");
			return loginJsonResult;
		}
		
		//保存登陆信息到session中
		SysUsersVO user = new SysUsersVO();
		user.setUserName(username);
		user.setPassword(MD5Tools.getMD5(password));
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("userName", username);
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
		if(request.getSession().getAttribute("user")!=null)
		{
			return "home/main";
		}
		return "login/login";
		
//		return "home/main";
	}
	

	/*private User getDBUser(String userName, String password) {
		User loginUser = new User();
		loginUser.setLoginName(userName);
		loginUser.setLoginPass(password);
		return userService.findByLogin(loginUser);
	}*/
	/**
	 * @Title: toresetpassword 
	 * @Description:跳转到后台用户重置密码
	 * @time: 2017年1月17日 下午4:37:59  
	 * @return:String
	 */
	@RequestMapping(value = "/sys/toresetpassword")
	public String toresetpassword(HttpServletRequest request,HttpServletResponse response) {
		return "home/resetPassword";
	}
	/**
	 * @Title: resetpassword 
	 * @Description:后台用户修改登录密码
	 * @time: 2017年1月17日 下午6:22:27  
	 * @return:String
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sys/resetpassword")
	public void resetpassword(String password,String passwordNew,String passwordNewRepeat,HttpServletResponse response,HttpSession session) throws IOException {
		/**设置返回结果*/
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code","-1");
		/**验证session是否有效*/
		SysUsersVO user=(SysUsersVO) session.getAttribute("user");
		if(user==null){
			result.put("message","修改密码失败，当前用户登录失效，请重新登录");
		}else{
			/**执行密码修改操作*/
			//验证密码是否符合要求
			if("".equals(passwordNew) || passwordNew.length()<6 || passwordNew.length()>32){
				result.put("message","修改密码失败，新密码不符合要求");	
			}
			if(!passwordNew.equals(passwordNewRepeat)){
				result.put("message","修改密码失败，两次输入的新密码不一致");
			}
			//修改密码
			Map<String,Object> updateResult=sysUsersService.updatePassword(user.getUserName(), password, passwordNew);
			if("1".equals(updateResult.get("code"))){
				result.put("code","1");
				result.put("message","修改密码成功");
				user.setPassword(MD5Tools.getMD5(passwordNew));
				session.setAttribute("user",user);
			}else{
				result.put("message",updateResult.get("message"));
			}
		}
		JSONObject json=JSONObject.fromObject(result);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the
												// proxy server
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		response.getWriter().write(json.toString());
		response.flushBuffer();
	}
	
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
