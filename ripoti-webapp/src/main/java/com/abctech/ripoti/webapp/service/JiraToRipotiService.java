package com.abctech.ripoti.webapp.service;

import com.abctech.ripoti.webapp.json.jira.search.Issue;
import com.abctech.ripoti.webapp.json.ripoti.ChildIssue;
import com.abctech.ripoti.webapp.json.ripoti.ParentIssue;
import com.abctech.ripoti.webapp.json.ripoti.RipotiIssue;
import com.abctech.ripoti.webapp.json.ripoti.TimeSpent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JiraToRipotiService {

    private static final Logger log = LoggerFactory.getLogger(JiraToRipotiService.class);

    private static final String TIME_SPENT_UNIT = "hour";

    /**
     * Convert json from jira to ripoti.
     * @param issues
     * @return RipotiIssue
     */
    public RipotiIssue convert(Issue[] issues) {
        log.debug("Converting json from jira to ripoti");
        RipotiIssue ripotiIssue = new RipotiIssue();
        List<ParentIssue> parentIssueList = new ArrayList<>();
        int totalTimeSpent = 0;
        for(Issue pIssue : issues) {
            // Parent issue.
            if(!pIssue.getField().getIssueType().isSubTask()) {
                log.debug("Parent : {} | {}", pIssue.getField().getSummary(), pIssue.getField().getTimeSpent());
                ParentIssue parentIssue = new ParentIssue();
                parentIssue.setKey(pIssue.getKey());
                parentIssue.setSummary(pIssue.getField().getSummary());
                parentIssue.setTitle(createTitle(pIssue.getKey(), pIssue.getField().getSummary()));
                List<ChildIssue> childIssueList = new ArrayList<>();
                // Child issue.
                for(Issue cIssue : issues) {
                    if(cIssue.getField().getIssueType().isSubTask() &&
                            cIssue.getField().getParent() != null &&
                            cIssue.getField().getParent().getKey().equals(pIssue.getKey())) {
                        log.debug("-- Child : {} | {}", cIssue.getField().getSummary(), cIssue.getField().getTimeSpent());
                        ChildIssue childIssue = new ChildIssue();
                        childIssue.setKey(cIssue.getKey());
                        childIssue.setSummary(cIssue.getField().getSummary());
                        childIssue.setTitle(createTitle(cIssue.getKey(), cIssue.getField().getSummary()));
                        TimeSpent cTimeSpent = new TimeSpent();
                        cTimeSpent.setUnit(TIME_SPENT_UNIT);
                        cTimeSpent.setValue(secToHr(cIssue.getField().getTimeSpent()));
                        childIssue.setTimeSpent(cTimeSpent);
                        // Increase total time spent.
                        totalTimeSpent += cIssue.getField().getTimeSpent();
                        childIssueList.add(childIssue);
                    }
                } // End child issue.
                TimeSpent pTimeSpent = new TimeSpent();
                pTimeSpent.setUnit(TIME_SPENT_UNIT);
                pTimeSpent.setValue(secToHr(pIssue.getField().getAggregateTimeSpent()));
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

    private String createTitle(String key, String summary) {
        return "[ " + key + " ] " + summary;
    }
}
