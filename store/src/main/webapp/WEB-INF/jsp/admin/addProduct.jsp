<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/numeric.js"></script>
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Products" text="Products" /></a></li>
  <c:choose>
    <c:when test="${empty productDto.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Product" text="Add Product" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Product" text="Edit Product" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="<%=FieldNames.PRODUCTDTO %>" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.PRODUCTS %>">
<form:hidden path="<%=FieldNames.ID %>"/>
<div class="container py-5">
    <h6><spring:message code="Product Details" text="Product Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.CATEGORY_ID %>"><spring:message code="Category" text="Category" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="<%=FieldNames.CATEGORY_ID %>" id="<%=FieldNames.CATEGORY_ID %>" class="form-control form-control-sm">
		                <form:option value="-1"><spring:message code="Select Category" text="Select Category" /></form:option>
		                <c:forEach var="category" items="${categories}">
		                 <form:option value="${category.id}">${category.name}</form:option>
		                </c:forEach>
		              </form:select>
                  <form:errors path="<%=FieldNames.CATEGORY_ID %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.BRAND_NAME %>"><spring:message code="Brand Name" text="Brand Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.BRAND_NAME %>" id="<%=FieldNames.BRAND_NAME %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.BRAND_NAME %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.NAME %>"><spring:message code="Name" text="Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.IMAGE %>"><spring:message code="Image" text="Image" /></label>
                  <form:input type="file" path="<%=FieldNames.IMAGE %>" id="<%=FieldNames.IMAGE %>" class="form-control form-control-sm"/>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                <label for="<%=FieldNames.DESCRIPTION %>"><spring:message code="Description" text="Description" /></label>
                <form:textarea path="<%=FieldNames.DESCRIPTION %>" rows="5" cols="60" class="form-control form-control-sm" />
              </div>
          </div>
        </div>
    </div>
    <h6><spring:message code="Pricing" text="Pricing" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PURCHASE_PRICE %>"><spring:message code="Purchase Price" text="Purchase Price" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.PURCHASE_PRICE %>" id="<%=FieldNames.PURCHASE_PRICE %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.PURCHASE_PRICE %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PER_PRODUCT_PRICE %>"><spring:message code="Sell Price" text="Sell Price" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.PER_PRODUCT_PRICE %>" id="<%=FieldNames.PER_PRODUCT_PRICE %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.PER_PRODUCT_PRICE %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
        </div>
    </div>
    <h6><spring:message code="Quantity" text="Quantity" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.QUANTITY %>"><spring:message code="Quantity" text="Quantity" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.QUANTITY %>" id="<%=FieldNames.QUANTITY %>" class="form-control form-control-sm integer" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.QUANTITY %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.ALERT_QUANTITY %>"><spring:message code="Alert Quantity" text="Alert Quantity" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.ALERT_QUANTITY %>" id="<%=FieldNames.ALERT_QUANTITY %>" class="form-control form-control-sm integer" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.ALERT_QUANTITY %>" class="help-inline has-error"></form:errors>
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