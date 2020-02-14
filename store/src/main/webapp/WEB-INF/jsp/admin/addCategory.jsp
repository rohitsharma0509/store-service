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
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.CATEGORIES %>"><spring:message code="Categories" text="Categories" /></a></li>
  <c:choose>
    <c:when test="${empty productCategory.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Category" text="Add Category" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Category" text="Edit Category" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<form:form method="POST" modelAttribute="<%=FieldNames.PRODUCT_CATEGORY_DTO %>" class="form-horizontal" action="<%=RequestUrls.CATEGORIES %>">
<form:hidden path="<%=FieldNames.ID %>" />
<h6><spring:message code="Category Details" text="Category Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.NAME %>"><spring:message code="Category Name" text="Category Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6"></div>
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