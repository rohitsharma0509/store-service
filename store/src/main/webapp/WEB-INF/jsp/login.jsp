<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="container">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card">
				<div class="card-header">Login Now</div>
				<div class="card-body">
					<form method="POST" action="${contextPath}/login">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<c:if test="${not empty message}">
							<div id="logoutMsg" class="alert alert-success hide">${message}</div>
						</c:if>
						<div class="form-group">
							<label for="username" class="control-label">Username</label>
							<input type="text" class="form-control" id="username" name="username" value="" required placeholder="Username">
							<span class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Password</label>
							<input type="password" class="form-control" id="password" name="password" value="" required placeholder="Password">
							<span class="help-block"></span>
						</div>
						<c:if test="${not empty error}">
							<div id="loginErrorMsg" class="alert alert-danger hide">${error}</div>
						</c:if>
						<div class="checkbox">
							<label><input type="checkbox" name="rememberMe" id="rememberMe"> Remember login</label>
						</div>
						<button type="submit" class="btn btn-success btn-block">Signin</button>
						<a href="${contextPath}<%=RequestUrls.FORGET_PASSWORD %>" class="pull-left">Forget Password?</label>
            <a href="${contextPath}<%=RequestUrls.REGISTRATION %>" class="pull-right need-help">SignUp</a>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>