package com.abctech.ripoti.webapp.json.ripoti;

public class ParentIssue extends BaseIssue {

    private ChildIssue[] childIssues;

    public ChildIssue[] getChildIssues() {
        return childIssues;
    }

    public void setChildIssues(ChildIssue[] childIssues) {
        this.childIssues = childIssues;
    }
}
