<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ripoti</title>
        <script type="text/javascript" src="<spring:url value='/assets/js/jquery/jquery-2.0.3.min.js' />"></script>
    </head>
    <body>
        <jsp:include page="${pageContent}.jsp" />
    </body>
</html>
