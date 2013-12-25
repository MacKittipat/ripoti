package com.abctech.ripoti.webapp.json.ripoti;

public class RipotiIssue {

    private ParentIssue[] parentIssues;
    private TimeSpent timeSpent;

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
