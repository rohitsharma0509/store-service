<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
	<form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="firstName" path="firstName" class="form-control"
                            placeholder="First Name"></form:input>
                <form:errors path="firstName" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="lastName" path="lastName" class="form-control"
                            placeholder="Last Name"></form:input>
                <form:errors path="lastName" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="form-control"
                            placeholder="Email"></form:input>
                <form:errors path="email" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="mobile">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="mobile" path="mobile" class="form-control"
                            placeholder="Mobile"></form:input>
                <form:errors path="mobile" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="window.history.back()">Back</button>
    </form:form>

</div>