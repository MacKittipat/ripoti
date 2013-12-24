package com.abctech.ripoti.webapp.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RipotiProperties {

    @Value(value = "${ripoti.url.jira}")
    private String jiraUrl;

    @Value(value = "${ripoti.uri.session}")
    private String sessionUri;

    @Value(value = "${ripoti.uri.rapidview}")
    private String rapidViewUri;

    @Value(value = "${ripoti.uri.sprintquery}")
    private String sprintQueryUri;

    public String getJiraUrl() {
        return jiraUrl;
    }

    public void setJiraUrl(String jiraUrl) {
        this.jiraUrl = jiraUrl;
    }

    public String getSessionUri() {
        return sessionUri;
    }

    public void setSessionUri(String sessionUri) {
        this.sessionUri = sessionUri;
    }

    public String getRapidViewUri() {
        return rapidViewUri;
    }

    public void setRapidViewUri(String rapidViewUri) {
        this.rapidViewUri = rapidViewUri;
    }

    public String getSprintQueryUri() {
        return sprintQueryUri;
    }

    public void setSprintQueryUri(String sprintQueryUri) {
        this.sprintQueryUri = sprintQueryUri;
    }

    // =================================================================================================================

    public String getSessionUrl() {
        return getJiraUrl() + "/" + getSessionUri();
    }

    public String getRapidViewUrl() {
        return getJiraUrl() + "/" + getRapidViewUri();
    }

    public String getSprintQueryUrl(int viewId) {
        return getJiraUrl() + "/" + getSprintQueryUri() + "/" + viewId;
    }
}
