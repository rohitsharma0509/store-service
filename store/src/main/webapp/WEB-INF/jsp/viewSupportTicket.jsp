<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
  function postActivity(url) {
	  $.get(url, function(modal) {
	    $("#activityModal").html(modal);
	    $("#modalPostActivity").modal("show");
	  });
	}
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><i class="fa fa fa-headphones breadcrumb-icon"></i>&nbsp;&nbsp;<a href="${contextPath}<%=RequestUrls.SUPPORT_TICKETS %>"><spring:message code="Support" text="Support" /></a></li>
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
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Status" text="Status" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.status}</div>
          <div class="col-sm-3 text-right"><spring:message code="Assigned To" text="Assigned To" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.assignedTo}</div>
        </div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Created By" text="Created By" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.createdBy}</div>
          <div class="col-sm-3 text-right"><spring:message code="Created On" text="Created On" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.createdTs}</div>
        </div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Last Modified By" text="Last Modified By" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.lastModifiedBy}</div>
          <div class="col-sm-3 text-right"><spring:message code="Last Modified On" text="Last Modified On" /> : </div>
          <div class="col-sm-3 text-left">${supportTicketDto.lastModifiedTs}</div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
  <div class="col-sm-12">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title font-weight-bold"><spring:message code="Activity History" />&nbsp;
          <a href="#" onclick="postActivity('<%=RequestUrls.POST_SUPPORT_TICKET_ACTIVITY %>?<%=FieldNames.TICKET_ID %>=${supportTicketDto.id}')">(<spring:message code="Post Message" />)</a>
        </h5>
        <div class="row">
          <div class="col-sm-12">
        <c:choose>
          <c:when test="${supportTicketDto.getSupportTicketActivityHistoryDtos().size() > 0}">
            <div class="row">
              <div class="col-sm-12">
                <table class="table">
                  <tr>
                    <th><spring:message code="Date" text="Date" /></th>
                    <th><spring:message code="Added By" text="Added By" /></th>
                    <th><spring:message code="Message" text="Message" /></th>
                  </tr>
                  <c:forEach var="activity" items="${supportTicketDto.getSupportTicketActivityHistoryDtos()}" varStatus="loop">
                    <tr>
                      <td>${activity.createdTs}</td>
                      <td>${activity.createdBy}</td>
                      <td>${activity.message}</td>
                    </tr>
                  </c:forEach>
                </table>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <hr>
            <div class="row norecord">
              <div class="col-sm-12">
                <spring:message code="No Records Found" text="No Records Found" />
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
  <div class="col-sm-12">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title font-weight-bold"><spring:message code="Status Change History" text="Status Change History" /></h5>
        <div class="row">
          <div class="col-sm-12">
        <c:choose>
          <c:when test="${supportTicketDto.getSupportTicketStatusChangeHistoryDtos().size() > 0}">
            <div class="row">
              <div class="col-sm-12">
                <table class="table">
                  <tr>
                    <th><spring:message code="Status Changed On" text="Status Changed On" /></th>
                    <th><spring:message code="Status Changed By" text="Status Changed By" /></th>
                    <th><spring:message code="From" text="From" /></th>
                    <th><spring:message code="To" text="To" /></th>
                  </tr>
                  <c:forEach var="history" items="${supportTicketDto.getSupportTicketStatusChangeHistoryDtos()}" varStatus="loop">
                    <tr>
                      <td>${history.createdTs}</td>
                      <td>${history.createdBy}</td>
                      <td>${history.from}</td>
                      <td>${history.to}</td>
                    </tr>
                  </c:forEach>
                </table>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <hr>
            <div class="row norecord">
              <div class="col-sm-12">
                <spring:message code="No Records Found" text="No Records Found" />
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</div>
<div id="activityModal"></div>