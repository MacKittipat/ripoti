package com.abctech.ripoti.webapp.json.ripoti;

public class BaseIssue {

    private String title; // title = [key] summary
    private TimeSpent timeSpent;

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
