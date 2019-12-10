<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<form method="GET" action="${contextPath}<%=RequestUrls.CHANGE_PASSWORD %>" class="form-signin">
		<h2 class="form-signin-heading"><spring:message code="Enter Your Email" text="Enter Your Email" /></h2>
		<div class="form-group">
		  <spring:message code="Email" text="Email" var="label" />
			<input type="email" name="email" class="form-control" placeholder="${label}" required />
		</div>
		<button class="btn btn-lg btn-primary" type="button" onclick="window.history.back()"><spring:message code="Back" text="Back" /></button>
		<button class="btn btn-lg btn-primary" type="submit"><spring:message code="Continue" text="Continue" /></button>
	</form>
</div>