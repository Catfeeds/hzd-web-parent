package com.hzcf.platform.mgr.sys.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.exiao.platform.common.util.status.StatusCodes;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SystemHanlderExceptionResolver implements HandlerExceptionResolver, Ordered {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ObjectMapper objectMapper;

	public SystemHanlderExceptionResolver() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		logger.error("system has   exception", ex);
		ObjectNode root = objectMapper.createObjectNode();
		if (ex instanceof UnauthorizedException || ex instanceof AuthorizationException) {
			logger.error("system has permission  exception", ex);

			root.put("status", StatusCodes.PERMISSION_DENIED);
			root.put("msg", "当前用户没有此操作权限，请联系系统管理员!");

		} else {
			root.put("status", StatusCodes.UNCATCHED_EXCEPTION);
			root.put("msg", "服务器内部异常，请稍后再试");
		}
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getOutputStream().write(root.toString().getBytes());
			ex = null;
		} catch (IOException e) {
			logger.error("response outputStream write exception", e);
		}
		return new ModelAndView();
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
