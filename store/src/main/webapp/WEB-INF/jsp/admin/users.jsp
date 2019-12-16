<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Users" text="Users" /></li>
  <li class="ml-auto"><span id="hideShowDiv"><a href="#">Hide Filters</a></span></li>
</ol>
<div class="row" style="height: 10px;"></div>
<div class="row" id="filters">
	<div class="col-sm-12">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.USERS%>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-2">
							<label for="<%=FieldNames.NAME %>"><spring:message code="Name" text="Name" /></label>
							<input type="text" id="<%=FieldNames.NAME %>" name="<%=FieldNames.NAME %>" value="${param.name}" class="form-control input-sm" />
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<label for="<%=FieldNames.EMAIL %>"><spring:message code="Email" text="Email" /></label> 
							<input type="text" id="<%=FieldNames.EMAIL %>" name="<%=FieldNames.EMAIL %>" value="${param.email}" class="form-control input-sm" />
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
              <label for="<%=FieldNames.MOBILE %>"><spring:message code="Mobile" text="Mobile" /></label>
              <input type="text" id="<%=FieldNames.MOBILE %>" name="<%=FieldNames.MOBILE %>" value="${param.mobile}" class="form-control input-sm" />
            </div>
						<div class="col-sm-4"></div>
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
<c:choose>
	<c:when test="${page.getContent().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
				<table class="table content-table">
					<tr>
						<th><input type="checkbox" name="ids" id="all" /></th>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="Email" text="Email" /></th>
						<th><spring:message code="Mobile" text="Mobile" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="user" items="${page.getContent()}" varStatus="loop">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${user.id}" /></td>
							<td>${user.firstName} ${user.lastName}</td>
				   			<td>${user.email}</td>
				   			<td>${user.mobile}</td>
							<td>
							  <a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
                <div id="singleRecordAction${loop.index}" class="d-none">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.EDIT_USER %>?<%=FieldNames.ID %>=${user.id}"><spring:message code="Edit" text="Edit" /></a></li>
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
