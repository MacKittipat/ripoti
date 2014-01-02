<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="login-bg">
    <div class="login-box">

        <div class="login-logo">
            <img src="<spring:url value='/assets/image/amedia-logo-transparent.png' />" alt="Amedia Logo"/>
        </div>
        <header>
            <span>Login to </span>
            <span class="project-name">ripoti</span>
        </header>
        <form:form commandName="authForm">
            <div>
                <div>
                    <form:input path="username" placeholder="Username"/>
                </div>
                <div>
                    <form:password path="password" placeholder="Password"/>
                </div>
                <div>
                    <input type="submit" value="Login" />
                </div>
            </div>
        </form:form>
        <c:if test="${errorMsg != null}">
            <div class="error-msg">${errorMsg}</div>
        </c:if>
    </div>
</div>

