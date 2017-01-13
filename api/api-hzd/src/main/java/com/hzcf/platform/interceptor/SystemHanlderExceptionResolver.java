package com.hzcf.platform.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by leijiaming on 2017/1/5 0005.
 */
public class SystemHanlderExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(SystemHanlderExceptionResolver.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public SystemHanlderExceptionResolver() {
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        this.objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        this.logger.error("system has exception", ex);
        ObjectNode root = this.objectMapper.createObjectNode();
        root.put("status", 553);
        root.put("msg", "数据解析异常");

        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getOutputStream().write(root.toString().getBytes());
        } catch (IOException var7) {
            this.logger.error("response outputStream write exception", var7);
        }

        return new ModelAndView();
    }

    public int getOrder() {
        return 2147483647;
    }
}
