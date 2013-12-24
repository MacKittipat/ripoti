package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.json.jira.Sprint;
import com.abctech.ripoti.webapp.service.JiraRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "rest/a")
public class RestAjaxController {

    @Autowired
    private JiraRestService jiraRestService;

    /**
     * Return list of sprint in JSON format.
     */
    @ResponseBody
    @RequestMapping(
            value = "rapidviews/{rapidViewId}/sprints",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Sprint[] rapidViewsSprints(HttpServletRequest request, @PathVariable int rapidViewId) {
        Sprint[] sprints = jiraRestService.getSprints(
                request.getSession().getAttribute("authorization").toString(),
                rapidViewId);
        return sprints;
    }
}
