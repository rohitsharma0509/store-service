<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="/js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><i class="fa fa fa-headphones breadcrumb-icon"></i>&nbsp;&nbsp;<spring:message code="Support" text="Support" /></li>
</ol>
<section id="filters">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
			 <form method="GET" class="form-horizontal" action="<%=RequestUrls.SUPPORT_TICKETS%>">
				<div class="row">
					<div class="col-sm-2 p-0">
					  <input type="text" id="<%=FieldNames.TICKET_NUMBER %>" name="<%=FieldNames.TICKET_NUMBER %>" value="${param.ticketNumber}" class="form-control" placeholder="Ticket Number" />
					</div>
					<div class="col-sm-2 p-0">
						<input type="text" id="<%=FieldNames.ORDER_NUMBER %>" name="<%=FieldNames.ORDER_NUMBER %>" value="${param.orderNumber}" class="form-control" placeholder="Order Number" />
					</div>
					<div class="col-sm-2 p-0">
						<select id="<%=FieldNames.STATUS %>" name="<%=FieldNames.STATUS %>" class="form-control">
                <option value=""><spring:message code="Select Status" text="Select Status" /></option>
                <c:forEach var="status" items="${statuses}">
                  <c:choose>
                    <c:when test="${param.status == status.name()}">
                      <option selected value="${status.name()}">${status.name()}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${status.name()}">${status.name()}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
             </select>
					</div>
					<div class="col-sm-2 p-0">
					   <input type="date" name="<%=FieldNames.CREATED_TS %>" id="<%=FieldNames.CREATED_TS %>" value="${param.createdTs}" class="form-control" />
					</div>
					<div class="col-sm-2 p-0">
					  <button type="submit" class="btn btn-info"><i class="fa fa-search" style="font-size: 18px;"></i></button>
					</div>
					<div class="col-sm-2">
						<a class="btn btn-sm btn-info float-right ml-1" href="${contextPath}<%=RequestUrls.CREATE_SUPPORT_TICKET %>"><spring:message code="Create Ticket" text="Create Ticket" /></a>
					</div>
				</div>
			 </form>
			</div>
		</div>
	</div>
</section>
<c:choose>
  <c:when test="${page.getContent().size() > 0}">
    <div class="row" style="height: 20px;"></div>
    <div class="row">
      <div class="col-sm-12">
        <table class="table">
          <tr>
            <th><spring:message code="Ticket Number" text="Ticket Number" /></th>
            <th><spring:message code="Order Number" text="Order Number" /></th>
            <th><spring:message code="Status" text="Status" /></th>
            <th><spring:message code="Priority" text="Priority" /></th>
            <th><spring:message code="Assigned To" text="Assigned To" /></th>
            <th><spring:message code="Created By" text="Created By" /></th>
            <th><spring:message code="Creation Time" text="Creation Time" /></th>
            <th><spring:message code="Last Modified By" text="Last Modified By" /></th>
            <th><spring:message code="Last Modified Time" text="Last Modified Time" /></th>
            <th><spring:message code="Action" text="Action" /></th>
          </tr>
          <c:forEach var="ticket" items="${page.getContent()}" varStatus="loop">
            <tr>
              <td><a href="${contextPath}<%=RequestUrls.VIEW_SUPPORT_TICKET %>?<%=FieldNames.ID %>=${ticket.id}">${ticket.number}</a></td>
              <td>${ticket.orderNumber}</td>
              <td>${ticket.status}</td>
              <td>${ticket.priority}</td>
              <td>${ticket.assignedTo} ${sessionScope.UserDto.username }</td>
              <td>${ticket.createdBy}</td>
              <td>${ticket.createdTs}</td>
              <td>${ticket.lastModifiedBy}</td>
              <td>${ticket.lastModifiedTs}</td>
              <td>
                <a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
                <div id="singleRecordAction${loop.index}" class="d-none">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action"><a href="<%=RequestUrls.SUPPORT_TICKETS %>?<%=FieldNames.ID %>=${ticket.id}"><spring:message code="Change Priority" text="Change Priority" /></a></li>
                    <li class="list-group-item list-group-item-action"><a href="<%=RequestUrls.SUPPORT_TICKETS %>?<%=FieldNames.ID %>=${ticket.id}"><spring:message code="Change Status" text="Change Status" /></a></li>
                    <c:if test="${ticket.createdBy == sessionScope.UserDto.username}">
                      <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="SINGLE" data-url="<%=RequestUrls.SUPPORT_TICKETS %>/${ticket.id}"><spring:message code="Delete" text="Delete" /></a></li>
                    </c:if>
                  </ul>
                </div>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <hr>
    <div class="row norecord">
      <div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
    </div>
  </c:otherwise>
</c:choose>
  <c:choose>
    <c:when test="${page.getTotalPages() > 1}">
      <div class="row">
        <div class="col-sm-12">${pagging}</div>
      </div>
    </c:when>
  </c:choose>
<div class="row">
  <div class="modal fade" id="deleteConfirmation" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog delete-modal" role="document">
      <div class="modal-content">
        <div class="modal-body mx-3">
          <div align="center">
            <div><i class="fa fa-times-circle delete-icon"></i></div>
            <div class="delete-modal-header"><spring:message code="Are you sure?" text="Are you sure?" /></div>
            <div class="delete-modal-content"><spring:message code="Do you really want to delete these records? This process cannot be undone" text="Do you really want to delete these records? This process cannot be undone" />.</div>
          <div>
        <form id="deleteForm">
          <input type="hidden" id="deleteType">
          <button type="submit" class="btn btn-lg btn-danger"><spring:message code="Delete" text="Delete" /></button>
          <button type="button" class="btn btn-lg btn-light" data-dismiss="modal"><spring:message code="Close" text="Close" /></button>
        </form>
      </div>
      </div>
        </div>
      </div>  
    </div>
  </div>
</div>
<div class="row">
  <div class="modal fade" id="alertMessages" role="dialog" aria-labelledby="alertMessagesLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog delete-modal" role="document">
      <div class="modal-content ">
        <div class="modal-body mx-3">
          <div align="center">
            <div><i class="fa fa-warning warning-icon"></i></div>
            <div class="warning-modal-header"><spring:message code="Sorry" text="Sorry" />!</div>
            <div class="warning-modal-content"></div>
            <button type="button" class="btn btn-lg btn-light" data-dismiss="modal"><spring:message code="OK" text="OK" /></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
