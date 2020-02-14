<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.PRIVILEGES %>"><spring:message code="Privileges" text="Privileges" /></a></li>
  <c:choose>
    <c:when test="${empty productCategory.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Privilege" text="Add Privilege" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Privilege" text="Edit Privilege" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<form:form method="POST" modelAttribute="<%=FieldNames.PRIVILEGE_DTO %>" class="form-horizontal" action="<%=RequestUrls.PRIVILEGES %>">
<form:hidden path="<%=FieldNames.ID %>" />
<h6><spring:message code="Privilege Details" text="Privilege Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.NAME %>"><spring:message code="Privilege Name" text="Privilege Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.DESCRIPTION %>"><spring:message code="Description" text="Description" /></label>
                  <form:input type="text" path="<%=FieldNames.DESCRIPTION %>" id="<%=FieldNames.DESCRIPTION %>" class="form-control input-sm"/>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PARENT_ID %>"><spring:message code="Parent Privilege" text="Parent Privilege" /></label>
                  <form:select path="<%=FieldNames.PARENT_ID %>" id="<%=FieldNames.PARENT_ID %>" class="form-control form-control-sm">
                    <form:option value=""><spring:message code="Select Privilege" text="Select Privilege" /></form:option>
                    <c:forEach var="privilege" items="${privileges}">
                    <form:option value="${privilege.id}">${privilege.name}</form:option>
                    </c:forEach>
                  </form:select>
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