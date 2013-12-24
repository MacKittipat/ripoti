package com.abctech.ripoti.webapp.json.jira.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Field {

    private String summary;
    @JsonProperty(value = "subtasks")
    private SubTask[] subTasks;
    @JsonProperty(value = "issuetype")
    private IssueType issueType;
    private String description;
    private Component[] components;
    @JsonProperty(value = "timespent")
    private int timeSpent;
    @JsonProperty(value = "aggregatetimespent")
    private int aggregateTimeSpent;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SubTask[] getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(SubTask[] subTasks) {
        this.subTasks = subTasks;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        this.components = components;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getAggregateTimeSpent() {
        return aggregateTimeSpent;
    }

    public void setAggregateTimeSpent(int aggregateTimeSpent) {
        this.aggregateTimeSpent = aggregateTimeSpent;
    }
}
