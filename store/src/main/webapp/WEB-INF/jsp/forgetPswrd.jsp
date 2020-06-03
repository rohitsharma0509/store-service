<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<form method="GET" action="${contextPath}<%=RequestUrls.SEND_RESET_PSWRD_LINK %>" class="form-signin">
		<h2 class="form-signin-heading"><spring:message code="Enter Your Email" text="Enter Your Email" /></h2>
		<div class="form-group">
		  <spring:message code="Email" text="Email" var="label" />
			<input type="email" name="email" class="form-control" placeholder="${label}" required />
			<c:if test="${not empty error}">
			    <span class="help-inline has-error">${error}</span>
      </c:if>
		</div>
		<div>
			<a class="btn btn-lg btn-primary" href="${contextPath}<%=RequestUrls.LOGIN %>" style="margin-top: 10px;"><spring:message code="Back" text="Back" /></a>
			<button class="btn btn-lg btn-primary" type="submit"><spring:message code="Continue" text="Continue" /></button>
		</div>
	</form>
</div>