package com.abctech.ripoti.webapp.json.jira.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueType {

    private String self;
    private String id;
    private String name;
    @JsonProperty(value = "subtask")
    private boolean subTask;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubTask() {
        return subTask;
    }

    public void setSubTask(boolean subTask) {
        this.subTask = subTask;
    }
}
