package com.abctech.ripoti.webapp.json.ripoti;

public class BaseIssue {

    private String summary;
    private TimeSpent timeSpent;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public TimeSpent getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(TimeSpent timeSpent) {
        this.timeSpent = timeSpent;
    }
}
