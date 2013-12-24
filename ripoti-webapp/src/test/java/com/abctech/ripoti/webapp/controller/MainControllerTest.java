package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.json.jira.search.Issue;
import com.abctech.ripoti.webapp.json.jira.search.Search;
import com.abctech.ripoti.webapp.util.Base64Util;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/ripoti-context-test.xml")
public class MainControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        String base64Auth = Base64Util.encode("user:pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Auth);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Search> response = restTemplate.exchange(
                "https://apidev.atlassian.net/rest/api/2/search?jql=Sprint = 170 ORDER BY created ASC&maxResults=500&fields=summary,issuetype,timespent,description,subtasks,components,aggregatetimespent",
                HttpMethod.GET,
                httpEntity,
                Search.class);
        Search search = response.getBody();
        Issue[] issues = search.getIssues();
        for(Issue issue : issues) {
            System.out.println("Key = " + issue.getKey());
            System.out.println("Fields.Summary" + issue.getField().getSummary());
//            System.out.println("Fields.Desc" + issue.getField().getDescription());
            System.out.println("Fields.TimeSpent" + issue.getField().getTimeSpent());
            System.out.println("==========");
        }
    }
}
