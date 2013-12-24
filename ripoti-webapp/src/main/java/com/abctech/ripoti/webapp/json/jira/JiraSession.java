package com.abctech.ripoti.webapp.json.jira;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JiraSession {

    private String name;
    private String errorMessages;
    @JsonIgnore
    private String self;
    @JsonIgnore
    private String loginInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Override
    public String toString() {
        return "JiraSession{" +
                "name='" + name + '\'' +
                ", errorMessages='" + errorMessages + '\'' +
                '}';
    }
}
