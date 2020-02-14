<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <c:choose>
    <c:when test="${empty emailAccount.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Email Account" text="Add Email Account" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Email Account" text="Edit Email Account" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<form:form method="POST" modelAttribute="emailAccount" class="form-horizontal" enctype="multipart/form-data" action="addEmailAccount">
<form:hidden path="<%=FieldNames.ID %>"/>
<h6><spring:message code="Email Account Details" text="Email Account Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.HOST %>"><spring:message code="Host" text="Host" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.HOST %>" placeholder="smtp.gmail.com" id="host" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.HOST %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PORT %>"><spring:message code="Port" text="Port" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.PORT %>" id="<%=FieldNames.PORT %>" placeholder="587" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.PORT %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.USERNAME %>"><spring:message code="Email" text="Email" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.USERNAME %>" id="<%=FieldNames.USERNAME %>" placeholder="Enter email" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.USERNAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PASSWORD %>"><spring:message code="Password" text="Password" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="password" path="<%=FieldNames.PASSWORD %>" id="<%=FieldNames.PASSWORD %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.PASSWORD %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<div class="row">
    <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
    <div class="col-sm-10"></div>
</div>
</form:form>