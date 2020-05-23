<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="../js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Users" text="Users" /></li>
</ol>
<section id="filters">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
       <form method="GET" class="form-horizontal" action="<%=RequestUrls.USERS%>">
        <div class="row">
          <div class="col-sm-2 p-0">
            <spring:message code="Name" text="Name" var="label" />
            <input type="text" id="<%=FieldNames.NAME %>" name="<%=FieldNames.NAME %>" value="${param.name}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Email" text="Email" var="label" />
            <input type="text" id="<%=FieldNames.EMAIL %>" name="<%=FieldNames.EMAIL %>" value="${param.email}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Mobile" text="Mobile" var="label" />
            <input type="text" id="<%=FieldNames.MOBILE %>" name="<%=FieldNames.MOBILE %>" value="${param.mobile}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-6 p-0">
            <button type="submit" class="btn btn-info"><i class="fa fa-search" style="font-size: 18px;"></i></button>
          </div>
        </div>
       </form>
      </div>
    </div>
  </div>
</section>
<div class="row" style="height: 20px;"></div>
<c:choose>
	<c:when test="${page.getContent().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
				<table class="table">
					<tr>
						<th><input type="checkbox" id="all" /></th>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="Email" text="Email" /></th>
						<th><spring:message code="Mobile" text="Mobile" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="user" items="${page.getContent()}" varStatus="loop">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${user.id}" /></td>
							<td><a href="${contextPath}<%=RequestUrls.VIEW_USER %>?<%=FieldNames.ID %>=${user.id}" >${user.firstName} ${user.lastName}</a></td>
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
