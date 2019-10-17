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
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="<%=FieldNames.PRIVILEGE_DTO %>" class="form-horizontal" action="<%=RequestUrls.PRIVILEGES %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="container py-5">
    <h6><spring:message code="Privilege Details" text="Privilege Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="name"><spring:message code="Privilege Name" text="Privilege Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                  <form:errors path="name" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="name"><spring:message code="Description" text="Description" /></label>
                  <form:input type="text" path="description" id="description" class="form-control input-sm"/>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="parentId"><spring:message code="Parent Privilege" text="Parent Privilege" /></label>
                  <form:select path="parentId" id="parentId" class="form-control form-control-sm">
                    <form:option value="-1"><spring:message code="Select Privilege" text="Select Privilege" /></form:option>
                    <c:forEach var="privilege" items="${privileges}">
                    <form:option value="${privilege.id}">${privilege.name}</form:option>
                    </c:forEach>
                  </form:select>
              </div>
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