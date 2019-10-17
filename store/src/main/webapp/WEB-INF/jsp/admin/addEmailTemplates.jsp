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
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
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
<form:form method="POST" modelAttribute="emailTemplateDto" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.EMAIL_TEMPLATES %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="container py-5">
    <h6><spring:message code="Email Template Details" text="Email Template Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="type"><spring:message code="Template For" text="Template For" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="type" id="type" class="form-control input-sm">
                    <form:option value="">Select Type</form:option>
                    <c:forEach var="type" items="${types}">
                      <form:option value="${type.id}">${type.type}</form:option>
                    </c:forEach>
                  </form:select>
                  <form:errors path="type" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="subject"><spring:message code="Subject" text="Subject" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="subject" id="subject" class="form-control input-sm"/>
                  <form:errors path="subject" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="from"><spring:message code="From" text="From" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="from" id="from" class="form-control input-sm"/>
                  <form:errors path="from" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="to"><spring:message code="To" text="To" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="to" id="to" class="form-control input-sm"/>
                  <form:errors path="to" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="cc"><spring:message code="CC" text="CC" /></label>
                  <form:input type="text" path="cc" id="cc" class="form-control input-sm"/>
              </div>
              <div class="col-sm-6">
                  <label for="bcc"><spring:message code="BCC" text="BCC" /></label>
                  <form:input type="text" path="bcc" id="bcc" data-role="tagsinput" class="form-control input-sm"/>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                  <label for="body"><spring:message code="Body" text="Body" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:textarea path="body" rows="5" cols="60" />
		              <ckeditor:replace replace="body" basePath="/ckeditor/" />
		              <form:errors path="body" class="help-inline has-error"></form:errors>
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