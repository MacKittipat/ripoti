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

    @Value(value = "${ripoti.uri.search}")
    private String searchUri;

    @Value(value = "${ripoti.query.search}")
    private String searchQuery;

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

    public String getSearchUri() {
        return searchUri;
    }

    public void setSearchUri(String searchUri) {
        this.searchUri = searchUri;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
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

    public String getSearchTaskUrl(int sprintId) {
        return getJiraUrl() + "/" + getSearchUri() + getSearchQuery().replace("{sprintId}", Integer.toString(sprintId));
    }
}
