<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Support Ticket Assignment Strategy" text="Support Ticket Assignment Strategy" /></li>
</ol>
<form:form method="POST" modelAttribute="<%=FieldNames.SUPPORT_TICKET_ASSIGNMENT_STRATEGY_DTO %>" class="form-horizontal" action="<%=RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY %>">
<form:hidden path="<%=FieldNames.ID %>"/>
<h6><spring:message code="Strategy Details" text="Strategy Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="row">
            <div class="col-sm-12">
              <p class="card-title"><b><spring:message code="Automatically Assign Opened Support Ticket to" /></b></p>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-sm-12">
              <form:radiobutton path="<%=FieldNames.TYPE %>" value="NAME_BASED" checked="checked" />&nbsp;&nbsp;
              <spring:message code="User With Name" />&nbsp;&nbsp;
              <form:select path="<%=FieldNames.USER_ID %>" id="<%=FieldNames.USER_ID %>">
                <form:option value=""><spring:message code="Select User" text="Select User" /></form:option>
                <c:forEach var="userDto" items="${userDtos}">
                 <form:option value="${userDto.id}">${userDto.firstName} ${userDto.lastName} (${userDto.username})</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-sm-12">
              <form:radiobutton path="<%=FieldNames.TYPE %>" value="ROLE_BASED" />&nbsp;&nbsp;
              <spring:message code="User With Role" />&nbsp;&nbsp;
              <form:select path="<%=FieldNames.ROLE_ID %>" id="<%=FieldNames.ROLE_ID %>">
                <form:option value=""><spring:message code="Select Role" text="Select Role" /></form:option>
                <c:forEach var="roleDto" items="${roleDtos}">
                 <form:option value="${roleDto.id}">${roleDto.name}</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<div class="row">
    <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="Submit" text="Submit" /></button></div>
    <div class="col-sm-10"></div>
</div>
</form:form>