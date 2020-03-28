<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ct" %>
<link href="/css/stepper.css" rel="stylesheet">
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
  .btn-default {
    background-color: #2bbbad!important;
    color: #fff!important;
  }
  .btn-indigo {
    background-color: #3f51b5!important;
    color: #fff!important;
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
<div class="row">
  <div class="col-sm-12">
    <div class="card">
        <div class="card-body mb-4">
            <h2 class="text-center font-weight-bold pt-4 pb-5"><strong><spring:message code="Select Privileges" text="Select Privileges" /></strong></h2>
            <div class="steps-form">
                <div class="steps-row setup-panel">
                    <div class="steps-step">
                        <a href="#step-1" type="button" class="btn btn-success btn-circle">1</a>
                        <p><spring:message code="Select Role Details" text="Select Role Details" /></p>
                    </div>
                    <div class="steps-step">
                        <a href="#step-2" type="button" class="btn btn-indigo btn-circle">2</a>
                        <p><spring:message code="Select Privileges" text="Select Privileges" /></p>
                    </div>
                </div>
            </div>
            <form:form method="POST" modelAttribute="<%=FieldNames.ROLE_DTO %>" class="form-horizontal" action="<%=RequestUrls.ROLE_PRIVILEGES %>" >
            <form:hidden path="<%=FieldNames.ID %>" />
            <form:hidden path="<%=FieldNames.NAME %>" />
            <form:input type="hidden" path="<%=FieldNames.OLD_NAME %>" id="<%=FieldNames.OLD_NAME %>"/>
            <form:hidden path="<%=FieldNames.TYPE %>" />
            <form:hidden path="<%=FieldNames.DESCRIPTION %>" />
            <div class="p-3 justify-content-center text-center mt-40">
                <div class="row justify-content-center mb-4">
                    <div class="col-xl-10 col-lg-10 col-10 list text-left">
                      <label class="text-danger mb-3">Select atleast 1 privilege.</label>
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
					                <ct:privileges parentId="${privilegeDto.id}" noOfParentPriv="${roleDto.getPrivilegeDtos().size()}" childs="${privilegeDto.getChildPrivileges()}"/>
					                </c:forEach>
					               </tbody>
					              </table>
                    </div>
                </div>
                <div class="row justify-content-center mb-4">
                    <div class="col-xl-10 col-lg-10 col-10 list text-left">
                       <button type="submit" class="btn btn-info mr-1"><spring:message code="Submit" text="Submit" /></button>
                    </div>
                </div>
            </div>
            </form:form>
        </div>
    </div>
  </div>
</div>
<div class="row" style="height: 100px;"></div>