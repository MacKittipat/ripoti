package com.abctech.ripoti.webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class JiraAuthSessionStorageService implements IJiraAuthStorageService {

    @Override
    public String getAuthorizationValue() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if(request.getSession().getAttribute("authorization") == null) {
            return null;
        }
        return request.getSession().getAttribute("authorization").toString();
    }
}
