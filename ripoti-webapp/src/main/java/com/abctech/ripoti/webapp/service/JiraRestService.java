package com.abctech.ripoti.webapp.service;

import com.abctech.ripoti.webapp.json.jira.JiraSession;
import com.abctech.ripoti.webapp.json.jira.RapidView;
import com.abctech.ripoti.webapp.json.jira.RapidViewItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class JiraRestService {

    private static final Logger log = LoggerFactory.getLogger(JiraRestService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get session.
     * @param base64Auth
     * @return Jira session
     */
    public JiraSession getJiraSession(String base64Auth) throws HttpClientErrorException {
        log.debug("Calling rest/auth/1/session");
        ResponseEntity<JiraSession> response = restTemplate.exchange(
                "https://apidev.atlassian.net/rest/auth/1/session",
                HttpMethod.GET,
                createHeaderAuthorization(base64Auth),
                JiraSession.class);
        JiraSession jiraSession = response.getBody();
        return jiraSession;
    }

    /**
     * Get array of rapid view items.
     * @param base64Auth
     * @return Array of rapid view items
     */
    public RapidViewItem[] getRapidViewItems(String base64Auth) throws HttpClientErrorException {
        log.debug("Calling rest/greenhopper/1.0/rapidview");
        ResponseEntity<RapidView> response = restTemplate.exchange(
                "https://apidev.atlassian.net/rest/greenhopper/1.0/rapidview",
                HttpMethod.GET,
                createHeaderAuthorization(base64Auth),
                RapidView.class);
        RapidView rapidView = response.getBody();
        return rapidView.getRapidViewItems();
    }


    /**
     * Create header Authorization for authenticate Jira's REST service.
     * @param base64Auth
     * @return Header Authorization
     */
    private HttpEntity<String> createHeaderAuthorization(String base64Auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Auth);
        return new HttpEntity<>(headers);
    }
}
