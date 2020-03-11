package com.xzh.customer.log.interceptorLog;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/9 0009 14:20
 * @modify By:
 **/
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        JSONObject requestBody = new JSONObject();
        JSONObject headers = new JSONObject();
        Enumeration<String> em = request.getHeaderNames();
        while (em.hasMoreElements()) {
            String s = em.nextElement();
            headers.put(s, request.getHeader(s));
        }
        requestBody.put("headers", headers);

        Enumeration<String> pem = request.getParameterNames();
        while (pem.hasMoreElements()) {
            String s = pem.nextElement();
            headers.put(s, request.getParameter(s));
        }

        String path = request.getServletPath();
        String content = requestBody.toJSONString();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(df.format(System.currentTimeMillis()) + " ==== " + path + "\n" + content);
        return true;
    }
}
