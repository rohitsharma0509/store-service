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
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Roles" text="Roles" /></a></li>
  <c:choose>
    <c:when test="${empty role.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Product" text="Add Role" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Product" text="Edit Role" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="roleDto" class="form-horizontal" action="<%=RequestUrls.ROLES %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="container py-5">
    <h6><spring:message code="Role Details" text="Role Details" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="name"><spring:message code="Role Name" text="Role Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="hidden" path="oldName" id="oldName"/>
                  <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                  <form:errors path="name" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6"></div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                <label for="description"><spring:message code="Description" text="Description" /></label>
                <form:textarea path="description" rows="5" cols="60" class="form-control form-control-sm" />
              </div>
          </div>
        </div>
    </div>
    <h6><spring:message code="Privileges" text="Privileges" /></h6><hr>
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
			      <div class="col-sm-12">
			        <table class="table role-table">
			         <tbody>
			          <tr style="background-color: #8d99a2ab;">
			            <th><spring:message code="No." text="No." /></th>
			            <th><spring:message code="Privilege Name" text="Privilege Name" /></th>
			            <th><spring:message code="Description" text="Description" /></th>
			            <th><input type="checkbox" name="ids" id="all" /></th>
			          </tr>
		            <c:forEach var="privilegeDto" items="${roleDto.getPrivilegeDtos()}" varStatus="loop">
			          <tr style="background-color: #f6f6f6;">
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
    <hr>
    <div class="row">
        <div class="col-sm-2"><button type="submit" class="btn btn-success"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
        <div class="col-sm-10"></div>
    </div>
</div>
</form:form>