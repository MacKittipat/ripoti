package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.form.AuthForm;
import com.abctech.ripoti.webapp.form.ReportBuilderForm;
import com.abctech.ripoti.webapp.json.jira.JiraSession;
import com.abctech.ripoti.webapp.json.jira.rapidview.View;
import com.abctech.ripoti.webapp.json.jira.search.Search;
import com.abctech.ripoti.webapp.json.jira.sprintquery.Sprint;
import com.abctech.ripoti.webapp.service.IJiraAuthStorageService;
import com.abctech.ripoti.webapp.service.JiraRestService;
import com.abctech.ripoti.webapp.service.JiraToRipotiService;
import com.abctech.ripoti.webapp.service.JiraViewStorageService;
import com.abctech.ripoti.webapp.util.Base64Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private JiraToRipotiService jiraToRipotiService;

    @Autowired
    private JiraViewStorageService jiraViewStorageService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:auth";
    }

    @RequestMapping(value = "auth")
    public String auth(Model model, HttpServletRequest request, @ModelAttribute AuthForm authForm) {
        model.addAttribute("pageContent", "main/auth");
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.info("User {} is authenticating for Jira's REST service", authForm.getUsername());
            try {
                String base64Auth = Base64Util.encode(authForm.getUsername() + ":" + authForm.getPassword());
                JiraSession jiraSession = jiraRestService.getJiraSession(base64Auth);
                log.debug(jiraSession.toString());
                request.getSession().setAttribute("authorization", base64Auth);
                log.info("Authentication success");
                if(jiraAuthStorageService.getAuthorizationValue() != null) {
                    log.info("Redirecting to report page");
                    return "redirect:report";
                }
            } catch (HttpClientErrorException e) {
                log.warn("Authentication fail", e);
                model.addAttribute("errorMsg", "Authentication fail, Username or Password is not correct.");
            }
        }
        return "layout";
    }

    @RequestMapping(value = "report")
    public String report(
            Model model,
            HttpServletRequest request,
            @ModelAttribute ReportBuilderForm reportBuilderForm) {
        model.addAttribute("pageContent", "main/report");
        String authValue = jiraAuthStorageService.getAuthorizationValue();
        // Generate viewMap for ddl.
        View[] views = jiraViewStorageService.load();
        // If view storage does not exist, fetch view from jira and save to storage,
        if(views == null) {
            log.info("Load views from Jira REST service.");
            views = jiraRestService.getViews(authValue);
            jiraViewStorageService.save(views);
        }
        Map<String, String> viewMap = new LinkedHashMap<>();
        viewMap.put("0", "Please select");
        for(View view : views) {
            viewMap.put(Integer.toString(view.getId()), view.getName());
        }
        model.addAttribute("viewMap", viewMap);
        // Generate sprintMap for ddl.
        if(reportBuilderForm.getViewId() != null) {
            Map<String, String> sprintMap = new LinkedHashMap<>();
            Sprint[] sprints = jiraRestService.getSprints(
                    authValue,
                    reportBuilderForm.getViewId());
            sprintMap.put("0", "Please select");
            for(Sprint sprint : sprints) {
                sprintMap.put(Integer.toString(sprint.getId()), sprint.getName());
            }
            if(reportBuilderForm.getSprintId() != null) {
                model.addAttribute("sprintMap", sprintMap);
                // Get issues from jira.
                Search search = jiraRestService.getSearch(authValue, reportBuilderForm.getSprintId());
                try {
                    // Convert json from jira to ripoti.
                    model.addAttribute(
                            "ripotiJson",
                            jacksonObjectMapper.writeValueAsString(
                                    jiraToRipotiService.convert(search.getIssues())));
                } catch (JsonProcessingException e) {
                    log.warn("Can not convert object to json", e);
                }
            }
        }
        return "layout";
    }
}
