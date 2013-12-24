package com.abctech.ripoti.webapp.json.jira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RapidView {

    @JsonProperty(value = "views")
    private RapidViewItem[] rapidViewItems;

    public RapidViewItem[] getRapidViewItems() {
        return rapidViewItems;
    }

    public void setRapidViewItems(RapidViewItem[] rapidViewItems) {
        this.rapidViewItems = rapidViewItems;
    }
}
