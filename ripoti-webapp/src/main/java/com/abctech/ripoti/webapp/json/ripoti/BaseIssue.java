package com.abctech.ripoti.webapp.json.ripoti;

public class BaseIssue {

    private String key;
    private String summary;
    private String title; // title = [key] summary
    private TimeSpent timeSpent;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TimeSpent getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(TimeSpent timeSpent) {
        this.timeSpent = timeSpent;
    }
}
