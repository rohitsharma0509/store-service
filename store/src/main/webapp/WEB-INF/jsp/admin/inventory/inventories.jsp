<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Inventory" text="Inventory" /></li>
</ol>
<section id="filters">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
       <form method="GET" class="form-horizontal" action="<%=RequestUrls.INVENTORY %>">
        <div class="row">
          <div class="col-sm-2 p-0">
	        <spring:message code="Agency Name" text="Agency Name" var="label"/>
            <input type="text" id="<%=FieldNames.AGENCY_NAME %>" name="<%=FieldNames.AGENCY_NAME %>" value="${param.agencyName}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Payment Status" text="Payment Status" var="label"/>
            <select id="<%=FieldNames.PAYMENT_STATUS %>" name="<%=FieldNames.PAYMENT_STATUS %>" class="form-control">
                <option value=""><spring:message code="Select Payment Status" text="Select Payment Status" /></option>
                <c:forEach var="status" items="${paymentStatuses}">
                  <c:choose>
                    <c:when test="${param.paymentStatus == status.name()}">
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
            <spring:message code="Payment Mode" text="Payment Mode" var="label"/>
            <select id="<%=FieldNames.PAYMENT_MODE %>" name="<%=FieldNames.PAYMENT_MODE %>" class="form-control">
                <option value=""><spring:message code="Select Payment Mode" text="Select Payment Mode" /></option>
                <c:forEach var="mode" items="${paymentModes}">
                  <c:choose>
                    <c:when test="${param.paymentMode == mode.name()}">
                      <option selected value="${mode.name()}">${mode.name()}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${mode.name()}">${mode.name()}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
             </select>
          </div>
          <div class="col-sm-2 p-0">
            <button type="submit" class="btn btn-info"><i class="fa fa-search" style="font-size: 18px;"></i></button>
          </div>
          <div class="col-sm-4 p-0">
		    <a class="btn btn-sm btn-info float-right ml-1" href="${contextPath}<%=RequestUrls.ADD_INVENTORY %>"><spring:message code="Add Inventory" text="Add Inventory" /></a>
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
						<th><spring:message code="Bill No." text="Bill No." /></th>
						<th><spring:message code="Agency Name" text="Agency Name" /></th>
						<th><spring:message code="Payment Status" text="Payment Status" /></th>
						<th><spring:message code="Payment Mode" text="Payment Mode" /></th>
						<th><spring:message code="Total Amount" text="Total Amount" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="inventory" items="${page.getContent()}" varStatus="loop">
						<tr>
							<td><a href="${contextPath}<%=RequestUrls.VIEW_INVENTORY %>?<%=FieldNames.ID %>=${inventory.id}" >${inventory.billNumber}</a></td>
							<td>${inventory.agencyName}</td>
							<td>${inventory.paymentStatus}</td>
							<td>${inventory.paymentMode}</td>
							<td>${inventory.totalAmount}</td>
							<td>
								<a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
				                <div id="singleRecordAction${loop.index}" class="d-none">
				                  <ul class="list-group list-group-flush">
				                    <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_INVENTORY %>?<%=FieldNames.ID %>=${inventory.id}"><spring:message code="Edit" text="Edit" /></a></li>
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
			<div class="col-sm-12">
				<spring:message code="No Records Found" text="No Records Found" />
			</div>
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
