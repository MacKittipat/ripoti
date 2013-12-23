package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.form.AuthForm;
import com.abctech.ripoti.webapp.json.jira.Session;
import com.abctech.ripoti.webapp.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:auth";
    }

    @RequestMapping(value = "auth")
    public String auth(Model model, HttpServletRequest request, @ModelAttribute AuthForm authForm) {
        model.addAttribute("pageContent", "main/auth");
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.info("User {} is authenticating for Jira's REST service");
            String base64Creds = Base64Util.encode(authForm.getUsername() + ":" + authForm.getPassword());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            try {
                ResponseEntity<Session> response = restTemplate.exchange(
                        "https://apidev.atlassian.net/rest/auth/1/session",
                        HttpMethod.GET,
                        httpEntity,
                        Session.class);
                Session session = response.getBody();
                request.getSession().setAttribute(
                        "authorization",
                        base64Creds);
                log.info("Authentication success");
                log.debug(session.toString());
            } catch (HttpClientErrorException e) {
                log.warn("Authentication fail", e);
                model.addAttribute("errorMsg", "Authentication fail, Username or Password is not correct.");
            }
        }
        return "layout";
    }

    @RequestMapping(value = "test")
    public @ResponseBody String test(HttpServletRequest request) {
        log.debug(">>> " + request.getSession().getAttribute("authorization"));
        return "test";
    }
}
