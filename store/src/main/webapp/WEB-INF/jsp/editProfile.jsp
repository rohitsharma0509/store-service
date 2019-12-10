<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.MY_ACCOUNT %>"><spring:message code="My Account" text="My Account" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Edit Profile" text="Edit Profile" /></li>
</ol>
<div class="row" style="height: 10px;"></div>


<form:form method="POST" modelAttribute="<%=FieldNames.USER_DTO %>" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.EDIT_USERS %>">
<form:hidden path="<%=FieldNames.ID %>" />
<div class="container py-5">
    <h6><spring:message code="User Details" text="User Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.FIRST_NAME %>"><spring:message code="First Name" text="First Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.FIRST_NAME %>" id="<%=FieldNames.FIRST_NAME %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.FIRST_NAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.LAST_NAME %>"><spring:message code="Last Name" text="Last Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.LAST_NAME %>" id="<%=FieldNames.LAST_NAME %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.LAST_NAME %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.EMAIL %>"><spring:message code="Email" text="Email" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.EMAIL %>" id="<%=FieldNames.EMAIL %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.EMAIL %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.MOBILE %>"><spring:message code="Mobile" text="Mobile" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.MOBILE %>" id="<%=FieldNames.MOBILE %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.MOBILE %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.LANGUAGE %>"><spring:message code="Language" text="Language" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="<%=FieldNames.LANGUAGE %>" id="<%=FieldNames.LANGUAGE %>" class="form-control input-block">
                <form:option value="-1"><spring:message code="Select Language" text="Select Language" /></form:option>
                <c:forEach var="language" items="${languages}">
                 <form:option value="${language.key}"><spring:message code="${language.value}" text="${language.value}" /></form:option>
                </c:forEach>
              </form:select>
              <form:errors path="<%=FieldNames.LANGUAGE %>" class="help-inline has-error"></form:errors>
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