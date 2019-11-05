<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="../js/actions.js"></script>
<script>
var filterState=0;
$(document).ready(function(){
   $('#hideShowDiv').click(function() { 
      if(filterState==0){
        $("#filters").slideUp("slow");
        $("#hideShowDiv").html("<a href='#'>Show Filters</a>");
        filterState=1;
      }else if(filterState==1){
        $("#filters").slideDown("slow");
        $("#hideShowDiv").html("<a href='#'>Hide Filters</a>");
        filterState=0;
      }
   });
});
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Roles" text="Roles" /></li>
  <li class="ml-auto"><span id="hideShowDiv"><a href="#">Hide Filters</a></span></li>
</ol>
<div class="row" style="height:10px;"></div>
<div class="row" id="filters">
	<div class="col-sm-12">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.ROLES%>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-2">
							<label for="name"><spring:message code="User Name" text="User Name" /></label>
							<input type="text" id="name" name="name" value="${param.name}" class="form-control input-sm" />
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<label for="roleName"><spring:message code="Role Name" text="Role Name" /></label> 
							<input type="text" id="roleName" name="roleName" value="${param.roleName}" class="form-control input-sm" />
						</div>
						<div class="col-sm-7"></div>
					</div>
					<div class="row">
						<div class="col-sm-1">
							<input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-info form-control">
						</div>
						<div class="col-sm-11"></div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="row" style="height: 20px;"></div>
<div class="row">
	<div class="col-sm-11">
		<a class="btn btn-sm btn-info pover" href="#" rel="moreActions" data-popover-content="#moreActionContent" data-toggle="popover" ><spring:message code="More Actions" text="More Actions" />&nbsp;&nbsp;<i class="fa fa-caret-right" aria-hidden="true" ></i></a>
	</div>
</div>
<div id="moreActionContent" class="d-none">
	<ul class="list-group list-group-flush">
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_ROLE %>"><spring:message code="Add Role" text="Add Role" /></a></li>
    <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="MULTIPLE" data-url="<%=RequestUrls.DELETE_BULK_ROLES %>"><spring:message code="Delete" text="Delete" /></a></li>
    <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="ALL" data-url="<%=RequestUrls.DELETE_ALL_ROLES %>"><spring:message code="Delete All" text="Delete All" /></a></li>
	</ul>
</div>
<div class="row" style="height: 10px;"></div>
<c:choose>
	<c:when test="${page.getContent().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
				<table class="table content-table">
					<tr>
						<th><input type="checkbox" name="ids" id="all" /></th>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="User Assigned" text="User Assigned" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="role" items="${page.getContent()}" varStatus="loop">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${role.id}" /></td>
							<td>${role.name}</td>
				   			<td>${fn:length(role.userDtos)}</td>
							<td>
							  <a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
								<div id="singleRecordAction${loop.index}" class="d-none">
								  <ul class="list-group list-group-flush">
								    <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_ROLE %>?id=${role.id}"><spring:message code="Edit" text="Edit" /></a></li>
								    <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="SINGLE" data-url="<%=RequestUrls.ROLES %>/${role.id}"><spring:message code="Delete" text="Delete" /></a></li>
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
		<div class="row card norecord">
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
          <button type="submit" class="btn btn-lg btn-danger"><spring:message code="Delete" text="Delete" /></button>
          <button type="button" class="btn btn-lg btn-default" data-dismiss="modal"><spring:message code="Close" text="Close" /></button>
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
            <button type="button" class="btn btn-lg btn-default" data-dismiss="modal"><spring:message code="OK" text="OK" /></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>