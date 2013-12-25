<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
    <form:form commandName="reportBuilderForm" method="get">
        <form:select path="viewId" items="${viewMap}" />
        <form:select path="sprintId" items="${sprintMap}" />
        <input type="submit" value="Show Report" />
    </form:form>
    <div>
        ${json}
    </div>
</div>
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
</script>