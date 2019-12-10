<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
  <form method="POST" action="${contextPath}<%=RequestUrls.CHANGE_PASSWORD %>" class="form-signin">
    <h2 class="form-signin-heading"><spring:message code="Change Your Password" text="Change Your Password" /></h2>
    <div class="form-group">
      <spring:message code="Password" text="Password" var="label" />
      <input type="password" name="<%=FieldNames.PASSWORD %>" class="form-control" placeholder="${label}" required />
    </div>
    <div class="form-group">
      <spring:message code="Confirm Password" text="Confirm Password" var="label" />
      <input type="password" name="<%=FieldNames.PASSWORD_CONFIRM %>" class="form-control" placeholder="${label}" required />
    </div>
    <button class="btn btn-lg btn-primary" type="button" onclick="window.history.back()"><spring:message code="Back" text="Back" /></button>
    <button class="btn btn-lg btn-primary" type="submit"><spring:message code="Change Password" text="Change Password" /></button>
  </form>
</div>