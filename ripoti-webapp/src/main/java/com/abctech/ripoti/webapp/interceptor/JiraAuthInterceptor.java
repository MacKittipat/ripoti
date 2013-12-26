package com.abctech.ripoti.webapp.interceptor;

import com.abctech.ripoti.webapp.service.IJiraAuthStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JiraAuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(JiraAuthInterceptor.class);

    @Autowired
    private IJiraAuthStorageService jiraAuthStorageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(jiraAuthStorageService.getAuthorizationValue() == null) {
            try {
                response.sendRedirect("/auth");
                return false;
            } catch (IOException e) {
                log.error("Can not redirect to /auth", e);
            }
        }
        return true;
    }
}
