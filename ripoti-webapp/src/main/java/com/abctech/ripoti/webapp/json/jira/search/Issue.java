package com.abctech.ripoti.webapp.json.jira.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issue {

    private String id;
    private String self;
    private String key;
    @JsonProperty(value = "fields")
    private Field field;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
