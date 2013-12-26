<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="login-bg">
    <div class="login-box">
        <div class="login-logo">
            <img src="/assets/image/amedia-logo-transparent.png" alt="Amedia Logo"/>
        </div>
        <header>
            <span>Login to </span>
            <span class="project-name">ripoti</span>
        </header>
        <div>
            <form:form commandName="authForm">
                <div>
                    ${errorMsg}
                </div>
                <div>
                    <form:input path="username" placeholder="Username"/>
                </div>
                <div>
                    <form:password path="password" placeholder="Password"/>
                </div>
                <div>
                    <input type="submit" value="Login" />
                </div>
            </form:form>
        </div>
    </div>
</div>

