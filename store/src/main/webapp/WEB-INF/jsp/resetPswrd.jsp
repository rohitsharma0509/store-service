<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
  <form method="POST" action="${contextPath}<%=RequestUrls.CHANGE_CRED %>" class="form-signin">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <h2 class="form-signin-heading"><spring:message code="Change Your Password" text="Change Your Password" /></h2>
    <c:if test="${not empty error}">
      <div class="alert alert-danger" >${error}</div>
    </c:if>
    <div class="form-group">
      <spring:message code="Password" text="Password" var="label" />
      <input type="hidden" name="<%=FieldNames.USER_ID %>" value="${userId}">
      <input type="password" name="<%=FieldNames.PSWRD %>" class="form-control" placeholder="${label}" required />
    </div>
    <div class="form-group">
      <spring:message code="Confirm Password" text="Confirm Password" var="label" />
      <input type="password" name="<%=FieldNames.CONFIRM_PSWRD %>" class="form-control" placeholder="${label}" required />
    </div>
    <button class="btn btn-lg btn-primary" type="submit"><spring:message code="Change Password" text="Change Password" /></button>
  </form>
</div>