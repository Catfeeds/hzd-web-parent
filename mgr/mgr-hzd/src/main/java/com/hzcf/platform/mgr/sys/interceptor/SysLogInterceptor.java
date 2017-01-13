/*package com.hzcf.platform.mgr.sys.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hzcf.platform.core.sys.model.BaseUser;
import com.hzcf.platform.core.sys.model.SysLogVO;
import com.hzcf.platform.core.sys.service.SysLogService;
import com.hzcf.platform.mgr.sys.common.util.StringUtils;



public class SysLogInterceptor  implements HandlerInterceptor{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private SysLogService sysLogService;

	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		String requestRri = request.getRequestURI();
		String uriPrefix = request.getContextPath() ;
		logger.debug(" SysLogInterceptor requestRri:"+requestRri+"-------uriPrefix:"+uriPrefix);
		if ((StringUtils.startsWith(requestRri, uriPrefix)&& (StringUtils.contains(requestRri, "/role/")
				|| StringUtils.contains(requestRri, "/user/") ) ) || ex!=null){
			logger.debug(" Save log is start");
			//User user = UserUtils.getUser();
			//if (user!=null && user.getId()!=null){
				
				StringBuilder params = new StringBuilder();
				int index = 0;
				for (Object param : request.getParameterMap().keySet()){ 
					params.append((index++ == 0 ? "" : "&") + param + "=");
					params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((String)param, "password")
							? "" : request.getParameter((String)param), 100));
				}
				
				Subject subject = SecurityUtils.getSubject();
				BaseUser user = (BaseUser) subject.getPrincipal();
			
				SysLogVO sysLogVo = new SysLogVO();
				if(user!=null){
				  sysLogVo.setUser_id(new Integer(user.getId().toString()));
				  sysLogVo.setOperator(user.getLoginName());
				}
				sysLogVo.setType(ex == null ? new Integer(TYPE_ACCESS) : new Integer(TYPE_EXCEPTION));
				//log.setCreateBy(user);
				sysLogVo.setCreateTime(new Date());
				sysLogVo.setRemote_addr(StringUtils.getRemoteAddr(request));
				//log.setUserAgent(request.getHeader("user-agent"));
				sysLogVo.setRequest_uri(request.getRequestURI());
				sysLogVo.setMethod(request.getMethod());
				sysLogVo.setParams(params.toString());
				sysLogVo.setException(ex != null ? ex.toString() : "");
				sysLogService.create(sysLogVo);
		      }
				//logger.debug("save log {type: {}, loginName: {}, uri: {}}, ", log.getType(), user.getLoginName(), log.getRequestUri());
				
			//}
		//}
		
//		logger.debug("最大内存: {}, 已分配内存: {}, 已分配内存中的剩余空间: {}, 最大可用内存: {}", 
//				Runtime.getRuntime().maxMemory(), Runtime.getRuntime().totalMemory(), Runtime.getRuntime().freeMemory(), 
//				Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory()); 
	
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
*/