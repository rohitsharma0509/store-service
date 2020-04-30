<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><i class="fa fa-user-cog breadcrumb-icon"></i>&nbsp;&nbsp;<spring:message code="Admin" text="Admin" /></li>
</ol>
<div class="card">
  <div class="card-body">
		<div class="row">
			<div class="col-sm-6">
				<table class="table">
					<tr class="info"><th><b><spring:message code="User Management" text="User Management" /></b></th></tr>
					<tr><td><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Users" text="Users" /></a></td></tr>
				</table>
			</div>
			<div class="col-sm-6">
				<table class="table">
					<tr class="info"><th><b><spring:message code="Access Control" text="Access Control" /></b></th></tr>
					<tr><td><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Manage Roles" text="Manage Roles" /></a></td></tr>
					<tr><td><a href="${contextPath}<%=RequestUrls.PRIVILEGES %>"><spring:message code="Manage Privileges" text="Manage Privileges" /></a></td></tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<table class="table">
		      <tr class="info"><th><b><spring:message code="Configuration" text="Configuration" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.SETTINGS %>"><spring:message code="Manage Settings" text="Manage Settings" /></a></td></tr>
		    </table>
			</div>
			<div class="col-sm-6">
				<table class="table">
		      <tr class="info"><th><b><spring:message code="Categories & Products" text="Categories & Products" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.CATEGORIES %>"><spring:message code="Manage Category" text="Manage Category" /></a></td></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Manage Products" text="Manage Products" /></a></td></tr>
		    </table>
			</div>
		</div>
		<div class="row">
		  <div class="col-sm-6">
		    <table class="table">
		      <tr class="info"><th><b><spring:message code="Templates" text="Templates" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.TEMPLATES %>"><spring:message code="Manage Template" text="Manage Template" /></a></td></tr>
		    </table>
		  </div>
		  <div class="col-sm-6">
		    <table class="table">
		      <tr class="info"><th><b><spring:message code="Profit & Loss" text="Profit & Loss" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.PROFIT_LOSS %>"><spring:message code="Profit & Loss" text="Profit & Loss" /></a></td></tr>
		    </table>
		  </div>
		</div>
		<div class="row">
		  <div class="col-sm-6">
		    <table class="table">
		      <tr class="info"><th><b><spring:message code="Mailing" text="Mailing" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.COMPOSE_EMAIL %>"><spring:message code="Send Email" text="Send Email" /></a></td></tr>
		    </table>
		  </div>
		  <div class="col-sm-6">
		    <table class="table">
		      <tr class="info"><th><b><spring:message code="Support" text="Suppport" /></b></th></tr>
		      <tr><td><a href="${contextPath}<%=RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY %>"><spring:message code="Support Ticket Assignment strategy" text="Support Ticket Assignment strategy" /></a></td></tr>
		    </table>
		  </div>
		</div>
	</div>
</div>
<div class="row" style="height: 100px;"></div>