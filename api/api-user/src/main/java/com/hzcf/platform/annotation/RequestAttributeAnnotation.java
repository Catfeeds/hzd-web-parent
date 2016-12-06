package com.hzcf.platform.annotation;


import java.lang.annotation.Annotation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 
 * @description:增加方法注入，将含有RequestAttribute注解的方法参数注入当前登录用户
 * @author lei
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月6日                         lei      1.0       1.0 Version 
 * </pre>
 */
@Component
public class RequestAttributeAnnotation implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
    	return parameter.getParameterAnnotation(RequestAttribute.class) != null;
    }

/*    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	Annotation[] annotations = parameter.getParameterAnnotations();
        for (Annotation annotation : annotations) {
            if (BrokerRequestArgument.class.isInstance(annotation)) {
            	BrokerRequestArgument ann = (BrokerRequestArgument) annotation;
                String name = ann.value();
                return webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
            }
        }
        return null;
    }*/
    
    
   
    public Object resolveArgument(MethodParameter parameter,  
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,  
            WebDataBinderFactory binderFactory) throws Exception {  
   /* 	System.out.println("aaaa");
        User users = new User();  
        String token = webRequest.getHeader("Authorization");  
        String[] names = webRequest.getParameterValues("name");  
        String[] ages = webRequest.getParameterValues("age");  
          
        users.setPassword("1342802");
        users.setUsername("1342802");
        if(0==0){
        	throw new ParameterException("aaaa");
        }
        return users;  */
    	  Annotation[] annotations = parameter.getParameterAnnotations();
          for (Annotation annotation : annotations) {
              if (RequestAttribute.class.isInstance(annotation)) {
                  RequestAttribute ann = (RequestAttribute) annotation;
                  String name = ann.value();
                  return webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
              }
          }
          return null;
    }
   /* @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String uri = servletRequest.getRequestURI();

        // 取 JsonRequestBody 参数
        // boolean isArray = false;
        // Annotation[] methodAnnotations = parameter.getParameterAnnotations();
        // for (Annotation annotation : methodAnnotations) {
        // if (annotation instanceof JsonRequestBody) {
        // JsonRequestBody ssonRequestBody = (JsonRequestBody) annotation;
        // isArray = ssonRequestBody.isArray();
        // }
        // }

        // 取请求参数
        String jsonEncode = getRequestBody(webRequest);

        // json解密
        String jsonDecode = JsonDecode(jsonEncode);

        // 转json
        JSONObject json = toJsonObject(jsonDecode);

        // 校验登录
        CheckLogin(uri, json);

        // jsonobject 转bean
        Object obj = null;
        Method method = parameter.getMethod();
        Class<?>[] zlasss = method.getParameterTypes();
        for (Class<?> zlass : zlasss) {
            if (Request.class.isAssignableFrom(zlass)) {
                try {
                    obj = JSONObject.toBean(json, zlass);
                } catch (Exception e) {
                    throw new ParameterException(20000, "json to bean error", e);
                }
                continue;
            }
        }
        return obj;

    }

    *//**
     * app json统一解密
     * 
     * @param jsonEncode
     * @return
     *//*
    private String JsonDecode(String jsonEncode) {
        String requestBody = jsonEncode;
        // boolean isproductEnv = PropertiesConfig.getProperty("is_product_env",
        // 0) == 1;// 是否生产环境(1:是,0:否)
        if (true) {
            // RSA解密
            try {
                requestBody = RSA.decrypt(jsonEncode, RSAKeys.default_private_key, "utf-8");
            } catch (Exception e) {
                // 解密失败
                logger.error("请求报文解密失败,非法请求 = " + requestBody, e);
                throw new ParameterException(resultLoad.getResult(ResultKey.JSON_FORMAT_ERROR));
            } finally {
                if (StringUtil.isEmpty(requestBody)) {
                    // 返回异常结果
                    throw new ParameterException(resultLoad.getResult(ResultKey.msg_unpass_fail));
                }
            }
        }
        return requestBody;
    }

    *//**
     * 
     * @param jsonDecode
     * @return
     *//*
    private JSONObject toJsonObject(String jsonDecode) {
        JSONObject val = null;
        try {
            val = JSONObject.fromObject(jsonDecode);
        } catch (Exception e) {
            throw new ParameterException(resultLoad.getResult(ResultKey.JSON_FORMAT_ERROR));
        }
        return val;
    }

    private JSONArray toJsonArray(String jsonDecode) {
        JSONArray array = null;
        try {
            array = JSONArray.fromObject(jsonDecode);
        } catch (Exception e) {
            throw new ParameterException(resultLoad.getResult(ResultKey.JSON_FORMAT_ERROR));
        }
        return array;
    }

    *//**
     * 校验登录
     * 
     * @param requestJson
     * @return
     *//*
    private void CheckLogin(String uri, JSONObject requestJson) {
        boolean checkLogin = false;
        if (uri.toLowerCase().indexOf("/private") != -1) {
            checkLogin = true;
        }

        // 校验登录
        if (checkLogin) {
            // 判断token是否有效
            String token = requestJson.getString("token");
            if (StringUtil.isEmpty(token)) {
                // 返回登录
                throw new ParameterException(resultLoad.getResult(ResultKey.login_expire));
            }

            String checkToken = UserTokenTools.refreshToken(token);
            if (null == checkToken) {
                // token已失效
                throw new ParameterException(resultLoad.getResult(ResultKey.login_expire));
            }
        }

    }

    *//**
     * 取请求参数
     * 
     * @param webRequest
     * @return
     *//*
    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        String jsonBody = (String) webRequest.getAttribute(JSONBODYATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        if (jsonBody == null) {
            try {
                // 把reqeust的body读取到StringBuilder
                BufferedReader reader = servletRequest.getReader();
                StringBuilder sb = new StringBuilder();
                char[] buf = new char[1024];
                int rd;
                while ((rd = reader.read(buf)) != -1) {
                    sb.append(buf, 0, rd);
                }
                jsonBody = sb.toString();

                webRequest.setAttribute(JSONBODYATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new ParameterException(20000, "Request reader 失败");
            } catch (IllegalStateException e) {
                throw new ParameterException(20000, "getInputStream() has already been called for this request");
            }
        }
        return jsonBody;
    }*/
}
