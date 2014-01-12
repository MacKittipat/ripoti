<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
    .spinner {
        -webkit-animation: scaleout 0.7s infinite ease-in-out;
        animation: scaleout 0.7s infinite ease-in-out;
    }
    @-webkit-keyframes scaleout {
        0% { -webkit-transform: scale(0.0) }
        100% {
            -webkit-transform: scale(1.0);
            opacity: 0;
        }
    }
    @keyframes scaleout {
        0% {
            transform: scale(0.0);
            -webkit-transform: scale(0.0);
        } 100% {
              transform: scale(1.0);
              -webkit-transform: scale(1.0);
              opacity: 0;
          }
    }
</style>

<header>
    <div class="grid grid-pad">
        <div class="col-1-1">
            <img src="<spring:url value='/assets/image/amedia-logo-transparent.png' />" alt="Amedia Logo"/>
            <span class="project-name">ripoti</span>
        </div>
    </div>
</header>

<section class="msg-box">
    <article>
        <div class="grid grid-pad">
            <div class="col-1-1" style="text-align: center;">
                <span class="title"><strong style="font-size: 36px; color:#205081;">ripoti</strong> will generate <strong>Time Spend Report</strong> for you. Just follow the steps :)</span>
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
                        <div  class="custom-select" style="position: relative;">
                            <form:select path="viewId" items="${viewMap}" />
                        </div>
                    </div>
                </div>
                <div class="col-4-12">
                    <div class="col-3-12">
                        <div id="loadingSprint" class="num-round">
                            2
                        </div>
                    </div>
                    <div class="col-9-12" style="padding-top: 10px;">
                        <div  class="custom-select" style="position: relative;">
                            <form:select path="sprintId" items="${sprintMap}" />
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
                            <span style="float: left; width: 50px;">Story: </span>
                            <div class="edit-box">
                                <span class="text_label" data-bind="text: title"></span>
                                <div class="edit edit-btn"></div>
                                <input type="text" class="editable" data-bind="value: title" style="width: 100%;" />
                            </div>
                            <img src="<spring:url value='/assets/image/icon-del.gif' />" alt="Delete Icon" class="del-row" data-bind="click: $root.removeParentIssue"/>
                        </div>
                        <div class="task-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th data-bind="if: childIssues().length > 0">Task</th>
                                        <th data-bind="if: childIssues().length > 0">Detail</th>
                                        <th data-bind="if: childIssues().length > 0">Time (hr)</th>
                                        <th data-bind="if: childIssues().length > 0">Delete</th>
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
                                        <td>
                                            <div class="edit-box">
                                                <span class="text_label" data-bind="text: timeSpent.value"></span>
                                                <div class="edit edit-btn"></div>
                                                <input type="text" class="editable" data-bind="value: timeSpent.value, valueUpdate: 'afterkeydown'" />
                                            </div>
                                        </td>
                                        <td><img src="<spring:url value='/assets/image/icon-del.gif' />" alt="Delete Icon" class="del-row" data-bind="click: $root.removeChildIssue.bind($data, $parentContext.$index())"/></td>
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
                        <p>Download:</p>
                        <div class="col-1-1">
                            <input type="hidden" id="ripotiIssueJson" name="ripotiIssueJson" />
                            <input type="submit" value="PDF" />
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <a href="#go-down" class="go-down">Jump to Download</a>
</c:if>

<c:if test="${ripotiJson == null}">
<p style="text-align: center; font-size: 24px; color: #c0c0c0; margin-top: 200px;">Please select Board and Sprint.</p>
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
            $("#loadingSprint").addClass("spinner");
            $.get("<spring:url value='/rest/a/rapidviews/' />" + $(this).val() + "/sprints", function(data) {
                $("#sprintId option").remove();
                for(var i=0; i<data.length; i++) {
                    $("#sprintId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
                $("#loadingSprint").removeClass("spinner");
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
                childIssues.push(new ChildIssue(childIssue));
            }
            parentIssues.push(new ParentIssue(
                    parentIssue,
                    ko.observableArray(childIssues)
            ));
        }
        return parentIssues;
    }

    function ParentIssue(parentIssue, childIssues) {
        var self = this;
        self.key = parentIssue.key;
        self.summary = parentIssue.summary;
        self.title = parentIssue.title;
        self.timeSpent = new Object();
        var diffTimeSpentParentChild = null;
        self.timeSpent.value = ko.computed(function() {
            var myChildIssues = childIssues();
            var timeSpentValue = 0;
            for(var i=0; i<myChildIssues.length; i++) {
                timeSpentValue += parseFloat(myChildIssues[i].timeSpent.value());
            }
            if(diffTimeSpentParentChild == null) {
                diffTimeSpentParentChild = parentIssue.timeSpent.value - timeSpentValue;
            }
            return formatTimeSpent(timeSpentValue + diffTimeSpentParentChild);
        }, childIssues);
        self.childIssues = childIssues;
    }

    function ChildIssue(childIssue) {
        var self = this;
        self.key = childIssue.key;
        self.summary = childIssue.summary;
        self.title = childIssue.title;
        self.timeSpent = new Object();
        self.timeSpent.value = ko.observable(formatTimeSpent(childIssue.timeSpent.value));
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

    function formatTimeSpent(timeSpent) {
        var timeSpentStr = timeSpent + "";
        if (timeSpentStr.indexOf('.') >= 0) {
            return timeSpent.toFixed(2);
        }
        return timeSpent;
    }
    </c:if>
</script>