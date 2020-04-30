<%@page import="com.app.ecom.store.constants.FieldNames"%>
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
<div class="row">
  <div class="modal fade" id="modalAddAddress" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header text-center">
          <h4 class="modal-title w-100 font-weight-bold"><spring:message code="Modify Roles" text="Modify Roles" /></h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body mx-3">
          <form:form method="POST" modelAttribute="userDto" name="addressForm" action="addAddress">
            <form:hidden path="<%=FieldNames.ID %>"/>
            <div class="row form-group">
              <div class="col-sm-12">
                 <spring:message code="Country" text="Country" var="label"/>
                 <form:input type="text" path="<%=FieldNames.COUNTRY %>" id="<%=FieldNames.COUNTRY %>" placeholder="${label}" class="form-control" required='required' autocomplete="off"/>
              </div>
            </div>
            <div class="row form-group">
              <div class="col-sm-12">
                <button type="submit" class="btn btn-info"><spring:message code="Modify" text="Modify" /></button>
              </div>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>