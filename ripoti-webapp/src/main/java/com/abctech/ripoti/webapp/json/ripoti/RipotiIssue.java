package com.abctech.ripoti.webapp.json.ripoti;

public class RipotiIssue {

    private String viewName;
    private String sprintName;
    private ParentIssue[] parentIssues;
    private TimeSpent timeSpent;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public ParentIssue[] getParentIssues() {
        return parentIssues;
    }

    public void setParentIssues(ParentIssue[] parentIssues) {
        this.parentIssues = parentIssues;
    }

    public TimeSpent getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(TimeSpent timeSpent) {
        this.timeSpent = timeSpent;
    }
}
