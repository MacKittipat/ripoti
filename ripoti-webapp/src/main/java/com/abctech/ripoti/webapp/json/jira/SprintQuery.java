package com.abctech.ripoti.webapp.json.jira;

public class SprintQuery {

    private Sprint[] sprints;
    private int rapidViewId;

    public Sprint[] getSprints() {
        return sprints;
    }

    public void setSprints(Sprint[] sprints) {
        this.sprints = sprints;
    }

    public int getRapidViewId() {
        return rapidViewId;
    }

    public void setRapidViewId(int rapidViewId) {
        this.rapidViewId = rapidViewId;
    }
}
