<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
	<form:form method="POST" modelAttribute="<%=FieldNames.USER_DTO %>" class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="Create your account" text="Create your account" /></h2>
        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="First Name" text="First Name" var="label"/>
                <form:input type="text" path="<%=FieldNames.FIRST_NAME %>" class="form-control" placeholder="${label}" autofocus="true"></form:input>
                <form:errors path="<%=FieldNames.FIRST_NAME %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Last Name" text="Last Name" var="label"/>
                <form:input type="text" path="<%=FieldNames.LAST_NAME %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.LAST_NAME %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Username" text="Username" var="label"/>
                <form:input type="text" path="<%=FieldNames.USERNAME %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.USERNAME %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Password" text="Password" var="label"/>
                <form:input type="password" path="<%=FieldNames.PASSWORD %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.PASSWORD %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Confirm Password" text="Confirm Password" var="label"/>
                <form:input type="password" path="<%=FieldNames.PASSWORD_CONFIRM %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.PASSWORD_CONFIRM %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Email" text="Email" var="label" />
                <form:input type="email" path="<%=FieldNames.EMAIL %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.EMAIL %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="mobile">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="Mobile" text="Mobile" var="label" />
                <form:input type="text" path="<%=FieldNames.MOBILE %>" class="form-control" placeholder="${label}"></form:input>
                <form:errors path="<%=FieldNames.MOBILE %>" class="help-inline"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="Submit" text="Submit" /></button>
        <a class="btn btn-lg btn-primary" href="${contextPath}<%=RequestUrls.LOGIN %>" style="margin-top: 10px;width:100%;"><spring:message code="Back" text="Back" /></a>
    </form:form>

</div>