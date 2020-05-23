<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/css/stepper.css" rel="stylesheet">
<style>
.btn-default {
    background-color: #2bbbad!important;
    color: #fff!important;
}
.btn-indigo {
    background-color: #3f51b5!important;
    color: #fff!important;
}
</style>
<script>
$('form[id=roleForm]').submit(function(e) {
  e.preventDefault();
  this.submit();
});
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Roles" text="Roles" /></a></li>
  <c:choose>
    <c:when test="${empty role.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Role" text="Add Role" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Role" text="Edit Role" /></li>
    </c:otherwise>
  </c:choose>
</ol>
<div class="row">
  <div class="col-sm-12">
    <div class="card">
        <div class="card-body mb-4">
            <h2 class="text-center font-weight-bold pt-4 pb-5"><strong><spring:message code="Add Role" text="Add Role" /></strong></h2>
            <div class="steps-form">
                <div class="steps-row setup-panel">
                    <div class="steps-step">
                        <a href="#step-1" type="button" class="btn btn-indigo btn-circle">1</a>
                        <p><spring:message code="Select Role Details" text="Select Role Details" /></p>
                    </div>
                    <div class="steps-step">
                        <a href="#step-2" type="button" class="btn btn-default btn-circle">2</a>
                        <p><spring:message code="Select Privileges" text="Select Privileges" /></p>
                    </div>
                </div>
            </div>
            <form:form method="POST" modelAttribute="<%=FieldNames.ROLE_DTO %>" id="roleForm" class="form-horizontal" action="<%=RequestUrls.ROLES %>" onsubmit="submitForm(event)">
            <form:hidden path="<%=FieldNames.ID %>" />
            <div class="p-3 justify-content-center text-center mt-40">
                <div class="row justify-content-center mb-4">
                    <div class="col-xl-7 col-lg-8 col-10 list text-left">
                      <label class="text-danger mb-3">Field marked with * are required.</label>
                      <div class="form-group">
                        <label for="<%=FieldNames.TYPE %>"><spring:message code="Role Type" text="Role Type" />&nbsp;<span class="urgent_fields">*</span></label>
                        <form:select path="<%=FieldNames.TYPE %>" id="<%=FieldNames.TYPE %>" class="form-control form-control-sm">
                          <form:option value=""><spring:message code="Select Role Type" /></form:option>
                          <c:forEach var="roleType" items="${roleTypes}">
                             <form:option value="${roleType.name()}">${roleType.name()}</form:option>
                          </c:forEach>
                        </form:select>
                        <form:errors path="<%=FieldNames.TYPE %>" class="help-inline has-error"></form:errors>
                      </div>
                      <div class="form-group">
                        <label for="<%=FieldNames.NAME %>"><spring:message code="Role Name" text="Role Name" />&nbsp;<span class="urgent_fields">*</span></label>
                        <form:input type="hidden" path="<%=FieldNames.OLD_NAME %>" id="<%=FieldNames.OLD_NAME %>"/>
                        <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm"/>
                        <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
                      </div>
                      <div class="form-group">
                        <label for="<%=FieldNames.DESCRIPTION %>"><spring:message code="Description" text="Description" /></label>
                        <form:textarea path="<%=FieldNames.DESCRIPTION %>" rows="5" cols="60" class="form-control form-control-sm" />
                      </div>
                    </div>
                </div>
                <div class="row justify-content-center mb-4">
                    <div class="col-xl-7 col-lg-8 col-10 list text-left">
                       <button type="submit" class="btn btn-info mr-1"><spring:message code="Next" text="Next" /></button>
                       <a href="${contextPath}<%=RequestUrls.ROLES %>" class="btn btn-info mr-1" ><spring:message code="Back" text="Back" /></a>
                    </div>
                </div>
            </div>
            </form:form>
        </div>
    </div>
  </div>
</div>
<div class="row" style="height: 100px;"></div>