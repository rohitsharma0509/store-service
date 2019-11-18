<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.SETTINGS %>"><spring:message code="Manage Settings" text="Manage Settings" /></a></li>
  <c:choose>
    <c:when test="${empty settingDto.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Setting" text="Add Setting" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Setting" text="Edit Setting" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="settingDto" class="form-horizontal" action="<%=RequestUrls.SETTINGS %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="container py-5">
    <h6><spring:message code="Setting Details" text="Setting Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="name"><spring:message code="Name" text="Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                  <form:errors path="name" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6"></div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                <label for="description"><spring:message code="Description" text="Description" /></label>
                <form:textarea path="description" rows="5" cols="60" class="form-control form-control-sm" />
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="name"><spring:message code="Value" text="Value" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="value" id="value" class="form-control input-sm"/>
                  <form:errors path="value" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6"></div>
          </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm-2"><button type="submit" class="btn btn-success"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>