<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.SUPPORT_TICKETS %>"><spring:message code="Support" text="Support" /></a></li>
  <c:choose>
    <c:when test="${empty supportTikcetDto.id}">
      <li class="breadcrumb-item active"><spring:message code="Create Support Ticket" text="Create Support Ticket" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Modify Support Ticket" text="Modify Support Ticket" /></li>
      <% action = "Modify"; %>
    </c:otherwise>
  </c:choose>
</ol>
</div>
<form:form method="POST" modelAttribute="<%=FieldNames.SUPPORT_TICKET_DTO %>" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.SUPPORT_TICKETS %>">
<form:hidden path="<%=FieldNames.ID %>"/>
<div class="container py-5">
    <h6><spring:message code="Ticket Details" text="Ticket Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.ORDER_NUMBER %>"><spring:message code="Order Number" text="Order Number" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.ORDER_NUMBER %>" id="<%=FieldNames.ORDER_NUMBER %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.ORDER_NUMBER %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PRIORITY %>"><spring:message code="Priority" text="Priority" /></label>
                  <form:select path="<%=FieldNames.PRIORITY %>" id="<%=FieldNames.PRIORITY %>" class="form-control form-control-sm">
                    <c:forEach var="priority" items="${priorities}">
                     <form:option value="${priority.name()}">${priority.name()}</form:option>
                    </c:forEach>
                  </form:select>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                <label for="<%=FieldNames.DESCRIPTION %>"><spring:message code="Description" text="Description" /></label>
                <form:textarea path="<%=FieldNames.DESCRIPTION %>" rows="5" cols="60" class="form-control form-control-sm" />
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
