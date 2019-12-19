<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script src="ckeditor/ckeditor/ckeditor.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Send Email" text="Send Email" /></li>
</ol>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="email" class="form-horizontal" enctype="multipart/form-data" action="sendEmail">
<div class="container py-5">
    <h6><spring:message code="Mail Details" text="Mail Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.SUBJECT %>"><spring:message code="Subject" text="Subject" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.SUBJECT %>" id="<%=FieldNames.SUBJECT %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.SUBJECT %>" class="help-inline has-error"></form:errors>
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
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.BCC %>"><spring:message code="BCC" text="BCC" /></label>
                  <form:input type="text" path="<%=FieldNames.BCC %>" id="<%=FieldNames.BCC %>" data-role="tagsinput" class="form-control input-sm"/>
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
        <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="Send Email" text="Send Email" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>