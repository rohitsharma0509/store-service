<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$('form[name=addressForm]').submit(function(e) {
	e.preventDefault();
	$.post({
        url : '<%=RequestUrls.ADD_ADDRESS %>',
        data : $("form[name=addressForm]").serialize(),
        success : function(response) {
          window.parent.location.reload();
        }
    });
});
</script>
<%
String action = "Add Your Address";
%>
<div class="row">
  <div class="modal fade" id="modalAddAddress" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header text-center">
          <c:choose>
	          <c:when test="${empty addressDto.id}">
	            <h4 class="modal-title w-100 font-weight-bold"><spring:message code="Add Your Address" text="Add Your Address" /></h4>
	          </c:when>
	          <c:otherwise>
	            <h4 class="modal-title w-100 font-weight-bold"><spring:message code="Edit Your Address" text="Edit Your Address" /></h4>
	            <% action = "Edit Your Address"; %>
	          </c:otherwise>
	        </c:choose>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body mx-3">
          <form:form method="POST" modelAttribute="addressDto" name="addressForm">
          <form:hidden path="<%=FieldNames.ID %>"/>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Address Line1" text="Address Line1" var="label"/>
                  <form:input type="text" path="<%=FieldNames.ADDRESS_LINE1 %>" id="<%=FieldNames.ADDRESS_LINE1 %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Address Line2" text="Address Line2" var="label"/>
                  <form:input type="text" path="<%=FieldNames.ADDRESS_LINE2 %>" id="<%=FieldNames.ADDRESS_LINE2 %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="City" text="City" var="label"/>
                  <form:input type="text" path="<%=FieldNames.CITY %>" id="<%=FieldNames.CITY %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="State" text="State" var="label"/>
                  <form:input type="text" path="<%=FieldNames.STATE %>" id="<%=FieldNames.STATE %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Pincode" text="Pincode" var="label"/>
                  <form:input type="text" path="<%=FieldNames.PINCODE %>" id="<%=FieldNames.PINCODE %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Country" text="Country" var="label"/>
                  <form:input type="text" path="<%=FieldNames.COUNTRY %>" id="<%=FieldNames.COUNTRY %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                 <button type="submit" class="btn btn-info"><spring:message code="<%=action %>" text="<%=action %>" /></button>
                 <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close"><spring:message code="Close" text="Close" /></button>
               </div>
             </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>