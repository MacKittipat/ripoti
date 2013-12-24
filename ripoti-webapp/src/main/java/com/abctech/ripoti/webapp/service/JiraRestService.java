package com.abctech.ripoti.webapp.service;

import com.abctech.ripoti.webapp.json.jira.JiraSession;
import com.abctech.ripoti.webapp.util.Base64Util;
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
     * @param username
     * @param password
     * @return JiraSession
     */
    public JiraSession getJiraSession(String username, String password) throws HttpClientErrorException {
        ResponseEntity<JiraSession> response = restTemplate.exchange(
                "https://apidev.atlassian.net/rest/auth/1/session",
                HttpMethod.GET,
                createHeaderAuthorization(username, password),
                JiraSession.class);
        JiraSession jiraSession = response.getBody();
        return jiraSession;
    }

    /**
     * Create header Authorization for authenticate Jira's REST service.
     * @param username
     * @param password
     * @return header Authorization
     */
    private HttpEntity<String> createHeaderAuthorization(String username, String password) {
        String base64Creds = Base64Util.encode(username + ":" + password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return new HttpEntity<>(headers);
    }
}
