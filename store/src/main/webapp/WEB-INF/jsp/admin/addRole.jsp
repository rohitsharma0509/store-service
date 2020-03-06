<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ct" %>
<script>
function setPrivileges(priv, index) {
	  var obj = $("input[name='privilegeDtos["+index+"].isInRole']");	  
	  obj.val($(priv).prop("checked"));
}

$(document).ready(function(){
  $("#all").change(function() {
    $(".checkbox").prop("checked", $(this).prop("checked"));
  });
  
  $(".checkbox").change(function() {
    if(false==$(this).prop("checked")){
      $("#all").prop("checked", false);
    }
    if($(".checkbox:checked").length == $(".checkbox").length) {
      $("#all").prop("checked", true);
    }
  });
  
  /* $(".parent-checkbox").change(function() {
	    var parentId = $(this).val();
	    $("."+parentId).prop("checked", $(this).prop("checked"));
	}); */
  $(".child").change(function() {
	  var parentId = $(this).data("parent");
	  if($(this).prop("checked") == false) {
	    $("#"+parentId).prop("checked", false); 
	  } else if($(this).prop("checked") == true) {
	    if($("input:checkbox[data-parent="+parentId+"]:checked").length == $("input:checkbox[data-parent="+parentId+"]").length) {
	      $("#"+parentId).prop("checked", true); 
	    }
	  }
	});
});
</script>
<style>
  .role-table th {
     border-top: 0px solid #dee2e6;
  }
  .inner-table td, .inner-table th {
     border-top: 0px solid #dee2e6;
     line-height: 0px;
  }
</style>
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Roles" text="Roles" /></a></li>
  <c:choose>
    <c:when test="${empty role.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Role" text="Add Role" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Role" text="Edit Role" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<form:form method="POST" modelAttribute="<%=FieldNames.ROLE_DTO %>" class="form-horizontal" action="<%=RequestUrls.ROLES %>">
<form:hidden path="<%=FieldNames.ID %>" />
<h6><spring:message code="Role Details" text="Role Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.TYPE %>"><spring:message code="Role Type" text="Role Type" /></label>
                  <form:select path="<%=FieldNames.TYPE %>" id="<%=FieldNames.TYPE %>" class="form-control form-control-sm">
                    <form:option value=""><spring:message code="Select Role Type" /></form:option>
                    <c:forEach var="roleType" items="${roleTypes}">
                     <form:option value="${roleType.name()}">${roleType.name()}</form:option>
                    </c:forEach>
                  </form:select>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.NAME %>"><spring:message code="Role Name" text="Role Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="hidden" path="<%=FieldNames.OLD_NAME %>" id="<%=FieldNames.OLD_NAME %>"/>
                  <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm"/>
                  <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
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
  </div>
</div>
<div class="row spacer"></div>
<h6><spring:message code="Privileges" text="Privileges" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
			      <div class="col-sm-12">
			        <table class="table">
			         <tbody>
			          <tr>
			            <th><spring:message code="No." text="No." /></th>
			            <th><spring:message code="Privilege Name" text="Privilege Name" /></th>
			            <th><spring:message code="Description" text="Description" /></th>
			            <th><input type="checkbox" name="ids" id="all" /></th>
			          </tr>
		            <c:forEach var="privilegeDto" items="${roleDto.getPrivilegeDtos()}" varStatus="loop">
			          <tr>
			            <td>${loop.index+1}</td>
			            <td>${privilegeDto.name}</td>
                  <td>${privilegeDto.description}</td>
			            <td>
			              <c:choose>
				              <c:when test="${privilegeDto.isInRole}">
				                 <input type="hidden" name="privilegeDtos[${loop.index}].isInRole" id="privilegeDtos[${loop.index}].isInRole" value="true"/>
				                 <input class="checkbox" type="checkbox" name="privilegeDtos[${loop.index}].id" id="${privilegeDto.id}" value="${privilegeDto.id}" checked onChange="setPrivileges(this, ${loop.index})" />
				              </c:when>
				              <c:otherwise>
				                 <input type="hidden" name="privilegeDtos[${loop.index}].isInRole" id="privilegeDtos[${loop.index}].isInRole" value="false"/>
				                 <input class="checkbox" type="checkbox" name="privilegeDtos[${loop.index}].id" id="${privilegeDto.id}" value="${privilegeDto.id}" onChange="setPrivileges(this, ${loop.index})" />
				              </c:otherwise>
			              </c:choose>
			            </td>
			          </tr>
			          <ct:privileges parentId="${privilegeDto.id}" childs="${privilegeDto.getChildPrivileges()}"/>
			          </c:forEach>
			         </tbody>
			        </table>
			      </div>
			    </div>
			  </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<div class="row">
  <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
  <div class="col-sm-10"></div>
</div>
</form:form>