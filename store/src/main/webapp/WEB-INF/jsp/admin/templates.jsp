<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="../js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Templates" text="Templates" /></li>
</ol>
<section id="filters">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
       <form method="GET" class="form-horizontal" action="<%=RequestUrls.TEMPLATES%>">
        <div class="row">
          <div class="col-sm-2 p-0">
            <spring:message code="Template For" text="Template For" var="label" />
            <input type="text" id="<%=FieldNames.TYPE %>" name="<%=FieldNames.TYPE %>" value="${param.type}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <button type="submit" class="btn btn-info"><i class="fa fa-search" style="font-size: 18px;"></i></button>
          </div>
          <div class="col-sm-8 p-0">
            <c:if test="${page.getContent().size() > 0}"> 
				      <a class="btn btn-sm btn-info float-right ml-1 deleteBtn" href="#" data-flag="ALL" data-url="<%=RequestUrls.DELETE_ALL_TEMPLATES %>"><spring:message code="Delete All" text="Delete All" /></a>
				      <a class="btn btn-sm btn-info float-right ml-1 deleteBtn" href="#" data-flag="MULTIPLE" data-url="<%=RequestUrls.DELETE_BULK_TEMPLATES %>"><spring:message code="Delete" text="Delete" /></a>
				    </c:if>
            <a class="btn btn-sm btn-info float-right ml-1" href="${contextPath}<%=RequestUrls.ADD_TEMPLATE %>"><spring:message code="Add Template" text="Add Template" /></a>
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
						<th><input type="checkbox" id="all" /></th>
						<th><spring:message code="Template For" text="Template For" /></th>
						<th><spring:message code="From" text="From" /></th>
						<th><spring:message code="To" text="To" /></th>
						<th><spring:message code="CC" text="CC" /></th>
						<th><spring:message code="BCC" text="BCC" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="template" items="${page.getContent()}" varStatus="loop">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${template.id}" /></td>
							<td>${template.type}</td>
				   			<td>${template.from}</td>
				   			<td>${template.to}</td>
				   			<td>${template.cc}</td>
				   			<td>${template.bcc}</td>
							<td>
						    <a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
                <div id="singleRecordAction${loop.index}" class="d-none">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_TEMPLATE %>?<%=FieldNames.ID %>=${template.id}"><spring:message code="Edit" text="Edit" /></a></li>
                    <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="SINGLE" data-url="<%=RequestUrls.TEMPLATES %>/${template.id}"><spring:message code="Delete" text="Delete" /></a></li>
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