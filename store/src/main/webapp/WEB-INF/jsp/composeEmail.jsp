<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script src="ckeditor/ckeditor/ckeditor.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
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
                  <label for="subject"><spring:message code="Subject" text="Subject" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="subject" id="subject" class="form-control input-sm"/>
                  <form:errors path="subject" class="help-inline has-error"></form:errors>
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
        <div class="col-sm-2"><button type="submit" class="btn btn-success"><spring:message code="Send Email" text="Send Email" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>