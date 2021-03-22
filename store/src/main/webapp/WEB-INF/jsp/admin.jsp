<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<style>
.fixed-div {
    position: fixed;
    z-index: 1;
    background: rgba(80,135,199,.9);
    color: #fff;
    padding: 10px;
    margin-top: 5px;
    margin-left: -50px;
}
.fixed-div > a {
  color: white;
}
</style>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><i class="fa fa-user-cog breadcrumb-icon"></i>&nbsp;&nbsp;<spring:message code="Admin" text="Admin" /></li>
  <li class="ml-auto">
    <div class="fixed-div">
      <a href="#top"><spring:message code="Back to Top" text="Back to Top" /></a>
    </div>
  </li>
</ol>
<div class="row" id="top">
  <div class="col-sm-4">
	   <ul class="list-group">
			  <li class="list-group-item"><a href="#users"><spring:message code="Users" text="Users" /></a></li>
			  <li class="list-group-item"><a href="#settings"><spring:message code="Settings" text="Settings" /></a></li>
			  <security:authorize access="hasAuthority('CAN_VIEW_PRODUCT_MODULE')">
				  <li class="list-group-item"><a href="#products"><spring:message code="Categories & Products" text="Categories & Products" /></a></li>
				  <li class="list-group-item"><a href="#profitLoss"><spring:message code="Profit & Loss" text="Profit & Loss" /></a></li>
			  </security:authorize>
			  <li class="list-group-item"><a href="#templates"><spring:message code="Templates" text="Templates" /></a></li>
			  <li class="list-group-item"><a href="#mailing"><spring:message code="Mailing" text="Mailing" /></a></li>
			  <security:authorize access="hasAuthority('CAN_VIEW_SUPPORT_MODULE')">
			   <li class="list-group-item"><a href="#support"><spring:message code="Support" text="Support" /></a></li>
			  </security:authorize>
	   </ul>
  </div>
  <div class="col-sm-8">
     <ul class="list-group">
       <li class="list-group-item" id="users">
          <div>
	          <div class="d-flex w-100 justify-content-between">
	            <h4><spring:message code="Users" text="Users" /></h4>
	          </div>
	          <p class=""><spring:message code="Manage your users here, you can view and update user information, deactivate them, can change their roles and privileges" />.</p>
          </div>
          <div class="row">
            <div class="col-sm-4">
              <a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Manage Users" text="Manage Users" /></a>
              <p><spring:message code="View/Modify users information" />.</p>
            </div>
            <div class="col-sm-4">
              <a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Manage Roles" text="Manage Roles" /></a>
              <p><spring:message code="Define user roles and associate privileges with them" />.</p>
            </div>
            <div class="col-sm-4">
              <a href="${contextPath}<%=RequestUrls.PRIVILEGES %>"><spring:message code="Manage Privileges" text="Manage Privileges" /></a>
              <p><spring:message code="Add/update/delete role privileges" />.</p>
            </div>
          </div>
       </li>
       <li class="list-group-item" id="settings">
          <div>
	          <div class="d-flex w-100 justify-content-between">
	            <h4><spring:message code="Settings" text="Settings" /></h4>
	          </div>
	          <p class=""><spring:message code="Configure your system settings" /></p>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <a href="${contextPath}<%=RequestUrls.SETTINGS %>"><spring:message code="Manage Settings" text="Manage Settings" /></a>
              <p><spring:message code="Add/update/delete system settings" />.</p>
            </div>
          </div>
       </li>
       <security:authorize access="hasAuthority('CAN_VIEW_SUPPORT_MODULE')">
	       <li class="list-group-item" id="products">
	          <div>
		          <div class="d-flex w-100 justify-content-between">
		            <h4><spring:message code="Categories & Products" text="Categories & Products" /></h4>
		          </div>
		          <p class=""><spring:message code="Configure product categories and products" /></p>
	          </div>
	          <div class="row">
	            <div class="col-sm-4">
	              <a href="${contextPath}<%=RequestUrls.INVENTORY %>"><spring:message code="Manage Inventory" text="Manage Inventory" /></a>
	              <p class=""><spring:message code="Manage Inventory" text="Manage Inventory" /></p>
	            </div>
	            <div class="col-sm-4">
	              <a href="${contextPath}<%=RequestUrls.CATEGORIES %>"><spring:message code="Manage Category" text="Manage Category" /></a>
	              <p class=""><spring:message code="Add/modify/delete product categories" /></p>
	            </div>
	            <div class="col-sm-4">
	              <a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Manage Products" text="Manage Products" /></a>
	              <p class=""><spring:message code="Add/modify/delete products" /></p>
	            </div>
	          </div>
	       </li>
	       <li class="list-group-item" id="profitLoss">
	          <div>
		          <div class="d-flex w-100 justify-content-between">
		            <h4><spring:message code="Profit & Loss" text="Profit & Loss" /></h4>
		          </div>
		          <p class=""><spring:message code="View daily/monthly/quarterly and yearly profit or loss" />.</p>
	          </div>
	          <div class="row">
	            <div class="col-sm-12">
	              <a href="${contextPath}<%=RequestUrls.PROFIT_LOSS %>"><spring:message code="Profit & Loss" text="Profit & Loss" /></a>
	              <p class=""><spring:message code="View daily/monthly/quarterly and yearly profit or loss" />.</p>
	            </div>
	          </div>
	       </li>
	     </security:authorize>
       <li class="list-group-item" id="templates">
          <div>
	          <div class="d-flex w-100 justify-content-between">
	            <h4><spring:message code="Templates" text="Templates" /></h4>
	          </div>
	          <p class=""><spring:message code="Manage email templates" />.</p>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <a href="${contextPath}<%=RequestUrls.TEMPLATES %>"><spring:message code="Manage Template" text="Manage Template" /></a>
              <p class=""><spring:message code="Manage email templates" />.</p>
            </div>
          </div>
       </li>
       <li class="list-group-item" id="mailing">
          <div>
	          <div class="d-flex w-100 justify-content-between">
	            <h4><spring:message code="Mailing" text="Mailing" /></h4>
	          </div>
	          <p class=""><spring:message code="Manage mail related configurations" />.</p>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <a href="${contextPath}<%=RequestUrls.COMPOSE_EMAIL %>"><spring:message code="Send Email" text="Send Email" /></a>
              <p class=""><spring:message code="Compose and send mail" />.</p>
            </div>
          </div>
       </li>
       <security:authorize access="hasAuthority('CAN_VIEW_SUPPORT_MODULE')">
	       <li class="list-group-item" id="support">
	          <div>
		          <div class="d-flex w-100 justify-content-between">
		            <h4><spring:message code="Support" text="Support" /></h4>
		          </div>
		          <p class=""><spring:message code="Manage support tickets" />.</p>
	          </div>
	          <div class="row">
	            <div class="col-sm-12">
	              <a href="${contextPath}<%=RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY %>"><spring:message code="Support Ticket Assignment strategy" text="Support Ticket Assignment strategy" /></a>
	              <p class=""><spring:message code="Manage support tickets assignment strategy" />.</p>
	            </div>
	          </div>
	       </li>
       </security:authorize>
    </ul>
  </div>
</div>
<div class="row" style="height: 100px;"></div>