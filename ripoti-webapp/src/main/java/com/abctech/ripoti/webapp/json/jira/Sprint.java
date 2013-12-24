package com.abctech.ripoti.webapp.json.jira;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sprint {

    private int id;
    private String name;
    private String state;
    @JsonIgnore
    private int linkedPagesCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLinkedPagesCount() {
        return linkedPagesCount;
    }

    public void setLinkedPagesCount(int linkedPagesCount) {
        this.linkedPagesCount = linkedPagesCount;
    }
}
