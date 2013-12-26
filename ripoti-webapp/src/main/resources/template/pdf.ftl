<!DOCTYPE html>
<html>
    <body>
        <#list ripotiIssue.parentIssues as parentIssue>
            <div style="background-color: gray;">
                <span>${parentIssue.title}</span>
                <span>${parentIssue.timeSpent.value}</span>
            </div>
            <div>
                <#list parentIssue.childIssues as childIssue>
                    <div>
                        <span>${childIssue.title}</span>
                        <span>${childIssue.timeSpent.value}</span>
                    </div>
                </#list>
            </div>
        </#list>
    <div style="background-color: cornflowerblue;">
        <span>Total</span>
        <span>${ripotiIssue.timeSpent.value}</span>
    </div>
    </body>
</html>
