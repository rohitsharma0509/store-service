<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function submitRoleForm(f, e, userId) {
    e.preventDefault();
    var isValid = true;
    var selectedIds = $('#ids').val();
    var idsDto = { "ids" : selectedIds };
    $.post({
       url: '<%=RequestUrls.USERS%>'+'/'+userId,
       data: JSON.stringify(idsDto),
       contentType: "application/json",
       headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
       success: function(response) {
         if(response.code == 200) {
           $('#roleModal').modal('hide');
           $('.modal-backdrop').css("position", "unset");
           openTabAndShowSuccessMsg('accountTab', "role-update-success", "d-none", "success-msg", response.description);
         } else if(response.code == 400) {
           $("#idsError").removeClass("d-none");
           $("#idsError").text(response.description);
           isValid = false;
         } else {
           $('#roleModal').modal('hide');
           $('.modal-backdrop').css("position", "unset");
           openTabAndShowSuccessMsg('accountTab', "role-update-failure", "d-none", "failure-msg", response.description);
         }
       }
    });
    return isValid;
}
</script>
<div class="row">
  <div class="modal fade" id="roleModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header text-center">
          <h4 class="modal-title w-100 font-weight-bold"><spring:message code="Modify Roles" text="Modify Roles" /></h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body mx-3">
          <form:form method="POST" name="roleForm" modelAttribute="<%=FieldNames.IDS_DTO %>" onsubmit="return submitRoleForm(this, event, '${userId}');" >
            <div class="row form-group">
              <div class="col-sm-12">
                <label for="<%=FieldNames.NAME %>"><spring:message code="Select Roles" text="Select Roles" />&nbsp;<span class="urgent_fields">*</span></label>
                <form:select multiple="true" path="<%=FieldNames.IDS %>" id="<%=FieldNames.IDS %>" items="${roleDtos}" itemValue="id" itemLabel="name" class="form-control">
								</form:select>
								<span id="idsError" class="help-inline has-error d-none"></span>
              </div>
            </div>
            <div class="row form-group">
              <div class="col-sm-12">
                <button type="submit" class="btn btn-info"><spring:message code="Modify" text="Modify" /></button>
                <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close"><spring:message code="Close" text="Close" /></button>
              </div>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>