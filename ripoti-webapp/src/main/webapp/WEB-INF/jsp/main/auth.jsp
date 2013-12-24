<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
    <form:form commandName="authForm">
        <div>
            ${errorMsg}
        </div>
        <div>
            Username : <form:input path="username" />
        </div>
        <div>
            Password : <form:password path="password" />
        </div>
        <div>
            <input type="submit" value="Login" />
        </div>
    </form:form>
</div>