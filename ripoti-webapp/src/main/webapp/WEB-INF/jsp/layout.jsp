<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ripoti</title>
        <script type="text/javascript" src="<spring:url value='/assets/js/jquery/jquery-2.0.3.min.js' />"></script>
        <link rel="stylesheet" href="<spring:url value='/assets/css/reset.css' />">
        <link rel="stylesheet" href="<spring:url value='/assets/css/style.css' />">
        <link rel="stylesheet" href="<spring:url value='/assets/css/simplegrid.css' />" />
        <style type="text/css" media="screen">
        /*<![CDATA[*/@import '<spring:url value="/assets/css/stylesheet.css" />';/*]]>*/
        </style>
    </head>
    <body>
        <jsp:include page="${pageContent}.jsp" />
    </body>
</html>
