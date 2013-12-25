package com.abctech.ripoti.webapp.service;

import com.abctech.ripoti.webapp.json.jira.search.Issue;
import com.abctech.ripoti.webapp.json.ripoti.ChildIssue;
import com.abctech.ripoti.webapp.json.ripoti.ParentIssue;
import com.abctech.ripoti.webapp.json.ripoti.RipotiIssue;
import com.abctech.ripoti.webapp.json.ripoti.TimeSpent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JiraToRipotiService {

    private static final String TIME_SPENT_UNIT = "hour";

    /**
     * Convert json from jira to ripoti.
     * @param issues
     * @return RipotiIssue
     */
    public RipotiIssue convert(Issue[] issues) {
        RipotiIssue ripotiIssue = new RipotiIssue();
        List<ParentIssue> parentIssueList = new ArrayList<>();
        int totalTimeSpent = 0;
        for(Issue pIssue : issues) {
            // Parent issue.
            if(!pIssue.getField().getIssueType().isSubTask()) {
                ParentIssue parentIssue = new ParentIssue();
                parentIssue.setSummary(pIssue.getField().getSummary());
                int parentTotatTimeSpent = 0;
                List<ChildIssue> childIssueList = new ArrayList<>();
                // Child issue.
                for(Issue cIssue : issues) {
                    if(cIssue.getField().getIssueType().isSubTask() &&
                            cIssue.getField().getParent() != null &&
                            cIssue.getField().getParent().getKey().equals(pIssue.getKey())) {
                        ChildIssue childIssue = new ChildIssue();
                        childIssue.setSummary(cIssue.getField().getSummary());
                        TimeSpent cTimeSpent = new TimeSpent();
                        cTimeSpent.setUnit(TIME_SPENT_UNIT);
                        cTimeSpent.setValue(secToHr(cIssue.getField().getTimeSpent()));
                        childIssue.setTimeSpent(cTimeSpent);
                        // Increase total time spent.
                        parentTotatTimeSpent += cIssue.getField().getTimeSpent();
                        totalTimeSpent += cIssue.getField().getTimeSpent();
                        childIssueList.add(childIssue);
                    }
                } // End child issue.
                TimeSpent pTimeSpent = new TimeSpent();
                pTimeSpent.setUnit(TIME_SPENT_UNIT);
                pTimeSpent.setValue(secToHr(parentTotatTimeSpent)); // Not pIssue.getField().getAggregateTimeSpent()
                parentIssue.setTimeSpent(pTimeSpent);
                parentIssue.setChildIssues(childIssueList.toArray(new ChildIssue[childIssueList.size()]));
                parentIssueList.add(parentIssue);
            } // End parent issue.
        }
        ripotiIssue.setParentIssues(parentIssueList.toArray(new ParentIssue[parentIssueList.size()]));
        TimeSpent timeSpent = new TimeSpent();
        timeSpent.setUnit(TIME_SPENT_UNIT);
        timeSpent.setValue(secToHr(totalTimeSpent));
        ripotiIssue.setTimeSpent(timeSpent);
        return ripotiIssue;
    }

    private double secToHr(int second) {
        return second / 3600.00;
    }
}
