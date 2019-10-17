<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<form method="GET" action="${contextPath}<%=RequestUrls.CHANGE_PASSWORD %>" class="form-signin">
		<h2 class="form-signin-heading">Enter Your Email</h2>
		<div class="form-group">
			<input type="email" name="email" class="form-control" placeholder="Email" required />
		</div>
		<button class="btn btn-lg btn-primary" type="button" onclick="window.history.back()">Back</button>
		<button class="btn btn-lg btn-primary" type="submit">Continue</button>
	</form>
</div>