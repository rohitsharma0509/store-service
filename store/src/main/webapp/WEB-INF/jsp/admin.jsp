<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="Admin" text="Admin" /></li>
</ol>
<div class="row">
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Users" text="Users" /></b></span></th></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Users" text="Users" /></a></td></tr>
			<!-- <tr><td><a href="${contextPath}<%=RequestUrls.REGISTRATION_CONFIG %>"><spring:message code="New User Settings" text="New User Settings" /></a></td></tr> -->
		</table>
	</div>
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Access Control" text="Access Control" /></b></span></th></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Roles" text="Roles" /></a></td></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.PRIVILEGES %>"><spring:message code="Privileges" text="Privileges" /></a></td></tr>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-sm-6">
		<table class="table">
      <tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Configuration" text="Configuration" /></b></span></th></tr>
      <tr><td><a href="${contextPath}<%=RequestUrls.SETTINGS %>"><spring:message code="Manage Settings" text="Manage Settings" /></a></td></tr>
    </table>
	</div>
	<div class="col-sm-6">
		<table class="table">
      <tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Manage Categories & Products" text="Manage Categories & Products" /></b></span></th></tr>
      <tr><td><a href="${contextPath}<%=RequestUrls.CATEGORIES %>"><spring:message code="Category" text="Category" /></a></td></tr>
      <tr><td><a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Products" text="Products" /></a></td></tr>
    </table>
	</div>
</div>
<div class="row">
  <div class="col-sm-6">
    <table class="table">
      <tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Email" text="Email" /></b></span></th></tr>
      <tr><td><a href="${contextPath}/getEmailAccount"><spring:message code="Add Email Account" text="Add Email Account" /></a></td></tr>
      <tr><td><a href="${contextPath}<%=RequestUrls.EMAIL_TEMPLATES %>"><spring:message code="Manage Email Template" text="Manage Email Template" /></a></td></tr>
      <tr><td><a href="${contextPath}/composeEmail"><spring:message code="Send Email" text="Send Email" /></a></td></tr>
    </table>
  </div>
  <div class="col-sm-6">
    <table class="table">
      <tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Profit & Loss" text="Profit & Loss" /></b></span></th></tr>
      <tr><td><a href="${contextPath}<%=RequestUrls.PROFIT_LOSS %>"><spring:message code="Profit & Loss" text="Profit & Loss" /></a></td></tr>
    </table>
  </div>
</div>
<div class="row" style="height: 100px;"></div>