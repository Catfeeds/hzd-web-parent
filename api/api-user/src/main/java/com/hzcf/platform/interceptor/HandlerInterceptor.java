package com.hzcf.platform.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.ConstantsToken;
import com.hzcf.platform.core.MyfStatusCodeEnum;
import com.hzcf.platform.core.user.model.RequestAgent;
import com.hzcf.platform.core.user.model.UserVO;
/**
 * 
 * @description:自定义拦截器
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static Log logger = Log.getLogger(HandlerInterceptor.class);
	@Autowired
    private ICache cache;
	/* @Autowired
    private TokenManager manager;*/
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (handler instanceof HandlerMethod) {
        	
			StringBuffer url = request.getRequestURL();
			// 获取域名 
			String host = url.delete(url.length() - request.getRequestURI().length(), url.length())
 					.append(request.getServletContext().getContextPath()).append("/").toString();
			String agent = request.getHeader("user-agent");
			/*	
			String[] split = agent.split("#");
			RequestAgent ra = new RequestAgent();
			// 安卓 /IOS 规范 
			if (split.length == 2) {
				ra.setTerminal(split[0]);
				ra.setVersion(split[1]);
			} else {
				//微信规范
				ra.setTerminal(agent);
				ra.setVersion("-1");
			}*/
			
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String token = request.getHeader(ConstantsToken.AUTHORIZATION);
        if(StringUtils.isBlank(token)){
        	  response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	return false;
        }
        UserVO uesr = (UserVO)cache.load(ConstantsToken.USER_CACHE_KEY+token);
        if(uesr!=null){
        	uesr.setToken(token);
        	request.setAttribute("user", uesr);// 访问用户
        	return true;
        }
        
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        System.out.println("进入false");
        PrintWriter out = null;  
            try {
            
               out = response.getWriter();  
               out.print(JsonUtil.json2String(new BackResult(MyfStatusCodeEnum.MEF_CODE_1111.getCode(),MyfStatusCodeEnum.MEF_CODE_1111.getMsg())));
           } catch (IOException e) {  
               e.printStackTrace();  
          } finally {  
             if (out != null) {  
                    out.close();  
               }  
           }  

        return false;
        
    /*    //验证token
        //TokenModel model = manager.getToken(authorization);
        if (authorization) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        Map<String, Object> map = new HashMap<String, Object>();
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        if (authorization== null) {
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("进入false");
            PrintWriter out = null;  
                try {  
                   out = response.getWriter();  
                   out.print(JsonUtil.json2String(""));
               } catch (IOException e) {  
                   e.printStackTrace();  
              } finally {  
                 if (out != null) {  
                        out.close();  
                   }  
               }  

            return false;
        }
        
        System.out.println("进入true");
        return true;*/
    }
    
    @Override    
   public void afterCompletion(HttpServletRequest request,    
                HttpServletResponse response, Object handler, Exception ex)    
               throws Exception {    
       }    
    
    public void postHandle(HttpServletRequest request,  
    		            HttpServletResponse response, Object o, ModelAndView mav)  
    		           throws Exception {  
    		    }  
}
