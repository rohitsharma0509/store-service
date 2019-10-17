<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Users" text="Users" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Edit User" text="Edit User" /></li>
</ol>
<div class="row" style="height: 10px;"></div>

<form:form method="POST" modelAttribute="userDto" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.USERS %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="container py-5">
    <h6><spring:message code="User Details" text="User Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="firstName"><spring:message code="First Name" text="First Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                  <form:errors path="firstName" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="lastName"><spring:message code="Last Name" text="Last Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                  <form:errors path="lastName" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="email"><spring:message code="Email" text="Email" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                  <form:errors path="email" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="mobile"><spring:message code="Mobile" text="Mobile" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="mobile" id="mobile" class="form-control input-sm"/>
                  <form:errors path="mobile" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="language"><spring:message code="Language" text="Language" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="language" id="language" class="form-control input-block">
                <form:option value="-1"><spring:message code="Select Language" text="Select Language" /></form:option>
                <c:forEach var="language" items="${languages}">
                 <form:option value="${language.key}"><spring:message code="${language.value}" text="${language.value}" /></form:option>
                </c:forEach>
              </form:select>
              <form:errors path="language" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6"></div>
          </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm-2"><button type="submit" class="btn btn-success"><spring:message code="Update" text="Update" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>