<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<header>

    <div class="grid grid-pad">
        <div class="col-1-1">
        <img src="/assets/image/amedia-logo-transparent.png" alt="Amedia Logo"/><span class="project-name">ripoti</span>
        </div>
    </div>

</header>

<section class="msg-box">
    <article>
        <div class="grid grid-pad">
            <div class="col-1-1" style="text-align: center; padding-top: 10px;">
                <span class="title"><strong>ripoti</strong> will generate <strong>Time Spend Report</strong> for you. Just follow the steps :)</span>
            </div>
        </div>

        <form:form commandName="reportBuilderForm" method="get">
        <div class="grid grid-pad">
            <div class="col-4-12">

                <div class="col-3-12">
                    <div class="num-round">
                    1
                    </div>
                </div>
                <div class="col-9-12" style="padding-top: 10px;">
                    <div  style="position: relative;">
                    <form:select path="viewId" items="${viewMap}" />
                    <div class="arrow-select">&nbsp;</div>
                    </div>
                </div>

            </div>

            <div class="col-4-12">

                <div class="col-3-12">
                    <div class="num-round">
                    2
                    </div>
                </div>
                <div class="col-9-12" style="padding-top: 10px;">

                    <div  style="position: relative;">
                    <form:select path="sprintId" items="${sprintMap}" />
                    <div class="arrow-select">&nbsp;</div>
                    </div>
                </div>

            </div>

            <div class="col-4-12">

                <div class="col-3-12">
                <div class="num-round">
                3
                </div>
                </div>
                <div class="col-9-12">
                    <input type="submit" value="Show Report" style="width: 100%;" />
                </div>

            </div>

        </div>
        </form:form>
    </article>
</section>

<c:if test="${ripotiJson != null}">
<section>
     <div class="grid grid-pad">
        <div class="col-1-2">

            <span>Board: </span><span class="vip-color">${viewName}</span>
        </div>
        <div class="col-1-2" style="text-align: right;">

            <span>Sprint: </span><span class="vip-color">${sprintName}</span>
        </div>
    </div>
    <div  data-bind="foreach: parentIssues">
    <div class="grid grid-pad">
        <div class="col-1-1">
                <div class="story-box">
                    <div class="story-name">
                    <img src="/assets/image/icon-del.gif" alt="Delete Icon" class="del-row" data-bind="click: $root.removeParentIssue" style="margin-right: 10px;"/><span>Story: </span>
                        <span class="vip-color" data-bind="text: key"></span>
                        <span class="vip-color" data-bind="text: summary"></span><br/>
                    </div>
                    <div class="task-table">
                    <table>
                        <thead>
                            <tr>

                                <th>Task</th>
                                <th>Detail</th>
                                <th>Time</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: childIssues">
                            <tr>
                                <td><span data-bind="text: key"></span></td>
                                <td>
                                    <div class="edit-box">
                                        <span class="text_label" data-bind="text: summary"></span>
                                        <div class="edit edit-btn"></div>
                                        <input type="text" class="editable" data-bind="value: summary" />
                                    </div>
                                </td>
                                <td><input type="text" data-bind="value: timeSpent.value, valueUpdate: 'afterkeydown'" /></td>
                                <td><img src="/assets/image/icon-del.gif" alt="Delete Icon" class="del-row" data-bind="click: $root.removeChildIssue.bind($data, $parentContext.$index())"/></td>

                            </tr>

                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">Total</td>
                                <td colspan="2"><span data-bind="text: timeSpent.value"></span></td>
                            </tr>
                        </tfoot>
                    </table>
                    </div>
                </div>


        </div>
    </div>

    </div>

    <div class="grid grid-pad">
        <div class="col-1-3">&nbsp;
        </div>
        <div class="col-1-3">&nbsp;
        </div>
        <div class="col-1-3">
            <div class="total-box">
                <span>Grand Total</span>
                <span data-bind="text: timeSpent.value"></span>
            </div>
        </div>
    </div>

</section>
<a name="go-down"></a>

<section>
<form id="frmExportPdf" action="<spring:url value='/export/pdf' />" onsubmit="exportFile('frmExportPdf')" method="post">
     <div class="grid grid-pad">
            <div class="col-1-3">&nbsp;
            </div>
            <div class="col-1-3">&nbsp;
            </div>
            <div class="col-1-3">
                <div class="convert-box">
                    <p>Convert file to:</p>
                        <div class="col-3">
                            <input type="hidden" id="ripotiIssueJson" name="ripotiIssueJson" />
                            <input type="submit" value="PDF" />
                        </div>
                        <div class="col-3">
                            <input type="submit" class="disable-btn" value="Excel"/>
                        </div>
                        <div class="col-3">
                            <input type="submit" class="disable-btn" value="CSV"/>
                        </div>

                </div>
            </div>
    </div>
</form>
</section>
<a href="#go-down" class="go-down">Convert and Download</a>

</c:if>

<footer>
 <!--
    <div class="grid grid-pad">
        <div class="col-1-1">
        Created by Amedia/Bangkok
        </div>
    </div>
-->
</footer>


<script type="text/javascript" src="<spring:url value='/assets/js/knockout/knockout-3.0.0.js' />"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#viewId").change(function() {
            if($(this).val() == 0) {
                $("#sprintId option").remove();
                return;
            }
            $.get("<spring:url value='/rest/a/rapidviews/' />" + $(this).val() + "/sprints", function(data) {
                $("#sprintId option").remove();
                for(var i=0; i<data.length; i++) {
                    $("#sprintId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
            })
        });
    });

    <c:if test="${ripotiJson != null}">
    // Binding Ripoti json with html
    var ripotiJson = ${ripotiJson};

    function createParentIssues() {
        var parentIssues = new Array();
        for(var i=0; i<ripotiJson.parentIssues.length; i++) {
            var parentIssue = ripotiJson.parentIssues[i];
            var childIssues = new Array();
            for(var j=0; j<parentIssue.childIssues.length; j++) {
                var childIssue = parentIssue.childIssues[j];
                childIssues.push(new ChildIssue(
                        childIssue.key,
                        childIssue.summary,
                        childIssue.title,
                        childIssue.timeSpent.value
                ));
            }
            parentIssues.push(new ParentIssue(
                    parentIssue.key,
                    parentIssue.summary,
                    parentIssue.title,
                    ko.observableArray(childIssues)
            ));
        }
        return parentIssues;
    }

    function ParentIssue(key, summary, title, childIssues) {
        var self = this;
        self.key = key;
        self.summary = summary;
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

    function ChildIssue(key, summary, title, timeSpentValue) {
        var self = this;
        self.key = key;
        self.summary = summary;
        self.title = title;
        self.timeSpent = new Object();
        self.timeSpent.value = ko.observable(timeSpentValue);
    }

    function RipotiIssue() {
        var self = this;
        self.viewName = "${viewName}";
        self.sprintName = "${sprintName}";
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

    function exportFile(frmId) {
        var ripotiIssueJson = ko.toJSON(ripotiIssue)
        $("#" + frmId + " #ripotiIssueJson").val(ripotiIssueJson);
    }
    </c:if>
</script>