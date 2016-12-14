package com.hzcf.platform.mgr.sys.shiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.common.util.time.DateUtil;
import com.hzcf.platform.core.sys.model.BaseUser;
import com.hzcf.platform.core.sys.model.PermissionVO;
import com.hzcf.platform.core.sys.model.UserVO;
import com.hzcf.platform.core.sys.service.PermissionService;
import com.hzcf.platform.core.sys.service.UserService;

/**
 * 
 * <p>
 * Title: CustomRealm
 * </p>
 * <p>
 * Description:自定义realm
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author
 * @date
 * @version 1.0
 */
public class CustomRealm extends AuthorizingRealm {

	//private static final String Session = null;

	// 注入service

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	// realm的认证方法，从数据库查询用户信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userCode = (String) token.getPrincipal();
		// 第二步：根据用户输入的userCode从数据库查询
		UserVO userVO = null;
		BaseUser currentUser = null;
        List<String> menus=null;
		try {
			userVO = (UserVO) userService.getUserByLoginName(userCode).getItems();
			
			if(userVO.getLocked()!=null&&userVO.getLocked().equals("1")){
				/***
				 * DisabledAccountException（禁用的帐号）
				 * LockedAccountException（锁定的帐号）、
				 * UnknownAccountException（错误的帐号）、
				 * ExcessiveAttemptsException（登录失败次数过多）、
				 * IncorrectCredentialsException （错误的凭证）、
				 * ExpiredCredentialsException（过期的凭证）
				 */
				throw new LockedAccountException(); //账号锁定 
			}
			if (userVO != null) {
				currentUser = new BaseUser();
				currentUser.setId(userVO.getId());
				currentUser.setLoginName(userVO.getLoginName());
				currentUser.setUserName(userVO.getUserName());
				//currentUser.setMenus(menus);
				userVO.setLoginDate(new java.util.Date());
				userService.update(userVO);
			}else{
				return null;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return new SimpleAuthenticationInfo(currentUser, userVO.getPassword(), ByteSource.Util.bytes(userVO.getSalt()),
				this.getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		logger.info("----####进入 shiro 授权 开始，加载权限 ####");

		BaseUser user = (BaseUser) principals.getPrimaryPrincipal();
        List<String> permissions=null;
		try {
			// 根据身份信息获取权限信息
			permissions=this.permissionService.getPermissionListByUserId(user.getId().intValue());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.info("----#### 查询用户权限 异常 ####:"+e1.getMessage());
			e1.printStackTrace();
		}
		// 单独定一个集合对象
		//logger.info("----####进入 shiro 授权 开始，用户加载权限####");
		logger.info("----#### 加载用户权限 ####:"+permissions);


		//跟新用户最新session活动时间
		Subject subject = SecurityUtils.getSubject();

		Date sessionStartTime = subject.getSession().getStartTimestamp();
		logger.info("************sessionFilter UserSessionFresh  sessionStartTime :"+DateUtil.convertDate2String(sessionStartTime));
		Date sessionLasstAccessTime = subject.getSession().getLastAccessTime();
		logger.info("************sessionFilter UserSessionFresh  sessionLasstAccessTime :"+DateUtil.convertDate2String(sessionLasstAccessTime));

		long timeout = subject.getSession().getTimeout();
		logger.info("************sessionFilter UserSessionFresh  timeout :"+timeout);
		//更新会话最后访问时间
		subject.getSession().touch();
		
		Date sessionStartTime2 = subject.getSession().getStartTimestamp();
		logger.info("************sessionFilter UserSessionFresh  sessionStartTime2 :"+DateUtil.convertDate2String(sessionStartTime2));
		Date sessionLasstAccessTime2 = subject.getSession().getLastAccessTime();
		logger.info("************sessionFilter UserSessionFresh  sessionLasstAccessTime2 :"+DateUtil.convertDate2String(sessionLasstAccessTime2));

		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;

	}

	// 清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
