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


<style>
.card.show {
    display: block !important
}

.fa-circle {
    background-color: lightgrey;
    color: #fff;
    padding: 2px 3.1555px;
    border-radius: 50%
}

.step-container {
    border-bottom: 1px solid lightgrey;
}

.step-box {
    padding: 10px;
    background-color: #fff
}

.step-active {
    background-color: #2196F3;
    color: #fff !important;
    border-left: 1px solid #2196F3;
    border-right: 1px solid #2196F3
}

.fa-check {
    border-radius: 50%;
    background-color: #00C853;
    color: #fff;
    padding: 3px
}
</style>

<div class="container-fluid px-1 py-5 mx-auto">
    <div class="row d-flex text-center">
        <div class="col-lg-12 col-md-12">
            <div class="card b-0 rounded-0 show">
                <div class="row justify-content-center mx-auto step-container">
                    <div class="col-md-6 step-box step-active">
                        <h6 class="step-title-0"> <span class="fa fa-circle"></span> <span class="break-line"></span> <span class="step-title">Role Details</span></h6>
                    </div>
                    <div class="col-md-6 step-box">
                        <h6 class="step-title-0"> <span class="fa fa-circle"></span> <span class="break-line"></span> <span class="step-title">Select Privileges</span></h6>
                    </div>
                </div>
                <div class="p-3 justify-content-center text-center">
                    <div class="row justify-content-center mb-4">
                        <div class="col-xl-7 col-lg-8 col-10 list text-left">
                          <label class="text-danger mb-3">Field marked with * are required.</label>
                          <div class="form-group">
                            <label for="<%=FieldNames.TYPE %>"><spring:message code="Role Type" text="Role Type" />&nbsp;<span class="urgent_fields">*</span></label>
                            <form:select path="<%=FieldNames.TYPE %>" id="<%=FieldNames.TYPE %>" class="form-control form-control-sm">
                              <form:option value=""><spring:message code="Select Role Type" /></form:option>
                              <c:forEach var="roleType" items="${roleTypes}">
                                 <form:option value="${roleType.name()}">${roleType.name()}</form:option>
                              </c:forEach>
                            </form:select>
                          </div>
                          <div class="form-group">
                            <label for="<%=FieldNames.NAME %>"><spring:message code="Role Name" text="Role Name" />&nbsp;<span class="urgent_fields">*</span></label>
                            <form:input type="hidden" path="<%=FieldNames.OLD_NAME %>" id="<%=FieldNames.OLD_NAME %>"/>
                            <form:input type="text" path="<%=FieldNames.NAME %>" id="<%=FieldNames.NAME %>" class="form-control input-sm"/>
                            <form:errors path="<%=FieldNames.NAME %>" class="help-inline has-error"></form:errors>
                          </div>
                          <div class="form-group">
                            <label for="<%=FieldNames.DESCRIPTION %>"><spring:message code="Description" text="Description" /></label>
                            <form:textarea path="<%=FieldNames.DESCRIPTION %>" rows="5" cols="60" class="form-control form-control-sm" />
                          </div>
                        </div>
                    </div>
                    <div class="row justify-content-center mb-4">
                        <div class="col-xl-7 col-lg-8 col-10 list text-left">
                           <button type="submit" class="btn btn-info mr-1"><spring:message code="<%=action %>" text="<%=action %>" /></button>
                           <button type="button" class="btn btn-info mr-1" onclick="window.history.back()">Back</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form:form>