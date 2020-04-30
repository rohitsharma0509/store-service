<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="/js/tab.js"></script>
<script type="text/javascript">
$(function() {
  showTabOnStartUp('accountTab');
});
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Users" text="Users" /></a></li>
  <li class="breadcrumb-item active">${userDto.firstName} ${userDto.lastName}</li>
</ol>
<div class="row">
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <li class="nav-item"><a class="nav-link" data-toggle="tab" id="accountTab" data-target="#account" href="${contextPath}<%=RequestUrls.ACCOUNT_SETTING %>?<%=FieldNames.ID %>=${userDto.id}"><spring:message code="Account Setting" text="Account Setting" /></a></li>
      <li class="nav-item"><a class="nav-link"  data-toggle="tab" id="personalTab" data-target="#personal" href="${contextPath}<%=RequestUrls.PERSONAL_INFORMATION %>?<%=FieldNames.ID %>=${userDto.id}"><spring:message code="Personal Information" text="Personal Information" /></a></li>
    </ul>
    
    <div class="tab-content">
      <div class="tab-pane container fade" id="account"></div>
      <div class="tab-pane container fade" id="personal"></div>
    </div>
    <div class="d-flex justify-content-center" style="height: 110px; align-items: flex-end;">
      <div class="spinner-grow spinner-grow-lg text-muted"></div>
      <div class="spinner-grow spinner-grow-lg text-muted"></div>
      <div class="spinner-grow spinner-grow-lg text-muted"></div>
      <div class="spinner-grow spinner-grow-lg text-muted"></div>
      <div class="spinner-grow spinner-grow-lg text-muted"></div>
    </div>
  </div>
</div>