<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="My Account" text="My Account" /></li>
</ol>
<div class="row">
	<div class="col-sm-12">
		<a class="float-right" href="${contextPath}<%=RequestUrls.EDIT_PROFILE %>"><spring:message code="Edit Profile" text="Edit Profile" /></a>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div>
			<h5>${user.firstName} ${user.lastName}</h5>
			<security:authorize access="hasAuthority('ADMIN')">
			   <p><spring:message code="Admin User" text="Admin User" /></p>
			</security:authorize>
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><spring:message code="About" text="About" /></a></li>
				<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><spring:message code="Contact" text="Contact" /></a></li>
			</ul>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="tab-content profile-tab" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
			  <div class="row" style="height:50px;"></div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="User Id" text="User Id" /></label></div>
					<div class="col-md-9"><p>${user.username }</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Name" text="Name" /></label></div>
					<div class="col-md-9"><p>${user.firstName} ${user.lastName}</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Language" text="Language" /></label></div>
					<div class="col-md-9"><p>${user.language}</p></div>
				</div>
			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
				<div class="row" style="height:50px;"></div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Email" text="Email" /></label></div>
					<div class="col-md-9"><p>${user.email}</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Mobile" text="Mobile" /></label></div>
					<div class="col-md-9"><p>${user.mobile}</p></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="height: 100px;"></div>