<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$('form[name=addressForm]').submit(function(e) {
	e.preventDefault();
	$.post({
        url : "addAddress",
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
          <form:form method="POST" modelAttribute="addressDto" name="addressForm" action="addAddress">
          <form:hidden path="id"/>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Address Line1" text="Address Line1" var="label"/>
                  <form:input type="text" path="addressLine1" id="addressLine1" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Address Line2" text="Address Line2" var="label"/>
                  <form:input type="text" path="addressLine2" id="addressLine2" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="City" text="City" var="label"/>
                  <form:input type="text" path="city" id="city" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="State" text="State" var="label"/>
                  <form:input type="text" path="state" id="state" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Pincode" text="Pincode" var="label"/>
                  <form:input type="text" path="pincode" id="pincode" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Country" text="Country" var="label"/>
                  <form:input type="text" path="country" id="country" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-2">
                 <button type="submit" class="btn btn-success"><spring:message code="<%=action %>" text="<%=action %>" /></button>
               </div>
               <div class="col-sm-10"></div>
             </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>