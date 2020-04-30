<%@page import="com.app.ecom.store.dto.userservice.UserDto"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
UserDto userDto = (UserDto) request.getSession().getAttribute(FieldNames.USER);
%>
<div class="row" style="height:20px;"></div>
<div class="row">
  <div class="col-sm-12">
    <form method="GET" class="form-horizontal" name="pswrdForm" onsubmit="submitToUrl(event, this, 'pswrdTab', '<%=RequestUrls.CHANGE_PSWRD %>')">
    <input type="hidden" name="<%=FieldNames.ID %>" value="<%=userDto.getId() %>">
    <input type="hidden" name="<%=FieldNames.IS_VALIDATION_REQUIRED %>" value="true">
      <div class="p-3 justify-content-center text-center">
          <div class="row justify-content-center mb-4">
              <div class="col-xl-7 col-lg-8 col-10 list text-left">
                <c:if test="${not empty message}">
				          <div class="alert alert-success hide" >${message}</div>
				        </c:if>
				        <c:if test="${not empty error}">
                  <div class="alert alert-danger hide" >${error}</div>
                </c:if>
                <div class="form-group">
                  <label for="<%=FieldNames.PSWRD %>"><spring:message code="New Password" text="New Password" />&nbsp;<span class="urgent_fields">*</span></label>
                  <input type="password" name="pswrd" class="form-control input-sm"/>
                </div>
                <div class="form-group">
                  <label for="<%=FieldNames.CONFIRM_PSWRD %>"><spring:message code="Confirm New Password" text="Confirm New Password" />&nbsp;<span class="urgent_fields">*</span></label>
                  <input type="password" name="confirmPswrd" class="form-control input-sm"/>
                </div>
              </div>
          </div>
          <div class="row justify-content-center mb-4">
              <div class="col-xl-7 col-lg-8 col-10 list text-left">
                 <button type="submit" class="btn btn-info mr-1"><spring:message code="Change Password" text="Change Password" /></button>
              </div>
          </div>
      </div>
    </form>
  </div>
</div>