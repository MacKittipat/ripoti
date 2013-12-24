package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.form.AuthForm;
import com.abctech.ripoti.webapp.form.ReportBuilderForm;
import com.abctech.ripoti.webapp.json.jira.JiraSession;
import com.abctech.ripoti.webapp.json.jira.View;
import com.abctech.ripoti.webapp.service.IJiraAuthStorageService;
import com.abctech.ripoti.webapp.service.JiraRestService;
import com.abctech.ripoti.webapp.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private JiraRestService jiraRestService;

    @Autowired
    private IJiraAuthStorageService jiraAuthStorageService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:auth";
    }

    @RequestMapping(value = "auth")
    public String auth(Model model, HttpServletRequest request, @ModelAttribute AuthForm authForm) {
        model.addAttribute("pageContent", "main/auth");
        if(jiraAuthStorageService.getAuthorizationValue() != null) {
            return "redirect:report";
        }
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.info("User {} is authenticating for Jira's REST service", authForm.getUsername());
            try {
                String base64Auth = Base64Util.encode(authForm.getUsername() + ":" + authForm.getPassword());
                JiraSession jiraSession = jiraRestService.getJiraSession(base64Auth);
                log.debug(jiraSession.toString());
                request.getSession().setAttribute("authorization", base64Auth);
                log.info("Authentication success");
            } catch (HttpClientErrorException e) {
                log.warn("Authentication fail", e);
                model.addAttribute("errorMsg", "Authentication fail, Username or Password is not correct.");
            }
        }
        return "layout";
    }

    @RequestMapping(value = "report")
    public String report(Model model, HttpServletRequest request, @ModelAttribute ReportBuilderForm reportBuilderForm) {
        model.addAttribute("pageContent", "main/report");
        View[] views = jiraRestService.getViews(
                jiraAuthStorageService.getAuthorizationValue());
        Map<String, String> viewMap = new LinkedHashMap<>();
        viewMap.put("0", "Please select");
        for(View view : views) {
            viewMap.put(Integer.toString(view.getId()), view.getName());
        }
        model.addAttribute("viewMap", viewMap);
        return "layout";
    }
}
