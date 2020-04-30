<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
function modifyRoles(userId) {
	var url = '<%=RequestUrls.USERS%>/'+userId;
	
  $.get(url, function(modal) {
    $("#addressModal").html(modal);
    $("#modalAddAddress").modal("show");
  });
}
</script>
<div class="row" style="height: 20px;"></div>
<div class="row">
  <div class="col-sm-3 text-right"><spring:message code="Email" text="Email" /> : </div>
  <div class="col-sm-3 text-left">${userDto.email}</div>
  <div class="col-sm-3 text-right"><spring:message code="Username" text="Username" /> : </div>
  <div class="col-sm-3 text-left">${userDto.username}</div>
</div>
<div class="row">
  <div class="col-sm-3 text-right"><spring:message code="Account Status" text="Account Status" /> : </div>
  <c:choose>
    <c:when test="${userDto.isEnabled}">
      <div class="col-sm-3 text-left"><spring:message code="Enabled" text="Enabled" /></div>
    </c:when>
    <c:otherwise>
      <div class="col-sm-3 text-left"><spring:message code="Disabled" text="Disabled" /></div>
    </c:otherwise>
  </c:choose>
  <div class="col-sm-3 text-right"><spring:message code="Language" text="Language" /> : </div>
  <div class="col-sm-3 text-left">${userDto.language}</div>
</div>
<%--<div class="heading"><spring:message code="Roles & Privileges" text="Roles & Privileges" />&nbsp;(<a href="#" onclick="modifyRoles('${userDto.id}');"><spring:message code="Modify Roles" text="Modify Roles" /></a>)</div> --%>
<div class="heading"><spring:message code="Roles & Privileges" text="Roles & Privileges" /></div>
<c:choose>
	<c:when test="${userDto.getRoles().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
			  <c:forEach var="roleDto" items="${userDto.getRoles()}" varStatus="loop">
			  <h6>&nbsp;&nbsp;${roleDto.name} (${roleDto.description})</h6>
				<table class="table">
					<tr>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="Description" text="Description" /></th>
					</tr>
					<c:forEach var="privilegeDto" items="${roleDto.getPrivilegeDtos()}">
						<tr>
							<td>${privilegeDto.name}</td>
							<td>${privilegeDto.description}</td>
						</tr>
					</c:forEach>
				</table>
				</c:forEach>
			</div>
		</div>
		<div class="row" style="height: 20px;"></div>
	</c:when>
	<c:otherwise>
		<div class="row norecord">
			<div class="col-sm-12">
				<spring:message code="No Records Found" text="No Records Found" />
			</div>
		</div>
	</c:otherwise>
</c:choose>