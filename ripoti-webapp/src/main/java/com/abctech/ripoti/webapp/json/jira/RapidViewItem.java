package com.abctech.ripoti.webapp.json.jira;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RapidViewItem {

    public int id;
    public String name;
    @JsonIgnore
    public boolean canEdit;
    @JsonIgnore
    public boolean sprintSupportEnabled;

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

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isSprintSupportEnabled() {
        return sprintSupportEnabled;
    }

    public void setSprintSupportEnabled(boolean sprintSupportEnabled) {
        this.sprintSupportEnabled = sprintSupportEnabled;
    }

    @Override
    public String toString() {
        return "RapidViewItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
