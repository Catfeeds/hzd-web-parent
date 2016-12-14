package com.hzcf.platform.mgr.sys.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.common.util.json.parser.JsonParserFactory;
import com.hzcf.platform.common.util.response.ResponseBuilder;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.model.BaseUser;
import com.hzcf.platform.core.sys.service.UserService;

import com.hzcf.platform.framework.webmvc.session.SessionCache;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LoginController {

	// public static final String CACHE_USER = "user";

	private Logger logger = LoggerFactory.getLogger(this.getClass());
     
	@Autowired
	private UserService userService;

	private ObjectMapper objectMapper;

	@Autowired
	private SessionCache cache;

	/**
	 * redis 分布式缓存， 保存用户session
	 */

	/**
	 * 登录方法
	 * 
	 * @param req
	 * @param loginParam
	 * @return
	 */

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Object> login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		logger.info("======系统未登录  或session 超时========");
		//response.sendRedirect("login.html");
		//return null;
		Subject subject = SecurityUtils.getSubject();
		SecurityUtils.getSubject().logout();
		Result result = new Result();
		result.setStatus(StatusCodes.UNAUTHORIZED);
		result.setMsg("系统未登录或session超时！");
		
		return ResponseBuilder.instance().body(result).build();
	}

	// 用户登陆提交方法

	/**
	 * 
	 * <p>
	 * Title: login
	 * 
	 * @param session
	 *            输入的验证码
	 * @param usercode
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(HttpServletRequest request, HttpServletResponse response, String username, String password)
			throws Exception {
		// logger.info("rememberMe=" + CookieUtils.getCookie(request,
		// "rememberMe"));
		 logger.info("---login username:" + username + "--password:" +
		 password);
		// 如果已经登录，则跳转到管理首页
		Result result = new Result();

		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		result.setStatus(404);
		result.setMsg("登录账号或密码错误,请重新输入！");
		Subject subject = SecurityUtils.getSubject();
		SecurityUtils.getSubject().logout();
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		//response.sendRedirect("/login.html");
		return ResponseBuilder.instance().body(result).build();
	}

	/**
	 * 鉴权失败跳转地址
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/refuse", method = RequestMethod.GET)
	public ResponseEntity<Object> refuse(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
	
		logger.info("=====权限验证失败  ,"+user.getLoginName()+"--用户没有此权限====");		
		Result<Long> result = new Result<Long>();
		result.setStatus(StatusCodes.PERMISSION_DENIED);
		
		return ResponseBuilder.instance().body(result).build();
	}
   
	// 系统首页
	@RequestMapping("/index")
	public ResponseEntity<Object> index(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		logger.info("=====用户登录成功====");
		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		logger.info("=====用户认证成功====:" + subject.isAuthenticated());
		Session session = SecurityUtils.getSubject().getSession();
		session.setTimeout(-1000l);
		// timeout:-1000ms 永不超时
		//session.setTimeout(30*60*1000); //30分钟
		// 保存cookieid 到redis 中
		// 使用redis 共享缓存
		objectMapper = this.getObjectMapper();
		ObjectNode root = objectMapper.createObjectNode();
		root.put("status", StatusCodes.OK);
		root.put("msg", "成功");
		
		logger.info("=====用户data:" +JsonParserFactory.getParser().toJson(user));

		root.put("data", JsonParserFactory.getParser().toJson(user));
		ObjectNode headerNode = objectMapper.createObjectNode();
		logger.info("=====用户认证成功==rememberMe:" + session.getId().toString());
		headerNode.put("rememberMe", session.getId().toString());
		root.put("header", headerNode);

		return ResponseBuilder.instance().body(root).build();
	}

	/**
	 * 手动退出，然后给前台返回结果
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("=====用户退出登录====");
		Subject subject = SecurityUtils.getSubject();
		logger.info("=====用户退出====:" + subject.isAuthenticated());

		Result result = new Result();
		try {
			if (subject.isAuthenticated()) {
				// 退出登录
				Cookie[] cookies = (Cookie[]) request.getCookies();//这样便可以获取一个cookie数组
				logger.info(" cookies  size is :"+cookies.length);
				for(Cookie cookie : cookies){
					logger.info(" cookies is name:"+cookie.getName()+" domain:"
						    +cookie.getDomain()+" path:"+cookie.getPath()+" MaxAge:"+cookie.getMaxAge()+":"+cookie.getValue()+":"+cookie.getComment());
				  
				}
				SecurityUtils.getSubject().logout();
				
				result.setMsg("退出成功 ！");
				result.setStatus(StatusCodes.OK);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(404);
			result.setMsg("未知错误 ！");
		}
		return ResponseBuilder.instance().body(result).build();
	}

	private ObjectMapper getObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}

}
