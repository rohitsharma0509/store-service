<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.SUPPORT_TICKETS %>"><spring:message code="Support" text="Support" /></a></li>
  <li class="breadcrumb-item active">${supportTicketDto.number}</li>
</ol>
<div class="row">
  <div class="col-sm-12">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title font-weight-bold">${supportTicketDto.number}</h4>
        <p class="mb-2"><label><spring:message code="Priority" text="Priority" /></label> : ${supportTicketDto.priority}</p>
        <p class="card-text">${supportTicketDto.description}</p>
        <hr class="my-4">
        <p class="lead"><strong><spring:message code="Status" text="Status" /> : ${supportTicketDto.status}</strong></p>
        <p><spring:message code="Assigned To" text="Assigned To" /> : <spring:message code="Unassigned" text="Unassigned" /></p>
        <p><spring:message code="Created By" text="Created By" /> : ${supportTicketDto.createdBy}</p>
        <p><spring:message code="Created On" text="Created On" /> : ${supportTicketDto.createdTs}</p>
        <p><spring:message code="Last Modified By" text="Last Modified By" /> : ${supportTicketDto.lastModifiedBy}</p>
        <p><spring:message code="Last Modified On" text="Last Modified On" /> : ${supportTicketDto.lastModifiedTs}</p>
      </div>
    </div>
  </div>
</div>