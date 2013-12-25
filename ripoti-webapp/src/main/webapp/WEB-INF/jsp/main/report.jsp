<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
    <form:form commandName="reportBuilderForm" method="get">
        <form:select path="viewId" items="${viewMap}" />
        <form:select path="sprintId" items="${sprintMap}" />
        <input type="submit" value="Show Report" />
    </form:form>
    <div>
        ${ripotiJson}
    </div>
    <div>
        <div data-bind="foreach: parentIssues">
            <div>
                <div style="background-color: lightgray">
                    <span data-bind="text: title"></span>
                    <span data-bind="text: timeSpent.value"></span>
                    <span data-bind="click: $root.removeParentIssue">&times;</span>
                </div>
                <div data-bind="foreach: childIssues">
                    <div>
                        <span data-bind="text: title"></span>
                        <input type="text" data-bind="value: timeSpent.value, valueUpdate: 'afterkeydown'" />
                        <span data-bind="click: $root.removeChildIssue.bind($data, $parentContext.$index())">&times;</span>
                    </div>
                </div>
            </div>
        </div>
        <div style="background-color: darkgrey">
            <span>Total</span>
            <span data-bind="text: timeSpent.value"></span>
        </div>
    </div>
</div>
<div>

</div>
<script type="text/javascript" src="<spring:url value='/assets/js/knockout/knockout-3.0.0.js' />"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#viewId").change(function() {
            $.get("<spring:url value='/rest/a/rapidviews/' />" + $(this).val() + "/sprints", function(data) {
                $("#sprintId option").remove();
                for(var i=0; i<data.length; i++) {
                    $("#sprintId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
            })
        });
    });

    // Binding Ripoti json with html
    var ripotiJson = ${ripotiJson};

    function createParentIssues() {
        var parentIssues = new Array();
        for(var i=0; i<ripotiJson.parentIssues.length; i++) {
            var parentIssue = ripotiJson.parentIssues[i];
            var childIssues = new Array();
            for(var j=0; j<parentIssue.childIssues.length; j++) {
                var childIssue = parentIssue.childIssues[j];
                childIssues.push(new ChildIssue(childIssue.title, childIssue.timeSpent.value));
            }
            parentIssues.push(new ParentIssue(parentIssue.title, ko.observableArray(childIssues)))
        }
        return parentIssues;
    }

    function ParentIssue(title, childIssues) {
        var self = this;
        self.title = title;
        self.timeSpent = new Object();
        self.timeSpent.value = ko.computed(function() {
            var myChildIssues = childIssues();
            var timeSpentValue = 0;
            for(var i=0; i<myChildIssues.length; i++) {
                timeSpentValue += parseFloat(myChildIssues[i].timeSpent.value());
            }
            return timeSpentValue;
        }, childIssues);
        self.childIssues = childIssues;
    }

    function ChildIssue(title, timeSpentValue) {
        var self = this;
        self.title = title;
        self.timeSpent = new Object();
        self.timeSpent.value = ko.observable(timeSpentValue);
    }

    function RipotiIssue() {
        var self = this;
        self.parentIssues = ko.observableArray(createParentIssues());
        self.timeSpent = new Object();
        self.timeSpent.value = ko.computed(function() {
            var myParentIssues = self.parentIssues();
            var timeSpentValue = 0;
            for(var i=0; i< myParentIssues.length; i++) {
                timeSpentValue += parseFloat(myParentIssues[i].timeSpent.value());
            }
            return timeSpentValue;
        }, self);

        self.removeParentIssue = function(parentIssue) {
            self.parentIssues.remove(parentIssue);
        }

        self.removeChildIssue = function(parentIssueIndex, childIssue) {
            self.parentIssues()[parentIssueIndex].childIssues.remove(childIssue);
            if(self.parentIssues()[parentIssueIndex].childIssues().length == 0) {
                self.parentIssues.splice(parentIssueIndex, 1);
            }
        }
    }

    var ripotiIssue = new RipotiIssue();
    ko.applyBindings(ripotiIssue);
</script>