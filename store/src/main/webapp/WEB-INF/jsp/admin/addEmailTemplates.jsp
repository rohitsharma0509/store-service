<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="../ckeditor/ckeditor/ckeditor.js"></script>
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.EMAIL_TEMPLATES %>"><spring:message code="Email Templates" text="Email Templates" /></a></li>
  <c:choose>
    <c:when test="${empty emailTemplateDto.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Email Template" text="Add Email Template" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Email Template" text="Edit Email Template" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="<%=FieldNames.EMAIL_TEMPLATE_DTO %>" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.EMAIL_TEMPLATES %>">
<form:hidden path="<%=FieldNames.ID %>"/>
<div class="container py-5">
    <h6><spring:message code="Email Template Details" text="Email Template Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.TYPE %>"><spring:message code="Template For" text="Template For" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="<%=FieldNames.TYPE %>" id="<%=FieldNames.TYPE %>" class="form-control input-sm">
                    <form:option value="">Select Type</form:option>
                    <c:forEach var="type" items="${types}">
                      <form:option value="${type.id}">${type.type}</form:option>
                    </c:forEach>
                  </form:select>
                  <form:errors path="<%=FieldNames.TYPE %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.SUBJECT %>"><spring:message code="Subject" text="Subject" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.SUBJECT %>" id="<%=FieldNames.SUBJECT %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.SUBJECT %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.FROM %>"><spring:message code="From" text="From" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.FROM %>" id="<%=FieldNames.FROM %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.FROM %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.TO %>"><spring:message code="To" text="To" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.TO %>" id="<%=FieldNames.TO %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.TO %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.CC %>"><spring:message code="CC" text="CC" /></label>
                  <form:input type="text" path="<%=FieldNames.CC %>" id="<%=FieldNames.CC %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.CC %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.BCC %>"><spring:message code="BCC" text="BCC" /></label>
                  <form:input type="text" path="<%=FieldNames.BCC %>" id="<%=FieldNames.BCC %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.BCC %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                  <label for="<%=FieldNames.BODY %>"><spring:message code="Body" text="Body" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:textarea path="<%=FieldNames.BODY %>" rows="5" cols="60" />
		              <ckeditor:replace replace="body" basePath="/ckeditor/" />
		              <form:errors path="<%=FieldNames.BODY %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>